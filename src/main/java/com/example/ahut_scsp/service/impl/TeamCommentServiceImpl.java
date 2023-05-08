package com.example.ahut_scsp.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ahut_scsp.controller.form.SearchTeamCommentsForm;
import com.example.ahut_scsp.domain.TeamComment;
import com.example.ahut_scsp.domain.User;
import com.example.ahut_scsp.mapper.UserMapper;
import com.example.ahut_scsp.service.TeamCommentService;
import com.example.ahut_scsp.mapper.TeamCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author Good Boy
* @description 针对表【team_comment(团队评论表)】的数据库操作Service实现
* @createDate 2023-05-01 22:38:34
*/
@Service
public class TeamCommentServiceImpl extends ServiceImpl<TeamCommentMapper, TeamComment>
    implements TeamCommentService{

    @Autowired
    private TeamCommentMapper teamCommentMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public HashMap addTeamComment(TeamComment comment) {
        teamCommentMapper.insert(comment);
        HashMap teamComment = teamCommentMapper.selectComment(comment.getId(), comment.getUserId());
        return teamComment;
    }

    @Override
    public ArrayList<HashMap> searchTeamCommentsPage(SearchTeamCommentsForm form) {
        ArrayList<HashMap> comments = teamCommentMapper.selectCommentPage(form.getTeamId(), form.getStart(), form.getLength());
        return comments;
    }

    @Override
    public int searchTeamCommentsCount(int teamId) {
        int commentTotalCount = teamCommentMapper.searchTeamCommentsCount(teamId);
        return commentTotalCount;
    }
}




