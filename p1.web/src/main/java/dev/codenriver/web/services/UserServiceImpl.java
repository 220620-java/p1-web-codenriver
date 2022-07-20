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
    DAO dao = new DAO();
    String messageT = "message";
    String userT = "person";

    @Override
    public User registerUser(User user) throws UsernameAlreadyExistsException{
        boolean stored;
        try{
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
    public User logIn(String username, String passwd, User userClass) throws SQLException, IllegalAccessException {
        userClass = new User();
        User user = (User) dao.getByField(username, userClass, userT);
        if (user != null && (user.getPassword() !=null && passwd.equals(user.getPassword()))) {
            return user;
        }
            return null;
    }

    @Override
    public ArrayList<Message> viewAllMessages() throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (ArrayList<Message>) dao.getTable(Message.class, messageT);
    }

    @Override
    public Message getMessage(String id, Message messageClass) throws SQLException, IllegalAccessException {
        return (Message) dao.getByField(id, messageClass, messageT);
    }

    @Override
    public User getUser(String id, User user) throws SQLException, IllegalAccessException {
        return (User) dao.getByField(id, user, userT);
    }

    @Override
    public Message addMessage(Message msg) throws SQLException {
        dao.storeObject(msg, messageT);
        return msg;
    }
}
