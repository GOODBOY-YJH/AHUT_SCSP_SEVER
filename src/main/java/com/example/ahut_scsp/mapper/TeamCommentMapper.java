package com.example.ahut_scsp.mapper;

import com.example.ahut_scsp.domain.TeamComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author Good Boy
* @description 针对表【team_comment(团队评论表)】的数据库操作Mapper
* @createDate 2023-05-01 22:38:34
* @Entity com.example.ahut_scsp.domain.TeamComment
*/
@Mapper
public interface TeamCommentMapper extends BaseMapper<TeamComment> {

    public HashMap selectComment(Integer commentId, Integer userId);

    public ArrayList<HashMap> selectCommentByTeamId(int teamId);

    public ArrayList<HashMap> selectCommentPage(int teamId, int start, int length);

    public int searchTeamCommentsCount(int teamId);
}




