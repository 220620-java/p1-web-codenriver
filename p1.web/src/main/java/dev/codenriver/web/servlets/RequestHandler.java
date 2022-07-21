package dev.codenriver.web.servlets;

import dev.codenriver.web.delegates.FrontControllerDelegate;
import dev.codenriver.web.delegates.MessageDelegate;
import dev.codenriver.web.delegates.UserDelegate;
import dev.codenriver.web.delegates.ValidationDelegate;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {
    private Map<String, FrontControllerDelegate> delegateMap;
    {
       delegateMap = new HashMap<>();

       delegateMap.put("users", new UserDelegate());
       delegateMap.put("messages", new MessageDelegate());
       delegateMap.put("valid", new ValidationDelegate());
    }

    public FrontControllerDelegate map(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("OPTIONS".equals(req.getMethod())) {
            return (request1, response1) -> {};
        }

        StringBuilder uriString = new StringBuilder(req.getRequestURI());
        uriString.replace(0, req.getContextPath().length()+1, "");

        if (uriString.indexOf("/") != -1) {
            req.setAttribute("path", uriString.substring(uriString.indexOf("/") + 1));

            uriString.replace(uriString.indexOf("/"), uriString.length(), "");
        }

        return delegateMap.get(uriString.toString());
    }
}
