package dev.codenriver.web.delegates;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import dev.codenriver.web.exceptions.UsernameAlreadyExistsException;
import dev.codenriver.web.models.User;
import dev.codenriver.web.services.UserService;
import dev.codenriver.web.services.UserServiceImpl;

import jakarta.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDelegate implements FrontControllerDelegate {
	private UserService userServ = new UserServiceImpl();
	private ObjectMapper objMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();

		switch(method){
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
        	resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        	break;
        default:
        }
    }


    private void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		if(path==null || "".equals(path)){
            resp.sendError(403, "Access to all users is forbidden.");
        } else {
            try{
                String id = path;
                //User user = userServ.getUser(String id, User user);
            } catch (NumberFormatException e){
                resp.sendError(400, e.getMessage());
            }
        }
    }

	private void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		if(path==null || "".equals(path)){
            try{
                User user = objMapper.readValue(req.getInputStream(), User.class);
                if(user == null) throw new RuntimeException();
            	try {
                    user = userServ.registerUser(user);
					resp.getWriter().write(objMapper.writeValueAsString(user)); // ?????
                } catch (UsernameAlreadyExistsException e){
                    resp.sendError(409, "A user with that name already exists.");
                }
            } catch(MismatchedInputException | RuntimeException e){
                    resp.sendError(400, "The request body was empty.");
            }
        } else {
            resp.sendError(400, "Cannot POST to this URI. Try sending the request to /users.");
        }
    }

    private void put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
	}

}
