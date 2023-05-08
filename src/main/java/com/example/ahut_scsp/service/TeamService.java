package com.example.ahut_scsp.service;

import com.example.ahut_scsp.controller.form.AddTeamForm;
import com.example.ahut_scsp.controller.form.SearchTeamCommentsForm;
import com.example.ahut_scsp.controller.form.UpdateTeamForm;
import com.example.ahut_scsp.domain.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ahut_scsp.util.PageUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author Good Boy
* @description 针对表【team(团队表)】的数据库操作Service
* @createDate 2023-05-01 11:02:30
*/
public interface TeamService extends IService<Team> {
    public PageUtils searchTeamByPage(HashMap param);

    public ArrayList searchAllCollege();

    public ArrayList searchAllType();

    public ArrayList searchTeamCollege(boolean isMe);

    public ArrayList searchTeamType(boolean isMe);

    public HashMap searchTeamInfoById(int id);

    public HashMap teamUpdateInfoByTeamId(int id);

    public int addTeam(AddTeamForm from);


    public String searchPostDelete(int teamId);

    public int deleteTeamById(int teamId);

    public int updateTeam(UpdateTeamForm form);
}
