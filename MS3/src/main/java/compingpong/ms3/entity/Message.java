package compingpong.ms3.entity;


import compingpong.ms3.enumerations.Microservices;
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
