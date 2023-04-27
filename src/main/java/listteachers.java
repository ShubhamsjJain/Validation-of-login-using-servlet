

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class listteachers
 */
@WebServlet("/listteachers")
public class listteachers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listteachers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			

			
			// Creating object of DBConnection jdbc class
			
			DB3Connection conn = new DB3Connection("jdbc:mysql://localhost:3306/learnersacademy", "root","Sj.shubham@123");
			conn.setDBConnection();
			out.println("DB Connection Successful!<br>");	
			

			//Create a Statement
			
			
			Statement stmt = conn.getDBConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			
			//Execute a SQL Query
			
			ResultSet rst = stmt.executeQuery("select * from teachers"); //Run a SQL Query.
			
			//Print the Results from the students table.
			while(rst.next())
			{
				out.println(rst.getInt("id")+" ,"+rst.getString("name_full")+" ,"+rst.getString("email")+" ,"+rst.getString("city")+"<br>");
			}
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
