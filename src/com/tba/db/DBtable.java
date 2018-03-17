package com.tba.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBtable {
	//建表
	public DBtable() throws SQLException {

	}
	//数据更新
	public void Updata(String sql) throws SQLException{
		DBConnTB db = new DBConnTB();

		db.executeUpdate(sql);
		
	}
	//数据导出
	public ResultSet Output(String table){
		
		DBConnTB dbtb = new DBConnTB();
		ResultSet rs=dbtb.executeQuery("select * from "+table);
		dbtb.close();
		return rs;
		
	}
	//重构表
	public void reBuild() throws SQLException{
		DBConnTB dbtb = new DBConnTB();
		String sql="";
		dbtb.executeUpdate(sql);
		dbtb.close();
	}
	//清除多余数据
	public void Clearup() throws SQLException{
		
	}


}
