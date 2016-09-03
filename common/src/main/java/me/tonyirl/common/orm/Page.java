package me.tonyirl.common.orm;

import me.tonyirl.common.orm.po.base.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 16/2/1.
 */
public class Page<T extends BaseEntity> implements Serializable {
    public static final int DEFAULT_PAGE_NO = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int DEFAULT_MAX_PAGE_INDEX_NUMBER = 9;
    private static final long serialVersionUID = 4591633545735882690L;
    private int maxPageIndexNumber;

    private int pageNo;

    private int pageSize;

    private int totalPage;

    private int totalCount;

    private boolean hasNext;

    private boolean hasPrevious;

    private int previousPage;

    private int nextPage;

    private List<T> data;

    private int[] pageNumberList;

    public Page(int pageNo, int pageSize) {
        this.maxPageIndexNumber = DEFAULT_MAX_PAGE_INDEX_NUMBER;
        this.hasNext = false;
        this.hasPrevious = false;
        this.data = new ArrayList<T>();
        if (pageNo <= 0)
            pageNo = DEFAULT_PAGE_NO;
        if (pageSize <= 0)
            pageSize = DEFAULT_PAGE_SIZE;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public void init(int totalCount, int pageNo, int pageSize) {
        this.totalCount = totalCount;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        calculatePage();
    }

    private void calculatePage() {
        //计算总页数
        if ((totalCount % pageSize) == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        //判断是否还有上一页
        hasPrevious = (pageNo - 1) > 0;

        //判断是否还有下一页
        hasNext = pageNo < totalPage;
        //计算上一页
        if (hasPrevious) {
            previousPage = pageNo - 1;
        }
        //计算下一页
        if (hasNext) {
            nextPage = pageNo + 1;
        }
    }

    public int getMaxPageIndexNumber() {
        return maxPageIndexNumber;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int[] getPageNumberList() {
        if (totalPage > this.maxPageIndexNumber) {
            this.pageNumberList = new int[this.maxPageIndexNumber];
            int offset = (this.maxPageIndexNumber - 4) / 2;
            if (this.pageNo - offset <= (1 + 2)) {
                for (int index = 0; index < maxPageIndexNumber - 2; index++) {
                    pageNumberList[index] = (index + 1);
                }
            } else if (this.pageNo + offset >= (totalPage - 2)) {
                int start = totalPage;
                for (int index = maxPageIndexNumber - 1; index > 1; index--) {
                    pageNumberList[index] = (start--);
                }
            } else {
                int start = pageNo - offset;
                for (int index = 2; index < maxPageIndexNumber - 2; index++) {
                    pageNumberList[index] = (start++);
                }
            }
            pageNumberList[0] = 1;
            pageNumberList[maxPageIndexNumber - 1] = totalPage;
        } else {//总页数小于 设置的页码标签数
            this.pageNumberList = new int[this.totalPage];
            for (int index = 0; index <= totalPage - 1; index++) {
                pageNumberList[index] = (index + 1);
            }
        }
        return pageNumberList;
    }
}
