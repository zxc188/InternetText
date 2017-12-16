package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQlUtil {
	/*
	 * 数据库建立连接 返回一个Connection对象
	 */
	public Connection Con() {
		/*
		 * Connection dbConn = null; String driverName =
		 * "com.mysql.jdbc.Driver";
		 * 
		 * String dbURL = "jdbc:mysql://123.207.149.200:3306/java_web"; String
		 * userName = "adminadmin";
		 * 
		 * String userPwd = "adminabcd1996"; try { Class.forName(driverName);
		 * dbConn = DriverManager.getConnection(dbURL, userName, userPwd); }
		 * catch (SQLException e) { e.printStackTrace(); } catch
		 * (ClassNotFoundException e) { e.printStackTrace(); } return dbConn;
		 */
		Connection dbConn = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=InternetTest";

		String userName = "sa";

		String userPwd = "123456";
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;
	}
}
