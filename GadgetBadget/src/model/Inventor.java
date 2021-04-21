package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Inventor {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/paf_lab_03",
			 "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	// insert method
			public String insertInventor(String name, String email, String contact, String type) {
				Connection con = connect();
				String output = "";
				if (con == null) {
					return "Error while connecting to the database";
				}
				
				// create a prepared statement
				String query = " insert into inventors (`InventorID`,`InventorName`,`InventorEmail`,`InventorContact`,`InventorType`)"
						+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt;
				try {
					preparedStmt = con.prepareStatement(query);

					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, name);
					preparedStmt.setString(3, email);
					preparedStmt.setString(4, contact);
					preparedStmt.setString(5, type);

					preparedStmt.execute();
					con.close();
					output = "Inserted successfully";
					
				} catch (SQLException e) {
					output = "Error while inserting";
					System.err.println(e.getMessage());
				}
				
				

				return output;
			}

			public String readInventors()
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Inventor Name</th><th>Inventor Email</th>" +
			 "<th>Inventor Contact</th>" +
			 "<th>Inventor Type</th>" +
			 "<th>Update</th><th>Remove</th></tr>";

			 String query = "select * from inventors";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
			 String InventorID = Integer.toString(rs.getInt("InventorID"));
			 String InventorName = rs.getString("InventorName");
			 String InventorEmail = rs.getString("InventorEmail");
			 String InventorContact = rs.getString("InventorContact");
			 String InventorType = rs.getString("InventorType");
			 
			
}
