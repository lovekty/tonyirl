package me.tonyirl.common.orm.mybatis.annotation.collector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyBatisEntityDef {

	/**
	 * clazz.getSimpleName()
	 */
	private String entityName;
	/**
	 * clazz.getName()
	 */
	private String entityClassName;
	private Class<?> entityClass;

	private String tableName;
	private String[] primaryKey;

	private List<MyBatisColumnDef> columns = new LinkedList<MyBatisColumnDef>();
	private Map<String, MyBatisColumnDef> fieldMap = new HashMap<String, MyBatisColumnDef>();
	private Set<String> fieldNameSet = new HashSet<String>();

	public void buileFieldMap() {
		fieldMap.clear();
		fieldNameSet.clear();
		for (MyBatisColumnDef column : columns) {
			fieldMap.put(column.getFieldName(), column);
			fieldNameSet.add(column.getFieldName());
		}
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityClassName() {
		return entityClassName;
	}

	public void setEntityClassName(String entityClassName) {
		this.entityClassName = entityClassName;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String[] getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String[] primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<MyBatisColumnDef> getColumns() {
		return columns;
	}

	public void setColumns(List<MyBatisColumnDef> columns) {
		this.columns = columns;
	}

	public Map<String, MyBatisColumnDef> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(Map<String, MyBatisColumnDef> fieldMap) {
		this.fieldMap = fieldMap;
	}

	public Set<String> getFieldNameSet() {
		return fieldNameSet;
	}

	public void setFieldNameSet(Set<String> fieldNameSet) {
		this.fieldNameSet = fieldNameSet;
	}

}
