package com.tba.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class DBattribution extends DBtable {

	public DBattribution() throws SQLException {
		DBConnTB db = new DBConnTB();

		try{
			String sql_table = "create table attributionP" + "("
					+ "positive char(64) not null"
					+ ")";			
			db.executeUpdate(sql_table);
			
		
			String sql_table1 = "create table attributionN" + "("
					+ "negative char(64) not null"
					+ ")";	
			db.executeUpdate(sql_table1);
		}catch(Exception e){
			
		}
	}
	
public void reBuild() throws SQLException {
		
		DBConnTB db = new DBConnTB();
		String sql = "truncate attributionP;";
		String sql1 = "truncate attributionN;";
		db.executeUpdate(sql);
		db.executeUpdate(sql1);
		db.close();

	}
//清除多余的数据
public void Clearup() throws SQLException{
	try{
	DBConnTB db = new DBConnTB();
	String sql1="SET SQL_SAFE_UPDATES = 0;";
	String sql2="delete  FROM attributionN where negative is null;";
	String sql3="delete  FROM attributionP where positive is null;";
	
	db.executeUpdate(sql1);
	db.executeUpdate(sql2);
	db.executeUpdate(sql3);

	}catch (Exception e){
		e.printStackTrace();
	}
}
//写入hownet 感情偏向词
public void words() throws IOException, SQLException{
	DBConnTB db = new DBConnTB();
	
	BufferedReader br=new BufferedReader(new FileReader(new File("F:\\程序运行\\负面评价词语（中文）.txt")));
	
	while(br.readLine()!=null){
	String str=br.readLine();
	System.out.println(str);
	String sql="insert into attributionP (positive) values ('"+str+"') ";
	String sql1="insert into attributionN (negative) values ('"+str+"') ";
	try {
		db.executeUpdate(sql1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	br.close();
}

}
