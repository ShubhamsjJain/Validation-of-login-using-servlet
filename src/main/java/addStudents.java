

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addStudents
 */
@WebServlet("/addStudents")
public class addStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addStudents() {
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
			
			/*String name =;
			String e_mail =;
			String place =;*/
			
			out.println("<html><body>");

			// Get the URL, username, password from the config.properties file and load into props object.
			
			InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties"); // Java NIO Package.
			Properties props = new Properties();
			props.load(in);

						
			// Creating object of DBConnection jdbc class
						
			DB2Connection conn = new DB2Connection(props.getProperty("url"), props.getProperty("userid"),props.getProperty("password"));
			conn.setDBConnection();
			out.println("DB Connection Successful!<br>");	
			
			

			//Create a Statement
			
			Statement stmt = conn.getDBConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			
			//Execute a SQL Query
			stmt.executeUpdate("INSERT INTO students(name_full,email,city) values(request.getParameter(\"name\"),request.getParameter(\"e_mail\"),request.getParameter(\"place\"))"); // Insert some product into eproduct table.
			ResultSet rst = stmt.executeQuery("select * from students"); //Run a SQL Query.
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

}
