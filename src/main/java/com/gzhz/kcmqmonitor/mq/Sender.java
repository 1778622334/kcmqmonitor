package com.gzhz.kcmqmonitor.mq;

import com.gzhz.kcmqmonitor.entity.*;
import com.gzhz.kcmqmonitor.mq.config.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@Slf4j
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;





    /**
     * 发起快传
     */
    public void sendFastPass(String id) {

        // 第一个参数：TopicExchange名字
        // 第二个参数：Route-Key
        // 第三个参数：要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "kc.send.fastPass", id );
        log.info("【sendFastPass  已发送消息】"+ id );

    }

    /**
     * 邀请参与人
     */
    public void addReceiverUser(){
        String id = "241847";
        // 第一个参数：TopicExchange名字
        // 第二个参数：Route-Key
        // 第三个参数：要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "kc.add.receiver", id );
        log.info("【addReceiverUser 已发送消息】" + id );
    }
    /**
     * 新增附件
     */
    public void updateFile(){
        String id = "241847";
        // 第一个参数：TopicExchange名字
        // 第二个参数：Route-Key
        // 第三个参数：要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "kc.update.files", id );
        log.info("【updateFile 已发送消息】" + id );
    }

    /*public void removeFiles(){
        RemoveFile removeFile = new RemoveFile();
        removeFile.setId("123213213132");
        removeFile.setCollection("gz_kc");
        removeFile.setField("files");
        removeFile.setAction("remove");
        removeFile.setModel("single");
        removeFile.setMultiValue(true);
        removeFile.setOriginalContent("***7月个人台账");   //要删除的文件名
        log.info("【addReceiverUser 已发送消息】");
        // 第一个参数：TopicExchange名字
        // 第二个参数：Route-Key
        // 第三个参数：要发送的内容
        String jsonStr = JSONObject.fromObject(removeFile).toString();
        this.amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "kc.remove.files", jsonStr );

    }*/


    public void updateFavorites(){
        String id = "241847";
        // 第一个参数：TopicExchange名字
        // 第二个参数：Route-Key
        // 第三个参数：要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "kc.update.favorite", id );
        log.info("【updateFavorites 已发送消息】" + id );
    }

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
//        InsertModel insertModel = new InsertModel();
//        insertModel.setPayload(new KcSearchNewModel[]{model});
//        String id = "3";//"241847";

}
