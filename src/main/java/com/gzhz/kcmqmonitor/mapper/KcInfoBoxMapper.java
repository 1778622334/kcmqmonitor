package com.gzhz.kcmqmonitor.mapper;

import com.gzhz.kcmqmonitor.entity.KcInfoBox;
import com.gzhz.kcmqmonitor.model.SearchInfoBoxModel;
import com.gzhz.kcmqmonitor.model.SearchStaffNoListModel;

import java.util.List;


public interface KcInfoBoxMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(KcInfoBox record);

    int insertSelective(KcInfoBox record);


    KcInfoBox selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(KcInfoBox record);


    int updateByPrimaryKey(KcInfoBox record);

    /**
     * 按时间段 查询快传列表
     * @param searchInfoBoxModel
     * @return
     */
    List<KcInfoBox> selectByPublishTime(SearchInfoBoxModel searchInfoBoxModel);

    /**
     * 查询出某条快传的接收人列表
     * @param model
     * @return
     */
    List<KcInfoBox> selectStaffNoBysessionguid(SearchStaffNoListModel model);

    /**
     * 通过sessionid查出接收人列表   （不包含发起人自己）
     * @param sessionid
     * @return
     */
    List<KcInfoBox> selectBySessionid(String sessionid);

    List<KcInfoBox> selectReceiverUserByPrimaryKey(Integer id);
}