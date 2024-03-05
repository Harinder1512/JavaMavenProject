import java.sql.*;

//public class HanaJdbcExample {
//	
//	public static final String JDBC_URL = "jdbc:sap://172.25.210.176:37615/?autocommit=true";
//	public static final String USERNAME = "SS_USER";
//	public static final String PASSWORD = "Pass1234";
//
//    public static void main(String[] args) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//        	
//        	
//        } catch (Exception e) {
//            e.printStackTrace();
//        } 
//    }
//}

import java.sql.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class HanaJdbcExample {

	public static void main(String[] args) throws ClassNotFoundException {
		String jsonInput = "";

		JSONArray jsonArray = new JSONArray(jsonInput);

		try {
			
			//Dev env
			String url = "jdbc:sap://172.25.210.176:37615/?autocommit=true";
			String user = "";
			String password = "";


			Class.forName("com.sap.db.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("DB Connected ..");
			System.out.println();

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject supervisorObject = jsonArray.getJSONObject(i);
				String supervisorEmpId = supervisorObject.getString("SUPERVISOR_EMP_ID");

				// Check if supervisor with SUPERVISOR_EMP_ID exists
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT * FROM AP_MASTER.SLEEK_COMPLAINTS_SUPERVISOR_TABLE WHERE SUPERVISOR_EMP_ID = ?");
				preparedStatement.setString(1, supervisorEmpId);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next()) {
					// Supervisor exists, perform update
//					performUpdate(connection, supervisorObject);
					System.out.println("Existing Empid  " + resultSet.getString(5));

				} else {
					// Supervisor doesn't exist, perform insert
//					performInsert(connection, supervisorObject);
					System.out.println("New Empid : " + supervisorEmpId);
				}

				// Close resources
				resultSet.close();
				preparedStatement.close();
			}

			// Close connection
			connection.close();
			System.out.println("Operations completed successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void performUpdate(Connection connection, JSONObject supervisorObject) throws SQLException {
		String updateQuery = "UPDATE AP_MASTER.SLEEK_COMPLAINTS_SUPERVISOR_TABLE SET SUPERVISOR_TITLE = ?, SUPERVISOR_FIRST_NAME = ?, SUPERVISOR_LAST_NAME = ?, SUPERVISOR_EMAIL = ?, SUPERVISOR_PHONE = ?, SUPERVISOR_DESIGNATION = ?, SUPERVISOR_USER_TYPE = ? WHERE SUPERVISOR_EMP_ID = ?";
	
		
		PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
		updateStatement.setString(1, supervisorObject.getString("SUPERVISOR_TITLE"));
		updateStatement.setString(2, supervisorObject.getString("SUPERVISOR_FIRST_NAME"));
		updateStatement.setString(3, supervisorObject.getString("SUPERVISOR_LAST_NAME"));
		updateStatement.setString(4, supervisorObject.getString("SUPERVISOR_EMAIL"));
		updateStatement.setLong(5, supervisorObject.getLong("SUPERVISOR_PHONE"));
		updateStatement.setString(6, supervisorObject.getString("SUPERVISOR_DESIGNATION"));
		updateStatement.setString(7, supervisorObject.getString("SUPERVISOR_USER_TYPE"));
		updateStatement.setString(8, supervisorObject.getString("SUPERVISOR_EMP_ID"));
//		updateStatement.executeUpdate();
		updateStatement.close();
	}

	@SuppressWarnings("unused")
	private static void performInsert(Connection connection, JSONObject supervisorObject) throws SQLException {
		String insertQuery = "INSERT INTO AP_MASTER.SLEEK_COMPLAINTS_SUPERVISOR_TABLE (SUPERVISOR_TITLE, SUPERVISOR_FIRST_NAME, SUPERVISOR_LAST_NAME, SUPERVISOR_EMAIL, SUPERVISOR_EMP_ID, SUPERVISOR_PHONE, SUPERVISOR_DESIGNATION, SUPERVISOR_USER_TYPE, DEVICE_TOKEN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
		insertStatement.setString(1, supervisorObject.getString("SUPERVISOR_TITLE"));
		insertStatement.setString(2, supervisorObject.getString("SUPERVISOR_FIRST_NAME"));
		insertStatement.setString(3, supervisorObject.getString("SUPERVISOR_LAST_NAME"));
		insertStatement.setString(4, supervisorObject.getString("SUPERVISOR_EMAIL"));
		insertStatement.setString(5, supervisorObject.getString("SUPERVISOR_EMP_ID"));
		insertStatement.setLong(6, supervisorObject.getLong("SUPERVISOR_PHONE"));
		insertStatement.setString(7, supervisorObject.getString("SUPERVISOR_DESIGNATION"));
		insertStatement.setString(8, supervisorObject.getString("SUPERVISOR_USER_TYPE"));
		insertStatement.setString(9, supervisorObject.getString("DEVICE_TOKEN"));
//		int z=insertStatement.executeUpdate();
		insertStatement.close();

	}

}