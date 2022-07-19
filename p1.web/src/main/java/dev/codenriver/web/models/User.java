package dev.codenriver.web.models;

public class User {
	private String username;
	private int userid;
	private String password;
	private Message[] messages;
	
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
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Message[] getMessages() {
		return messages;
	}
	public void setMessages(Message[] messages) {
		this.messages = messages;
	}
}
