package com.fjt.pojo.dto;

import lombok.Data;

@Data
public class UserQueryDTO {
    private Long id;
    private String username;
    private String realName;
    private Integer role;
}
