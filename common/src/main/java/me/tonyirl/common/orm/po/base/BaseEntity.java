package me.tonyirl.common.orm.po.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tony on 2016/9/4.
 */
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -306913224119839160L;

    private long id;

    private Date insertTime;

    private Date updateTime;

    private boolean visible;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
