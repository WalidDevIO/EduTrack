package fr.elite.messagesapi.servlet;

import com.mongodb.client.model.Filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

import fr.elite.exceptions.APIException;
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
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Max-Age", "1728000");
        response.setStatus(HttpServletResponse.SC_OK);
    }

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
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Max-Age", "1728000");
        response.setContentType("application/json; charset=UTF-8");
        try {
            // Récupérer le pathInfo qui est la partie de l'URL après le nom de la servlet
            String pathInfo = request.getPathInfo();
            if (pathInfo == null || pathInfo.isEmpty()) {
                throw new APIException("Route inconnue", 404);
            }
            
            if(pathInfo.startsWith("/student/") && method == "GET") {
                pathInfo = pathInfo.substring(9);
                try {
                    Integer studentNumber = Integer.parseInt(pathInfo);
                    handleStudentNumberMapping(request, response, studentNumber);
                    return;
                } catch (Exception e) {}
            }

            pathInfo = pathInfo.replace("/", "");

            //Partie nouveau message
            if(pathInfo.isEmpty() && method == "POST") {
                handleNewMessage(request, response);
                return;
            }

            handleMessageIdMapping(request, response, method, pathInfo);
        } catch (APIException e) {
            e.generateHttpResponse(response);
        }
    }

    private void handleStudentNumberMapping(HttpServletRequest request, HttpServletResponse response, Integer studentNumber) throws IOException {
        //Récupérer Message correspondant et les mettre dans une liste
        List<Message> list = new ArrayList<>();
        access.find(Filters.eq("student", studentNumber)).forEach(list::add);

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
            throw new APIException("ID invalide", 400);
        }

        if(method.equals("DELETE")) {
            
            //Vérifier si le message existe
            Message msg = access.find(Filters.eq("_id", oid)).first();
            if(msg == null) {
                throw new APIException("Le message d'id " + oid.toHexString() + " n'existe pas", 404);
            }
            PrintWriter out;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                throw new APIException("Erreur interne", 500);
            }
            //Supprimer le message
            access.deleteOne(Filters.eq("_id", oid));
            //Reponse 204 définie dans openapi.yml
            response.setStatus(204);
            out.println("Le message d'id " + oid.toHexString() + " a été supprimé");
            out.close();

        } else if(method.equals("PUT")) {
            
            //Mettre à jour le message
            BufferedReader reader = null;
            try {
                reader = request.getReader();
            } catch (IOException e){
                throw new APIException("Erreur interne", 500);
            }
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                throw new APIException("Erreur interne", 500);
            }
            //Conversion du message JSON en objet Message
            Message msg = MessageJson.toMessage(sb.toString());
            if(msg == null) {
                throw new APIException("Erreur de format", 400);
            } else {
                access.replaceOne(Filters.eq("_id", oid), msg);
            }
            PrintWriter out;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                throw new APIException("Erreur interne", 500);
            }
            response.setStatus(200);
            out.println(MessageJson.toJson(msg));
            out.close();

        } else if(method.equals("GET")) {
            
            Message msg = access.find(Filters.eq("_id", oid)).first();
            if(msg == null) {
                throw new APIException("Le message d'id " + oid.toHexString() + " n'existe pas", 404);
            }
            PrintWriter out;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                throw new APIException("Erreur interne", 500);
            }
            out.println(MessageJson.toJson(msg));
            out.close();

        }

        throw new APIException("Méthode non implémenté", 501);
    }
    
    private void handleNewMessage(HttpServletRequest request, HttpServletResponse response) throws APIException {
        // Méthode POST => Créer un message
        // Récupérer le message depuis le corps de la requête
        BufferedReader reader = null;
        try {
            reader = request.getReader();
        } catch (IOException e){
            throw new APIException("Erreur interne", 500);
        }
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new APIException("Erreur interne", 500);
        }
        //Conversion du message JSON en objet Message avec readed = false
        Message msg = MessageJson.toMessagePOST(sb.toString());
        if(msg == null) {
            throw new APIException("Erreur de format", 400);
        } else {
            access.insertOne(msg);
        }
        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            throw new APIException("Erreur interne", 500);
        }
        response.setStatus(201);
        out.println(MessageJson.toJson(msg));
        out.close();
    }

}
