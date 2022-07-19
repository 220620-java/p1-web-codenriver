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

    @Override
    public User registerUser(User user) throws UsernameAlreadyExistsException{
        boolean stored;
        try{
            stored = true;
            dao.storeObject(user, "table");
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
        User user = (User) dao.getByField(username, userClass, "tableName");
        if (user != null && (user.getPassword() !=null && passwd.equals(user.getPassword()))) {
            return user;
        }
            return null;
    }

    @Override
    public ArrayList<Message> viewAllMessages() throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {

        return (ArrayList<Message>) dao.getTable(Message.class, "tableName");
    }

    @Override
    public Message getMessage(String id, Message messageClass) throws SQLException, IllegalAccessException {
        return (Message) dao.getByField(id, messageClass, "tableName");
    }

    @Override
    public User getUser(String id, User user) throws SQLException, IllegalAccessException {
        return (User) dao.getByField(id, user, "tableName");
    }
}
