package com.bsoft.bean;

import lombok.Data;

@Data
public class Role {
    private Long roleId;

    private String roleName;

    private String description;

    private Short status;

}