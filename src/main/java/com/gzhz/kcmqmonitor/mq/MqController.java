package com.gzhz.kcmqmonitor.mq;

import com.gzhz.kcmqmonitor.mq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqController {

    @Autowired
    private Sender sender;

    /**
     * 发起快传
     * @return
     */
    @GetMapping("/sendFastPass/{id}")
    public Object sendTopic(@PathVariable String id) {
        sender.sendFastPass(id);
        return "sendFastPass ： ok";
    }

    @GetMapping("/addReceiverUser")
    public Object addReceiverUser() {
        sender.addReceiverUser();
        return "addReceiverUser ： ok";
    }
    @GetMapping("/updateFile")
    public Object updateFile() {
        sender.updateFile();
        return "updateFile : ok";
    }
    @GetMapping("/removeFiles")
    public Object removeFiles() {
//        sender.removeFiles();
        return "removeFiles : ok  暂时没有使用";
    }
    @GetMapping("/updateFavorites")
    public Object updateFavorites() {
        sender.updateFavorites();
        return "removeFiles : ok";
    }
    @GetMapping("/deleteFastPass")
    public Object deleteFastPass() {
        sender.deleteFastPass();
        return "deleteFastPass 彻底删除快传 : ok";
    }

    @GetMapping("/updateFastPassState")
    public Object updateFastPassState() {
        sender.updateFastPassState();
        return "updateFastPassState 逻辑上删除快传 : ok";
    }
}
