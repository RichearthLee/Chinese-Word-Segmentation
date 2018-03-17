package com.tba.analyzer;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tba.db.DBConnTB;
import com.tba.db.DBresult;

public class HospitalMatch {

	static String resultstr[][] = new String[1000][10];
	static String source = "";

	private String hospital = "";
	private String city="";
	

	/*
	 * 把贴吧的信息（userdate表）与医院的信息（hospital表）进行匹配 
	 * 先对字段小于256的进行模糊匹配
	 * 再对所有字段的进行精确匹配
	 * 符合条件的存入result表中 
	 */
	
	public void select() throws SQLException {

		DBConnTB db = new DBConnTB();
		String sql = "select * from hospital group by hospital";
		ResultSet rs = db.executeQuery(sql);

		AttributePick ap = new AttributePick();

		String[] arr;
		String h="";
		while (rs.next()) {
			h = rs.getString(2) + " ";
			hospital = rs.getString(1);
			city=rs.getString(3);
			arr = ap.picking(h, "").split("/");
			matching_h(arr);
		}
		matching_l();
	}

	
	//字段小于256的模糊匹配
	public void matching_h(String[] arr) throws SQLException {
		 DBresult dbr = new DBresult();
		DBConnTB db1 = new DBConnTB();
		String sql_1 = "select DISTINCT userDATE from userdate where length(userDATE)<256";
		ResultSet rs = db1.executeQuery(sql_1);

		EffectMatch em = new EffectMatch();
		OrderToMatch otm = new OrderToMatch();

		while (rs.next()) {
			source = rs.getString(1);
			otm.ordertomatch(source, arr);                    // 循序匹配   并生成count（匹配量）属性 和rate（匹配率）属性

			if (otm.getRate() < 0.7 || otm.getCount() <= 2) {
				continue;
			}

			em.positive(source);                              //匹配感情偏向p
			em.negative(source);                             //匹配感情偏向n

			dbr.Input(source, hospital,city, em.getP(), em.getN(), otm.getCount(),
					otm.getRate());                                        //插入result表

		}

	}
	
	//对所有字段的精确匹配
	public void matching_l() throws SQLException {
		 DBresult dbr = new DBresult();
		DBConnTB dbtb = new DBConnTB();
		DBConnTB dbtb1 = new DBConnTB();
		
		EffectMatch em = new EffectMatch();

		String sql_1 = "select DISTINCT hospital from hospital ";
		ResultSet rs1 = dbtb.executeQuery(sql_1);

		try {
			while (rs1.next()) {

				hospital = rs1.getString(1);

				String sql_2 = "select userDATE from userdate where userDATE like '%"
						+ hospital + "%' and   length(userDATE)>256";

				ResultSet rs2 = dbtb1.executeQuery(sql_2);

				while (rs2.next()) {

					source = rs2.getString(1);
					em.positive(source);                               //匹配感情偏向p
					em.negative(source);                               //匹配感情偏向n

					dbr.Input(source, hospital,city, em.getP(), em.getN(), -1, 1);

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}