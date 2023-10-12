package com.amdocs.nursery.dao;

import java.sql.*;

///In this class we are establishing a connection to our Oracle database, which we'll be using in other classes.

public class PlantConnection {
	Connection con;

	public Connection getConnection() {

		con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:XE", "SYSTEM", "admin");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
