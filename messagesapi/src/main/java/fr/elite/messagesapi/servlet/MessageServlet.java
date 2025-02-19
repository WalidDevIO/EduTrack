package fr.elite.messagesapi.servlet;

import com.mongodb.client.model.Filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

import fr.elite.messagesapi.access.MongoAccessSingleton;
import fr.elite.messagesapi.pojo.Message;
import fr.elite.messagesapi.utils.MessageJson;
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
        if(pathInfo == null || pathInfo.isEmpty()) {
            //Handle erreur 400
        }
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

    private void handleStudentNumberMapping(HttpServletRequest request, HttpServletResponse response, String method, Long studentNumber) throws IOException {
        // Méthode GET => Renvoyer tableau JSON des messages dont student == studentNumber
        if(method == "GET") {
            //Récupérer Message correspondant et les mettre dans une liste
            List<Message> list = new ArrayList<>();
            access.find(Filters.eq("student", studentNumber)).forEach(list::add);

            //Préparer les headers de la réponse (CORS et type de contenu)
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
            response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
            response.addHeader("Access-Control-Max-Age", "1728000");
            response.setContentType("application/json; charset=UTF-8");

            //Ecrire un tableau vide ou contenant les messages sérializés en json séparés par des virgules
            PrintWriter out = response.getWriter();
            out.println("[" + list.stream().map(m -> MessageJson.toJson(m)).reduce((acc, c) -> acc + "," + c).orElse("") + "]");
            out.close();
        }
        // Méthode POST => Créer un message non lu en fonction du contenu pour l'étudiant
    }

    private void handleMessageIdMapping(HttpServletRequest request, HttpServletResponse response, String method, ObjectId id) {
        // Méthode DELETE => Supprimer message d'_id == id
        // Méthode PUT => Mettre le message d_id == id à lu
        // Méthode GET => Renvoyer le message d'_id == id
    }

}
