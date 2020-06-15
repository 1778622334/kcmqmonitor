package com.gzhz.kcmqmonitor.services;

import com.gzhz.kcmqmonitor.entity.DynStaffNo;

import java.util.List;


public interface DynStaffNoService {

    /**
     * 通过staffNo 查询用户名
     */
    List<DynStaffNo> setlectStaffNameByStaffNo (List<Long> staffNo);
}
