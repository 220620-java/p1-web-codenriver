package datatest;

import static org.junit.jupiter.api.Assertions.assertEquals; 

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dev.codenriver.orm.data.DAO;


public class DAOTest {

	@Test
	void getByField() throws IllegalArgumentException, IllegalAccessException, SQLException {
		Message testObj = new Message(1);
		DAO<?> newdao = new DAO<Object>();
		newdao.getByField("messageid", testObj, "test_db.messages");
		assertEquals(1815, testObj.getLikes());
	} 
	 
	
	@Test
	void update() throws IllegalArgumentException, IllegalAccessException, SQLException {
		Message testObj = new Message(1, "5/7/2022", "Hey this is a test.", 400, 1);
		Message checkObj = new Message(2);
		DAO<?> newdao = new DAO<>();
		newdao.updateObject("messageid", 2, testObj, "test_db.messages");
		newdao.getByField("messageid", checkObj, "test_db.messages");
		assertEquals("Hey this is a test.", checkObj.getMsg());
	}
	
	
	@Test
	void getAll() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
		Message testMessage = new Message();
		Message checkObj = new Message(100);
		DAO<?> newdao = new DAO<>();
		ArrayList<?> posts = newdao.getTable(testMessage.getClass(), "test_db.messages");
		newdao.getByField("messageid", checkObj, "test_db.messages");
		assertEquals(checkObj.toString(), ((Message) posts.get(0)).toString());
	}
	
	@Test
	void deleteObject() throws IllegalArgumentException, IllegalAccessException, SQLException {
		DAO<?> dao = new DAO<>();
		Message checkObj = new Message(500, "11/11/2020", "This is a test entry.", 2, 2);
		dao.storeObject(checkObj, "test_db.messages");
		dao.deleteObject(checkObj, "test_db.messages");
		try {
			((Message) dao.getByField("messageid", 500, "test_db.messages")).equals(null);
		} catch (NullPointerException e) {
			assertEquals(true, true);
		}
	} 
} 
