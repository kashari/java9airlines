package com.sda.java9.finalproject.dto;

import com.sda.java9.finalproject.security.entity.RoleName;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppRoleDTO {
    private Long id;
    private RoleName name;
}
