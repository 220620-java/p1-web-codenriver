package dev.codenriver.web.services;

import dev.codenriver.orm.data.DAO;
import dev.codenriver.web.exceptions.UsernameAlreadyExistsException;
import dev.codenriver.web.models.Message;
import dev.codenriver.web.models.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{
    String messageT = "message.messages";
    String userT = "message.person";

    @Override
    public User registerUser(User user) throws UsernameAlreadyExistsException{
    
        boolean stored;
        try{
        	DAO<?> dao = new DAO<>();
            stored = true;
            dao.storeObject(user, userT);
        } catch (SQLException e){
            stored = false;
            throw new UsernameAlreadyExistsException();
        }
        if (stored) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean logIn(String username, String passwd, User userClass) {
    	try {      
	        if (userClass != null && (userClass.getPassword() !=null && passwd.equals(userClass.getPassword()))) {
	            return true;
	        }
    	} catch(Exception e) {
    		
    	}
     	return false;
    }

    @Override
    public ArrayList<Object> viewAllMessages() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
    	DAO<?> dao = new DAO<>();
		return dao.getTable(Message.class, messageT);
    }

    @Override
    public Message getMessage(int id) throws IllegalArgumentException, IllegalAccessException, SQLException {
    		DAO<?> dao = new DAO<>();
    		Message message = new Message(id);
    		return (Message) dao.getByField("messageid", message, messageT);
    }
    
    @Override
    public User getUser(int id) {
    	try {
    		User userObj = new User(Integer.valueOf(id));
    		DAO<?> dao = new DAO<>();
        return (User) dao.getByField("userid", userObj, userT);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return null;
    }
    @Override
    public Message addMessage(Message msg) {
    	try {
    		DAO<?> dao = new DAO<>();
    		dao.storeObject(msg, messageT);
        return msg;
    	} catch(Exception e) {
    		
    	}
		return msg;
    }
}
