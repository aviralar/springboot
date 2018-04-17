package com.siemens.daacathon.lifePrediction.web.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
	System.out.println("-------- MySQL JDBC Connection Testing ------------");

	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return;
	}

	System.out.println("MySQL JDBC Driver Registered!");
	Connection connection = null;

	try {
		connection = DriverManager
		.getConnection("jdbc:mysql://localhost:3306/rollerbearinglifedb","root", "root");

	} catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return;
	}

	if (connection != null) {
		System.out.println("You made it, take control your database now!");
	} else {
		System.out.println("Failed to make connection!");
	}
	
	Statement stmt = connection.createStatement();  
//	/*ResultSet rs=*/stmt.execute("create table raw_data(id int,pressure double)");
	//stmt.execute("insert into raw_data values(1,12.5)");
	//stmt.execute("insert into raw_data values(2,12.7)");
	ResultSet rs=stmt.executeQuery("select * from raw_data_table");  
	while(rs.next())  
	System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getString(4));  
	connection.close();  
  }
}
