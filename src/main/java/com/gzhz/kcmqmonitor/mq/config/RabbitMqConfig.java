package com.gzhz.kcmqmonitor.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {


    /**
     * Topic模式
     * 消息类型
     * 	1：发起快传 （包含存草稿箱）
     * 	2：删除快传 （包含恢复、删除）
     * 	3：彻底删除
     * 	3：邀请参与人
     * 	4：添加文件
     * 	//5：文件排序
     * 	6：关注快传（包含取消关注）
     * 	7：转发快传
     * @return
     */
    public static final String INITIATE_A_FAST_PASS = "send.fastPass"; //发起快传
    public static final String ADD_RECEIVE_USER = "add.receiver";  //邀请参与人
    public static final String ADD_FILES = "add.files";  //新增附件
    public static final String REMOVE_FILES = "remove.files";  //删除附件
    public static final String FAVORITE = "favorite.fastPass";  //关注快传（包含取消关注）


    public static final String TOPIC_EXCHANGE = "topic.exchange";
    @Bean
    public Queue INITIATE_A_FAST_PASS() {
        return new Queue(INITIATE_A_FAST_PASS);
    }
    @Bean
    public Queue ADD_RECEIVE_USER() {
        return new Queue(ADD_RECEIVE_USER);
    }
    @Bean
    public Queue ADD_FILES() {
        return new Queue(ADD_FILES);
    }
    @Bean
    public Queue REMOVE_FILES() {
        return new Queue(REMOVE_FILES);
    }
    @Bean
    public Queue FAVORITE() {
        return new Queue(FAVORITE);
    }

    //交换机 一个应该够用了
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    //绑定发起快传的队列
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(INITIATE_A_FAST_PASS()).to(topicExchange()).with("kc.send.fastPass.#");
    }
    //绑定邀请参与人的队列
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(ADD_RECEIVE_USER()).to(topicExchange()).with("kc.add.receiver.#");
    }

    //绑定新增附件的队列
    @Bean
    public Binding topicBinding3() {
        return BindingBuilder.bind(ADD_FILES()).to(topicExchange()).with("kc.add.files.#");
    }
    //绑定删除附件的队列
    @Bean
    public Binding topicBinding4() {
        return BindingBuilder.bind(REMOVE_FILES()).to(topicExchange()).with("kc.remove.files.#");
    }
    //绑定关注快传的队列
    @Bean
    public Binding topicBinding5() {
        return BindingBuilder.bind(FAVORITE()).to(topicExchange()).with("kc.favorite.#");
    }


}
