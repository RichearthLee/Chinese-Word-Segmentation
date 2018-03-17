package com.tba.db;

import java.sql.SQLException;

//DB        finalresult表的相关操作
public class DBfinalresult extends DBtable {
	
	public DBfinalresult() throws SQLException {
		DBConnTB db = new DBConnTB();

		try {
			String sql_table =  "create table result" + "("
					+ "sourcestr varchar(4096) not null,"
					+ "hospital char(64) not null," + "city char(64) not null," + "positive int ,"
					+ "negative int," +"count double," + "rate double" + ")";
			db.executeUpdate(sql_table);

		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public void reBuild() throws SQLException {
		DBConnTB db = new DBConnTB();


		db.executeUpdate("truncate finalresult;");
		db.close();

	}

}
