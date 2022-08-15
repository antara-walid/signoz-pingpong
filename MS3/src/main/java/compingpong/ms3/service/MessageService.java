package compingpong.ms3.service;


import compingpong.ms3.entity.Message;

public interface MessageService {
    Message initialProcess();

    Message processMessage(Message message);

    Message sendMessage(Message message);

    Message getDataFrom(String ms,String counter);

    String generateRandomMicroservice();
}
