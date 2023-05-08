package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SearchTeamCommentsForm {

    @NotNull(message = "start不能为空")
    private int start;

    @NotNull(message = "length不能为空")
    private int length;

    @NotNull(message = "id不能为空")
    private int teamId;
}
