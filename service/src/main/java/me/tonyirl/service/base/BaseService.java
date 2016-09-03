package me.tonyirl.service.base;

import me.tonyirl.common.orm.Page;
import me.tonyirl.common.orm.po.base.BaseEntity;
import me.tonyirl.dao.mybatis.base.BaseMyBatisDao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by tony on 16/2/1.
 */
public class BaseService<T extends BaseEntity, PK extends Serializable> {
    protected BaseMyBatisDao<T, PK> baseDao;

    public BaseMyBatisDao<T, PK> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseMyBatisDao<T, PK> baseDao) {
        this.baseDao = baseDao;
    }

    public T findById(PK id) {
        return this.baseDao.findById(id);
    }

    public T insert(T entity) {
        return this.baseDao.insert(entity);
    }

    public T update(T entity) {
        return this.baseDao.update(entity);
    }

    public int delete(PK id) {
        return this.baseDao.delete(id);
    }

    public List<T> findAll() {
        return this.baseDao.findAll();
    }

    public List<T> findList(String statement, Map<String, Object> param, int skipResult, int length) {
        return this.baseDao.findList(statement, param, skipResult, length);
    }

    public List<T> findList(String statement, Map<String, Object> param) {
        return this.baseDao.findList(statement, param);
    }

    public List<T> findList(String statement) {
        return this.baseDao.findList(statement);
    }

    public T findOne(String statement, Map<String, Object> param) {
        return this.baseDao.findOne(statement, param);
    }

    public T findOne(String statement) {
        return this.baseDao.findOne(statement);
    }

    public Integer count(String statement, Map<String, Object> param) {
        return this.baseDao.count(statement, param);
    }

    public Integer count(String statement) {
        return this.baseDao.count(statement);
    }

    public Page<T> findPage(String statement, Map<String, Object> param, int pageNo, int pageSize) {
        Page<T> oRtn = new Page<T>(pageNo, pageSize);
        List<T> data = findList(statement, param, pageNo, pageSize);
        int maxCount = count(statement, param);
        oRtn.init(maxCount, pageNo, pageSize);
        oRtn.setData(data);
        return oRtn;
    }

}
