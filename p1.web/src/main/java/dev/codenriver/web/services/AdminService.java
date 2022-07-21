package dev.codenriver.web.services;

import dev.codenriver.web.models.Message;

public interface AdminService {

    /**
     *  Adds the message to the application and returns it with a newly assigned ID.
     * @param msg
     * @return the message with its new ID
     */
    public Message addMessage(Message msg);

    /**
     * Uses the message ID to update the message's information in the application. Returns
     * null if the message with that ID does not exist.
     * @param msg
     * @return the updated message or nulll if the message does not exist in the system
     */
    public Message editMessage(Message msg);
}
