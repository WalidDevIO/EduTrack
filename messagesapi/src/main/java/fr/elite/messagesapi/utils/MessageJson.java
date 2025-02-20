package fr.elite.messagesapi.utils;

import org.bson.Document;
import org.bson.types.ObjectId;

import fr.elite.excpetions.APIException;
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

    public static Message toMessage(String json) throws APIException  {
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
                Boolean readed = doc.getBoolean("readed");
                Long student = doc.getLong("student");

                if(text == null || readed == null || student == null) {
                    throw new APIException("Le message n'est pas valide!", 400);
                }

                return new Message(id, text, readed, student);
            } catch(ClassCastException e) {
                throw new APIException("Le message n'est pas valide!", 400);
            }

        } catch (APIException e) {
            throw e;
        } catch (Exception e) {
            throw new APIException("Le message n'est pas valide!", 400);
        }
    }
}
