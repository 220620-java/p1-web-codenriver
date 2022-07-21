package dev.codenriver.web.delegates;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import dev.codenriver.orm.util.Json;
import dev.codenriver.web.models.Message;
import dev.codenriver.web.models.User;
import dev.codenriver.web.services.UserServiceImpl;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class MessageDelegate implements FrontControllerDelegate{
    private UserServiceImpl userImpl = new UserServiceImpl();
    private ObjectMapper objMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String method = req.getMethod();

        switch (method) {
            case "GET":
                get(req, resp);
                break;
            case "POST":
                post(req, resp);
                break;
            case "PUT":
                put(req, resp);
                break;
            case "DELETE":
                delete(req, resp);
                break;
            default:
        }
    }

    public void get(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        String path = (String) req.getAttribute("path");
        if (path == null || "".equals("path")) {
            resp.getWriter().write(Json.nodeToString(Json.toJson(userImpl.viewAllMessages())));
        } else {
           int id = Integer.valueOf(path.toString());
           resp.getWriter().write(Json.nodeToString(Json.toJson(userImpl.getMessage(id)))); 
        }

    }

    public void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String path = (String) req.getAttribute("path");
        if(path== null || "".equals(path)){
            try {
            	Message message = objMapper.readValue(req.getInputStream(), Message.class);
            	userImpl.addMessage(message);
            } catch (RuntimeException e) {
                resp.sendError(400, "The request body was empty.");
            }
        } else {
            resp.sendError(400, "Cannot POTS to this URI. Try sending the request to /messages.");
        }
    }

    public void put(HttpServletRequest req, HttpServletResponse resp) {
        String path = (String) req.getAttribute("path");
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = (String) req.getAttribute("path");
        if(path== null || "".equals(path)){
            try {
            	Message message = objMapper.readValue(req.getInputStream(), Message.class);
            	
            } catch (RuntimeException e) {
                resp.sendError(400, "The request body was empty.");
            }
        } else {
            resp.sendError(400, "Cannot POTS to this URI. Try sending the request to /messages.");
        }
    }
}
