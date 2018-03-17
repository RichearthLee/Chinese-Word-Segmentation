package com.tba.db;

import java.sql.SQLException;

//DB    result表的相关操作
public class DBresult extends DBtable {

	public DBresult() throws SQLException {
		DBConnTB db = new DBConnTB();

		try {
			String sql_table = "create table result" + "("
					+ "sourcestr varchar(2048) not null,"
					+ "hospital char(64) not null," + "city char(64) not null," + "positive int ,"
					+ "negative int," +"count double," + "rate double" + ")";
			db.executeUpdate(sql_table);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		db.close();

	}

	// 建立表
	public void reBuild() throws SQLException {
		
		DBConnTB db = new DBConnTB();
		String sql_table = "truncate result;";
		db.executeUpdate(sql_table);
		db.close();

	}

	// 表输入
	public void Input(String source, String hospital,String city, int p, int n,
			double count, double rate) throws SQLException {

		DBConnTB db = new DBConnTB();

		System.out.println(source + "," + hospital + ","+ city + "," + p + "," + n + ","
				+ count + "," + rate);

		String sql_1 = "insert into result(sourcestr,hospital,city,positive,negative,count,rate) "
				+ "values('"
				+ source
				+ "','"
				+ hospital
				+ "','"
				+ city
				+ "','"
				+ p
				+ "','"
				+ n + "','" + count + "','" + rate + "')";

		db.executeUpdate(sql_1);
	}

}
