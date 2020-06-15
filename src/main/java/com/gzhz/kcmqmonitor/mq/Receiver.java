package com.gzhz.kcmqmonitor.mq;

import com.gzhz.kcmqmonitor.entity.KcSearchNewModel;
import com.gzhz.kcmqmonitor.mq.config.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {
    // queues是指要监听的队列的名字
    //监听发起快传的队列
    @RabbitListener(queues = RabbitMqConfig.INITIATE_A_FAST_PASS)
    public void receiveTopic1( String string ) {

        try {
            if(StringUtils.isNotBlank(string) ){
                System.out.println("INITIATE_A_FAST_PASS   "+string);
                JSONObject jsonObject = JSONObject.fromObject(string);
                KcSearchNewModel model = (KcSearchNewModel) JSONObject.toBean(jsonObject, KcSearchNewModel.class);
                log.info("【INITIATE_A_FAST_PASS 发起快传队列  监听到消息】" + model.toString());
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
                System.out.println("ADD_RECEIVE_USER   "+string);
                JSONObject jsonObject = JSONObject.fromObject(string);
                KcSearchNewModel model = (KcSearchNewModel) JSONObject.toBean(jsonObject, KcSearchNewModel.class);
                log.info("【ADD_RECEIVE_USER 添加接收人队列 监听到消息】" + model.toString());
            }else {
                log.info("【传入 ADD_RECEIVE_USER 添加接收人队列的消息为空 】" );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = RabbitMqConfig.ADD_FILES)
    public void receiveTopic3(String string) {

        log.info("【ADD_FILES 添加附件队列 监听到消息】" + string);
    }

    @RabbitListener(queues = RabbitMqConfig.FAVORITE)
    public void receiveTopic4(String string) {

        log.info("【FAVORITE 添加关注的队列 监听到消息】" + string);
    }

}
