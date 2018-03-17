package com.tba.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

   //贴吧数据库的 DB Conn
public class DBConnTB {
	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tieba";
	String userName = "root";
	String password = "123456";
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	public DBConnTB() {
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, userName, password);
			stmt = con.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String sql) {
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("发生异常：" + e.getMessage());
			System.out.println("异常SQL语句：" + sql);
		}
		return rs;

	}

	public int executeUpdate(String sql) throws SQLException {
		int rowCount = 0;
		rowCount = stmt.executeUpdate(sql);
		return rowCount;
	}

	public void close() {
		try {
			con.close();
			con = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
