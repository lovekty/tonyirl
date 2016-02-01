package me.tonyirl.generator.main;

import me.tonyirl.common.orm.mybatis.annotation.collector.MyBatisColumnDef;

import java.util.List;

public class MyBatisToMySqlColumn {

    public static void buildTypeAndModifier(MyBatisColumnDef column) {
        String type = column.getJdbcType();
        Integer length = column.getLength();
        Boolean notNull = column.getNotNull();

        if (null == type || type.trim().length() == 0)
            type = "varchar";
        if (length <= 0)
            length = 1;
        if (null == notNull)
            notNull = false;

        String columnType;
        String columnModifier = "";
        String javaTypeDeclare = "";

        if ("timestamp".equalsIgnoreCase(type)) {
            columnType = "timestamp";
        } else if ("integer".equalsIgnoreCase(type)) {
            if (length >= 8) {
                columnType = "int(" + length + ")";
            } else if (length > 0) {
                columnType = "tinyint(" + length + ")";
            } else {
                columnType = "tinyint(1)";
            }
        } else {

            columnType = type + "(" + length + ")";
        }

        if (notNull)
            columnModifier = "not null";

        Class<?> columnClazz = column.getFieldClass();
        if (columnClazz.isEnum()) {
            String className = columnClazz.getName();
            javaTypeDeclare = "javaType=\"" + className + "\"";
        }

        column.setColumnType(columnType);
        column.setColumnModifier(columnModifier);
        column.setJavaTypeDeclare(javaTypeDeclare);
    }

    public static void buildTypeAndModifier(List<MyBatisColumnDef> columns) {
        for (MyBatisColumnDef column : columns)
            buildTypeAndModifier(column);
    }

}
