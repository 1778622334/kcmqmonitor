package com.gzhz.kcmqmonitor.mq;

import com.gzhz.kcmqmonitor.entity.*;
import com.gzhz.kcmqmonitor.mq.config.*;
import com.gzhz.kcmqmonitor.search.SearchService;
import com.gzhz.kcmqmonitor.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {

    @Autowired
    SearchService searchService;

    // queues是指要监听的队列的名字
    //监听 发起快传的队列
    @RabbitListener(queues = RabbitMqConfig.INITIATE_A_FAST_PASS)
    public void receiveTopic1( String string ) {
        try {
            if(StringUtils.isNotBlank(string) ){
//                System.out.println("INITIATE_A_FAST_PASS   "+string);
                JSONObject jsonObject = JSONObject.fromObject(string);
//                KcSearchNewModel model = (KcSearchNewModel) JSONObject.toBean(jsonObject, KcSearchNewModel.class);
//                log.info("【INITIATE_A_FAST_PASS 发起快传队列  监听到消息】" + model.toString());
                InsertModel insertModel = (InsertModel) JSONObject.toBean(jsonObject, InsertModel.class);
                log.info("【INITIATE_A_FAST_PASS 发起快传队列  监听到消息:】" + string);
                ResultVo vo = searchService.add(insertModel);
                log.info("【INITIATE_A_FAST_PASS 发起快传队列  消费消息结果:】" + vo.toString());
//                System.out.println(vo.toString());
            }else {
                log.info("【传入 INITIATE_A_FAST_PASS 发起快传队列的消息为空 】" );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = RabbitMqConfig.ADD_RECEIVE_USER)
    public void receiveTopic2(String string) {
        try {
            if(StringUtils.isNotBlank(string) ){
//                System.out.println("ADD_RECEIVE_USER "+string);
                JSONObject jsonObject = JSONObject.fromObject(string);
                updateReceiverUserModel model = (updateReceiverUserModel) JSONObject.toBean(jsonObject, updateReceiverUserModel.class);
                log.info("【ADD_RECEIVE_USER 添加接收人队列 监听到消息】" + model.toString());
                ResultVo vo = searchService.updateReceiverUser(model);
                log.info("【ADD_RECEIVE_USER 添加接收人队列  消费消息结果:】" + vo.toString());
            }else {
                log.info("【传入 ADD_RECEIVE_USER 添加接收人队列的消息为空 】" );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = RabbitMqConfig.ADD_FILES)
    public void receiveTopic3(String string) {
        try {
            if(StringUtils.isNotBlank(string) ){
//                System.out.println("ADD_FILES "+string);
                JSONObject jsonObject = JSONObject.fromObject(string);
                UpdateFilesModel model = (UpdateFilesModel) JSONObject.toBean(jsonObject, UpdateFilesModel.class);
                log.info("【ADD_FILES 添加附件队列 监听到消息】" + model.toString());
                ResultVo vo = searchService.updateFiles(model);
                log.info("【ADD_FILES 添加附件队列  消费消息结果:】" + vo.toString());
            }else {
                log.info("【ADD_FILES 添加附件队列 监听到消息】" + string);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = RabbitMqConfig.REMOVE_FILES)
    public void receiveTopic4(String string) {
        try {
            if(StringUtils.isNotBlank(string) ){
//                System.out.println("ADD_FILES "+string);
                JSONObject jsonObject = JSONObject.fromObject(string);
                RemoveFile model = (RemoveFile) JSONObject.toBean(jsonObject, RemoveFile.class);
                log.info("【REMOVE_FILES 添加附件队列 监听到消息】" + model.toString());
                ResultVo vo = searchService.removeFiles(model);
                log.info("【REMOVE_FILES 添加附件队列  消费消息结果:】" + vo.toString());
            }else {
                log.info("【REMOVE_FILES 添加附件队列 监听到消息】" + string);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = RabbitMqConfig.FAVORITE)
    public void receiveTopic5(String string) {

        log.info("【FAVORITE 添加关注的队列 监听到消息】" + string);
    }

}
