package com.pingpong.ms1.service;

import com.pingpong.ms1.entity.Message;
import org.springframework.web.bind.annotation.PathVariable;

public interface MessageService {
    Message initialProcess();

    Message processMessage(Message message);

    Message sendMessage(Message message);

    Message getDataFrom(String ms,String counter);

    String generateRandomMicroservice();
}
