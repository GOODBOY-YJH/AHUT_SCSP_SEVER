package com.example.ahut_scsp.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ahut_scsp.controller.form.AddTeamForm;
import com.example.ahut_scsp.controller.form.UpdateTeamForm;
import com.example.ahut_scsp.domain.Team;
import com.example.ahut_scsp.domain.TeamAchievement;
import com.example.ahut_scsp.domain.TeamTeacher;
import com.example.ahut_scsp.mapper.*;
import com.example.ahut_scsp.service.TeamService;
import com.example.ahut_scsp.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author Good Boy
* @description 针对表【team(团队表)】的数据库操作Service实现
* @createDate 2023-05-01 11:02:30
*/
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
    implements TeamService{

        @Autowired
        private TeamMapper teamMapper;
        @Autowired
        private TeamAchievementMapper teamAchievementMapper;
        @Autowired
        private TeamTeacherMapper teamTeacherMapper;
        @Autowired
        private TeamCollegesMapper teamCollegesMapper;
        @Autowired
        private TeamTypesMapper teamTypesMapper;

        @Override
        public PageUtils searchTeamByPage(HashMap param) {
            ArrayList<HashMap> list = teamMapper.searchTeamByPage(param);
            long count = teamMapper.searchTeamCount(param);
            int start = (Integer)param.get("start");
            int length = (Integer)param.get("length");
            PageUtils pageUtils = new PageUtils(list, count, start, length);
            return pageUtils;
        }

        @Override
        public ArrayList searchAllCollege() {
            return teamCollegesMapper.searchAllCollege();
        }

        @Override
        public ArrayList searchAllType() {
            return teamTypesMapper.searchAllType();
        }


        @Override
        public ArrayList searchTeamCollege(boolean isMe) {
            return teamMapper.searchTeamCollege(isMe);
        }

        @Override
        public ArrayList searchTeamType(boolean isMe) {
            return teamMapper.searchTeamType(isMe);
        }

        @Override
        public HashMap searchTeamInfoById(int id) {
            HashMap teamInfo = teamMapper.selectTeamById(id);
            String[] images = teamInfo.get("images").toString().split(",");
            ArrayList<HashMap<String, String>> cover = new ArrayList<>();
            // 需要和前端的数据结果对应
            for (String image: images) {
                cover.add(new HashMap() {{
                    put("id", UUID.randomUUID());
                    put("src", image);
                }});
            }
            // 移除不需要的数据
            teamInfo.remove("images");
            // 把图片字符串分割后存储
            teamInfo.put("cover", cover);

            // 查询团队指导老师
            ArrayList<HashMap> teachers = teamTeacherMapper.selectTeacherByTeamId(id);
            teamInfo.put("teachers", teachers);

            // 查询团队所获奖项
            ArrayList<HashMap> achievements = teamAchievementMapper.selectAchievementByTeamId(id);
            teamInfo.put("achievements", achievements);

            return teamInfo;
        }

    @Override
    public HashMap teamUpdateInfoByTeamId(int id) {
        HashMap teamInfo = teamMapper.selectUpdateTeamById(id);
        String[] images = teamInfo.get("images").toString().split(",");
        ArrayList<HashMap<String, String>> cover = new ArrayList<>();
        // 需要和前端的数据结果对应
        for (String image: images) {
            cover.add(new HashMap() {{
                put("id", UUID.randomUUID());
                put("url", image);
            }});
        }
        // 移除不需要的数据
        teamInfo.remove("images");
        // 把图片字符串分割后存储
        teamInfo.put("cover", cover);

        // 查询团队指导老师
        ArrayList<HashMap> teachers = teamTeacherMapper.selectTeacherByTeamId(id);
        for (HashMap teacher : teachers) {
            teacher.put("url", teacher.get("avatar"));
            teacher.remove("avatar");
        }
        teamInfo.put("teachers", teachers);

        // 查询团队所获奖项
        ArrayList<HashMap> achievements = teamAchievementMapper.selectAchievementByTeamId(id);
        for (HashMap achievement : achievements) {
            achievement.put("url", achievement.get("avatar"));
            achievement.remove("avatar");
        }
        teamInfo.put("achievements", achievements);

        return teamInfo;
    }


    @Override
    public int addTeam(AddTeamForm form) {

        // 插入团队数据
        Team team = new Team();
        team.setTeamName(form.getTeamName());
        team.setTeamIntroduction(form.getTeamIntroduction());
        team.setTeamCollege(form.getTeamCollege());
        team.setTeamType(form.getTeamType());
        team.setTeamCreateTime(DateUtil.parse(form.getTeamCreateTime()));
        team.setTeamStatus("waiting");
        team.setUserId(StpUtil.getLoginIdAsInt());
        ArrayList<HashMap<String, String>> teamImages = form.getTeamImage();
        StringBuilder teamImage = new StringBuilder();
        for (HashMap<String, String> image : teamImages) {
            teamImage.append(image.get("url") + ",");
        }
        team.setTeamImage(teamImage.toString());
        int rows = teamMapper.insert(team);



        // 插入团队指导老师
        ArrayList<HashMap<String, String>> teamTeachers = form.getTeamTeachers();
        if (teamTeachers.size() > 0){
            for (HashMap<String, String> teacher : teamTeachers) {
                TeamTeacher teamTeacher = new TeamTeacher();
                teamTeacher.setTeamId(team.getId());
                teamTeacher.setName(teacher.get("name"));
                teamTeacher.setAvatar(teacher.get("url"));
                teamTeacherMapper.insert(teamTeacher);
            }
        }

        // 插入团队所获荣誉
        ArrayList<HashMap<String, String>> teamHonors = form.getTeamHonors();
        if (teamHonors.size() > 0){
            for (HashMap<String, String> honor : teamHonors) {
                TeamAchievement teamAchievement = new TeamAchievement();
                teamAchievement.setTeamId(team.getId());
                teamAchievement.setAchievementDescription(honor.get("description"));
                teamAchievement.setAchievementImage(honor.get("url"));
                teamAchievementMapper.insert(teamAchievement);
            }
        }

        return rows;
    }

    @Override
    public int updateTeam(UpdateTeamForm form) {
        // 更新团队数据
        Team team = new Team();
        team.setId(form.getTeamId());
        team.setTeamName(form.getTeamName());
        team.setTeamIntroduction(form.getTeamIntroduction());
        team.setTeamCollege(form.getTeamCollege());
        team.setTeamType(form.getTeamType());
        team.setTeamStatus("waiting");
        team.setTeamCreateTime(DateUtil.parse(form.getTeamCreateTime()));
        ArrayList<HashMap<String, String>> teamImages = form.getTeamImage();
        StringBuilder teamImage = new StringBuilder();
        for (HashMap<String, String> image : teamImages) {
            teamImage.append(image.get("url") + ",");
        }
        team.setTeamImage(teamImage.toString());
        int rows = teamMapper.updateById(team);

        // 删除原来的团队荣誉数据
        teamTeacherMapper.delete(new QueryWrapper<TeamTeacher>().eq("team_id", form.getTeamId()));
        // 插入团队指导老师
        ArrayList<HashMap<String, String>> teamTeachers = form.getTeamTeachers();
        if (teamTeachers.size() > 0){
            for (HashMap<String, String> teacher : teamTeachers) {
                TeamTeacher teamTeacher = new TeamTeacher();
                teamTeacher.setTeamId(team.getId());
                teamTeacher.setName(teacher.get("name"));
                teamTeacher.setAvatar(teacher.get("url"));
                // 携带id的话insert会覆盖原来的数据, 因为有新的数据和原来的数据混在一起,不能直接update
                teamTeacherMapper.insert(teamTeacher);
            }
        }

        //删除原来的老师数据
        teamAchievementMapper.delete(new QueryWrapper<TeamAchievement>().eq("team_id", form.getTeamId()));
        // 插入团队所获荣誉
        ArrayList<HashMap<String, String>> teamHonors = form.getTeamHonors();
        if (teamHonors.size() > 0){
            for (HashMap<String, String> honor : teamHonors) {
                TeamAchievement teamAchievement = new TeamAchievement();
                teamAchievement.setTeamId(team.getId());
                teamAchievement.setAchievementDescription(honor.get("description"));
                teamAchievement.setAchievementImage(honor.get("url"));
                // 携带id的话insert会覆盖原来的数据, 因为有新的数据和原来的数据混在一起,不能直接update
                teamAchievementMapper.insert(teamAchievement);
            }
        }

        return rows;
    }

    @Override
    public String searchPostDelete(int teamId) {
        return teamMapper.searchTeamDelete(teamId);
    }

    @Override
    public int deleteTeamById(int teamId) {
        return teamMapper.deleteTeamById(teamId);
    }
}




