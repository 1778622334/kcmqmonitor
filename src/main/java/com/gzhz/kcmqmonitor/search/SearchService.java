package com.gzhz.kcmqmonitor.search;

import com.gzhz.kcmqmonitor.entity.InsertModel;
import com.gzhz.kcmqmonitor.utils.HttpRequestUtils;
import com.gzhz.kcmqmonitor.utils.HttpUtils;
import com.gzhz.kcmqmonitor.vo.ResultVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

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

    /*public String getRequestUrl(String ){

    }*/

    public ResultVo add (InsertModel model){
//        String result = HttpUtils.sendPost(url+":"+port0+"/admin/v2/api/profiler",JSONObject.fromObject(model));
//        System.out.println("新增的结果："+result);
//        return null;

        String result = HttpRequestUtils.doPost(url+":"+port0+"/admin/v2/api/profiler",null,JSONObject.fromObject(model).toString());
        System.out.println("新增的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        ResultVo vo = (ResultVo)JSONObject.toBean(jsonObject,ResultVo.class);
        return vo;
    }


    public ResultVo delete (String id){
        String result = HttpRequestUtils.doDelete(url+":"+port0+"/admin/v2/api/index/gz_kc/"+id,null,null);
        System.out.println("删除的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        ResultVo vo = (ResultVo)JSONObject.toBean(jsonObject,ResultVo.class);
        return vo;
    }

}
