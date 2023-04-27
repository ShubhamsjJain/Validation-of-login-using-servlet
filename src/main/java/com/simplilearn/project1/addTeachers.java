package com.simplilearn.project1;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addTeachers
 */
@WebServlet("/addTeachers")
public class addTeachers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addTeachers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			
			String name = request.getParameter("name");
			String e_mail = request.getParameter("e_mail");
			String place = request.getParameter("place");
			

			// Get the URL, username, password from the config.properties file and load into props object.

			
			// Creating object of DBConnection jdbc class
			
			DB3Connection conn = new DB3Connection("jdbc:mysql://localhost:3306/learnersacademy", "root","Sj.shubham@123");
			conn.setDBConnection();
			out.println("DB Connection Successful!<br>");	
			

			//Create a Statement
			
			
			PreparedStatement pstmt = conn.getDBConnection().prepareStatement("INSERT INTO teachers(name_full, email, city) values(?,?,?)");
			
			pstmt.setString(1, name);
			pstmt.setString(2, e_mail);
			pstmt.setString(3, place);
			
			//Execute a SQL Query
			pstmt.executeUpdate(); // Insert some product into students table.
			
			
			/*ResultSet rst = pstmt.executeQuery("select * from students"); //Run a SQL Query.
			//Print the Results from the students table.
			while(rst.next())
			{
				out.println(rst.getInt("id")+" ,"+rst.getString("name_full")+" ,"+rst.getString("email")+" ,"+rst.getString("city")+"<br>");
			}*/
			
			pstmt.close();
			
			// Close the Connection.
			conn.closeConnection();
			out.println("DB Connection Closed.<br>");

			out.println("</body></html>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
