package com.accolite.miniau.CRUDEmployeeRest.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	private static DatabaseConnection conn;
	private Connection con;
	
	private DatabaseConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeCRUD","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DatabaseConnection getConnection()
	{
		if(conn == null)
			conn = new DatabaseConnection();
		return conn;
	}

	public Connection getCon() {
		return con;
	}
}
