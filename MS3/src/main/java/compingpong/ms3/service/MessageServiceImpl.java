package compingpong.ms3.service;



import compingpong.ms3.entity.Message;
import compingpong.ms3.enumerations.Microservices;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    RestTemplate restTemplate;

    public MessageServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Message initialProcess() {

        // 1. Create the first message
        Message initialMessage = new Message(UUID.randomUUID().toString(), Microservices.MS1,"MS2",0);
        // 2. Send the message
        return sendMessage(initialMessage);
    }

    @Override
    public Message processMessage(Message message) {

        // 1. Concat the microService name to existing body
        String newBody = message.getBody().concat("-> MS2 ");
        // 2. Create the message to be sent with the new body
        Message messageToBeSent = new Message(UUID.randomUUID().toString(),Microservices.MS3, newBody, message.getCounter()+1);
        // 3. Send Message
        return sendMessage(messageToBeSent);
    }

    @Override
    public Message sendMessage(Message message) {

        // This methode has being made separately in order to have the ability to change the protocol used
        // in communication between microservices or the destination of requests
        String destinationUrl = "lb://"+"DISPATCHER"+"/process";
        return restTemplate.postForObject(destinationUrl,message,Message.class);
    }

    @Override
    public Message getDataFrom(String ms) {
        String destinationUrl = "lb://"+ms+"/getData";
        Message dataFromMs2 = restTemplate.getForObject(destinationUrl,Message.class);
        dataFromMs2.setBody(dataFromMs2.getBody() + "-> MS3");
        return dataFromMs2;
    }
}
