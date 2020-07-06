package com.gzhz.kcmqmonitor.search;

import com.gzhz.kcmqmonitor.entity.*;
import com.gzhz.kcmqmonitor.utils.DateTimeUtils;
import com.gzhz.kcmqmonitor.utils.HttpRequestUtils;
import com.gzhz.kcmqmonitor.utils.PageHelper;
import com.gzhz.kcmqmonitor.utils.SelfStringUtils;
import com.gzhz.kcmqmonitor.vo.KcSearchResultVo;
import com.gzhz.kcmqmonitor.vo.ResultVo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.*;

@Configuration
@Service("searchService")
public class SearchService {

    @Value("${search.client.type}")
    private String type;
    @Value("${search.client.port0}")
    private String port0;
    @Value("${search.client.port1}")
    private String port1;
    @Value("${search.client.url}")
    private String url;

    private String Bearer  = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcnlEYXRlIjoxNjQwOTY2NDAwMDAwLCJ3b3Jrc3BhY2UiOiJneiIsInVzZXJfbmFtZSI6Imd6RGVtbyIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsInRydXN0Il0sImNvbGxlY3Rpb24iOm51bGwsImV4cCI6MTYwMjgwMjQxOSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9ST0xFX0NMSUVOVF9ERUxFVEUiLCJST0xFX1JPTEVfVVNFUl9BQ0NFU1NfREVMIiwiUk9MRV9ST0xFX0NPTExFQ1RJT05fU0NIRSIsIlJPTEVfUk9MRV9TRUFSQ0hfSk9CIiwiUk9MRV9ST0xFX0RFTEVURV9SRVNPVVJDRSIsIlJPTEVfUk9MRV9BRERfUkVTT1VSQ0UiLCJST0xFX1JPTEVfQ0xJRU5UX1VQREFURSIsIlJPTEVfUk9MRV9ERUxFVEVfSk9CIiwiUk9MRV9ST0xFX1JFU09VUkNFX0FERCIsIlJPTEVfUk9MRV9DUkVBVEVfQ09ORklHX1MiLCJST0xFX1JPTEVfQUREX0pPQiIsIlJPTEVfUk9MRV9VUERBVEVfUkVTT1VSQ0UiLCJST0xFX1JPTEVfQ0xJRU5UX0FERCIsIlJPTEVfUk9MRV9DTElFTlRfQUxMIiwiUk9MRV9ST0xFX0NPTExFQ1RJT05fR0VUIiwiUk9MRV9ST0xFX0dFVF9ET0NVTUVOVCIsIlJPTEVfUk9MRV9BSV9UQUdHSU5HIiwiUk9MRV9ST0xFX0VYVFJBQ1RfVEFHR0lORyIsIlJPTEVfUk9MRV9VU0VSX1VQREFURSIsIlJPTEVfUk9MRV9TRUFSQ0giLCJST0xFX1JPTEVfVVBMT0FEX1JFU09VUkNFIiwiUk9MRV9ST0xFX1JFU09VUkNFX0RFTEVURSIsIlJPTEVfUk9MRV9DT0xMRUNUSU9OX0FERCIsIlJPTEVfUk9MRV9VU0VSX0FERCIsIlJPTEVfUk9MRV9VU0VSX0FMTCIsIlJPTEVfUk9MRV9DT0xMRUNUSU9OX0RFTEUiLCJST0xFX1JPTEVfRE9XTkxPQURfUkVTT1VSIiwiUk9MRV9ST0xFX0FERF9GSUVMRCIsIlJPTEVfUk9MRV9BRE1JTiIsIlJPTEVfUk9MRV9UQUdHSU5HIiwiUk9MRV9ST0xFX0RFTEVURV9RVUVSWV9ETyIsIlJPTEVfUk9MRV9ERUxFVEVfRE9DVU1FTlQiLCJST0xFX1JPTEVfQUREX0NPUFlfRklFTEQiLCJST0xFX1JPTEVfVVNFUl9BQ0NFU1NfQVNTIiwiUk9MRV9ST0xFX0FERF9PVEhFUl9UQUciLCJST0xFX1JPTEVfT1BFUkFURV9UQUciLCJST0xFX1JPTEVfUkVTT1VSQ0VfR0VUIiwiUk9MRV9ST0xFX1BVUkdFX0ZJRUxEIiwiUk9MRV9ST0xFX01BTkFHRV9KT0IiLCJST0xFX1JPTEVfREVMRVRFX0NPTkZJR19TIiwiUk9MRV9ST0xFX1VTRVJfQUNDRVNTX0dFVCIsIlJPTEVfUk9MRV9SRUNPTU1FTkRfRE9DVU0iLCJST0xFX1JPTEVfRVhUUkFDVF9USU1FIiwiUk9MRV9ST0xFX0FJX1RBR0dJTkdfQUNDVSIsIlJPTEVfUk9MRV9DTElFTlRfR0VUIiwiUk9MRV9ST0xFX0VYVFJBQ1RfRU5USVRZIiwiUk9MRV9ST0xFX1VQREFURV9ET0NVTUVOVCIsIlJPTEVfUk9MRV9ERUxFVEVfRklFTEQiLCJST0xFX1JPTEVfQ09MTEVDVF9BQ1RJT04iLCJST0xFX1JPTEVfREVMRVRFX0NPTExFQ1RJIiwiUk9MRV9ST0xFX1VTRVJfREVMRVRFIiwiUk9MRV9ST0xFX01BSU5UQUlOX0NPTExFQyIsIlJPTEVfUk9MRV9TWVNBRE1JTiIsIlJPTEVfUk9MRV9HRVRfUkVTT1VSQ0VfQlkiLCJST0xFX1JPTEVfR0VUX0xBQkVMIiwiUk9MRV9ST0xFX0lOREVYSU5HIiwiUk9MRV9ST0xFX0RFTEVURV9JRFNfRE9DVSIsIlJPTEVfUk9MRV9SRVNPVVJDRV9VUERBVEUiLCJST0xFX1JPTEVfQ1JFQVRFX0NPTExFQ1RJIiwiUk9MRV9BRE1JTiIsIlJPTEVfUk9MRV9VU0VSX0dFVCIsIlJPTEVfUk9MRV9DUkVBVEVfQ09SUFVTIl0sImp0aSI6IjBkNmRmZjgzLTgxMzktNGFkOS05ZTY3LWJkNjc4YTc5ODZiOSIsImNsaWVudF9pZCI6ImQxNjk3OWNiYTk2ZDRmYmI5MTRiOGFlYzFiNGJkODEyIn0.tqdiJ_EipUQaHxwIbeG1axnnYIPhHhRPpsCnBzcRz2E";

