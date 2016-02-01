/**
 *
 */
package me.tonyirl.generator.main;

import me.tonyirl.common.orm.mybatis.annotation.MyBatisColumn;
import me.tonyirl.common.orm.mybatis.annotation.MyBatisEntity;
import me.tonyirl.common.orm.mybatis.annotation.collector.MyBatisColumnDef;
import me.tonyirl.common.orm.mybatis.annotation.collector.MyBatisEntityDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;


/**
 * @author tony
 */
public class MyBatisAnnotationCollector {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisAnnotationCollector.class);

    public static List<MyBatisEntityDef> collect(String packagePath) {
        List<MyBatisEntityDef> lRtn = new LinkedList<MyBatisEntityDef>();

        String scanPattern = "classpath*:" + ClassUtils.convertClassNameToResourcePath(packagePath) + File.separator + "**" + File.separator + "*.class";
        try {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resourcePatternResolver.getResources(scanPattern);
            if (null != resources && resources.length > 0) {
                MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
                for (Resource resource : resources) {
                    try {
                        if (resource.isReadable()) {
                            MetadataReader reader = readerFactory.getMetadataReader(resource);
                            String className = reader.getClassMetadata().getClassName();
                            Class<?> clazz = resourcePatternResolver.getClassLoader().loadClass(className);

                            MyBatisEntityDef def = deal(clazz);
                            if (null != def) {
                                lRtn.add(def);
                            }
                        }
                    } catch (SecurityException se) {
                        logger.error("[MyBatisAnnotationCollector][collect]read resource security exception", se);
                    } catch (Exception ex) {
                        logger.error("[MyBatisAnnotationCollector][collect]exception", ex);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("[MyBatisAnnotationCollector][collect]exception", e);
        }

        return lRtn;
    }

    public static MyBatisEntityDef deal(Class<?> clazz) {
        MyBatisEntityDef oRtn = null;
        boolean isEntity = clazz.isAnnotationPresent(MyBatisEntity.class);
        if (isEntity) {
            oRtn = new MyBatisEntityDef();
            MyBatisEntity entityAnno = clazz.getAnnotation(MyBatisEntity.class);
            oRtn.setEntityClass(clazz);
            oRtn.setEntityName(clazz.getSimpleName());
            oRtn.setEntityClassName(clazz.getName());
            oRtn.setTableName(entityAnno.tableName());
            oRtn.setPrimaryKey(entityAnno.primaryKey());

            List<MyBatisColumnDef> columns = new LinkedList<MyBatisColumnDef>();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    boolean isColumn = field.isAnnotationPresent(MyBatisColumn.class);
                    if (isColumn) {
                        MyBatisColumnDef columnDef = new MyBatisColumnDef();
                        MyBatisColumn columnAnno = field.getAnnotation(MyBatisColumn.class);
                        columnDef.setColumnName(columnAnno.columnName());
                        columnDef.setFieldClass(field.getType());
                        columnDef.setFieldName(field.getName());
                        columnDef.setJdbcType(columnAnno.jdbcType().toUpperCase());
                        columnDef.setLength(columnAnno.length());
                        columnDef.setNotNull(columnAnno.notNull());
                        columnDef.setUniq(columnAnno.uniq());
                        columns.add(columnDef);
                    }
                }
            }
            oRtn.setColumns(columns);
            if (columns.size() > 0)
                oRtn.buileFieldMap();
        }
        return oRtn;
    }

}
