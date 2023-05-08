package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UpdateUserInfoForm {
    @NotBlank(message = "name不能为空")
    private String name;
    private int userId;
    private String avatar;
    private String gender;
    private String birthday;
    private String email;
    private String address;
    private String phoneNumber;
}
