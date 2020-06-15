package com.gzhz.kcmqmonitor.services;

import com.gzhz.kcmqmonitor.entity.KcInfoBox;
import com.gzhz.kcmqmonitor.entity.KcSearchModel;
import com.gzhz.kcmqmonitor.entity.KcSearchNewModel;
import com.gzhz.kcmqmonitor.model.SearchInfoBoxModel;

import java.util.List;

public interface KcInfoBoxService {
    int deleteByPrimaryKey(Integer id);


    int insert(KcInfoBox record);

    int insertSelective(KcInfoBox record);


    KcInfoBox selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(KcInfoBox record);


    int updateByPrimaryKey(KcInfoBox record);

    /**
     * 按时间段查询快传列表
     * @param searchInfoBoxModel

     * @return
     */
    List<KcSearchNewModel>  selectByPublishTime(SearchInfoBoxModel searchInfoBoxModel);
}
