package dev.codenriver.web.servlets;

import dev.codenriver.web.delegates.FrontControllerDelegate;
import jakarta.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FrontControllerServlet {
	private RequestHandler mapper = new RequestHandler();

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontControllerDelegate delegate = mapper.map(req, resp);

        if (delegate != null ) {
            delegate.handle(req, resp);
        } else {
            resp.sendError(404);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
}
