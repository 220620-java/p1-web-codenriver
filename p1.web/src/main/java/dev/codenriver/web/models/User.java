package dev.codenriver.web.models;

import dev.codenriver.orm.data.DAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class User {
	private String username;
	private int userid;
	private String passwd;
	private ArrayList<Message> messages;

	public User() {
		this.username = "";
		this.passwd = "";
		this.userid = 0;
		this.messages = new ArrayList<>();
	}

	public User(int id) {
		this.username = "";
		this.passwd = "";
		this.userid = id;
		this.messages = new ArrayList<>();
	}

	public User(String username, String password, int id) {
		this.username = username;
		this.passwd = password;
		this.userid = id;
		this.messages = getMessages();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return passwd;
	}

	public void setPassword(String password) {
		this.passwd = password;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(Message[] messages) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
		DAO dao = new DAO();
		ArrayList<Message> allMessages = dao.getTable(Message.class, "");
		for (Message mes: allMessages) {
			if (mes.getOwnerId() == this.userid) {
				this.messages.add(mes);
			}
		}
	}

	@Override
	public int hashCode() {

		return Objects.hash(userid, username, passwd, messages);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(username, other.username) && userid == other.userid && Objects.equals(passwd, other.passwd) && Objects.equals(messages, other.messages);
	}

	@Override
	public String toString() {
		return "Message [id=" + userid + ", Username=" + username + ", Password= " + passwd + ", First message=" + messages.get(0) + "]";

	}

}
