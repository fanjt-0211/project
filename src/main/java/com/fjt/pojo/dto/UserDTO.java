package com.fjt.pojo.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String realName;
    private String phone;
    private String email;
    private Integer role;
}
