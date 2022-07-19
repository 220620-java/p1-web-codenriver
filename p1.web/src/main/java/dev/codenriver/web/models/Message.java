package dev.codenriver.web.models;

import java.util.Objects;

import dev.codenriver.annotations.BasicConstructor;
import dev.codenriver.annotations.PrimaryKey;

public class Message {
	@PrimaryKey
	private int messageid;
	private String postdate;
	private String contents;
	private int likes;
	private int ownerid;

	@BasicConstructor
	public Message() {
		this.messageid = 0;
		this.postdate = "";
		this.contents = "";
		this.likes = 0;
	}
	
	Message(int id) {
		this.messageid = id;
		this.postdate = "";
		this.contents = "";
		this.likes = 0;
	}
	
	Message(int id, String date, String content, int likes, int userid) {
		this.messageid = id;
		this.postdate = date;
		this.contents = content;
		this.likes = likes;
		this.ownerid = userid;
	}
	
	public int getMessageid() {
		return messageid;
	}
	
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	
	public String getPostdate() {
		return postdate;
	}
	
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getOwnerId() {
		return ownerid;
	}

	public void setOwnerId(int ownerId) {
		this.ownerid = ownerId;
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(messageid, postdate, contents, likes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true; 
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(postdate, other.postdate) && messageid == other.messageid && likes == other.likes && Objects.equals(contents, other.contents);
	}

	@Override
	public String toString() {
		return "Message [id=" + messageid + ", date=" + postdate + ", Post= " + contents + ", likes=" + likes + "]";
		
	}
}
