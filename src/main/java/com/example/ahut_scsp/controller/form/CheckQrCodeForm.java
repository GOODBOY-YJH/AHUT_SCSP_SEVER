package com.example.ahut_scsp.controller.form;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CheckQrCodeForm {

    @NotBlank(message = "验证码不能为空")
    private String code;

}