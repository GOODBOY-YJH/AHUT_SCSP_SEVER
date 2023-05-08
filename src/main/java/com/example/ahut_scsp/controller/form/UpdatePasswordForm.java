package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePasswordForm {
    @NotBlank(message = "password不能为空")
    private String password;
}
