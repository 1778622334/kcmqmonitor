package com.gzhz.kcmqmonitor.controller;

import com.gzhz.kcmqmonitor.entity.*;
import com.gzhz.kcmqmonitor.search.SearchService;
import com.gzhz.kcmqmonitor.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {
    @Autowired
    SearchService searchService;

    /**
     * 新增一条记录
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public String  add(){
        KcSearchNewModel model = new KcSearchNewModel();
        model.setId("123213213132");
        model.setTitle("蒲公英的约定");
        model.setInfo("曲：周杰伦 词：方文山");
        model.setReceiverusers(new String[] {"张三","李四","韩非子"});
        model.setReceiveruserids(new String[] {"323232122","231312321321","3213213213"});
        model.setFiles(new String[] {"***5月个人台账","***7月个人台账","***8月个人台账"});
        model.setPublishtime("2020-06-11T12:20:36Z");
        model.setPublishuser("张晓明");
        model.setPublishuserid("2557623232");
        model.setFavorites(new String []{"32132132","321312312","23213213"});
        model.setType((byte)1);
        InsertModel insertModel = new InsertModel();
        insertModel.setPayload(new KcSearchNewModel[]{model});
        ResultVo vo = searchService.add(insertModel);
        System.out.println("=============");
        System.out.println(vo);
        return vo.toString();
    }

    /**
     * 删除一条记录
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public ResultVo  delete(@PathVariable String id){
//        String id = "123213213132";
        ResultVo vo = searchService.delete(id);
        return vo;
    }

    /**
     * 查看一条记录
     * @param id
     * @return
     */
    @RequestMapping("/view/{id}")
    public String  view(@PathVariable String id){
//        String id = "123213213132";
        String vo = searchService.view(id);
        return vo;
    }

//    @RequestMapping("/update")
//    public ResultVo  update(){    //UPDATE  or CLEAR
//        KcSearchNewModel model = new KcSearchNewModel();
//        model.setId("123213213132");
//        model.setTitle("蒲公英的约定");
//        model.setInfo("曲：周杰伦 词：方文山");
//        model.setReceiverusers(new String[] {"李四","韩非子"});
//        model.setReceiveruserids(new String[] {"323232122","231312321321","3213213213"});
//        model.setFiles(new String[] {"***5月个人台账","***7月个人台账","***8月个人台账"});
//        model.setPublishtime("2020-06-11T12:20:36Z");
//        model.setPublishuser("张晓鸿");
//        model.setPublishuserid("2557623232");
//        model.setFavorites(new String []{"32132132","321312312","23213213"});
//        model.setType((byte)1);
//
//        UpdateModel updateModel = new UpdateModel();
//        updateModel.setAction("CLEAR");
//        updateModel.setCollection("gz_kc");
//        updateModel.setData(new KcSearchNewModel[] {model});
////        String id = "123213213132";
//        ResultVo vo = searchService.update(updateModel);
//        return vo;
//    }

//    /**
//     * 新增附件 or 删除附件
//     * @return
//     */
//    @RequestMapping("/updateFiles")
//    public ResultVo  updateFiles(){
//        UpdateFilesModel model = new UpdateFilesModel();
//        Files files = new Files();
//        files.setId("123213213132");
//        files.setFiles("***6月个人台账");
//
//        model.setData(new Files[]{files});
//        model.setAction("ADD");  //新增附件
////        model.setAction("CLEAR");  //删除附件
//        model.setCollection("gz_kc");
//
//        ResultVo vo = searchService.updateFiles(model);
//        return vo;
//    }

    /**
     * 新增附件
     * @return
     */
    @RequestMapping("/addFiles")
    public ResultVo  addFiles(){
        UpdateFilesModel model = new UpdateFilesModel();
        Files files = new Files();
        files.setId("123213213132");
        files.setFiles("***6月个人台账");
        Files file2 = new Files();
        file2.setId("123213213132");
        file2.setFiles("***7月个人台账");

        model.setData(new Files[]{files,file2});
        model.setAction("ADD");  //新增附件
//        model.setAction("CLEAR");  //删除附件
        model.setCollection("gz_kc");

        ResultVo vo = searchService.updateFiles(model);
        return vo;
    }
    /**
     * 删除附件
     */
    @RequestMapping("/removeFiles")
    public ResultVo  removeFiles(){

        RemoveFile removeFile = new RemoveFile();
        removeFile.setId("123213213132");
        removeFile.setCollection("gz_kc");
        removeFile.setField("files");
        removeFile.setAction("remove");
        removeFile.setModel("single");
        removeFile.setMultiValue(true);
        removeFile.setOriginalContent("***7月个人台账");   //要删除的文件名

        ResultVo vo = searchService.removeFiles(removeFile);
        return vo;
    }

    /**
     * 邀请参与人    注：不能删除已邀请的人
     * @return
     */
    @RequestMapping("/updateReceiverUser")
    public ResultVo  updateReceiverUser(){
        updateReceiverUserModel model = new updateReceiverUserModel();
        ReceiverUser user = new ReceiverUser();
        user.setId("123213213132");
        user.setReceiveruserids("1323");
        user.setReceiverusers("王五");
        model.setData(new ReceiverUser[] {user});
        model.setAction("ADD");  //新增接收人
        model.setCollection("gz_kc");

        ResultVo vo = searchService.updateReceiverUser(model);
        return vo;
    }

    /**
     * 模糊搜索
     * @return
     */
    @RequestMapping("/search")
    public String search(){
        SearchModel model = new SearchModel();
        model.setCollection("kc");
        //高亮参数
        HighLightParam highLightParam = new HighLightParam();
        highLightParam.setField(new String[]{"title","info","receiverusers","files","publishuser"});
        model.setHighLightParam(highLightParam);
        //查询参数
        QueryParamSearch query = new QueryParamSearch();
        query.setQuery("测试");
        String queryrStr = "files:* OR title:* OR receiverusers:* OR publishuser:* OR info.*";
        String type = "type:1";
        String queryTime = "publishtime:[1999-06-01T00:00:00Z TO 2000-01-01T05:00:00Z]";
        query.setFilterQuery(new String[]{queryrStr,type,queryTime});
        model.setQueryParam(query);
        //设置返回的参数
        model.setReturnParam(new ReturnParam(new String[]{"id","title","info","receiverusers","receiveruserids","files","publishtime","publishuser","publishuserid","favorites","type"},10,0) );
        String result = searchService.search(model);
        return result;
    }

}
