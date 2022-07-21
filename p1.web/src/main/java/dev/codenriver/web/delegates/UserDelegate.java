 package dev.codenriver.web.delegates;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import dev.codenriver.orm.data.DAO;
import dev.codenriver.orm.util.Json;
import dev.codenriver.web.exceptions.UsernameAlreadyExistsException;
import dev.codenriver.web.models.User;
import dev.codenriver.web.services.UserService;
import dev.codenriver.web.services.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserDelegate implements FrontControllerDelegate {
	private UserService userServ = new UserServiceImpl();
	private ObjectMapper objMapper = new ObjectMapper();
	private String userTable = "message.person";

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, SQLException {
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


    private void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, SQLException {
		StringBuilder path = (StringBuilder) req.getAttribute("path");
		if(path==null || "".equals(path.toString())){
            resp.sendError(403, "Access to all users is forbidden.");
        } else {
            try{
            	String pass = req.getParameter("password");
            	String usrn = req.getParameter("username");
            	int id = Integer.valueOf(path.replace(0, path.length()-1, "").toString());
                User user = userServ.getUser(id); 
                if (userServ.logIn(usrn, pass, user)) {
                	resp.getWriter().write(Json.nodeToString(Json.toJson(user)));
                }
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
					resp.getWriter().write(objMapper.writeValueAsString(user)); 
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
