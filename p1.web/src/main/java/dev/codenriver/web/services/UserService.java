package dev.codenriver.web.services;

import dev.codenriver.web.exceptions.UsernameAlreadyExistsException;
import dev.codenriver.web.models.Message;
import dev.codenriver.web.models.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface UserService {

    /**
     * Creates a new user in the application and returns the newly created user. If a user with
     * that name exists already, it will throw an exception.
     * @param user
     * @return
     * @throws UsernameAlreadyExistsException
     */
    public User registerUser(User user) throws UsernameAlreadyExistsException;

    /**
     * Retrieves a user with the specified username in the application, returning
     * that user only if the specified password matches the password
     * of the retrieved user.
     * @param username of the desired user
     * @param password the password of the desired user
     * @return the user matching the username if the password matches or null if there is
     * no user with that username or the password does not match
     */
    public User logIn(String username, String password, User userClass) throws SQLException, IllegalAccessException;

    /**
     * Retrieves all of the currently available messages in the application.
     * @return a list of the available messages
     */
    public List<Message> viewAllMessages() throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException;

    /**
     * Retrieves the pet that has the specified ID.
     * @param id
     * @return the pet with the ID, or null if the pet doesn't exist
     */
    public Message getMessage(String id, Message message) throws SQLException, IllegalAccessException;

    /**
     * Retrieves the user that has the specified ID.
     * @param id
     * @return the user with the ID, or null if the user doesn't exist
     */
    public User getUser(String id, User user) throws SQLException, IllegalAccessException;
}
