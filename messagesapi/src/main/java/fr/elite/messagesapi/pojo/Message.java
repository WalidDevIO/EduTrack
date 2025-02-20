package fr.elite.messagesapi.pojo;

import java.util.Objects;

import org.bson.types.ObjectId;

public class Message {

    private ObjectId id;

    private String text;

    private Boolean readed;

    private Integer student;

    public ObjectId getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Boolean getReaded() {
        return readed;
    }

    public Integer getStudent() {
        return student;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public void setReaded(Boolean readed) {
        this.readed = readed;
    }
    
    public void setStudent(Integer student) {
        this.student = student;
    }

    public Message() {

    }

    public Message(ObjectId id, String text, Boolean readed, Integer student) {
        this.id = id;
        this.text = text;
        this.readed = readed;
        this.student = student;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((readed == null) ? 0 : readed.hashCode());
        result = prime * result + ((student == null) ? 0 : student.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        return Objects.equals(other.id, id) 
            && Objects.equals(other.text, text) 
            && Objects.equals(other.readed, readed) 
            && Objects.equals(other.student, student);
    }

    

}
