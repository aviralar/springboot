package com.siemens.daacathon.lifePrediction.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.siemens.daacathon.lifePrediction.web.data.LiveData;

public class DBUtil {

	private static Connection con = null;

	private static DBUtil dbutil_instance = null;

	private DBUtil() {
	}

	public static DBUtil getInstance() {
		if (dbutil_instance == null)
			dbutil_instance = new DBUtil();

		return dbutil_instance;
	}

	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rollerbearinglifedb", "root", "root");
			return connection;
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		return null;

	}

	public static LiveData executeQuery(int i) {
		Statement st;
		try {
			if (con == null)
				con = createConnection();
			if (con != null) {
				st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from raw_data_table where id=" + i);

				LiveData liveData = new LiveData();
				while (rs.next()) {
					liveData.setId(rs.getInt("id"));
					liveData.setPressure(rs.getDouble("pressure"));
					liveData.setRpm(rs.getInt("rpm"));
					liveData.setTemperature(rs.getDouble("temperature"));
				}
				return liveData;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static int executeFirstIdQuery() {
		Statement st;
		int id = 0;
		if(con==null) con=createConnection();
		if(con!=null) {
			try {
				st = con.createStatement();
				ResultSet rs = st.executeQuery("select id from raw_data_table limit 1");
				while(rs.next()) {
					System.out.println(rs.getInt("id"));
					id =  rs.getInt("id");
					return id;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return id;
		
	}
}
