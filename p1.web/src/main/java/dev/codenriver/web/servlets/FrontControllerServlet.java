package dev.codenriver.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dev.codenriver.web.delegates.FrontControllerDelegate;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class FrontControllerServlet extends HttpServlet {
	private RequestHandler mapper = new RequestHandler();

    private void process(HttpServletRequest req, HttpServletResponse resp) {
    	try {
        FrontControllerDelegate delegate = mapper.map(req, resp);

        if (delegate != null ) {
            delegate.handle(req, resp);
        } else {
            resp.sendError(404);
        }
    } catch (Exception e) {
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
