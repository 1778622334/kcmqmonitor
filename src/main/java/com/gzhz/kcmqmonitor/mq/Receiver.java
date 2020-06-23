package com.gzhz.kcmqmonitor.mq;

import com.gzhz.kcmqmonitor.entity.*;
import com.gzhz.kcmqmonitor.mq.config.*;
import com.gzhz.kcmqmonitor.search.SearchService;
import com.gzhz.kcmqmonitor.services.impl.KcInfoBoxServiceImpl;
import com.gzhz.kcmqmonitor.vo.ResultVo;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class Receiver   {

    @Autowired
    SearchService searchService;
    @Autowired
    KcInfoBoxServiceImpl kcInfoBoxService;

    // queues是指要监听的队列的名字
    //监听 发起快传的队列
    @RabbitListener(queues = RabbitMqConfig.INITIATE_A_FAST_PASS)
    public void receiveTopic1( Message message ,Channel channel) {
        try {
            String id = new String (message.getBody());
            if(StringUtils.isNotBlank(id) ){
//                System.out.println("INITIATE_A_FAST_PASS   "+id);
                KcSearchNewModel newModel = kcInfoBoxService.selectByPrimaryKey(Integer.valueOf(id));

                InsertModel insertModel = new InsertModel();
                insertModel.setPayload(new KcSearchNewModel[]{newModel});
                log.info("【INITIATE_A_FAST_PASS 发起快传队列  监听到消息:】" + id);
                ResultVo vo = searchService.add(insertModel);
                log.info("【INITIATE_A_FAST_PASS 发起快传队列  消费消息结果:】" + vo.toString());
            }else {
                log.info("【传入 INITIATE_A_FAST_PASS 发起快传队列的消息为空 】" );
            }
            //确认接收到消息，
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }catch (Exception e){
            e.printStackTrace();
            log.info("【传入 INITIATE_A_FAST_PASS 队列的消息 数据处理出错 】"+new String (message.getBody()) );
            try {
                //如果newModel为空，表示php后端还没有将数据保存到数据库，则此消息（id）需要重新接收
//                    channel.basicRecover(false);  //true表示重新投递的消息可以被其他消费者消费，false表示只补发给当前消费者
                //OR
                //消息不正确，或者php还没有将该id的快传保存在数据库中，所以
                //拒绝处理该消息,ack返回false，清楚消息，返回true，重新回到队列
                //重发消息很可能还是处理失败，
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            //TODO 做补偿处理

        }
    }

    //新增邀请人
    @RabbitListener(queues = RabbitMqConfig.ADD_RECEIVE_USER)
    public void receiveTopic2(Message message ,Channel channel) {
        try {
            String id = new String (message.getBody());
            if(StringUtils.isNotBlank(id) ){
                updateReceiverUserModel model =  kcInfoBoxService.selectReceiverUserByPrimaryKey(Integer.valueOf(id));
                log.info("【ADD_RECEIVE_USER 添加接收人队列 监听到消息】" + model.toString());
                ResultVo vo = searchService.updateReceiverUser(model);
                log.info("【ADD_RECEIVE_USER 添加接收人队列  消费消息结果:】" + vo.toString());
            }else {
                log.info("【传入 ADD_RECEIVE_USER 添加接收人队列的消息为空 】" );
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }catch (Exception e){
            e.printStackTrace();
            log.info("【传入 ADD_RECEIVE_USER 队列的消息 数据处理出错 】"+new String (message.getBody()) );
        }

    }

    //更新附件
    @RabbitListener(queues = RabbitMqConfig.UPDATE_FILES)
    public void receiveTopic3(Message message,Channel channel) {
        try {
            String id = new String(message.getBody());
            if(StringUtils.isNotBlank(id) ){
                UpdateFilesModel model = kcInfoBoxService.selectFilesById(Integer.valueOf(id));
                log.info("【UPDATE_FILES 更新附件队列 监听到消息】" + model.toString());
                ResultVo vo = searchService.updateFiles(model);
                log.info("【UPDATE_FILES 更新附件队列  消费消息结果:】" + vo.toString());
            }else {
                log.info("【UPDATE_FILES 更新附件队列 监听到消息为空】" );
            }
            //确认接收到消息，
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }catch (Exception e){
            e.printStackTrace();
            log.info("【UPDATE_FILES 更新附件队列 处理消息出错】"+ new String(message.getBody()) );
        }

    }

//    //删除附件
//    @RabbitListener(queues = RabbitMqConfig.REMOVE_FILES)
//    public void receiveTopic4(String id) {
//        try {
//            if(StringUtils.isNotBlank(id) ){
////                System.out.println("ADD_FILES "+string);
//                JSONObject jsonObject = JSONObject.fromObject(id);
//                RemoveFile model = (RemoveFile) JSONObject.toBean(jsonObject, RemoveFile.class);
//                log.info("【REMOVE_FILES 删除附件队列 监听到消息】" + model.toString());
//                ResultVo vo = searchService.removeFiles(model);
//                log.info("【REMOVE_FILES 删除附件队列  消费消息结果:】" + vo.toString());
//            }else {
//                log.info("【REMOVE_FILES 删除附件队列 监听到消息为空】" );
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

    @RabbitListener(queues = RabbitMqConfig.FAVORITE)
    public void receiveTopic5(Message message,Channel channel) {
        try {
            String id = new String(message.getBody());
            if(StringUtils.isNotBlank(id) ){
                updateFavoritesModel model = kcInfoBoxService.selectFavoritesById(Integer.valueOf(id));
                log.info("【FAVORITE 添加关注的队列 监听到消息】" + model.toString());
                ResultVo vo = searchService.selectFavorites(model);
                log.info("【FAVORITE 添加关注的队列  消费消息结果:】" + vo.toString());
            }else {
                log.info("【FAVORITE 添加关注的队列 监听到消息为空】" );
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("【FAVORITE 添加关注的队列 处理消息出错】" +new String(message.getBody()) );
        }

    }

}
