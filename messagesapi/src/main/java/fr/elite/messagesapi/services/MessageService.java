package fr.elite.messagesapi.services;

import fr.elite.messagesapi.entities.Message;
import fr.elite.messagesapi.exceptions.APIException;

import java.util.List;

public interface MessageService {

    Message getMessageById(String id) throws APIException;

    List<Message> getMessagesByStudent(int studentNumber);

    Message createMessage(String jsonBody) throws APIException;

    Message updateMessage(String id, String jsonBody) throws APIException;

    void deleteMessage(String id) throws APIException;
}
