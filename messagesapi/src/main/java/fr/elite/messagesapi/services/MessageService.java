package fr.elite.messagesapi.services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import fr.elite.messagesapi.access.MongoAccessSingleton;
import fr.elite.messagesapi.exceptions.APIException;
import fr.elite.messagesapi.pojo.Message;
import fr.elite.messagesapi.utils.MessageJson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MessageService {

    private final MongoCollection<Message> messageCollection;

    public MessageService() {
        this.messageCollection = MongoAccessSingleton.getDatabase();
    }

    public Message getMessageById(String id) throws APIException {
        try {
            ObjectId oid = new ObjectId(id);
            Message message = messageCollection.find(Filters.eq("_id", oid)).first();
            if (message == null) {
                throw new APIException("Message not found", 404);
            }
            return message;
        } catch (IllegalArgumentException e) {
            throw new APIException("Invalid ID format", 400);
        }
    }

    public List<Message> getMessagesByStudent(int studentNumber) {
        List<Message> messages = new ArrayList<>();
        messageCollection.find(Filters.eq("student", studentNumber)).forEach(messages::add);
        return messages;
    }

    public Message createMessage(String jsonBody) throws APIException {
        Message message = MessageJson.toMessagePOST(jsonBody);
        if (message == null) {
            throw new APIException("Invalid message format", 400);
        }
        messageCollection.insertOne(message);
        return message;
    }

    public Message updateMessage(String id, String jsonBody) throws APIException {
        try {
            ObjectId oid = new ObjectId(id);
            Message message = MessageJson.toMessage(jsonBody);
            if (message == null) {
                throw new APIException("Invalid message format", 400);
            }
            messageCollection.replaceOne(Filters.eq("_id", oid), message);
            return message;
        } catch (IllegalArgumentException e) {
            throw new APIException("Invalid ID format", 400);
        }
    }

    public void deleteMessage(String id) throws APIException {
        try {
            ObjectId oid = new ObjectId(id);
            Message message = messageCollection.find(Filters.eq("_id", oid)).first();
            if (message == null) {
                throw new APIException("Message not found", 404);
            }
            messageCollection.deleteOne(Filters.eq("_id", oid));
        } catch (IllegalArgumentException e) {
            throw new APIException("Invalid ID format", 400);
        }
    }
}
