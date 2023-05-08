package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class AddTeamForm {
    @NotBlank(message = "name不能为空")
    private String teamName;
    @NotBlank(message = "introduction不能为空")
    private String teamIntroduction;
    @NotEmpty(message = "image不能为空")
    private ArrayList teamImage;
    @NotBlank(message = "college不能为空")
    private String teamCollege;
    @NotBlank(message = "type不能为空")
    private String teamType;
    private String teamCreateTime;
    private ArrayList teamTeachers;
    private ArrayList teamHonors;
}
