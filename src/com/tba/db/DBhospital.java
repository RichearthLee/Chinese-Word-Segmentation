package com.tba.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.SegTag;

import com.tba.spider.Position;
import com.tba.spider.ReadDate;

//DB     hospital表的相关操作   本表为程序运行前准备数据
public class DBhospital extends DBtable implements Runnable {

	public DBhospital() throws SQLException {
		DBConnTB db = new DBConnTB();

		try {

			String sql_table = "create table hospital" + "("
					+ "hospital varchar(128) not null,"
					+ "fenci char(128) not null," + "city char(128) not null"
					+ ")";
			db.executeUpdate(sql_table);
			db.close();

		} catch (Exception e) {

		}
	}

	private String src = "";
	private String src1 = "";
	private String city = "";
	private String hos = "";
	DBConnTB dbtb = new DBConnTB();

	public void reBuild() throws SQLException {

		DBConnTB db = new DBConnTB();

		String sql = "truncate hospital;";

		db.executeUpdate(sql);
		db.close();

	}

	public void run() {

		ReadDate rd = new ReadDate("http://yyk.99.com.cn/");
		try {
			src = rd.getCodes();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int a = src.indexOf("citylink");
		int b = src.indexOf("class=\"margintop8 clearbox");
		src = src.substring(a, b);
		Position p1 = new Position("<li><a href=", "</a>", src);
		try {
			while (true) {
				Thread.sleep(1000);

				city = p1.position();
				if (city == null)
					break;

				System.out.println(city + "++++++++++++++++++++++++++");

				ReadDate rd1 = new ReadDate("http://yyk.99.com.cn/"
						+ city.replaceAll("[^a-zA-Z]", ""));
				src1 = rd1.getCodes();

				city = city.replaceAll("[^\u4e00-\u9fa5]", "");

				System.out.println(src1);

				int c = src1.indexOf("您现在的位置");
				int d = src1.indexOf("</body>");
				src1 = src1.substring(c, d);
				Position p2 = new Position("title=", "target", src1);

				while (true) {

					hos = p2.position();
					if (hos == null)
						break;
					hos = hos.replaceAll("[^\u4e00-\u9fa5]", "");
					System.out.println(hos + "====================");

					String str1 = "";
					SegTag segTag = new SegTag(2);
					SegResult segResult = segTag.split(hos.trim());
					str1 = segResult.getFinalResult();

					String sql_2 = "insert into hospital (hospital,fenci,city) values ('"
							+ hos + "','" + str1 + "','" + city + "')";

					dbtb.executeUpdate(sql_2);

					System.out.println(hos);
					System.out.println(str1);
					System.out.println(city);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 把医院进行分词处理 并存入数据库
	public void fenci(String str, String city) throws SQLException {
		DBConnTB dbtb = new DBConnTB();

		String str1 = "";
		SegTag segTag = new SegTag(2);
		SegResult segResult = segTag.split(str.trim());
		str1 = segResult.getFinalResult();

		String sql_2 = "insert into hospital (hospital,fenci,city) values ('"
				+ str + "','" + str1 + "','" + city + "')";

		dbtb.executeUpdate(sql_2);

		System.out.println(str);
		System.out.println(str1);
		System.out.println(city);

	}

}
