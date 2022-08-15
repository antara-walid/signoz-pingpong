package com.pingpong.ms2.service;


import com.pingpong.ms2.entity.Message;
import com.pingpong.ms2.enumerations.Microservices;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
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
        Message messageToBeSent = new Message(UUID.randomUUID().toString(),Microservices.MS2, newBody, message.getCounter()+1);
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
    public Message getDataFrom(String ms, String counterString) {

        Integer counter;
        Map<String, String> params = new HashMap<>();

        if (counterString == null) {
            params.put("counter", "0");
            counter = 0;
            params.put("ms", ms);
        }else{
            counter = Integer.parseInt(counterString);
            counter++;
            params.put("counter", counter.toString());
        }

        if (ms != null) {
            String nextDestination = generateRandomMicroservice();
            params.put("ms", nextDestination);

        }

        if (counter < 5) {
            String destinationUrl = "lb://" + ms + "/getDataFrom?ms={ms}&counter={counter}";
            Message dataFromMs = restTemplate.getForObject(destinationUrl, Message.class, params.get("ms"), params.get("counter"));
            dataFromMs.setBody(dataFromMs.getBody() + "-> MS2");
            return dataFromMs;
        } else {
            Message message = new Message(UUID.randomUUID().toString(), Microservices.MS2, "MS2", 0);
            return message;
        }

    }

    @Override
    public String generateRandomMicroservice() {
        int randomNumber2 = (int) (Math.random() *3);
        Microservices nextDestination = Microservices.values()[randomNumber2];
        return nextDestination.toString();
    }
}
