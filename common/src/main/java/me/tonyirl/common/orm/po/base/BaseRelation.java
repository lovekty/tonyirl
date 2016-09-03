package me.tonyirl.common.orm.po.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tony on 2016/9/4.
 */
public abstract class BaseRelation implements Serializable {
    private static final long serialVersionUID = -6616432827586157050L;

    private Date effectiveDate;

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
