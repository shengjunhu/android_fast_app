package com.hsj.db.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2018/1/23/18:02
 * @Version:V1.0
 * @Class:UserInfo
 * @Description:
 */
@Entity
public class UserInfo {

    @Id long dbId;

    private String userId;
    private String userName;
    private Long userPhone;
    private Integer userAge;
    private Boolean userSex;

    public UserInfo() {
    }

    public UserInfo(long dbId, String userId, String userName, Long userPhone, Integer userAge, Boolean userSex) {
        this.dbId = dbId;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAge = userAge;
        this.userSex = userSex;
    }

    public long getDbId() {
        return dbId;
    }

    public void setDbId(long dbId) {
        this.dbId = dbId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Boolean getUserSex() {
        return userSex;
    }

    public void setUserSex(Boolean userSex) {
        this.userSex = userSex;
    }

}
