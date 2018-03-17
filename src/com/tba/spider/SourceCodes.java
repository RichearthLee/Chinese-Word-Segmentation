package com.tba.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

public class SourceCodes {
	private String strurl=new String();
	private String path=new String();
	
	
	public SourceCodes(String url,String path){
		this.strurl=url;
		this.path=path;
	}
	
	public String getSourcePath(){
		return path;
		
	}
	
	
	public void getCodes() throws Exception{
		URL url=new URL(strurl);
		File file=new File(path);
		BufferedReader br=new BufferedReader(
				new InputStreamReader(url.openStream(),"utf-8"));
		OutputStreamWriter osw=new OutputStreamWriter(
				new FileOutputStream(file));

		String s;
		
			while((s=br.readLine())!=null){
				//System.out.println(s);
				osw.write(s);
		}
		
		br.close();
		osw.close();
}
}
