package fr.elite.messagesapi.utils;

import org.bson.Document;
import org.bson.types.ObjectId;

import fr.elite.messagesapi.pojo.Message;

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

    public static Message toMessage(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            Document doc = Document.parse(json);

            ObjectId id = doc.containsKey("id") && doc.getString("id") != null
                    ? new ObjectId(doc.getString("id"))
                    : null;

            String text = doc.getString("text");
            Boolean readed = doc.getBoolean("readed");
            Long student = doc.getLong("student");

            return new Message(id, text, readed, student);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
