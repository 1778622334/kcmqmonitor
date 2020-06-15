package com.gzhz.kcmqmonitor.controller;

import com.gzhz.kcmqmonitor.entity.InsertModel;
import com.gzhz.kcmqmonitor.entity.KcAttList;
import com.gzhz.kcmqmonitor.entity.KcSearchNewModel;
import com.gzhz.kcmqmonitor.search.SearchService;
import com.gzhz.kcmqmonitor.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    SearchService searchService;

    @RequestMapping("/add")
    public ResultVo  add(){
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
        return vo;
    }

    @RequestMapping("/delete/{id}")
    public ResultVo  delete(@PathVariable String id){
//        String id = "123213213132";
        ResultVo vo = searchService.delete(id);
        return vo;
    }

//    public Str

}
