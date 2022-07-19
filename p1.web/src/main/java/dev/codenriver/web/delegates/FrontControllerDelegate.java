package dev.codenriver.web.delegates;

import jakarta.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FrontControllerDelegate {
        public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
