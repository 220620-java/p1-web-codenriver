package dev.codenriver.orm.data;

import java.sql.SQLException;

/**
 * This is the Interface of the data retrieval system
 * of this ORM. There are a few things that should be 
 * taken into account when implementing these methods.
 * 
 * 1. Any fields should have the same name as it's database
 * 	counterpart.
 * 
 * 2. This only covers basic CRUD operations so this is
 * not suited for complex queries.
 * 
 * SideNotes: I may revisit this and add additional functionality
 * like creating Schemas, tables, deleting whole tables, etc
 * 
 * @author Cooley
 *
 * @param <O>
 */
public interface DataAccessObject <O> {
	
	/**
	 * This is a method that will store a java Object in a 
	 * Postgres RD. 
	 * 
	 * **The Classes fields need to match the fields
	 * in the database for the method to function properly**
	 * 
	 * @param obj This is the Java object which will be stored.
	 * @param table The names of the table in the database that
	 * 				the object will be stored in.
	 * @throws SQLException 
	 */
	void storeObject(O obj, String table) throws SQLException;
	
	/**
	 * This will remove the rows related to the Java object that was
	 * passed into this method. 
	 * **There are no do overs! be sure that you actually want this
	 * to be deleted** 
	 * 
	 * @param obj The object instantiation of the rows to be deleted.
	 * @param table The table that the rows will be removed from.
	 * @throws SQLException 
	 */
	void deleteObject(O obj, String table) throws SQLException;
	
	/**
	 * 
	 * @param fieldKey Name of the field in the Database.
	 * @param obj The object that will be instantiated to represent
	 * 			resent the row in the DataBase.
	 * @param table The table that data will be queried and pulled from.
	 * @return A completed representation of the row in the table as a 
	 * 			Java Object.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SQLException 
	 */
	Object getByField(String fieldKey, O obj, String table) throws IllegalArgumentException, IllegalAccessException, SQLException;
	
	/**
	 * This method will update any database values that may have been
	 * changed while instantiated as a Java Object.
	 * 
	 * @param primaryKey The name of the table's primary key
	 * @param pKeyIndex The value that is associated with 
	 * 		the desired row's primary key.
	 * @param obj the object that has been freshly instantiated with 
	 * 			a parameterless constructor.
	 * @param table: The table that holds the data.
	 * @throws SQLException 
	 */
	void updateObject(String primaryKey, int pKeyIndex, Object obj, String table) throws SQLException;
}
