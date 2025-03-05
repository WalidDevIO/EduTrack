package fr.elite.messagesapi.entities;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Message {

    private ObjectId id;

    private String text;

    private Boolean readed;

    private Integer student;    

}
