package com.example.ahut_scsp.controller.form;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
// @Schema(name = "RegisterForm", description = "注册表单")
public class RegisterForm {
    @NotBlank(message = "名称不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;

}