package com.tba.spider;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class WriteDate {
	private String strdate=new String();
	private String path=new String();
	
	public WriteDate(String path,String strdate){
		this.path=path;
		this.strdate=strdate;
	}
	
	public void getCodes() throws Exception{
		File file=new File(path);
		
		OutputStreamWriter osw=new OutputStreamWriter(
				new FileOutputStream(file));

				osw.write(strdate);
				osw.close();
	}
		

		
}


