package me.tonyirl.webtest.model;

import java.io.Serializable;

/**
 * Created by tony on 2016/10/19.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 2795992553286036783L;

    private Long id;

    private String name;

    private String identityCardNumber;

    private Integer gender;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
