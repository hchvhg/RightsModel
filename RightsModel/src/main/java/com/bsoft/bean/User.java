package com.bsoft.bean;

import java.util.Date;

import lombok.Data;

@Data
public class User {
    private Long userId;

    private Long createUser;

    private String description;

    private Date createTime;

    private Date deleteTime;

    private Short isAdmin;

    private Date lastLoginTime;

    private String loginName;

    private String loginPassword;

    private String username;

    private Short sfyx;

}