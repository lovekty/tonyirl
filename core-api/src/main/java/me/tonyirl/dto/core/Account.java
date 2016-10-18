package me.tonyirl.dto.core;

import java.io.Serializable;

/**
 * Created by tony on 2016/10/18.
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 2172361545868404666L;

    private Long userId;

    private String username;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