    /**
     * 新增一条记录
     * @param model  新增对象
     * @return
     */
    public ResultVo add (InsertModel model){
//        String result = HttpUtils.sendPost(url+":"+port0+"/admin/v2/api/profiler",JSONObject.fromObject(model));
//        System.out.println("新增快传的结果："+result);
//        return null;

        String result = HttpRequestUtils.doPost(url+":"+port0+"/admin/v2/api/profiler",null,JSONObject.fromObject(model).toString());
//        System.out.println("新增快传的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        ResultVo vo = (ResultVo)JSONObject.toBean(jsonObject,ResultVo.class);
        return vo;
    }

    /**
     *删除一条记录
     * @param id
     * @return
     */
    public ResultVo delete (String id){
        String result = HttpRequestUtils.doDelete(url+":"+port0+"/admin/v2/api/index/gz_kc/"+id,null,null);
        System.out.println("删除的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        ResultVo vo = (ResultVo)JSONObject.toBean(jsonObject,ResultVo.class);
        return vo;
    }

    /**
     * 查看一条记录
     * @param id
     * @return
     */
    public KcSearchNewModel view (String id){
        String result = HttpRequestUtils.doPost(url+":"+port1+"/v2/api/document/"+id,Bearer,getViewStr());
        System.out.println("查询的的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
//        ResultVo vo = (ResultVo)JSONObject.toBean(jsonObject,ResultVo.class);
        Map<String,Object> response = ( Map<String,Object>)jsonObject.get("response");
        KcSearchNewModel kcModel = new KcSearchNewModel();
        List<Map<String,List>> resultList = ( List<Map<String,List>> )response.get("docs");
        for (Map<String,List> map: resultList) {

            JSONObject jsonStr = JSONObject.fromObject(map);
            kcModel = (KcSearchNewModel) JSONObject.toBean(jsonStr,KcSearchNewModel.class);

        }
        return kcModel;
    }

    /**
     * 现在只用来新增附件
     * @param model
     * @return
     */
    public ResultVo updateFiles (UpdateFilesModel model){
//        System.out.println("更新时传入的json字符串"+JSONObject.fromObject(model).toString());
        String result = HttpRequestUtils.doPut(url+":"+port0+"/admin/v2/api/index",Bearer,JSONObject.fromObject(model).toString());
//        System.out.println("更新附件的的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        ResultVo vo = (ResultVo)JSONObject.toBean(jsonObject,ResultVo.class);
        return vo;
    }

    /**
     * 删除附件  根据附件名   http://59.215.190.43:9090/admin/v2/api/index/operate   POST
     * @param removeFile
     * @return
     */
    public ResultVo removeFiles (RemoveFile removeFile){
        System.out.println("删除附件时传入的json字符串"+JSONObject.fromObject(removeFile).toString());
        String result = HttpRequestUtils.doPost(url+":"+port0+"/admin/v2/api/index/operate",Bearer,JSONObject.fromObject(removeFile).toString());
        System.out.println("删除附件的的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        ResultVo vo = (ResultVo)JSONObject.toBean(jsonObject,ResultVo.class);
        return vo;
    }

    /**
     * 邀请人只能新增
     * @param model
     * @return
     */
    public ResultVo updateReceiverUser (updateReceiverUserModel model){
//        System.out.println("新增接收人时传入的json字符串"+JSONObject.fromObject(model).toString());
        String result = HttpRequestUtils.doPut(url+":"+port0+"/admin/v2/api/index",Bearer,JSONObject.fromObject(model).toString());
//        System.out.println("新增接收人的的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        ResultVo vo = (ResultVo)JSONObject.toBean(jsonObject,ResultVo.class);
        return vo;
    }

    /**
     * 更新一条快传的关注人列表
     * @param model
     * @return
     */
    public ResultVo selectFavorites (updateFavoritesModel model){
//        System.out.println("更新时传入的json字符串"+JSONObject.fromObject(model).toString());
        String result = HttpRequestUtils.doPut(url+":"+port0+"/admin/v2/api/index",Bearer,JSONObject.fromObject(model).toString());
//        System.out.println("更新关注人列表的的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        ResultVo vo = (ResultVo)JSONObject.toBean(jsonObject,ResultVo.class);
        return vo;
    }

    public ResultVo updateFastPassState (UpdateStateModel model){
        System.out.println("逻辑删除时传入的json字符串"+JSONObject.fromObject(model).toString());
        String result = HttpRequestUtils.doPut(url+":"+port0+"/admin/v2/api/index",Bearer,JSONObject.fromObject(model).toString());
//        System.out.println("逻辑删除的的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        ResultVo vo = (ResultVo)JSONObject.toBean(jsonObject,ResultVo.class);
        return vo;
    }


    /**
     * 查看一条记录时，需要配置的查询条件
     * @return
     */
    private String getViewStr () {
        ViewModel model = new ViewModel();
        model.setCollection("kc");
        model.setQueryParam( new QueryParamSelectOne("*:*" ) );
        // 设置返回字段
        model.setReturnParam(new ReturnParam(new String[]{"id","title","info","receiverusers","receiveruserids","files","publishtime","publishuser","publishuserid","headImageUrl","favorites","type"},1,0) );
        String jsonStr = JSONObject.fromObject(model).toString();
        System.out.println("jsonStr========="+jsonStr);
        return jsonStr;
    }



    /**
     *          老版本
     * 根据 标题title   附件名files   接收人名字receiverUsers   发起人名字publishUser
     * 注   内容info 以后考虑
     * @param model
     * @return
     */
    /*public ResultVo search (SearchBlurryModel model){
        ResultVo vo = new ResultVo();
        List<KcSearchNewModel> returnList = new ArrayList<>();
        PageHelper pageHelper = new PageHelper();
        System.out.println("查询时传入的json字符串"+JSONObject.fromObject(model).toString());
        SearchModel searchModel = null;
        try {
            searchModel = dealData(model);
            System.out.println(JSONObject.fromObject(searchModel).toString());
            String result = HttpRequestUtils.doPost(url+":"+port1+"/v2/api/search",Bearer,JSONObject.fromObject(searchModel).toString());
            System.out.println("查询的结果："+result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            Map<String,Object> response = ( Map<String,Object>)jsonObject.get("response");
            int count = (int)response.get("numFound");    //查询到的总数
            if(count == 0){
                vo.setStatus(-1);
                vo.setMessage("查询到的数据数量为0");
                return vo;
            }

            pageHelper.setCurrentPage(searchModel.getReturnParam().getStart() + 1);
            pageHelper.setPageSize(searchModel.getReturnParam().getRows());
            pageHelper.setTotalCount(count);//设置总数量
            pageHelper.setTotalPage(); //设置总页数
            List<Map<String,List>> resultList = ( List<Map<String,List>> )response.get("docs");
            for (Map<String,List> map: resultList) {
                KcSearchNewModel kcModel = null;
                JSONObject jsonStr = JSONObject.fromObject(map);
                kcModel = (KcSearchNewModel) JSONObject.toBean(jsonStr,KcSearchNewModel.class);
                returnList.add(kcModel);
            }
            pageHelper.setResultList(returnList); //设置结果集
            vo.setPageHelper(pageHelper);
            vo.setMessage("查询成功");
            vo.setStatus(0);
            return vo;
        }catch (Exception e){
            e.printStackTrace();
            vo.setStatus(-1);
            vo.setMessage("查询出错");
            return vo;
        }
    }*/


    /**
     *      新版本
     * 根据 标题title   附件名files   接收人名字receiverUsers   发起人名字publishUser
     * 注   内容info 以后考虑
     * @param model
     * @return
     */
    public Map<String,Object> search (SearchBlurryModel model){
        ResultVo vo = new ResultVo();
//        List<KcSearchNewModel> returnList = new ArrayList<>();
        List<KcSearchResultVo> returnList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        System.out.println("查询时传入的json字符串"+JSONObject.fromObject(model).toString());
        SearchModel searchModel = null;
        try {
            searchModel = dealData(model);
            System.out.println(JSONObject.fromObject(searchModel).toString());
            String result = HttpRequestUtils.doPost(url+":"+port1+"/v2/api/search",Bearer,JSONObject.fromObject(searchModel).toString());
            System.out.println("查询的结果："+result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            Map<String,Object> response = ( Map<String,Object>)jsonObject.get("response");
            int count = (int)response.get("numFound");    // 查询到的总数
            if(count == 0){
                map.put("totalCount",0);    // 查询结果为0条数据
                map.put("message","查询成功");
                map.put("statue",0);
                return map;
            }
            map.put("currentPage",model.getCurrentPage());
            map.put("pageSize",model.getPageSize());
            map.put("totalCount",count);    // 设置总数量
            // 设置总页数
            if(count == 0){
                map.put("totalPage",0);
            }else {
                int t = count % searchModel.getReturnParam().getRows();
                if(t == 0){
                    map.put("totalPage",count / searchModel.getReturnParam().getRows());
                }else {
                    map.put("totalPage",count / searchModel.getReturnParam().getRows() +1 );
                }
            }
            // 获取结果集
            List<Map<String,List>> resultList = ( List<Map<String,List>> )response.get("docs");
            for (Map<String,List> resultMap: resultList) {
                KcSearchNewModel kcModel = null;
                KcSearchResultVo searchResultVo = new KcSearchResultVo();
                JSONObject jsonStr = JSONObject.fromObject(resultMap);
                kcModel = (KcSearchNewModel) JSONObject.toBean(jsonStr,KcSearchNewModel.class);
                // 将kcModel的值赋给vo
                BeanUtils.copyProperties(kcModel,searchResultVo);
                returnList.add(searchResultVo);

            }
            // 处理结果集
//            Map<String,Object> dateMap = dealSearchResult(returnList,model.getUserId(),model.getKeyWord());
            // 处理新讨论后的结果集
            List<KcSearchResultVo> dateMap = dealNewSearchResult(returnList,model.getUserId(),model.getKeyWord());
            map.put("resultDate",dateMap);
            map.put("message","查询成功");
            map.put("statue",0);
            return map;

        }catch (Exception e){
            e.printStackTrace();
            map.put("message","处理查询结果出错");
            map.put("statue",-1);
            return map;
        }
    }

    /**
     * 将查询到的结果进行分类处理
     * @param returnList
     * @param userId
     * @return
     * @throws Exception
     */
    public Map<String,Object> dealSearchResult( List<KcSearchNewModel> returnList, Long userId, String keyWord) throws Exception{

        Map<String,Object> map = new HashMap<>();
        List<KcSearchNewModel> IPublishList = new ArrayList<>();
        List<KcSearchNewModel> IReceiveList = new ArrayList<>();
        List<KcSearchNewModel> IFavoriteList = new ArrayList<>();
        List<KcSearchNewModel> IsDraftList = new ArrayList<>();
        List<KcSearchNewModel> IsDeleteList = new ArrayList<>();
        List<KcSearchNewModel> IsFilesList = new ArrayList<>();
        try {
            for (KcSearchNewModel model : returnList) {
                // 判断是否是我发出的
                if(StringUtils.isNotBlank(model.getPublishuserid()) && model.getPublishuserid().equals(userId.toString() )){
                    IPublishList.add(model);
                    // 判断是否是我的草稿
                    if(model.getType() == 8){
                        IsDraftList.add(model);
                    }
                    // 判断是否是我已删除的
                    if(model.getType() == 4 ){
                        IsDeleteList.add(model);
                    }
                }
                // 判断是否是我接收的
                if( null != model.getReceiveruserids() && model.getReceiveruserids().length > 0){
//                    int index = Arrays.binarySearch(model.getReceiveruserids(),userId.toString());
                    List<String> asList = Arrays.asList( model.getReceiveruserids() );
//                    if(index >= 0){  // 包含该userId
//                        IReceiveList.add(model);
//                    }
                    if(asList.contains(userId.toString())){
                        IReceiveList.add(model);
                    }
                }
                // 判断是否是我关注的
                if(null != model.getFavorites() && model.getFavorites().length > 0 && StringUtils.isNotBlank(model.getFavorites()[0]) ){
//                    int index = Arrays.binarySearch(model.getFavorites(),userId.toString());
                    List<String> asList = Arrays.asList( model.getFavorites() );
//                    if(index > 0){  // 包含该userId
//                        IFavoriteList.add(model);
//                    }
                    if(asList.contains(userId.toString())){  // 包含该userId
                        IFavoriteList.add(model);
                    }
                }
                // 判断是否是含有附件
                if(null != model.getFiles() && model.getFiles().length > 0 && StringUtils.isNotBlank(model.getFiles()[0]) ){
                    KcSearchNewModel model1 = new KcSearchNewModel();
                    // 将model的值赋给model1
                    BeanUtils.copyProperties(model,model1);
                    List<String> list = Arrays.asList(model1.getFiles());
                    List<String> fileList = new ArrayList<>(list);
                    if(StringUtils.isNotBlank(keyWord) ){
                        for (int i = 0; i < fileList.size(); i++){
                            // 查看附件名中是否含有查询时输入的关键字
                            if( !fileList.get(i).contains(keyWord)){
                                fileList.remove(i);
                                i--;
                            }
                        }
                        if(null != fileList && fileList.size() > 0){
                            model1.setFiles( (String[]) fileList.toArray( new String[0]));
                        }else {
                            model1.setFiles(new String[] {""});
                        }
                    }
                    if(null != model1.getFiles() && model.getFiles().length > 0){
                        IsFilesList.add(model1);
                    }
                }
            }
            map.put("AllData",returnList);  // 所有数据
            map.put("IPublishList",IPublishList);// 我发出的
            map.put("IReceiveList",IReceiveList); // 我接收的
            map.put("IFavoriteList",IFavoriteList);  // 我关注的
            map.put("IsDraftList",IsDraftList);   // 草稿
            map.put("IsDeleteList",IsDeleteList);  // 已删除的
            map.put("IsFilesList",IsFilesList);  // 附件

            return map ;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 将查询到的结果进行分类处理
     * @param returnList
     * @param userId
     * @return
     * @throws Exception
     */
    public List<KcSearchResultVo> dealNewSearchResult( List<KcSearchResultVo> returnList, Long userId, String keyWord) throws Exception{

        try {
            for (KcSearchResultVo model : returnList) {
                // 判断是否是我的草稿
                if(model.getType() == 8){
                    setSbutitle(model,keyWord);
                    continue;
                }
                // 判断是否是我已删除的
                if(model.getType() == 4 ){
                    setSbutitle(model,keyWord);
                    continue;
                }
                // 判断是否是我接收的
                if( null != model.getReceiveruserids() && model.getReceiveruserids().length > 0){
                    List<String> asList = Arrays.asList( model.getReceiveruserids() );
                    // 包含该userId
                    if(asList.contains(userId.toString())){
                        model.setType((byte)2);
                    }
                }
                // 判断是否是我关注的
                if(null != model.getFavorites() && model.getFavorites().length > 0 && StringUtils.isNotBlank(model.getFavorites()[0]) ){
                    List<String> asList = Arrays.asList( model.getFavorites() );
                    // 包含该userId
                    if(asList.contains(userId.toString())){
                        model.setType((byte)3);
                    }
                }
                // 判断是否是我发出的
                if(StringUtils.isNotBlank(model.getPublishuserid()) && model.getPublishuserid().equals(userId.toString() )){
                    model.setType((byte)1);
                }
                // 设置副标题
                setSbutitle(model,keyWord);

            }
//            map.put("AllData",returnList);  // 所有数据
            return returnList ;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     *  设置副标题 设置高亮参数，优先级:附件>发起人>接收人>附言（info）>发起人
     * @param model
     */
    public void setSbutitle (KcSearchResultVo model, String keyWord){

        // 设置默认头像
        if(StringUtils.isBlank(model.getHeadImageUrl())){
            model.setHeadImageUrl("/static/mhMobile/images/default-header-img.jpg");
        }else {
            model.setHeadImageUrl("http://jg.gz.cegn.cn/orgcenterplat/"+model.getHeadImageUrl());
        }
        if(null != model.getFiles() && model.getFiles().length > 0){
            StringBuffer sbf = new StringBuffer("附件名：");
            List<String> list = Arrays.asList(model.getFiles());
            List<String> fileList = new ArrayList<>(list);
            for (int i = 0; i < fileList.size(); i++){
                // 查看附件名中是否含有查询时输入的关键字
                if( /*fileList.get(i).contains(keyWord) &&*/ fileList.get(i).contains("<em>") && fileList.get(i).contains("</em>")){
                    sbf.append(fileList.get(i) + "，");
                }
            }
            model.setSubtitle(sbf.toString().substring(0, sbf.toString().length() - 1));
            if(model.getSubtitle().length() > 4){
                return;
            }
        }

        if(StringUtils.isNotBlank(model.getPublishuser())){
            if(/*model.getPublishuser().contains(keyWord) &&*/ model.getPublishuser().contains("<em>") && model.getPublishuser().contains("</em>")){
                model.setSubtitle("发起人："+ model.getPublishuser());
            }
            if(model.getSubtitle().length() > 4){
                return;
            }
        }

        if(null != model.getReceiverusers() && model.getReceiverusers().length > 0){
            StringBuffer sbf = new StringBuffer("接收人：");
            List<String> list = Arrays.asList(model.getReceiverusers());
            List<String> receiverUserList = new ArrayList<>(list);
            for (int i = 0; i < receiverUserList.size(); i++){
                // 查看附件名中是否含有查询时输入的关键字
                if(/*receiverUserList.get(i).contains(keyWord) && */receiverUserList.get(i).contains("<em>") && receiverUserList.get(i).contains("</em>")){
                    sbf.append(receiverUserList.get(i) + "，");
                }
            }
            model.setSubtitle(sbf.toString().substring(0, sbf.toString().length() - 1));
            if(model.getSubtitle().length() > 4){
                return;
            }
        }

        if(StringUtils.isNotBlank(model.getInfo())){
            model.setSubtitle("附言："+model.getInfo());
            if(model.getSubtitle().length() > 3){
                return;
            }
        }

        if(StringUtils.isBlank(model.getSubtitle()) || model.getSubtitle().length() <= 3){
            model.setSubtitle("发起人："+ model.getPublishuser());
        }

    }

    /**
     * 封装查询时需要的参数
     * */
    public SearchModel dealData(SearchBlurryModel model) throws Exception{
        try {
            SearchModel searchModel = new SearchModel();
            searchModel.setCollection("kc");
            String [] fields = null;
            StringBuilder sbd = new StringBuilder("");
            if(null != model.getFindType() && model.getFindType().length > 0){
                fields = new String [model.getFindType().length];
                // 如果用户输入的关键字以 - 开头，则删除掉该字符  已经提示前端初步处理输入的关键字
                boolean flag = true;
                if(StringUtils.isBlank(model.getKeyWord())){
                    flag = false;
                }
                while (flag){
                    if("-".equals(model.getKeyWord().substring(0,1))){
                        model.setKeyWord(model.getKeyWord().substring(1,model.getKeyWord().length()));
                    }else {
                        flag = false;
                    }
                }
                for (int i = 0; i < fields.length; i++) {
                    if(model.getFindType()[i] == 1){
                        fields[i] = "publishuser";  //发起人

                        if(StringUtils.isBlank(sbd.toString())){
                            sbd.append("publishuser:"+model.getKeyWord());
                        }else {
                            sbd.append(" OR publishuser:"+model.getKeyWord());
                        }
                    }else if(model.getFindType()[i] == 2){
                        fields[i] = "receiverusers";  //接收人
                        if(StringUtils.isBlank(sbd.toString())){
                            sbd.append("receiverusers:"+model.getKeyWord());
                        }else {
                            sbd.append(" OR receiverusers:"+model.getKeyWord());
                        }
                    }else if(model.getFindType()[i] == 4){
                        fields[i] = "files";    //附件
                        if(StringUtils.isBlank(sbd.toString())){
                            sbd.append("files:"+model.getKeyWord());
                        }else {
                            sbd.append(" OR files:"+model.getKeyWord());
                        }
                    }else {
                        fields[i] = "title";    //主题
                        if(StringUtils.isBlank(sbd.toString())){
                            sbd.append("title:"+model.getKeyWord());
                        }else {
                            sbd.append(" OR title:"+model.getKeyWord());
                        }
                    }
                }
            }
            if(null != fields && fields.length > 0){
                //高亮参数
                HighLightParam highLightParam = new HighLightParam();
                highLightParam.setField(fields);  //*"info",   后面考虑加不加*//*
                searchModel.setHighLightParam(highLightParam);
            }else {
                searchModel.setHighLightParam(null);
            }
            //查询参数
            QueryParamSearch query = new QueryParamSearch();
            if(StringUtils.isNotBlank(model.getKeyWord()) ){
                query.setQuery(model.getKeyWord());  //查询关键字
            }else {
                query.setQuery("*:*");//查询 与用户相关的所有快传
            }
            String queryStr = sbd.toString();
            String queryByUserId = "publishuserid:"+"\""+model.getUserId()+"\""+" OR receiveruserids:"+"\""+model.getUserId()+"\""+" OR favorites:"+"\""+model.getUserId()+"\"";
//        String queryStr = "files:* OR title:* OR receiverusers:* OR publishuser:*";  //*OR info.*   后面考虑要不要搜索内容*//*
            String type = "type:1 OR type:4 OR type:8"; //OR type:4 OR type:8
//            String sortField = "sortField"+": ["+"publishtime desc"+"]";
            query.setSortField(new String[]{"score desc", "publishtime desc"});
//        String queryTime = "publishtime:[2020-06-01T00:00:00Z TO 2020-06-12T05:00:00Z]";
            //设置查询时间
            if(StringUtils.isNotBlank(model.getStartTime()) && StringUtils.isNotBlank(model.getEndTime())){
                String publishTimeStartStr = SelfStringUtils.blankToSpecific(model.getStartTime(),"T");
                String publishTimeEndStr = SelfStringUtils.blankToSpecific(model.getEndTime(),"T");
                publishTimeStartStr += "Z";
                publishTimeEndStr += "Z";
                String queryTime = "publishtime:["+publishTimeStartStr+" TO "+publishTimeEndStr+"]";
                query.setFilterQuery(new String[]{queryStr,queryByUserId,type,queryTime});
                searchModel.setQueryParam(query);
            }else {
                query.setFilterQuery(new String[]{queryStr,queryByUserId,type});
                searchModel.setQueryParam(query);
            }

            //设置返回的参数
            ReturnParam returnParam = new ReturnParam();
            returnParam.setField(new String[]{"id","title","info","receiverusers","receiveruserids",
                    "files","publishtime","publishuser","publishuserid","headImageUrl","favorites","type"});
            returnParam.setRows(model.getPageSize());
            if(model.getCurrentPage() == 0){
                returnParam.setStart(0);
            }else {
                returnParam.setStart((model.getCurrentPage() - 1) * model.getPageSize());
            }
            searchModel.setReturnParam(returnParam);

            return searchModel;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

}
