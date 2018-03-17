package com.tba.form;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.SegTag;

import com.tba.analyzer.AttributePick;
import com.tba.analyzer.HospitalMatch;
import com.tba.analyzer.ResultRank;
import com.tba.db.DBConnTB;
import com.tba.db.DBTieba;
import com.tba.db.DBfinalresult;
import com.tba.db.DBresult;
import com.tba.spider.ReadDate;

//主类
public class Main {

	public static void main(String[] args) throws Exception {

		UserInterfaceForm mf = new UserInterfaceForm();
		DBConnTB db = new DBConnTB();
		DBTieba dbt = new DBTieba();
		DBresult dbr = new DBresult();
		DBfinalresult dbf = new DBfinalresult();
		HospitalMatch mt = new HospitalMatch();
		//ResultRank rr = new ResultRank();
		


		
       //匹配医院信息
		mt.select();
		//rr.ranking();
		//rr.PN();
	
		
		//输出最终的结果
		String sql = "select * from finalresult  order by rate desc";
		ResultSet rs = db.executeQuery(sql);
		String finalresult = "";
		
		while (rs.next()) {

			finalresult += rs.getString(3) + "\t\t\t"
					+ String.valueOf(rs.getInt(4)) + "  "
					+ String.valueOf(rs.getInt(5)) + "  " + rs.getString(2)
					+ "\n";
			System.out.println(finalresult);
		}

		//显示结果
		//mf.showtext(finalresult);

	/*	
		DBTieba tb=new DBTieba();
		tb.Input("糖尿病治疗");
		tb.Input("1型糖尿病");
		tb.Input("糖尿病肾病");
		tb.Input("糖尿病自我康复");
		tb.Input("糖尿病饮食");
		tb.Input("一型糖尿病");
		tb.Input("糖尿病a");
		tb.Input("2型糖尿病");
		tb.Input("糖尿病食谱");
		tb.Input("糖尿病食品");
		tb.Input("糖尿病眼病");
		tb.Input("1型糖尿病");
	*/
	}

}
