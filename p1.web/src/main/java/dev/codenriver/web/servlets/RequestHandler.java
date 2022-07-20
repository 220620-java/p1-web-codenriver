package dev.codenriver.web.servlets;

import dev.codenriver.web.delegates.FrontControllerDelegate;
import dev.codenriver.web.delegates.MessageDelegate;
import dev.codenriver.web.delegates.UserDelegate;
import dev.codenriver.web.delegates.ValidationDelegate;
import jakarta.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public FrontControllerDelegate map(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("OPTIONS".equals(request.getMethod())) {
            return (request1, response1) -> {};
        }

        StringBuilder uriString = new StringBuilder(request.getRequestURI());
        uriString.replace(0, request.getContextPath().length()+1, "");

        if (uriString.indexOf("/") != -1) {
            request.setAttribute("path", uriString.substring(uriString.indexOf("/") + 1));

            uriString.replace(uriString.indexOf("/"), uriString.length(), "");
        }

        return delegateMap.get(uriString.toString());
    }
}
