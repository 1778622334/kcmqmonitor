package com.gzhz.kcmqmonitor.mapper;

import com.gzhz.kcmqmonitor.entity.DynMemberStaff;
import com.gzhz.kcmqmonitor.entity.DynStaffNo;

import java.util.List;

public interface DynMemberStaffMapper {

    int insert(DynMemberStaff record);

    int insertSelective(DynMemberStaff record);

    /**
     * 通过staffNo 查询用户名
     */
    List<DynStaffNo> setlectStaffNameByStaffNo (List<Long> list);
}