package com.tba.spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;



public class Collectengine {
	private String  targetpath;
	private String  sourcepath;
	
	public Collectengine(String targetpath,String sourcepath) {
		this.targetpath=targetpath;
		this.sourcepath=sourcepath;
		
	}
	
	public void collect() throws Exception{
		FileWriter fw=null;                             
		File f=new File(targetpath);    
		fw=new FileWriter(f,true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		File file=new File(sourcepath);
		FileInputStream fis =new FileInputStream(file);
		int length =fis.available();
		byte[] bt=new byte[length];
		fis.read(bt);
		String str=new String(bt);
		
		Position p=new Position("class=\"d_post_content j_d_post_content \">","</div><br>", str);
		try{
			while(true){
				String s;
				s=p.position();
				bw.append(s+"\r\n");
			}
		}catch(Exception e){
			
		}
		bw.close();
		fis.close();
	}

	}


