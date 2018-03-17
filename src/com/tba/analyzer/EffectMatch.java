package com.tba.analyzer;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tba.db.DBConnTB;



//对字段进行情感偏向的匹配  并赋予p(positive)和n(negative)属性
public class EffectMatch {
	private int p=0;
	private int n=0;
	
	public void positive(String str) throws SQLException{

		setP(0);
		DBConnTB db=new DBConnTB();
		String sql="select positive from attributionp";
		
		ResultSet rs=db.executeQuery(sql);
		while(rs.next()){
			if(str.contains(rs.getString(1))){
				setP(getP() + 1);
			}
		}
		
		
	}
	public void negative(String str) throws SQLException{

		setN(0);
		DBConnTB db=new DBConnTB();
		String sql="select negative from attributionn";
		ResultSet rs=db.executeQuery(sql);
		while(rs.next()){
			try{
				
			if(str.contains(rs.getString(1))){
				setN(getN() + 1);
			}
			
			}catch(Exception e){
				continue;
			}
			}
			
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}

}
