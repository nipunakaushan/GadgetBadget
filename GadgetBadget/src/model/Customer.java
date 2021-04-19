package model;
import java.sql.*;


public class Customer {
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget",
					 "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
			 
			 
			 
	// insert method
			public String insertCustomer(String name, String email, String type, String contact) {
				Connection con = connect();
				String output = "";
				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement
				String query = " insert into customers (`CustomerID`,`CustomerName`,`CustomerEmail`,`CustomerType`,`CustomerContact`)"
						+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt;
				try {
					preparedStmt = con.prepareStatement(query);

					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, name);
					preparedStmt.setString(3, email);
					preparedStmt.setString(4, type);
					preparedStmt.setString(5, contact);

					preparedStmt.execute();
					con.close();
					output = "Inserted successfully";
				} catch (SQLException e) {
					output = "Error while inserting";
					System.err.println(e.getMessage());
				}
				// binding values

				return output;
			}

		
		public String readCustomers()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Customer Name</th><th>customer Email</th>" +
		 "<th>Customer Type</th>" +
		 "<th>Customer Conatct</th>" +
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from customers";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String CustomerID = Integer.toString(rs.getInt("CustomerID"));
		 String CustomerName = rs.getString("CustomerName");
		 String CustomerEmail = rs.getString("CustomerEmail");
		 String CustomerType = rs.getString("CustomerType");
		 String CustomerConatct = rs.getString("CustomerContact");
		 
		 
		 
		 
		 // Add into the html table
		 output += "<tr><td>" + CustomerName + "</td>";
		 output += "<td>" + CustomerEmail + "</td>";
		 output += "<td>" +CustomerType + "</td>";
		 output += "<td>" + CustomerConatct + "</td>";
		 
		 // buttons
		 
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='items.jsp'>"
		 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='CustomerID' type='hidden' value='" + CustomerID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 
		 
		 
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		 
		public String updateCustomer(String ID, String name, String email, String type, String contact)
		
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 
		 
		 String query = " update customers set CustomerName= ? , CustomerEmail = ? , CustomerType = ? , CustomerContact = ?  where CustomerID = ? ";
			
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		    preparedStmt.setString(1, name);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, type);
			preparedStmt.setString(4, contact);

		   preparedStmt.setInt(5, Integer.parseInt(ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the customer.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		