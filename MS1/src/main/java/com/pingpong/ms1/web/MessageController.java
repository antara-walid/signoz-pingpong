package com.pingpong.ms1.web;

import com.pingpong.ms1.entity.Message;
import com.pingpong.ms1.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/process")
    Message initialProcess()
    {
        return messageService.initialProcess();
    }

    @PostMapping("/process")
    Message processMessage(@RequestBody Message message)
    {
        return messageService.processMessage(message);
    }

    @GetMapping("/getDataFrom{ms}")
    Message getDataFromMS3(@PathVariable String ms)
    {

        return messageService.getDataFrom(ms);
    }
}
