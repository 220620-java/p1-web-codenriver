package dev.codenriver.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.codenriver.web.services.UserService;
import dev.codenriver.web.services.UserServiceImpl;
import jakarta.servlet.http.HttpServlet;

public class MessageDelegate extends HttpServlet {
    private UserService userServ = new UserServiceImpl();
    private ObjectMapper objMapper = new ObjectMapper();
}
