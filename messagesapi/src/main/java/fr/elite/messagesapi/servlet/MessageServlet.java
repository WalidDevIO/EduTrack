package fr.elite.messagesapi.servlet;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

import fr.elite.messagesapi.access.MongoAccessSingleton;
import fr.elite.messagesapi.pojo.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/messages/*")
public class MessageServlet extends HttpServlet {

    @SuppressWarnings("unused")
    private MongoCollection<Message> access = MongoAccessSingleton.getDatabase();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handle(request, response, "GET");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handle(request, response, "POST");
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handle(request, response, "PUT");
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handle(request, response, "DELETE");
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, String method) {
        String pathInfo = request.getPathInfo();
        pathInfo = pathInfo.replace("/", "");

        if(method == "GET" || method == "POST") {
            try {
                Long studentNumber = Long.parseLong(pathInfo);
                handleStudentNumberMapping(request, response, method, studentNumber);
            } catch (Exception e) {
                handleMessageIdMapping(request, response, method, new ObjectId(pathInfo));
            }
        } else {
            handleMessageIdMapping(request, response, method, new ObjectId(pathInfo));
        }

    }

    private void handleStudentNumberMapping(HttpServletRequest request, HttpServletResponse response, String method, Long studentNumber) {
        // Méthode GET => Renvoyer tableau JSON des messages dont student == studentNumber
        // Méthode POST => Créer un message non lu en fonction du contenu pour l'étudiant
    }

    private void handleMessageIdMapping(HttpServletRequest request, HttpServletResponse response, String method, ObjectId id) {
        // Méthode DELETE => Supprimer message d'_id == id
        // Méthode PUT => Mettre le message d_id == id à lu
        // Méthode GET => Renvoyer le message d'_id == id
    }

}
