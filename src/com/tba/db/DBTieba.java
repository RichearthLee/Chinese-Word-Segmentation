package com.tba.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tba.spider.Position;
import com.tba.spider.ReadDate;

//DB    userdate表的相关操作
public class DBTieba extends DBtable {

	public DBTieba() throws SQLException {
		
		//创建userdate表
		DBConnTB dbtb = new DBConnTB();
		try {

			String sql_table = "create table userdate" + "("
					+ "id int unsigned not null auto_increment primary key,"
					+ "userID char(32) not null,"
					+ "userDATE varchar(2048) not null" + ")";
			dbtb.executeUpdate(sql_table);

		} catch (Exception e) {
			
		}
	}

	//  重构表
	public void reBuild() throws SQLException {
		DBConnTB dbtb = new DBConnTB();
		String sql="truncate userdate;";
		dbtb.executeUpdate(sql);
		dbtb.close();
		
	}

	// userdate表的输入
	public void Input(String tiebaname) throws Exception {

		DBConnTB dbtb = new DBConnTB();

		for (int a = 0; a <= 350; a++) {                                          //翻页循环
			ReadDate rd = new ReadDate("http://tieba.baidu.com/f?kw="
					+ tiebaname + "&ie=utf-8&pn=" + a);

			String str = rd.getCodes();

			Position p = new Position("<a href=\"/p/", " title=", str);    //定位信息

			String s = p.position().replaceAll("[^0-9]", "");
			System.out.println(s);
			ReadDate rd1 = new ReadDate("http://tieba.baidu.com/p/" + s);

			String str1 = rd1.getCodes();
			// System.out.println(str1);

			Position p1 = new Position(
					"class=\"d_post_content j_d_post_content", "</div><br>",
					str1);
			Position p2 = new Position("<div id=\"post_content_", "class", str1);               //定位帖子字段

			try {
				while (true) {

					String s2 = p2.position();
					if(s2==null)break;
					s2=s2.replaceAll("[^0-9]", "");
					String s1 = p1.position().replaceAll("[^\u4e00-\u9fa5]", "");
					
					

					// int n=Integer.parseInt(s2);
					// System.out.println(n);

					String sql = "INSERT INTO userdate(userID,userDATE)values('"                  //把数据写入数据库
							+ s2 + "','" + s1 + "')";
					dbtb.executeUpdate(sql);

					System.out.print(s1);
					System.out.println(s2);
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}
	

}
