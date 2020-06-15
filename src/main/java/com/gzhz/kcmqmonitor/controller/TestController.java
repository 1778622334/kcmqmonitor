package com.gzhz.kcmqmonitor.controller;

import com.gzhz.kcmqmonitor.entity.KcAttList;
import com.gzhz.kcmqmonitor.entity.KcSearchNewModel;
import com.gzhz.kcmqmonitor.model.SearchInfoBoxModel;
import com.gzhz.kcmqmonitor.services.impl.KcAttListServiceImpl;
import com.gzhz.kcmqmonitor.services.impl.KcInfoBoxServiceImpl;
import com.gzhz.kcmqmonitor.utils.DateTimeUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private KcAttListServiceImpl kcAttListService;

    @Autowired
    private KcInfoBoxServiceImpl kcInfoBoxService;

    @RequestMapping("/test")
    public String testPage(){

        System.out.println("whh");
        return "test";
    }



    @RequestMapping("/select/attList")
    public String attList(){

        List<KcAttList> list = kcAttListService.setlectBysessionIdAndGuid("si_5edf07d8c2e88");
        for (KcAttList kc: list) {
            System.out.println(kc);
        }
        return "test_attList";
    }

    @RequestMapping("/select/infoBox")
    public String infoBox(@RequestParam String startStr, @RequestParam String endStr){

        try {
//            String startStr = "2015-01-01 00:00:00";
//            String endStr = "2020-06-12 00:00:00";
//            19年 6月1号到5号数据太多，没导  6月1号—7月1号的数据

//            String startStr = "2019-10-01 00:00:00";
//            String endStr = "2020-06-12 23:59:59";

            long start = DateTimeUtils.strToSqlLongDate(startStr).getTime()/1000;
            System.out.println(start);

            long end = DateTimeUtils.strToSqlLongDate(endStr).getTime()/1000;
            System.out.println(end);

            SearchInfoBoxModel searchInfoBoxModel = new SearchInfoBoxModel(new Integer((int)start),
                    new Integer((int)end),0,0,0L,0L,(byte) 0);

            List<KcSearchNewModel> list = kcInfoBoxService.selectByPublishTime(searchInfoBoxModel);
            System.out.println(list.size());
            String kcSearchModelListJsonStr = JSONArray.fromObject(list).toString();
            System.out.println(kcSearchModelListJsonStr);

            //1. 文件夹的路径  文件名
            String directory = "D:\\kc_json_file";
            String filename = "kc_"+ startStr.substring(0,10)+"_"+endStr.substring(0,10)+ ".txt";
            //2.  创建文件夹对象     创建文件对象
            File file = new File(directory);
            //如果文件夹不存在  就创建一个空的文件夹
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(directory, filename);
            //如果文件不存在  就创建一个空的文件
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //3.写入数据
            //创建文件字节输出流
            FileOutputStream fos = new FileOutputStream(directory + "\\" + filename);
            //开始写
            String str = kcSearchModelListJsonStr;
            byte[] bytes = str.getBytes();
            //将byte数组中的所有数据全部写入
            fos.write(bytes);
            //关闭流
            fos.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return "test_infoBox";
    }

//    @RequestMapping("/insert/kcSearchNewModes")
//    public String insertKcSearchNewModes(){
//        try {
//            //读取json文件中的数据保存到另一个服务器
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return "insert_kcSearchNewModes";
//    }

}
