package fr.elite.messagesapi.utils;

import org.bson.Document;
import org.bson.types.ObjectId;

import fr.elite.messagesapi.entities.Message;
import fr.elite.messagesapi.exceptions.APIException;

public class MessageJson {

    public static String toJson(Message msg) {
        if (msg == null) {
            return null;
        }

        Document doc = new Document()
                .append("id", msg.getId() != null ? msg.getId().toString() : null)
                .append("text", msg.getText())
                .append("readed", msg.getReaded())
                .append("student", msg.getStudent());

        return doc.toJson();
    }

    public static Message toMessage(String json, boolean isPost) throws APIException {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            Document doc = Document.parse(json);

            ObjectId id = doc.containsKey("id") && doc.getString("id") != null
                    ? new ObjectId(doc.getString("id"))
                    : null;

            try {
                String text = doc.getString("text");
                Integer student = doc.getInteger("student");
                Boolean readed = isPost ? false : doc.getBoolean("readed");

                if (text == null || student == null || (!isPost && readed == null)) {
                    throw new APIException("Le message n'est pas valide!", 400);
                }

                return new Message(id, text, readed, student);
            } catch (ClassCastException e) {
                throw new APIException("Le message n'est pas valide!", 400);
            }

        } catch (Exception e) {
            throw new APIException("Le message n'est pas valide!", 400);
        }
    }

    // Alias pour les cas spécifiques
    public static Message toMessage(String json) throws APIException {
        return toMessage(json, false);
    }

    public static Message toMessagePOST(String json) throws APIException {
        return toMessage(json, true);
    }

}
