package com.tba.spider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadDate {
	private String strurl=new String();
	private String strdate=new String();
	
	public ReadDate(String url){
		this.strurl=url;
	}
	
	public String getCodes() throws Exception{
		URL url=new URL(strurl);
	BufferedReader br=new BufferedReader(
			new InputStreamReader(url.openStream()));
//		BufferedReader br=new BufferedReader(
//				new InputStreamReader(url.openStream()));


		String s;
		while((s=br.readLine())!=null){
				//System.out.println(s);
				strdate=strdate+s;
		}
		br.close();
		
		return strdate;
}

}
