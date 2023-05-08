package com.example.ahut_scsp.mapper;

import com.example.ahut_scsp.domain.TeamAchievement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author Good Boy
* @description 针对表【team_achievement(团队成果表)】的数据库操作Mapper
* @createDate 2023-05-01 11:08:17
* @Entity com.example.ahut_scsp.domain.TeamAchievement
*/
@Mapper
public interface TeamAchievementMapper extends BaseMapper<TeamAchievement> {

    public ArrayList<HashMap> selectAchievementByTeamId(int id);
}




