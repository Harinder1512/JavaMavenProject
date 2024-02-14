
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MsSqlServerJDBC {

	
	//Sql Server Windows authentication db connection 
	private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;"
			+ "databaseName=tempdb;integratedSecurity=true;trustServerCertificate=true";

	private static final String JDBC_USER = "harinder";// NR for windows authentication
	private static final String JDBC_PASSWORD = "admin"; // NR for windows authentication

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		System.out.println("after driver class :: ");
//		Connection connection = DriverManager.getConnection(JDBC_URL); // windows authentication
		Connection connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);// sql server authentication
		System.out.println("Connected to the database.");

	}
}