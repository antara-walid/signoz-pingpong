package com.pingpong.ms1.entity;

import com.pingpong.ms1.enumerations.Microservices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String id;
    private Microservices source;
    private String body;
    private int counter;
}
