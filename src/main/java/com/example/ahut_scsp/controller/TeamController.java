package com.example.ahut_scsp.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.ahut_scsp.controller.form.*;
import com.example.ahut_scsp.domain.Team;
import com.example.ahut_scsp.domain.TeamComment;
import com.example.ahut_scsp.service.TeamCommentService;
import com.example.ahut_scsp.service.TeamService;
import com.example.ahut_scsp.util.PageUtils;
import com.example.ahut_scsp.util.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/team")
@Tag(name = "TeamController", description = "团队模块web接口")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamCommentService teamCommentService;

    @PostMapping("/searchTeamLisByPage")
    @Operation(description = "获取分页团队列表")
    public R searchTeamLisByPage(@Valid @RequestBody SearchTeamByPageForm form){
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        if(form.getIsMe() != null && form.getIsMe()){
            param.put("userId", StpUtil.getLoginIdAsInt());
        }
        param.put("start", start);
        PageUtils pageUtils = teamService.searchTeamByPage(param);
        return R.ok().put("page", pageUtils);
    }

    @PostMapping("/checkTeam")
    @Operation(description = "审核通过")
    public R checkTeam(@Valid @RequestBody CheckTeamForm form){
        boolean rows = teamService.update(new UpdateWrapper<Team>().set("team_status", form.getStatus()).eq("id", form.getTeamId()));
        return R.ok().put("rows", rows);
    }

    @GetMapping("/searchTeamCollege")
    @Operation(description = "获取学院列表")
    public R searchTeamCollege(@RequestParam(required = false, defaultValue = "false", name = "isMe") boolean isMe){
        ArrayList collegeList =  teamService.searchTeamCollege(isMe);
        return R.ok().put("collegeList", collegeList);
    }

    @GetMapping("/searchTeamType")
    @Operation(description = "获取学院列表")
    public R searchTeamType(@RequestParam(required = false, defaultValue = "false", name = "isMe") boolean isMe){
        ArrayList teamTypeList =  teamService.searchTeamType(isMe);
        return R.ok().put("teamTypeList", teamTypeList);
    }

    @GetMapping("/searchAllCollege")
    @Operation(description = "获取所有学院列表")
    public R searchAllCollege(){
        ArrayList allCollegeList =  teamService.searchAllCollege();
        return R.ok().put("allCollegeList", allCollegeList);
    }

    @GetMapping("/searchAllType")
    @Operation(description = "获取所有团队类型列表")
    public R searchAllType(){
        ArrayList allTypeList =  teamService.searchAllType();
        return R.ok().put("allTypeList", allTypeList);
    }


    @PostMapping("/addTeam")
    @Operation(description = "添加团队信息")
    public R addTeam(@Valid @RequestBody AddTeamForm form){
        int rows = teamService.addTeam(form);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/updateTeam")
    @Operation(description = "更新团队信息")
    public R updateTeam(@Valid @RequestBody UpdateTeamForm form){
        int rows = teamService.updateTeam(form);
        return R.ok().put("rows", rows);
    }


    @GetMapping("/teamInfoByTeamId")
    @Operation(description = "获取团队信息")
    public R searchTeamInfoById(@RequestParam("teamId") Integer teamId){
        HashMap teamInfo = teamService.searchTeamInfoById(teamId);
        return R.ok().put("teamInfo", teamInfo);
    }

    @GetMapping("/teamUpdateInfoByTeamId")
    @Operation(description = "团队更新的时候获取团队原始信息")
    public R teamUpdateInfoByTeamId(@RequestParam("teamId") Integer teamId){
        HashMap teamInfo = teamService.teamUpdateInfoByTeamId(teamId);
        return R.ok().put("teamInfo", teamInfo);
    }

    @GetMapping("/getTeamStatusById")
    @Operation(description = "获取团队状态")
    public R getTeamStatusById(@RequestParam("teamId") Integer teamId){
        Team team = teamService.getById(teamId);
        return R.ok().put("status", team.getTeamStatus()).put("teamType", team.getTeamType());
    }

    @PostMapping("/teamCommentsById")
    @Operation(description = "获取团队评论信息")
    public R searchTeamCommentsPage(@Valid @RequestBody SearchTeamCommentsForm form){
        ArrayList<HashMap> comments = teamCommentService.searchTeamCommentsPage(form);
        int commentTotalCount = teamCommentService.searchTeamCommentsCount(form.getTeamId());
        return R.ok().put("comments", comments).put("length", form.getLength() + 3).put("commentTotalCount", commentTotalCount);
    }


    @PostMapping("/addTeamComment")
    @Operation(description = "添加团队评论")
    public R addTeamComment(@Valid @RequestBody AddTeamCommentForm form){
        TeamComment comment = JSONUtil.parse(form).toBean(TeamComment.class);
        comment.setUserId(StpUtil.getLoginIdAsInt());
        comment.setIsDelete("no");
        HashMap teamComment = teamCommentService.addTeamComment(comment);
        return R.ok().put("teamComment", teamComment);
    }

    @GetMapping("/isTeamDelete")
    @Operation(description = "查询团队是否删除")
    public R isTeamDelete(@RequestParam("teamId") Integer teamId){
        String isDelete = teamService.searchPostDelete(teamId);
        return R.ok().put("isDelete", isDelete);
    }


    @DeleteMapping("deleteTeamById")
    @Operation(description = "删除团队")
    public R deleteTeamById(@RequestParam("teamId") Integer teamId){
        int rows = teamService.deleteTeamById(teamId);
        return R.ok().put("rows", rows);
    }

}
