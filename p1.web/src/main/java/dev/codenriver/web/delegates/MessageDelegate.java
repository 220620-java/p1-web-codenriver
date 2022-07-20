package dev.codenriver.web.delegates;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.codenriver.util.Json;
import dev.codenriver.web.models.Message;
import dev.codenriver.web.services.UserServiceImpl;
import jakarta.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class MessageDelegate implements FrontControllerDelegate{
    private UserServiceImpl userImpl = new UserServiceImpl();
    private Json jsonMapper = new Json();

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
            try {
                int id = Integer.valueOf(path);
                Message message = new Message(id);
                userImpl.getMessage("msgid", message);
            } catch (Exception e) {

            }
        }

    }

    public void post(HttpServletRequest req, HttpServletResponse resp) {
        String path = (String) req.getAttribute("path");
    }

    public void put(HttpServletRequest req, HttpServletResponse resp) {
        String path = (String) req.getAttribute("path");
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String path = (String) req.getAttribute("path");
    }
}
