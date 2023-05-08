package com.example.ahut_scsp.service;

import com.example.ahut_scsp.controller.form.SearchTeamCommentsForm;
import com.example.ahut_scsp.domain.TeamComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author Good Boy
* @description 针对表【team_comment(团队评论表)】的数据库操作Service
* @createDate 2023-05-01 22:38:34
*/
public interface TeamCommentService extends IService<TeamComment> {

    HashMap addTeamComment(TeamComment comment);

    public ArrayList<HashMap> searchTeamCommentsPage(SearchTeamCommentsForm form);

    public int searchTeamCommentsCount(int teamId);
}
