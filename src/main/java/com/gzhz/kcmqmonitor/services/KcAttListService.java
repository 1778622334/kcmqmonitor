package com.gzhz.kcmqmonitor.services;

import com.gzhz.kcmqmonitor.entity.KcAttList;
import org.springframework.stereotype.Service;

import java.util.List;


public interface KcAttListService {
    int deleteByPrimaryKey(Integer id);


    int insert(KcAttList record);


    int insertSelective(KcAttList record);


    KcAttList selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(KcAttList record);


    int updateByPrimaryKey(KcAttList record);

    /**
     * 通过sessionId  查询附件列表
     */
    List<KcAttList> setlectBysessionIdAndGuid(String sessionId );
}
