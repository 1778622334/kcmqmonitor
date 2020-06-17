package com.gzhz.kcmqmonitor.mq;

import com.gzhz.kcmqmonitor.mq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqController {

    @Autowired
    private Sender sender;

    /**
     * 发起快传
     * @return
     */
    @GetMapping("/sendFastPass")
    public Object sendTopic() {
        sender.sendFastPass();
        return "sendFastPass ： ok";
    }

    @GetMapping("/addReceiverUser")
    public Object addReceiverUser() {
        sender.addReceiverUser();
        return "addReceiverUser ： ok";
    }
    @GetMapping("/addFiles")
    public Object addFiles() {
        sender.addFile();
        return "addFiles : ok";
    }
    @GetMapping("/removeFiles")
    public Object removeFiles() {
        sender.removeFiles();
        return "removeFiles : ok";
    }
}
