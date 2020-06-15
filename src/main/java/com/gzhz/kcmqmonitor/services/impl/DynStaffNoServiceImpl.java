package com.gzhz.kcmqmonitor.services.impl;

import com.gzhz.kcmqmonitor.entity.DynStaffNo;
import com.gzhz.kcmqmonitor.mapper.DynMemberStaffMapper;
import com.gzhz.kcmqmonitor.services.DynStaffNoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("dynStaffNoService")
public class DynStaffNoServiceImpl implements DynStaffNoService {

    @Resource
    DynMemberStaffMapper dynMemberStaffMapper;

    @Override
    public List<DynStaffNo> setlectStaffNameByStaffNo (List<Long> staffNo){

        return dynMemberStaffMapper.setlectStaffNameByStaffNo(staffNo);
    }
}
