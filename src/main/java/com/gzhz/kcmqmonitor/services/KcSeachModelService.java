package com.gzhz.kcmqmonitor.services;

import com.gzhz.kcmqmonitor.entity.KcSearchModel;

public interface KcSeachModelService {
    int deleteByPrimaryKey(Integer id);


    int insert(KcSearchModel record);


    int insertSelective(KcSearchModel record);


    KcSearchModel selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(KcSearchModel record);


    int updateByPrimaryKey(KcSearchModel record);
}
