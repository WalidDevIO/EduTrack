package fr.elite.messagesapi.controllers;

import fr.elite.messagesapi.entities.Message;
import fr.elite.messagesapi.exceptions.APIException;
import fr.elite.messagesapi.services.MessageService;
import fr.elite.messagesapi.services.impl.MessageServiceImpl;
import fr.elite.messagesapi.utils.MessageJson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/messages/*")
public class MessageServlet extends HttpServlet {

    private final MessageService messageService = new MessageServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String pathInfo = request.getPathInfo();

            // /messages/ => liste invalide (on pourrait implémenter une liste complète ici
            // si besoin)
            if (pathInfo == null || pathInfo.equals("/")) {
                throw new APIException("Route non valide pour GET", 400);
            }

            pathInfo = pathInfo.substring(1); // Supprime le premier '/'

            if (pathInfo.startsWith("student/")) {
                // GET /messages/student/{num}
                int studentNumber = Integer.parseInt(pathInfo.substring(8));
                List<Message> messages = messageService.getMessagesByStudent(studentNumber);
                writeResponse(
                    response, 
                    "[" 
                        + messages.stream().map(MessageJson::toJson).reduce((a, b) -> a + "," + b).orElse("")
                    + "]"
                );
                return;
            }

            // GET /messages/{id}
            Message msg = messageService.getMessageById(pathInfo);
            writeResponse(response, MessageJson.toJson(msg));

        } catch (NumberFormatException e) {
            new APIException("Le numéro étudiant est invalide", 400).generateHttpResponse(response);
        } catch (APIException e) {
            e.generateHttpResponse(response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // On doit être strictement sur /messages/
            if (request.getPathInfo() != null && !request.getPathInfo().equals("/")) {
                throw new APIException("Route non valide pour POST", 400);
            }

            String jsonBody = getRequestBody(request);
            Message msg = messageService.createMessage(jsonBody);
            response.setStatus(201);
            writeResponse(response, MessageJson.toJson(msg));
        } catch (APIException e) {
            e.generateHttpResponse(response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String pathInfo = request.getPathInfo();

            // PUT /messages/{id}
            if (pathInfo == null || pathInfo.equals("/")) {
                throw new APIException("ID requis pour PUT", 400);
            }

            pathInfo = pathInfo.substring(1);
            String jsonBody = getRequestBody(request);
            Message msg = messageService.updateMessage(pathInfo, jsonBody);
            writeResponse(response, MessageJson.toJson(msg));
        } catch (APIException e) {
            e.generateHttpResponse(response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String pathInfo = request.getPathInfo();

            // DELETE /messages/{id}
            if (pathInfo == null || pathInfo.equals("/")) {
                throw new APIException("ID requis pour DELETE", 400);
            }

            pathInfo = pathInfo.substring(1);
            messageService.deleteMessage(pathInfo);
            response.setStatus(204);
        } catch (APIException e) {
            e.generateHttpResponse(response);
        }
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private void writeResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(message);
        out.close();
    }
}
