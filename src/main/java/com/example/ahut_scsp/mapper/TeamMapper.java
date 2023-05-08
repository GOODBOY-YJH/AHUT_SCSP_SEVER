package com.example.ahut_scsp.mapper;

import com.example.ahut_scsp.domain.Team;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author Good Boy
* @description 针对表【team(团队表)】的数据库操作Mapper
* @createDate 2023-05-01 11:02:30
* @Entity com.example.ahut_scsp.domain.Team
*/
@Mapper
public interface TeamMapper extends BaseMapper<Team> {
    public ArrayList<HashMap> searchTeamByPage(HashMap param);

    public long searchTeamCount(HashMap param);

    public ArrayList searchTeamCollege(boolean isMe);

    public ArrayList searchTeamType(boolean isMe);

    public HashMap selectTeamById(int id);

    public String searchTeamDelete(int teamId);

    public int deleteTeamById(int teamId);

    public HashMap selectUpdateTeamById(int id);
}




