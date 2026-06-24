package com.fjt.pojo.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String realName;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    private String email;

    private Integer role;
}
