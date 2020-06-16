package com.gzhz.kcmqmonitor.search;

import com.gzhz.kcmqmonitor.entity.*;
import com.gzhz.kcmqmonitor.utils.HttpRequestUtils;
import com.gzhz.kcmqmonitor.vo.ResultVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private String Bearer  = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcnlEYXRlIjoxNjQwOTY2NDAwMDAwLCJ3b3Jrc3BhY2UiOiJneiIsInVzZXJfbmFtZSI6Imd6RGVtbyIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsInRydXN0Il0sImNvbGxlY3Rpb24iOm51bGwsImV4cCI6MTU5Mjk1NTQwOSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9ST0xFX0NMSUVOVF9ERUxFVEUiLCJST0xFX1JPTEVfVVNFUl9BQ0NFU1NfREVMIiwiUk9MRV9ST0xFX0NPTExFQ1RJT05fU0NIRSIsIlJPTEVfUk9MRV9TRUFSQ0hfSk9CIiwiUk9MRV9ST0xFX0RFTEVURV9SRVNPVVJDRSIsIlJPTEVfUk9MRV9BRERfUkVTT1VSQ0UiLCJST0xFX1JPTEVfQ0xJRU5UX1VQREFURSIsIlJPTEVfUk9MRV9ERUxFVEVfSk9CIiwiUk9MRV9ST0xFX1JFU09VUkNFX0FERCIsIlJPTEVfUk9MRV9DUkVBVEVfQ09ORklHX1MiLCJST0xFX1JPTEVfQUREX0pPQiIsIlJPTEVfUk9MRV9VUERBVEVfUkVTT1VSQ0UiLCJST0xFX1JPTEVfQ0xJRU5UX0FERCIsIlJPTEVfUk9MRV9DTElFTlRfQUxMIiwiUk9MRV9ST0xFX0NPTExFQ1RJT05fR0VUIiwiUk9MRV9ST0xFX0dFVF9ET0NVTUVOVCIsIlJPTEVfUk9MRV9BSV9UQUdHSU5HIiwiUk9MRV9ST0xFX0VYVFJBQ1RfVEFHR0lORyIsIlJPTEVfUk9MRV9VU0VSX1VQREFURSIsIlJPTEVfUk9MRV9TRUFSQ0giLCJST0xFX1JPTEVfVVBMT0FEX1JFU09VUkNFIiwiUk9MRV9ST0xFX1JFU09VUkNFX0RFTEVURSIsIlJPTEVfUk9MRV9DT0xMRUNUSU9OX0FERCIsIlJPTEVfUk9MRV9VU0VSX0FERCIsIlJPTEVfUk9MRV9VU0VSX0FMTCIsIlJPTEVfUk9MRV9DT0xMRUNUSU9OX0RFTEUiLCJST0xFX1JPTEVfRE9XTkxPQURfUkVTT1VSIiwiUk9MRV9ST0xFX0FERF9GSUVMRCIsIlJPTEVfUk9MRV9BRE1JTiIsIlJPTEVfUk9MRV9UQUdHSU5HIiwiUk9MRV9ST0xFX0RFTEVURV9RVUVSWV9ETyIsIlJPTEVfUk9MRV9ERUxFVEVfRE9DVU1FTlQiLCJST0xFX1JPTEVfQUREX0NPUFlfRklFTEQiLCJST0xFX1JPTEVfVVNFUl9BQ0NFU1NfQVNTIiwiUk9MRV9ST0xFX0FERF9PVEhFUl9UQUciLCJST0xFX1JPTEVfT1BFUkFURV9UQUciLCJST0xFX1JPTEVfUkVTT1VSQ0VfR0VUIiwiUk9MRV9ST0xFX1BVUkdFX0ZJRUxEIiwiUk9MRV9ST0xFX01BTkFHRV9KT0IiLCJST0xFX1JPTEVfREVMRVRFX0NPTkZJR19TIiwiUk9MRV9ST0xFX1VTRVJfQUNDRVNTX0dFVCIsIlJPTEVfUk9MRV9SRUNPTU1FTkRfRE9DVU0iLCJST0xFX1JPTEVfRVhUUkFDVF9USU1FIiwiUk9MRV9ST0xFX0FJX1RBR0dJTkdfQUNDVSIsIlJPTEVfUk9MRV9DTElFTlRfR0VUIiwiUk9MRV9ST0xFX0VYVFJBQ1RfRU5USVRZIiwiUk9MRV9ST0xFX1VQREFURV9ET0NVTUVOVCIsIlJPTEVfUk9MRV9ERUxFVEVfRklFTEQiLCJST0xFX1JPTEVfQ09MTEVDVF9BQ1RJT04iLCJST0xFX1JPTEVfREVMRVRFX0NPTExFQ1RJIiwiUk9MRV9ST0xFX1VTRVJfREVMRVRFIiwiUk9MRV9ST0xFX01BSU5UQUlOX0NPTExFQyIsIlJPTEVfUk9MRV9TWVNBRE1JTiIsIlJPTEVfUk9MRV9HRVRfUkVTT1VSQ0VfQlkiLCJST0xFX1JPTEVfR0VUX0xBQkVMIiwiUk9MRV9ST0xFX0lOREVYSU5HIiwiUk9MRV9ST0xFX0RFTEVURV9JRFNfRE9DVSIsIlJPTEVfUk9MRV9SRVNPVVJDRV9VUERBVEUiLCJST0xFX1JPTEVfQ1JFQVRFX0NPTExFQ1RJIiwiUk9MRV9BRE1JTiIsIlJPTEVfUk9MRV9VU0VSX0dFVCIsIlJPTEVfUk9MRV9DUkVBVEVfQ09SUFVTIl0sImp0aSI6ImM0MmYzOTczLWQ5YzEtNDk1Yy05MDFkLWMxYTk0MjA3ZjA2OSIsImNsaWVudF9pZCI6ImQxNjk3OWNiYTk2ZDRmYmI5MTRiOGFlYzFiNGJkODEyIn0.Qymn-rLWMSaHCC5tu9qMHAhrnECQguOirG2tdlDVPko";

    /**
     * 新增一条记录
     * @param model  新增对象
     * @return
     */
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
    public String view (String id){
        String result = HttpRequestUtils.doPost(url+":"+port1+"/v2/api/document/"+id,Bearer,getViewStr());
        System.out.println("查询的的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
//        ResultVo vo = (ResultVo)JSONObject.toBean(jsonObject,ResultVo.class);
        return jsonObject.toString();
    }

    /**
     * 现在只用来新增附件
     * @param model
     * @return
     */
    public ResultVo updateFiles (UpdateFilesModel model){
        System.out.println("更新时传入的json字符串"+JSONObject.fromObject(model).toString());
        String result = HttpRequestUtils.doPut(url+":"+port0+"/admin/v2/api/index",Bearer,JSONObject.fromObject(model).toString());
        System.out.println("更新的的结果："+result);
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
        System.out.println("删除的的结果："+result);
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
        System.out.println("新增接收人时传入的json字符串"+JSONObject.fromObject(model).toString());
        String result = HttpRequestUtils.doPut(url+":"+port0+"/admin/v2/api/index",Bearer,JSONObject.fromObject(model).toString());
        System.out.println("新增的的结果："+result);
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
        model.setReturnParam(new ReturnParam(new String[]{"id","title","info","receiverusers","receiveruserids","files","publishtime","publishuser","favorites","type"},1,0) );
        String jsonStr = JSONObject.fromObject(model).toString();
        System.out.println("jsonStr========="+jsonStr);
        return jsonStr;
    }

    /**
     * 根据 标题title  内容info  附件名files   接收人名字receiverUsers   发起人名字publishUser
     * @param model
     * @return
     */
    public String search (SearchModel model){
        List<KcSearchNewModel> returnList = new ArrayList<>();
        System.out.println("新增接收人时传入的json字符串"+JSONObject.fromObject(model).toString());
        String result = HttpRequestUtils.doPost(url+":"+port1+"/v2/api/search",Bearer,JSONObject.fromObject(model).toString());
        System.out.println("新增的的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
//        ResultVo vo = (ResultVo)JSONObject.toBean(jsonObject,ResultVo.class);
        Map<String,Object> response = ( Map<String,Object>)jsonObject.get("response");

        List<Map<String,List>> resultList = ( List<Map<String,List>> )response.get("docs");
        for (Map<String,List> map: resultList) {
            KcSearchNewModel kcModel = new KcSearchNewModel();
            Object[] s  = map.get("favorites").toArray();
            JSONObject jsonStr = JSONObject.fromObject(map);
            kcModel = (KcSearchNewModel) JSONObject.toBean(jsonStr,KcSearchNewModel.class);
            returnList.add(kcModel);
        }
        JSONArray jsonObjectList = JSONArray.fromObject(returnList);
        System.out.println(jsonObjectList.toString());
        return jsonObjectList.toString();

    }

}
