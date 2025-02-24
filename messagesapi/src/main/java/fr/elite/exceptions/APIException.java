package fr.elite.exceptions;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import fr.elite.messagesapi.config.Conf;
import jakarta.servlet.http.HttpServletResponse;

public class APIException extends Exception {

    Integer status;

    public APIException() {
        this("Une erreur est survenu lors de la transformation du JSON en message", 500);
    }

    public APIException(String message) {
        this(message, 500);
    }

    public APIException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    public void generateHttpResponse(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers",
                "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(status);

        // Ecrire un tableau vide ou contenant les messages sérializés en json séparés
        // par des virgules
        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            return;
        }
        StringBuilder sb = new StringBuilder();

        sb.append("{\"detail\": \"");
        sb.append(getMessage());
        sb.append("\"");

        if (Conf.mode == "DEBUG") {
            Writer writer = new StringWriter();
            printStackTrace(new PrintWriter(writer));
            String s = writer.toString();
            sb.append(", \"stacktrace\": \"");
            sb.append(s);
            sb.append("\"");
        }

        sb.append("}");

        out.println(sb.toString());
        out.close();
    }
}