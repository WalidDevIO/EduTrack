package fr.elite.messagesapi.servlet;

import com.mongodb.client.model.Filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

import fr.elite.excpetions.APIException;
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
        if (pathInfo == null || pathInfo.isEmpty()) {
            // Handle erreur 400
        }
        pathInfo = pathInfo.replace("/", "");

        if(pathInfo.equals("post") && method == "POST") {
            handleNewMessage(request, response);
            return;
        }

        if(method == "GET") {
            try {
                Integer studentNumber = Integer.parseInteger(pathInfo);
                handleStudentNumberMapping(request, response, studentNumber);
                return;
            } catch (Exception e) {}
        }
        try {
            handleMessageIdMapping(request, response, method, pathInfo);
        } catch (APIException e) {
            e.generateHttpResponse(response);
        }
    }

    private void handleStudentNumberMapping(HttpServletRequest request, HttpServletResponse response, Integer studentNumber) throws IOException {
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
    
    private void handleMessageIdMapping(HttpServletRequest request, HttpServletResponse response, String method, String id) throws APIException {
        // Méthode DELETE => Supprimer message d'_id == id
        // Méthode PUT => Mettre à jour le message d'_id == id
        // Méthode GET => Renvoyer le message d'_id == id

        ObjectId oid;
        try {
            oid = new ObjectId(id);
        } catch(IllegalArgumentException e) {
            throw new APIException("Route inconnue", 404);
        }

        if(method.equals("DELETE")) {
            
        } else if(method.equals("PUT")) {

        } else if(method.equals("GET")) {

        }

        throw new APIException("Route inconnue", 404);
    }
    
    private void handleNewMessage(HttpServletRequest request, HttpServletResponse response) {
        // Méthode POST => Créer un message
    }

}
