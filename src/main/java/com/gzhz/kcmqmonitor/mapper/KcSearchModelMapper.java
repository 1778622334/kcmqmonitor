package com.gzhz.kcmqmonitor.mapper;

import com.gzhz.kcmqmonitor.entity.KcSearchModel;
import org.springframework.stereotype.Repository;


public interface KcSearchModelMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(KcSearchModel record);


    int insertSelective(KcSearchModel record);


    KcSearchModel selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(KcSearchModel record);


    int updateByPrimaryKey(KcSearchModel record);
}