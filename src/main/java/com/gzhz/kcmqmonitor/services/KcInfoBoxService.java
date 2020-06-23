package com.gzhz.kcmqmonitor.services;

import com.gzhz.kcmqmonitor.entity.*;
import com.gzhz.kcmqmonitor.model.SearchInfoBoxModel;

import java.util.List;

public interface KcInfoBoxService {
    int deleteByPrimaryKey(Integer id);


    int insert(KcInfoBox record);

    int insertSelective(KcInfoBox record);

    /**
     * 主键id查找 kcInfoBox  后处理成需要的数据模型
     * @param id
     * @return
     */
    KcSearchNewModel selectByPrimaryKey(Integer id) throws Exception ;


    int updateByPrimaryKeySelective(KcInfoBox record);


    int updateByPrimaryKey(KcInfoBox record);

    /**
     * 按时间段查询快传列表
     * @param searchInfoBoxModel

     * @return
     */
    List<KcSearchNewModel>  selectByPublishTime(SearchInfoBoxModel searchInfoBoxModel);

    /**
     * 由主键id 查询接收人列表  处理结果集为新增接收人数据模型
     * @param id
     * @return
     */
    updateReceiverUserModel selectReceiverUserByPrimaryKey(Integer id);

    /**
     * 由主键id 查询附件列表  处理结果集为更新附件得数据模型
     * @param id
     * @return
     */
    UpdateFilesModel selectFilesById(Integer id);

    /**
     * 由主键id 查询关注该条快传的用户列表  处理结果集为更新关注列表的数据模型
     * @param id
     * @return
     */
    updateFavoritesModel selectFavoritesById(Integer id);
}
