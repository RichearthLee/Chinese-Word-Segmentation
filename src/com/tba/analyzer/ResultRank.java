package com.tba.analyzer;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tba.db.DBConnTB;
import com.tba.db.DBresult;

public class ResultRank {
	
  //把result表中的数据进行处理 存入finalresult表
	
	public void ranking() throws SQLException {

		DBConnTB dbr = new DBConnTB();
		DBConnTB dbr1 = new DBConnTB();
		DBConnTB dbr2 = new DBConnTB();

		// String
		// sql="alter table tieba.result add  id int unsigned not null auto_increment primary key;";
		// dbr.executeUpdate(sql);
		String sql_1 = "select * from  result group by sourcestr ";
		ResultSet rs = dbr.executeQuery(sql_1);

		while (rs.next()) {
			String sql_2 = "select * from tieba.result where (id in (select id from tieba.result where sourcestr='"
					+ rs.getString(1)
					+ "' ) and rate=(select max(rate) from tieba.result where id in (select id from tieba.result where sourcestr='"
					+ rs.getString(1) + "' )))order by count desc";

			ResultSet rs1 = dbr1.executeQuery(sql_2);

			rs1.next();
			System.out.println(rs.getString(2) + "   " + rs.getString(3));
			String sql_3 = "insert into finalresult(sourcestr,hospital,city,positive,negative,count,rate) "
					+ "values('"
					+ rs1.getString(1)
					+ "','"
					+ rs1.getString(2)
					+ "','"
					+ rs1.getString(3)
					+ "','"
					+ rs1.getInt(4)
					+ "','"
					+ rs1.getInt(5)
					+ "','"
					+ rs1.getInt(6)
					+ "','"
					+ rs1.getDouble(7) + "')";
			dbr2.executeUpdate(sql_3);

		}

	}

	 //进行 p 和n 属性的数据整合
	public void PN() throws SQLException {

		DBConnTB dbr = new DBConnTB();
		DBConnTB dbr1 = new DBConnTB();
		DBConnTB dbr2 = new DBConnTB();
		DBConnTB dbr3 = new DBConnTB();
		DBConnTB dbr4 = new DBConnTB();
		String sql = "select *from  finalresult group by hospital";
		ResultSet rs = dbr.executeQuery(sql);
		int n = 0;
		int p = 0;
		String h = "";
		while (rs.next()) {
			String sql_1 = "select count(*)from finalresult where hospital='"
					+ rs.getString(3) + "' and positive=1";
			String sql_2 = "select count(*) from finalresult where hospital='"
					+ rs.getString(3) + "' and negative=1";
			ResultSet rs1 = dbr1.executeQuery(sql_1);
			rs1.next();
			n = rs1.getInt(1);
			h = rs.getString(3);
			ResultSet rs2 = dbr4.executeQuery(sql_2);
			rs2.next();
			p = rs2.getInt(1);
			String sql_3 = "delete from finalresult where hospital='" + h + "'";
			String sql_4 = "insert into finalresult(hospital,positive,negative) value('"
					+ h + "','" + p + "','" + n + "')";
			dbr2.executeUpdate(sql_3);
			dbr3.executeUpdate(sql_4);
		}

	}

}
