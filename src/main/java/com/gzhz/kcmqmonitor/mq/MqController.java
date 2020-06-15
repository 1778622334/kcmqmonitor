package com.gzhz.kcmqmonitor.mq;

import com.gzhz.kcmqmonitor.mq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqController {

    @Autowired
    private Sender sender;

    @GetMapping("/sendTopic")
    public Object sendTopic() {
        sender.sendTopic();
        return "ok";
    }
}
