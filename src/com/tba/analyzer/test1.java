package com.tba.analyzer;

import java.sql.SQLException;
import java.util.Scanner;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.SegTag;

import com.tba.db.DBfinalresult;
import com.tba.db.DBresult;

public class test1 {

	public static void main(String[] args) throws SQLException {
/*
		DBresult dr=new  DBresult();
		dr.reBuild();
		HospitalMatch mt=new HospitalMatch();
		mt.select();
	
	*/
		DBfinalresult dbf=new DBfinalresult();
		dbf.reBuild();
		ResultRank rr=new ResultRank();
		rr.ranking();
		//rr.PN();
	/*
		DBfinalresult dbf=new DBfinalresult();
		dbf.reBuild();

 
		 ResultRank rr=new ResultRank();
		 rr.ranking();
		rr.PN();
		*/
		/*
		 * Scanner sc = new Scanner(System.in); String fileContent =
		 * sc.nextLine(); SegTag segTag = new SegTag(2);// 分词路径的数目 SegResult
		 * segResult = segTag.split(fileContent.trim()); String classifyContent
		 * = segResult.getFinalResult(); System.out.println(classifyContent);
		 */
	}

}
