/**
 *
 */
package me.tonyirl.generator.main;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import me.tonyirl.common.orm.mybatis.annotation.collector.MyBatisColumnDef;
import me.tonyirl.common.orm.mybatis.annotation.collector.MyBatisEntityDef;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 */
public class CodeGenerator {

    private Configuration config;
    private String outPath;
    private String packagePath;

    private String entityBasePath = "me.tonyirl.entity.po";
    private String daoBasePath = "me.tonyirl.dao.mybatis";
    private String serivceBasePath = "me.tonyirl.service";
    private String mapperBasePath = "sqlmap";
    private String ddlBasePath = "ddl";

    public CodeGenerator() {
        this("/Users/tony/Documents/codes/java/codegen/output", "me.tonyirl.entity.po", "/Users/tony/Documents/codes/java/tonyirl/generator/src/main/resources/ftl");
    }

    public CodeGenerator(String templatePath) {
        this("/Users/tony/Documents/codes/java/codegen/output", "me.tonyirl.entity.po", templatePath);
    }

    public CodeGenerator(String outPath, String packagePath, String templatePath) {
        this.packagePath = packagePath;
        this.outPath = outPath;
        config = new Configuration();
        try {
            config.setDirectoryForTemplateLoading(new File(templatePath));
            config.setObjectWrapper(new DefaultObjectWrapper());
        } catch (IOException e) {
            config = null;
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CodeGenerator g = new CodeGenerator();
        g.setPackagePath("com.dawn.platform.entity");
        List<MyBatisEntityDef> entities = MyBatisAnnotationCollector.collect("com.dawn.platform.entity");
        for (MyBatisEntityDef entity : entities) {
            boolean resultFlag = g.generate(entity);
            if (!resultFlag) {
                System.err.println("中途退出");
                System.exit(1);
            }
        }

    }

    public String getEntityBasePath() {
        return entityBasePath;
    }

    public void setEntityBasePath(String entityBasePath) {
        this.entityBasePath = entityBasePath;
    }

    public String getDaoBasePath() {
        return daoBasePath;
    }

    public void setDaoBasePath(String daoBasePath) {
        this.daoBasePath = daoBasePath;
    }

    public String getSerivceBasePath() {
        return serivceBasePath;
    }

    public void setSerivceBasePath(String serivceBasePath) {
        this.serivceBasePath = serivceBasePath;
    }

    public String getMapperBasePath() {
        return mapperBasePath;
    }

    public void setMapperBasePath(String mapperBasePath) {
        this.mapperBasePath = mapperBasePath;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public boolean generate(MyBatisEntityDef entity) {
        boolean bRtn = false;
        boolean b1 = generateDao(entity);
        boolean b2 = generateService(entity);
        boolean b3 = generateMapping(entity);
        boolean b4 = generateDdl(entity);
        bRtn = b1 && b2 && b3 && b4;
        return bRtn;
    }

    public boolean generateDao(MyBatisEntityDef entity) {
        boolean bRtn = false;
        try {
            String daoOutPath = this.outPath
                    + File.separator
                    + "dao"
                    + File.separator
                    + this.daoBasePath.replace(".", File.separator)
                    + getSubPackage(entity.getEntityClassName(), entityBasePath).replace(".", File.separator);
            File f = new File(daoOutPath);
            if (!f.exists() || !f.isDirectory())
                f.mkdirs();

            Template temp = config.getTemplate("dao.ftl", "utf8");
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("daoPackage", daoBasePath);
            root.put("subPackage", getSubPackage(entity.getEntityClassName(), entityBasePath));
            root.put("entityClassName", entity.getEntityClassName());
            root.put("entityClassSimpleName", entity.getEntityName());
            root.put("pkClassSimpleName", "Long");

            Writer out = new OutputStreamWriter(new FileOutputStream(daoOutPath + File.separator + getDaoName(entity)), "utf8");
            temp.process(root, out);
            out.flush();

            bRtn = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bRtn;
    }

    public boolean generateService(MyBatisEntityDef entity) {
        boolean bRtn = false;
        try {
            String serviceOutPath = this.outPath
                    + File.separator
                    + "service"
                    + File.separator
                    + this.serivceBasePath.replace(".", File.separator)
                    + getSubPackage(entity.getEntityClassName(), entityBasePath).replace(".", File.separator);
            File f = new File(serviceOutPath);
            if (!f.exists() || !f.isDirectory())
                f.mkdirs();

            Template temp = config.getTemplate("service.ftl", "utf8");
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("servicePackage", serivceBasePath);
            root.put("subPackage", getSubPackage(entity.getEntityClassName(), entityBasePath));
            root.put("entityClassName", entity.getEntityClassName());
            root.put("entityBeanName", lowerFirstLetter(entity.getEntityName()));
            root.put("entityClassSimpleName", entity.getEntityName());
            root.put("pkClassSimpleName", "Long");

            Writer out = new OutputStreamWriter(new FileOutputStream(serviceOutPath + File.separator + getServiceName(entity)), "utf8");
            temp.process(root, out);
            out.flush();

            bRtn = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bRtn;
    }

    public boolean generateMapping(MyBatisEntityDef entity) {
        boolean bRtn = false;
        try {
            String mapperOutPath = this.outPath + File.separator + mapperBasePath;
            File f = new File(mapperOutPath);
            if (!f.exists() || !f.isDirectory()) {
                f.mkdirs();
            }

            Template temp = config.getTemplate("mapper.ftl", "utf8");
            Map<String, Object> root = new HashMap<String, Object>();
            List<MyBatisColumnDef> columns = entity.getColumns();
            MyBatisToMySqlColumn.buildTypeAndModifier(columns);
            root.put("childClassFields", columns);
            root.put("tableName", entity.getTableName());
            root.put("entityClassName", entity.getEntityClassName());
            root.put("entityBeanName", lowerFirstLetter(entity.getEntityName()));
            root.put("entityClassSimpleName", entity.getEntityName());
            root.put("pkClassSimpleName", "Long");
            Writer out = new OutputStreamWriter(new FileOutputStream(mapperOutPath + File.separator + getMapperName(entity)), "utf8");
            temp.process(root, out);
            out.flush();

            bRtn = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bRtn;
    }

    public boolean generateDdl(MyBatisEntityDef entity) {
        boolean bRtn = false;
        try {
            String ddlOutPath = this.outPath + File.separator + ddlBasePath;
            File f = new File(ddlOutPath);
            if (!f.exists() || !f.isDirectory())
                f.mkdirs();

            Template temp = config.getTemplate("ddl.ftl", "utf8");
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("tableName", entity.getTableName());
            List<MyBatisColumnDef> columns = entity.getColumns();
            MyBatisToMySqlColumn.buildTypeAndModifier(columns);
            root.put("childClassFields", columns);

            Writer out = new OutputStreamWriter(new FileOutputStream(ddlOutPath + File.separator + getDdlName(entity)), "utf8");
            temp.process(root, out);
            out.flush();

            bRtn = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bRtn;
    }

    @SuppressWarnings("unused")
    private Object upperFirstLetter(String str) {
        String sRtn = null;
        if (null != str && str.length() > 0) {
            sRtn = str.length() > 1 ? str.substring(0, 1).toUpperCase() + str.substring(1)
                    : str.substring(0, 1).toUpperCase();
        }
        return sRtn;
    }

    private Object lowerFirstLetter(String str) {
        String sRtn = null;
        if (null != str && str.length() > 0) {
            sRtn = str.length() > 1 ? str.substring(0, 1).toLowerCase() + str.substring(1)
                    : str.substring(0, 1).toLowerCase();
        }
        return sRtn;
    }

    private String getSubPackage(String fullName, String prefix) {
        String sRtn = "";
        if (null != fullName && fullName.startsWith(prefix)) {
            sRtn = fullName.substring(prefix.length(),
                    fullName.lastIndexOf("."));
        }
        return sRtn;
    }

    private String getMapperName(MyBatisEntityDef entity) {
        return getSubPackage(entity.getEntityClassName(), entityBasePath).replaceFirst(".", "").replace(".", "_") + "_" + entity.getEntityName().toLowerCase() + ".xml";
    }

    private String getDdlName(MyBatisEntityDef entity) {
        return getSubPackage(entity.getEntityClassName(), entityBasePath).replaceFirst(".", "").replace(".", "_") + "_" + entity.getEntityName().toLowerCase() + ".sql";
    }

    private String getServiceName(MyBatisEntityDef entity) {
        return entity.getEntityName() + "Service.java";
    }

    private String getDaoName(MyBatisEntityDef entity) {
        return entity.getEntityName() + "Dao.java";
    }

}
