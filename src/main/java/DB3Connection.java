import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB3Connection {
	
	
	private Connection connection;
	String dbURL;
	String user;
	String pwd;
	
	//Constructor 
	
	public DB3Connection(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException {
		
		this.dbURL = dbURL;
		this.user = user;
		this.pwd = pwd;
		
	}

		
		public void setDBConnection() {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.connection = DriverManager.getConnection(this.dbURL,this.user,this.pwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	//To retrieve the connection whenever we want to use it.
	
	public Connection getDBConnection(){  
		
		return this.connection;
	}
	
	//Close the Connection
	public void closeConnection() throws SQLException {
		
		if(this.connection !=null) { // Check whether there is an active connection.
		
			this.connection.close();
		}
	
	}
	
}
