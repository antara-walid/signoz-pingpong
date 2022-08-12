package com.pingpong.ms2.service;


import com.pingpong.ms2.entity.Message;

public interface MessageService {
    Message initialProcess();

    Message processMessage(Message message);

    Message sendMessage(Message message);

    Message getDataFrom(String ms);
}
