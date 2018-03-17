package com.tba.db;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

//测试类  可删除
public class Test {

	public static void main(String[] args) throws Exception {
		/*
		 * DBTieba dt=new DBTieba(); System.out.println(dt.output("userDATE"));
		 * FileWriter fw = new FileWriter("F:/程序运行/test.txt");
		 * fw.write(dt.output("userDATE")); fw.close();
		 */

		/*
		 * DBhospital dt=new DBhospital(); Thread t=new Thread(dt); t.start();
		 * while(t.isAlive()&&!t.isInterrupted()){ try { t.sleep(300); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * System.out.println("nononononoonnononononononono"); } }
		 */
		
		/*
		DBhospital dbh = new DBhospital();
		dbh.fenci();
	   */
		//DBattribution dba=new DBattribution();
		//dba.Clearup();
		DBhospital dh=new DBhospital();
		dh.reBuild();
		dh.run();
	}
}
