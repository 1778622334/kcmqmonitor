package com.gzhz.kcmqmonitor.mq;

import com.gzhz.kcmqmonitor.mq.config.*;
import com.gzhz.kcmqmonitor.entity.KcSearchNewModel;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendTopic() {
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
        log.info("【sendTopic已发送消息】");
        // 第一个参数：TopicExchange名字
        // 第二个参数：Route-Key
        // 第三个参数：要发送的内容
        String jsonStr = JSONObject.fromObject(model).toString();
        this.amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "kc.send", jsonStr );
        this.amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "kc.add.receiver", jsonStr);
    }

}
