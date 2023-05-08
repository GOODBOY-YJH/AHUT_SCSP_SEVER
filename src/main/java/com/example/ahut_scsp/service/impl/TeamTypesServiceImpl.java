package com.example.ahut_scsp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ahut_scsp.domain.TeamTypes;
import com.example.ahut_scsp.service.TeamTypesService;
import com.example.ahut_scsp.mapper.TeamTypesMapper;
import org.springframework.stereotype.Service;

/**
* @author Good Boy
* @description 针对表【team_types(团队类型)】的数据库操作Service实现
* @createDate 2023-05-01 11:50:49
*/
@Service
public class TeamTypesServiceImpl extends ServiceImpl<TeamTypesMapper, TeamTypes>
    implements TeamTypesService{

}




