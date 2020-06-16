package com.gzhz.kcmqmonitor.services.impl;

import com.gzhz.kcmqmonitor.entity.*;
import com.gzhz.kcmqmonitor.mapper.*;
import com.gzhz.kcmqmonitor.model.SearchInfoBoxModel;
import com.gzhz.kcmqmonitor.model.SearchStaffNoListModel;
import com.gzhz.kcmqmonitor.services.KcInfoBoxService;
import com.gzhz.kcmqmonitor.utils.DateTimeUtils;
import com.gzhz.kcmqmonitor.utils.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("kcInfoBoxService")
public class KcInfoBoxServiceImpl implements KcInfoBoxService {

    @Resource
    KcInfoBoxMapper kcInfoBoxMapper;
    @Resource
    KcAttListMapper kcAttListMapper;
    @Resource
    KcFavoritesMapper kcFavoritesMapper;
    @Resource
    DynMemberStaffMapper dynMemberStaffMapper;
    @Resource
    KcSearchModelMapper kcSearchModelMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(KcInfoBox record){
        return 0;
    }
    @Override
    public int insertSelective(KcInfoBox record){
        return 0;
    }

    @Override
    public KcInfoBox selectByPrimaryKey(Integer id){
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(KcInfoBox record){
        return 0;
    }

    @Override
    public int updateByPrimaryKey(KcInfoBox record){
        return 0;
    }

    /**
     * 按时间段查询快传列表
     * @param searchInfoBoxModel
     * @return
     */
    @Override
    public List<KcSearchNewModel> selectByPublishTime(SearchInfoBoxModel searchInfoBoxModel){
        try {
            List<KcInfoBox> kcInfoBoxes = kcInfoBoxMapper.selectByPublishTime(searchInfoBoxModel);

            List<KcSearchNewModel> searchModels = new ArrayList<>();
            for (KcInfoBox infoBox : kcInfoBoxes) {
                KcSearchNewModel searchModel = new KcSearchNewModel();

                //通过 sessionId 查询出该条快传的附件列表
                List<KcAttList> attLists = kcAttListMapper.setlectBysessionIdAndGuid(infoBox.getSessionid());
                //通过快传的 kid  查询出关注该条快传的用户
                List<KcFavorites> favorites = kcFavoritesMapper.selectByKid(infoBox.getId());
                //通过 sessionguid 查询出接收人列表
                SearchStaffNoListModel staffNoModel = new SearchStaffNoListModel(infoBox.getPublishuser(),0L,
                        0,0, infoBox.getSessionguid(),(byte)0);
                List<KcInfoBox> kcInfoBoxesStaffNo = kcInfoBoxMapper.selectStaffNoBysessionguid(staffNoModel);

                //处理附件列表
                if(null != attLists && attLists.size() > 0){
                    String[] attArr = new String[attLists.size()] ;
                    for (int i = 0; i < attLists.size(); i++) {
                        attArr[i] = (attLists.get(i).getTitle());
                    }
                    searchModel.setFiles( attArr);
                }else {
                    String[] attArr = new String[1] ;
                    attArr[0] = "";
                    searchModel.setFiles( attArr );
                }
                //处理关注人列表

                if(null != favorites && favorites.size() > 0){
                    String[] favUserArr = new String[favorites.size()];
                    for (int i = 0; i < favorites.size(); i++){
                        favUserArr[0] = (favorites.get(i).getUid().toString());
                    }
                    searchModel.setFavorites( favUserArr );
                }else {
                    String[] favUserArr = new String[1];
                    favUserArr[0] = ("");
                    searchModel.setFavorites( favUserArr );
                }
                //处理接收人列表
                if(null != kcInfoBoxesStaffNo && kcInfoBoxesStaffNo.size() > 0){
                    String[] ids = new String[kcInfoBoxesStaffNo.size()] ;
                    List<Long> staffNoList = new ArrayList<>();
                    for (int i = 0; i < kcInfoBoxesStaffNo.size(); i++){
                        staffNoList.add(kcInfoBoxesStaffNo.get(i).getStaffno());
                        ids[i] = kcInfoBoxesStaffNo.get(i).getStaffno().toString();
                    }
                    //设置接收人ids
                    searchModel.setReceiveruserids(ids);
                    //查询用户名
                    List<DynStaffNo> nameList = dynMemberStaffMapper.setlectStaffNameByStaffNo(staffNoList);
                    if(null != nameList && nameList.size() > 0 ){
                        String[] names = new String[nameList.size()];
                        for (int i = 0; i < nameList.size(); i++){
                            names[i] = (nameList.get(i).getStaffname());
                        }
                        //设置接收人的名字
                        searchModel.setReceiverusers( names );
                    }else {
                        String[] names = new String[1];
                        names[0] = ("");
                        searchModel.setReceiverusers( names);
                    }

                }else {
                    String[] ids = new String[1];
                    ids[0] = ("");
                    searchModel.setReceiverusers( ids);

                    String[] names = new String[1];
                    names[0] = ("");
                    searchModel.setReceiverusers( names);

                }
                //处理发起人的名字
                List<Long> publishStaffNo = new ArrayList<>();
                publishStaffNo.add(infoBox.getPublishuser());
                List<DynStaffNo> publishName = dynMemberStaffMapper.setlectStaffNameByStaffNo(publishStaffNo);
                if(null != publishName && publishName.size() > 0 ){
                    searchModel.setPublishuser(publishName.get(0).getStaffname());
                }else {

                    searchModel.setPublishuser("");
                }
                searchModel.setPublishuserid(infoBox.getPublishuser().toString());
                //处理其他的数据
                searchModel.setId(infoBox.getId().toString());
                searchModel.setTitle(infoBox.getTitle());
                searchModel.setInfo(infoBox.getContent());
                //模型中type 1表示正常发出， 4已删除的  8草稿箱的  在数据库中用state字段表示
                searchModel.setType(infoBox.getState().byteValue());

                String s = infoBox.getPublishtime().toString();
                String publishTime = DateTimeUtils.timeStamp2Date(s,DateTimeUtils.TIME_FORMAT);
                String publishTimeStr = StringUtils.blankToSpecific(publishTime,"T");
                publishTimeStr += "Z";
                searchModel.setPublishtime(publishTimeStr);

                searchModels.add(searchModel);
            }
            return searchModels;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*@Override
    public List<KcSearchModel> selectByPublishTime(SearchInfoBoxModel searchInfoBoxModel){
        try {
            List<KcInfoBox> kcInfoBoxes = kcInfoBoxMapper.selectByPublishTime(searchInfoBoxModel);

            List<KcSearchModel> searchModels = new ArrayList<>();
            for (KcInfoBox infoBox : kcInfoBoxes) {
                KcSearchModel searchModel = new KcSearchModel();

                //通过 sessionId 查询出该条快传的附件列表
                List<KcAttList> attLists = kcAttListMapper.setlectBysessionIdAndGuid(infoBox.getSessionid());
                //通过快传的 kid  查询出关注该条快传的用户
                List<KcFavorites> favorites = kcFavoritesMapper.selectByKid(infoBox.getId());
                //通过 sessionguid 查询出接收人列表
                SearchStaffNoListModel staffNoModel = new SearchStaffNoListModel(infoBox.getPublishuser(),0L,
                        0,0, infoBox.getSessionguid(),(byte)0);
                List<KcInfoBox> kcInfoBoxesStaffNo = kcInfoBoxMapper.selectStaffNoBysessionguid(staffNoModel);

                //处理附件列表
                if(null != attLists && attLists.size() > 0){
                    StringBuilder attStr = new StringBuilder("");
                    for (KcAttList kcAtt:attLists) {
                        attStr.append(kcAtt.getTitle()+",");
                    }
                    searchModel.setFiles(attStr.toString().substring(0,attStr.toString().length()-1));
                }else {
                    searchModel.setFiles("");
                }
                //处理关注人列表
                if(null != favorites && favorites.size() > 0){
                    StrBuilder userIdStr = new StrBuilder("");
                    for (KcFavorites f:favorites) {
                        userIdStr.append(f.getUid()+",");
                    }
                    searchModel.setFavorites(userIdStr.toString().substring(0,userIdStr.toString().length()-1));

                }else {
                    searchModel.setFavorites("");
                }
                //处理接收人列表
                if(null != kcInfoBoxesStaffNo && kcInfoBoxesStaffNo.size() > 0){
                    StrBuilder ids = new StrBuilder("");
                    StrBuilder names = new StrBuilder("");
                    List<Long> staffNoList = new ArrayList<>();

                    for (KcInfoBox kcInfoBox:kcInfoBoxesStaffNo) {
                        staffNoList.add(kcInfoBox.getStaffno());
                        ids.append(kcInfoBox.getStaffno()+",");
                    }
                    //设置接收人ids
                    searchModel.setReceiveruserids(ids.toString().substring(0,ids.toString().length()-1));
                    //查询用户名
                    List<DynStaffNo> nameList = dynMemberStaffMapper.setlectStaffNameByStaffNo(staffNoList);
                    if(null != nameList && nameList.size() > 0 ){
                        for (DynStaffNo staffName:nameList) {
                            names.append(staffName+",");
                        }
                        //设置接收人的名字
                        searchModel.setReceiverusers(names.toString().substring(0,names.toString().length()-1));
                    }else {
                        searchModel.setReceiverusers("");
                    }

                }else {
                    searchModel.setReceiverusers("");
                    searchModel.setReceiveruserids("");
                }
                //处理发起人的名字
                List<Long> publishStaffNo = new ArrayList<>();
                publishStaffNo.add(infoBox.getPublishuser());
                List<DynStaffNo> publishName = dynMemberStaffMapper.setlectStaffNameByStaffNo(publishStaffNo);
                if(null != publishName && publishName.size() > 0 ){
                    searchModel.setPublishuser(publishName.get(0).getStaffname());
                }else {
                    searchModel.setPublishuser("");
                }
                searchModel.setPublishuserid(infoBox.getPublishuser());
                //处理其他的数据
                searchModel.setId(infoBox.getId());
                searchModel.setTitle(infoBox.getTitle());
                searchModel.setInfo(infoBox.getContent());
                searchModel.setType(infoBox.getType().byteValue());

                String s = infoBox.getPublishtime().toString();
                String publishTime = DateTimeUtils.timeStamp2Date(s,DateTimeUtils.TIME_FORMAT);
                publishTime.replace("","T");
                publishTime += "Z";
                searchModel.setPublishtime(publishTime);


                searchModels.add(searchModel);
            }
//            for (KcSearchModel s:searchModels) {
//                System.out.println(s.toString());
//            }
            return searchModels;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }*/

}
