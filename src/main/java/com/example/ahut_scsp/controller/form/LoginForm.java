package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {
    @NotBlank(message = "名称不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;
}
