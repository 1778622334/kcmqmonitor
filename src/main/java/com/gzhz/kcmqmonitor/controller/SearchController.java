package com.gzhz.kcmqmonitor.controller;

import com.gzhz.kcmqmonitor.entity.*;
import com.gzhz.kcmqmonitor.search.SearchService;
import com.gzhz.kcmqmonitor.utils.PageHelper;
import com.gzhz.kcmqmonitor.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

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
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultVo  add(@RequestBody KcSearchNewModel model){
//        KcSearchNewModel model = new KcSearchNewModel();
//        model.setId("123213213132");
//        model.setTitle("蒲公英的约定");
//        model.setInfo("曲：周杰伦 词：方文山");
//        model.setReceiverusers(new String[] {"张三","李四","韩非子"});
//        model.setReceiveruserids(new String[] {"323232122","231312321321","3213213213"});
//        model.setFiles(new String[] {"***5月个人台账","***7月个人台账","***8月个人台账"});
//        model.setPublishtime("2020-06-11T12:20:36Z");
//        model.setPublishuser("张晓明");
//        model.setPublishuserid("2557623232");
//        model.setFavorites(new String []{"32132132","321312312","23213213"});
//        model.setType((byte)1);
        InsertModel insertModel = new InsertModel();
        insertModel.setPayload(new KcSearchNewModel[]{model});
        ResultVo vo = searchService.add(insertModel);
        System.out.println("=============");
        System.out.println(vo.toString());
        vo.setResult(null);
        return vo ;
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
        System.out.println(vo.toString());
        return vo;
    }

    /**
     * 查看一条记录
     * @param id
     * @return
     */
    @RequestMapping("/view/{id}")
    public KcSearchNewModel  view(@PathVariable String id){
//        String id = "123213213132";
        KcSearchNewModel model = searchService.view(id);
        return model;
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
    public ResultVo  addFiles(@RequestBody UpdateFilesModel model){
//        UpdateFilesModel model = new UpdateFilesModel();
//        Files files = new Files();
//        files.setId("123213213132");
//        files.setFiles("***6月个人台账");
//        Files file2 = new Files();
//        file2.setId("123213213132");
//        file2.setFiles("***7月个人台账");
//
//        model.setData(new Files[]{files,file2});
//        model.setAction("ADD");  //新增附件
////        model.setAction("CLEAR");  //删除附件
//        model.setCollection("gz_kc");
        ResultVo vo = new ResultVo();
        if(!model.getAction().equals("ADD")){
            vo.setStatus(-1);
            vo.setMessage("传入的新增动作标识错误");
            return vo;
        }
        vo = searchService.updateFiles(model);
        return vo;
    }
    /**
     * 删除附件
     */
    @RequestMapping("/removeFiles")
    public ResultVo  removeFiles(@RequestBody RemoveFile removeFile){

//        RemoveFile removeFile = new RemoveFile();
//        removeFile.setId("123213213132");
//        removeFile.setCollection("gz_kc");
//        removeFile.setField("files");
//        removeFile.setAction("remove");
//        removeFile.setModel("single");
//        removeFile.setMultiValue(true);
//        removeFile.setOriginalContent("***7月个人台账");   //要删除的文件名
        ResultVo vo = new ResultVo();
        if(!removeFile.getAction().equals("remove")){
            vo.setStatus(-1);
            vo.setMessage("传入的删除动作标识错误");
            return vo;
        }
        vo = searchService.removeFiles(removeFile);
        return vo;
    }

    /**
     * 邀请参与人    注：不能删除已邀请的人
     * @return
     */
    @RequestMapping("/updateReceiverUser")
    public ResultVo  updateReceiverUser(@RequestBody updateReceiverUserModel model){
//        updateReceiverUserModel model = new updateReceiverUserModel();
//        ReceiverUser user = new ReceiverUser();
//        user.setId("123213213132");
//        user.setReceiveruserids("1323");
//        user.setReceiverusers("王五");
//        model.setData(new ReceiverUser[] {user});
//        model.setAction("ADD");  //新增接收人
//        model.setCollection("gz_kc");
        ResultVo vo = new ResultVo();
        if(!model.getAction().equals("ADD")){
           vo.setStatus(-1);
           vo.setMessage("传入的新增动作标识错误");
           return vo;
        }
        vo = searchService.updateReceiverUser(model);
        return vo;
    }

    /**
     * 模糊搜索
     * @return
     * z注解：@CrossOrigin  是为了解决跨越请求
     */

    @CrossOrigin
    @RequestMapping("kc/search")
    public Map<String,Object> search(@RequestBody SearchBlurryModel model ){
        Map<String,Object> map = new HashMap<>();
//        ResultVo vo = new ResultVo();
        /*SearchModel model = new SearchModel();
        model.setCollection("kc");
        //高亮参数
        HighLightParam highLightParam = new HighLightParam();
        highLightParam.setField(new String[]{"title","receiverusers","files","publishuser"});  *//*"info",   后面考虑加不加*//*
        model.setHighLightParam(highLightParam);
        //查询参数
        QueryParamSearch query = new QueryParamSearch();
        query.setQuery("测试");
        String queryrStr = "files:* OR title:* OR receiverusers:* OR publishuser:* ";  *//*OR info.*   后面考虑要不要搜索内容*//*
        String type = "type:1";
        String queryTime = "publishtime:[2020-06-01T00:00:00Z TO 2020-06-12T05:00:00Z]";
        query.setFilterQuery(new String[]{queryrStr,type,queryTime});
        model.setQueryParam(query);
        */
        //设置返回的参数
//        model.setReturnParam(new ReturnParam(new String[]{"id","title","info","receiverusers","receiveruserids","files","publishtime","publishuser","publishuserid","favorites","type"},10,0) );

//        if(){
//
//        }
        map = searchService.search(model);
//        PageHelper pageHelper = new PageHelper();
//        map.put("str","chenggong");
        return map;
    }

}
