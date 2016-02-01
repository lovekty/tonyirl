package me.tonyirl.dao.mybatis.base;

import me.tonyirl.common.orm.Page;
import me.tonyirl.common.orm.po.base.BasePoBean;
import me.tonyirl.common.utils.ReflectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tony on 16/2/1.
 */
public class BaseMyBatisDao<T extends BasePoBean, PK extends Serializable> extends SqlSessionDaoSupport {
    public static final String SQL_FIND_BY_ID = "findById";
    public static final String SQL_FIND_ALL = "findAll";
    public static final String SQL_INSERT = "insert";
    public static final String SQL_UPDATE = "update";
    public static final String SQL_DELETE = "delete";

    protected Class<T> entityClass;
    protected Class<PK> pkClass;
    protected String sqlmapNamespace;

    public BaseMyBatisDao() {
        init();
    }

    private void init() {
        entityClass = ReflectionUtils.getSuperClassGenricType(getClass(), 0);
        pkClass = ReflectionUtils.getSuperClassGenricType(getClass(), 1);
        setSqlmapNamespace(entityClass.getSimpleName());
    }

    public String getSqlmapNamespace() {
        return sqlmapNamespace;
    }

    public void setSqlmapNamespace(String sqlmapNamespace) {
        this.sqlmapNamespace = sqlmapNamespace;
    }

    public T insert(T entity) {
        int affectedRowNum = this.getSqlSession().insert(sqlmapNamespace + "." + SQL_INSERT, entity);
        if (affectedRowNum > 0) {
            Date now = new Date();
            entity.setInsertTime(now);
            entity.setUpdateTime(now);
            entity.setVisible(Boolean.TRUE);
        }
        return entity;
    }

    public T update(T entity) {
        Date lastUpdateTime = entity.getUpdateTime();
        entity.setUpdateTime(new Date());
        int affectedRowNum = this.getSqlSession().update(sqlmapNamespace + "." + SQL_UPDATE, entity);
        if (affectedRowNum == 0) {
            entity.setUpdateTime(lastUpdateTime);
        }
        return entity;
    }

    public int delete(PK id) {
        return this.getSqlSession().delete(sqlmapNamespace + "." + SQL_DELETE, id);
    }

    public int softDelete(PK id) {
        T entity = findById(id);
        if (null != entity) {
            entity.setVisible(Boolean.FALSE);
            return softDelete(entity);
        }
        return 0;
    }

    public int softDelete(T entity) {
        entity.setVisible(Boolean.FALSE);
        T result = update(entity);
        return result.getVisible() ? 0 : 1;
    }

    public T findById(PK id) {
        return this.getSqlSession().selectOne(sqlmapNamespace + "." + SQL_FIND_BY_ID, id);
    }

    public List<T> findAll() {
        return this.getSqlSession().selectList(sqlmapNamespace + "." + SQL_FIND_ALL);
    }

    public List<T> findList(String statement, Map<String, Object> param, int skipResult, int length) {
        RowBounds rb = new RowBounds(skipResult, length);
        return this.getSqlSession().selectList(sqlmapNamespace + "." + statement, param, rb);
    }

    public List<T> findList(String statement, Map<String, Object> param) {
        return this.getSqlSession().selectList(sqlmapNamespace + "." + statement, param);
    }

    public List<T> findList(String statement) {
        return this.getSqlSession().selectList(sqlmapNamespace + "." + statement);
    }

    public T findOne(String statement, Map<String, Object> param) {
        return this.getSqlSession().selectOne(statement, param);
    }

    public T findOne(String statement) {
        return this.getSqlSession().selectOne(statement);
    }

    public Integer count(String statement, Map<String, Object> param) {
        return this.getSqlSession().selectOne(statement, param);
    }

    public Integer count(String statement) {
        return this.getSqlSession().selectOne(statement);
    }

}