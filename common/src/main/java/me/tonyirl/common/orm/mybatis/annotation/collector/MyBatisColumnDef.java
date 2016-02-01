package me.tonyirl.common.orm.mybatis.annotation.collector;

public class MyBatisColumnDef {

	private String fieldName;
	private Class<?> fieldClass;
	private String columnName;
	private String jdbcType = "varchar";
	private Boolean notNull = true;
	private Integer length = 64;
	private Boolean uniq = false;

	private String columnType;
	private String columnModifier;
	private String javaTypeDeclare = "";

	public MyBatisColumnDef() {
		this.fieldName = "";
		this.fieldClass = null;
		this.columnName = "";
	}

	public MyBatisColumnDef(String fieldName, Class<?> fieldClass, String columnName) {
		this.fieldName = fieldName;
		this.fieldClass = fieldClass;
		this.columnName = columnName;
	}

	public MyBatisColumnDef(String fieldName, Class<?> fieldClass, String columnName, String jdbcType, Boolean notNull, Integer length, Boolean uniq) {
		this.fieldName = fieldName;
		this.fieldClass = fieldClass;
		this.columnName = columnName;
		this.jdbcType = jdbcType;
		this.notNull = notNull;
		this.length = length;
		this.uniq = uniq;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Class<?> getFieldClass() {
		return fieldClass;
	}

	public void setFieldClass(Class<?> fieldClass) {
		this.fieldClass = fieldClass;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public Boolean getNotNull() {
		return notNull;
	}

	public void setNotNull(Boolean notNull) {
		this.notNull = notNull;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Boolean getUniq() {
		return uniq;
	}

	public void setUniq(Boolean uniq) {
		this.uniq = uniq;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnModifier() {
		return columnModifier;
	}

	public void setColumnModifier(String columnModifier) {
		this.columnModifier = columnModifier;
	}

	public String getJavaTypeDeclare() {
		return javaTypeDeclare;
	}

	public void setJavaTypeDeclare(String javaTypeDeclare) {
		this.javaTypeDeclare = javaTypeDeclare;
	}

}
