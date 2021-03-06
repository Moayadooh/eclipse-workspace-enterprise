package MyPackage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.Types;

public class Demo {

	public static void main(String[] args) {
		
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","123");

			/*String sql = "SELECT * FROM EMPLOYEES";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				System.out.println(result.getInt(1));
				System.out.println(result.getString("FIRST_NAME"));
			}*/
			
			//Execute Stored Procedure
			String query = "{call sp_getEmpById(?,?,?,?,?)}"; 
			CallableStatement callStmt = con.prepareCall(query);  
			callStmt.setInt(1, 21);  
			callStmt.registerOutParameter(2, Types.VARCHAR);
			callStmt.registerOutParameter(3, Types.VARCHAR);
			callStmt.registerOutParameter(4, Types.NUMERIC);
			callStmt.registerOutParameter(5, Types.DECIMAL);
			callStmt.execute();
			
			System.out.println(callStmt.getString(2));
			System.out.println(callStmt.getString(3));
			System.out.println(callStmt.getInt(4));
			System.out.println(callStmt.getBigDecimal(5));
			
			//--------------------------------------------------------------------------------
			/*CallableStatement callStmt = con.prepareCall("{call getAllEmpDetails(?,?,?,?)}");
			callStmt.registerOutParameter(1, Types.VARCHAR);
			callStmt.registerOutParameter(2, Types.VARCHAR);
			callStmt.registerOutParameter(3, Types.NUMERIC);
			callStmt.registerOutParameter(4, Types.DECIMAL);
			
			//callStmt.execute();
			//System.out.println(callStmt.getString(1));
			
			ResultSet result = callStmt.executeQuery();
			while (result.next()) {
			    System.out.println(result.getString(1));
			}*/
			//--------------------------------------------------------------------------------
			
			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
