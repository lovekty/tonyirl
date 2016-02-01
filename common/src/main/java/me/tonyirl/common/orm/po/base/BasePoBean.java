package me.tonyirl.common.orm.po.base;

import me.tonyirl.common.orm.mybatis.annotation.MyBatisColumn;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tony on 16/2/1.
 */
public class BasePoBean implements Serializable {
    private static final long serialVersionUID = -1122639055410003424L;

    @MyBatisColumn(columnName = "id")
    private Long id;

    @MyBatisColumn(columnName = "insert_time")
    private Date insertTime;

    @MyBatisColumn(columnName = "update_time")
    private Date updateTime;

    @MyBatisColumn(columnName = "visible")
    private Boolean visible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
