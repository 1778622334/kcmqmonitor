package com.gzhz.kcmqmonitor.services.impl;

import com.gzhz.kcmqmonitor.entity.*;
import com.gzhz.kcmqmonitor.mapper.*;
import com.gzhz.kcmqmonitor.model.SearchInfoBoxModel;
import com.gzhz.kcmqmonitor.model.SearchStaffNoListModel;
import com.gzhz.kcmqmonitor.services.KcInfoBoxService;
import com.gzhz.kcmqmonitor.utils.DateTimeUtils;
import com.gzhz.kcmqmonitor.utils.SelfStringUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

                // 通过 sessionId 查询出该条快传的附件列表
                List<KcAttList> attLists = kcAttListMapper.setlectBysessionIdAndGuid(infoBox.getSessionid());
                // 通过快传的 kid  查询出关注该条快传的用户
                List<KcFavorites> favorites = kcFavoritesMapper.selectByKid(infoBox.getId());
                // 通过 sessionguid 查询出接收人列表
                SearchStaffNoListModel staffNoModel = new SearchStaffNoListModel(infoBox.getPublishuser(),0L,
                        0,0, infoBox.getSessionguid(),(byte)0);
                List<KcInfoBox> kcInfoBoxesStaffNo = kcInfoBoxMapper.selectStaffNoBysessionguid(staffNoModel);

                // 处理附件列表
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
                // 处理关注人列表

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
                // 处理接收人列表
                if(null != kcInfoBoxesStaffNo && kcInfoBoxesStaffNo.size() > 0){
                    String[] ids = new String[kcInfoBoxesStaffNo.size()] ;
                    List<Long> staffNoList = new ArrayList<>();
                    for (int i = 0; i < kcInfoBoxesStaffNo.size(); i++){
                        staffNoList.add(kcInfoBoxesStaffNo.get(i).getStaffno());
                        ids[i] = kcInfoBoxesStaffNo.get(i).getStaffno().toString();
                    }
                    // 设置接收人ids
                    searchModel.setReceiveruserids(ids);
                    // 查询用户名
                    List<DynStaffNo> nameList = dynMemberStaffMapper.setlectStaffNameByStaffNo(staffNoList);
                    if(null != nameList && nameList.size() > 0 ){
                        String[] names = new String[nameList.size()];
                        for (int i = 0; i < nameList.size(); i++){
                            names[i] = (nameList.get(i).getStaffname());
                        }
                        // 设置接收人的名字
                        searchModel.setReceiverusers(names);
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
                //处理发起人的信息 （名字和头像）
                List<Long> publishStaffNo = new ArrayList<>();
                publishStaffNo.add(infoBox.getPublishuser());
                List<DynStaffNo> publishName = dynMemberStaffMapper.setlectStaffNameByStaffNo(publishStaffNo);
                if(null != publishName && publishName.size() > 0 ){
                    searchModel.setPublishuser(publishName.get(0).getStaffname());
                    searchModel.setHeadImageUrl(publishName.get(0).getHeadImageUrl());
                }else {
                    searchModel.setPublishuser("");
                    searchModel.setHeadImageUrl("");
                }
                if(null == searchModel.getHeadImageUrl()){
                    searchModel.setHeadImageUrl("");
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
                String publishTimeStr = SelfStringUtils.blankToSpecific(publishTime,"T");
//                publishTimeStr += "Z";
                searchModel.setPublishtime(publishTimeStr);

                searchModels.add(searchModel);
            }
            return searchModels;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 主键id查找 kcInfoBox  后处理成新增快传的数据模型
     * @param id
     * @return
     */
    @Override
    public KcSearchNewModel selectByPrimaryKey(Integer id) throws Exception{
        KcSearchNewModel searchModel = new KcSearchNewModel();
        try {
            KcInfoBox kcInfoBox = kcInfoBoxMapper.selectByPrimaryKey(id);
            // 查出接收人列表
            List<KcInfoBox> kcInfoBoxesStaffNo = kcInfoBoxMapper.selectBySessionid(kcInfoBox.getSessionid());
            // 查出附件列表
            List<KcAttList> attLists = kcAttListMapper.setlectBysessionIdAndGuid(kcInfoBox.getSessionid());
            // 通过快传的 kid  查询出关注该条快传的用户
            List<KcFavorites> favorites = kcFavoritesMapper.selectByKid(kcInfoBox.getId());

            // 处理附件列表
            String[] attArr = dealFileList(attLists);
            searchModel.setFiles( attArr);
            // 处理关注人列表
            String[] favUserArr = dealFavorites(favorites);
            searchModel.setFavorites( favUserArr );
            // 处理接收人列表
            Map<String,String[]> map = dealReceiverUserList(kcInfoBoxesStaffNo);
            searchModel.setReceiveruserids(map.get("ids"));
            searchModel.setReceiverusers( map.get("names"));
            // 处理发起人的信息（包含名字和头像地址）
            List<Long> publishStaffNo = new ArrayList<>();
            publishStaffNo.add(kcInfoBox.getPublishuser());
            List<DynStaffNo> publishName = dynMemberStaffMapper.setlectStaffNameByStaffNo(publishStaffNo);
            if(null != publishName && publishName.size() > 0 ){
                searchModel.setPublishuser(publishName.get(0).getStaffname());
                searchModel.setHeadImageUrl(publishName.get(0).getHeadImageUrl());
            }else {
                searchModel.setHeadImageUrl("");
                searchModel.setPublishuser("");
            }
            if(null == searchModel.getHeadImageUrl()){
                searchModel.setHeadImageUrl("");
            }
            // 发起人的id
            searchModel.setPublishuserid(kcInfoBox.getPublishuser().toString());
            // 处理其他的数据
            searchModel.setId(kcInfoBox.getId().toString());
            searchModel.setTitle(kcInfoBox.getTitle());
            searchModel.setInfo(kcInfoBox.getContent());
            // 模型中type 1表示正常发出， 4已删除的  8草稿箱的  在数据库中用state字段表示
            searchModel.setType(kcInfoBox.getState().byteValue());
            // 处理publishTime格式
            String s = kcInfoBox.getPublishtime().toString();
            String publishTime = DateTimeUtils.timeStamp2Date(s,DateTimeUtils.TIME_FORMAT);
            String publishTimeStr = SelfStringUtils.blankToSpecific(publishTime,"T");
            publishTimeStr += "Z";
            searchModel.setPublishtime(publishTimeStr);
            return searchModel;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 由逐渐id 查询接收人列表  后处理结果集为新增接收人数据模型
     * @param id
     * @return
     */
    public updateReceiverUserModel selectReceiverUserByPrimaryKey(Integer id){
        try {
            updateReceiverUserModel model = new updateReceiverUserModel();
//        KcInfoBox kcInfoBox = kcInfoBoxMapper.selectByPrimaryKey(id);
//        //查出接收人列表
//        List<KcInfoBox> kcInfoBoxesStaffNo = kcInfoBoxMapper.selectBySessionid(kcInfoBox.getSessionid());

            List<KcInfoBox> kcInfoBoxesStaffNo = kcInfoBoxMapper.selectReceiverUserByPrimaryKey(id);
            Map<String,String[]> map = dealReceiverUserList(kcInfoBoxesStaffNo);
            model.setAction("UPDATE");
            model.setCollection("gz_kc");
            ReceiverUser user = new ReceiverUser();
            user.setId(id.toString());
            user.setReceiverusers(map.get("names"));
            user.setReceiveruserids(map.get("ids"));
            model.setData(new ReceiverUser[] {user});

            System.out.println( JSONObject.fromObject(model).toString() );
            return model;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 由主键id 查询附件列表  处理结果集为更新附件得数据模型
     * @param id
     * @return
     */
    public UpdateFilesModel selectFilesById(Integer id){
        try {
            KcInfoBox kcInfoBox = kcInfoBoxMapper.selectByPrimaryKey(id);
            //查出附件列表
            List<KcAttList> attLists = kcAttListMapper.setlectBysessionIdAndGuid(kcInfoBox.getSessionid());
            UpdateFilesModel model = new UpdateFilesModel();
            model.setAction("UPDATE");
            model.setCollection("gz_kc");
            Files files = new Files();
            files.setId(id.toString());
            files.setFiles(dealFileList(attLists));
            model.setData(new Files[]{files});
            return model;
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    /**
     * 由主键id 查询关注该条快传的用户列表  处理结果集为更新关注列表的数据模型
     * @param id
     * @return
     */
    public updateFavoritesModel selectFavoritesById(Integer id) {
        updateFavoritesModel model = new updateFavoritesModel();
        try {
            //通过快传的 kid  查询出关注该条快传的用户
            List<KcFavorites> favorites = kcFavoritesMapper.selectByKid(id);
            model.setAction("UPDATE");
            model.setCollection("gz_kc");
            Favorites favorites1 = new Favorites();
            favorites1.setId(id.toString());
            favorites1.setFavorites(dealFavorites(favorites));
            model.setData(new Favorites[]{favorites1});
            return model;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 处理附件列表
     */
    private String[] dealFileList(List<KcAttList> attLists ) throws Exception{
        String[] attArr = null;
        try {
            if(null != attLists && attLists.size() > 0){
                attArr = new String[attLists.size()] ;
                for (int i = 0; i < attLists.size(); i++) {
                    attArr[i] = (attLists.get(i).getTitle());
                }
            }else {
                attArr = new String[1] ;
                attArr[0] = "";
            }
            return attArr;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 处理关注人的列表
     * @return
     */
    private String[] dealFavorites(List<KcFavorites> favorites)throws Exception{
        String[] favUserArr = null;
        try {
            if(null != favorites && favorites.size() > 0){
                favUserArr = new String[favorites.size()];
                for (int i = 0; i < favorites.size(); i++){
                    favUserArr[0] = (favorites.get(i).getUid().toString());
                }

            }else {
                favUserArr = new String[1];
                favUserArr[0] = ("");
            }
            return favUserArr;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 处理接收人列表
     * @param kcInfoBoxesStaffNo
     * @return
     */
    private Map<String,String[]> dealReceiverUserList(List<KcInfoBox> kcInfoBoxesStaffNo) throws Exception{
        Map<String ,String[]> map = new HashMap<>();
        try{
            if(null != kcInfoBoxesStaffNo && kcInfoBoxesStaffNo.size() > 0){
                String[] ids = new String[kcInfoBoxesStaffNo.size()] ;
                List<Long> staffNoList = new ArrayList<>();
                for (int i = 0; i < kcInfoBoxesStaffNo.size(); i++){
                    staffNoList.add(kcInfoBoxesStaffNo.get(i).getStaffno());
                    ids[i] = kcInfoBoxesStaffNo.get(i).getStaffno().toString();
                }
                //设置接收人ids
                map.put("ids",ids);
                //查询用户名
                List<DynStaffNo> nameList = dynMemberStaffMapper.setlectStaffNameByStaffNo(staffNoList);
                String[] names = null;
                if(null != nameList && nameList.size() > 0 ){
                    names = new String[nameList.size()];
                    for (int i = 0; i < nameList.size(); i++){
                        names[i] = (nameList.get(i).getStaffname());
                    }
                }else {
                    names = new String[1];
                    names[0] = ("");
                }
                //设置接收人的名字
                map.put("names",names);

            }else {
                String[] ids = new String[1];
                ids[0] = ("");
                String[] names = new String[1];
                names[0] = ("");
                map.put("ids",ids);
                map.put("names",names);
            }
            return map;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }


    public UpdateStateModel selectStateById(Integer id) throws Exception{
        try {
            KcInfoBox kcInfoBox = kcInfoBoxMapper.selectByPrimaryKey(id);
            UpdateStateModel model = new UpdateStateModel();
            model.setAction("UPDATE");
            model.setCollection("gz_kc");
            KcState kcState = new KcState();
            kcState.setId(kcInfoBox.getId().toString());
            kcState.setType(kcInfoBox.getState());

            model.setData(new KcState[]{kcState});
            return model;
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }


}
