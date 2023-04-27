<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.Statement" %>
    <%@ page import="java.sql.ResultSet" %>
    <%@ page import="java.sql.SQLException" %>
    <%@ page import="com.simplilearn.project1.DB3Connection" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">

table{
border-collapse:collapse;
width: 100%;
}
th,td{
text-align: left;
padding: 8px;
border: 1px solid black;
}
th{
background-color: #ddd;
}
</style>
<title>List Students</title>
</head>
<body>

<table>
<tr>
<th>Student Name</th>
<th>Student E-Mail</th>
<th>Student City</th>
</tr>

<%
try {
	
	// Creating object of DBConnection jdbc class
	
	DB3Connection conn = new DB3Connection("jdbc:mysql://localhost:3306/learnersacademy", "root","Sj.shubham@123");
	conn.setDBConnection();
	out.println("DB Connection Successful!<br>");	
	

	//Create a Statement
	
	Statement stmt = conn.getDBConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	
	
	//Execute a SQL Query
	
	ResultSet rst = stmt.executeQuery("select * from students"); //Run a SQL Query.
	
	//Print the Results from the students table.
	while(rst.next())
	{
		out.print("<tr>");
		out.print("<td>"+rst.getInt("id")+"</td>");
		out.print("<td>"+rst.getString("name_full")+"</td>");
		out.print("<td>"+rst.getString("email")+"</td>");
		out.print("<td>"+rst.getString("city")+"</td>");
		out.print("</tr>");
	}
	rst.close();
	stmt.close();
	
	// Close the Connection.
	conn.closeConnection();
	out.println("DB Connection Closed.<br>");

	out.println("</body></html>");
} catch (ClassNotFoundException e) {
	e.printStackTrace();
} catch (SQLException e) {
	e.printStackTrace();
}
%>
</table>

</body>
</html>