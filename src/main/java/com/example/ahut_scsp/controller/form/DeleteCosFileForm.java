package com.example.ahut_scsp.controller.form;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DeleteCosFileForm {
    @NotEmpty(message = "pathes不能为空")
    private String[] pathes;
}
