package com.tba.spider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class test {

	public test(String tiebaname,String filepath) throws Exception{
		for(int a=0;a<=350;a=a+50){
			
		SourceCodes sc=new SourceCodes("http://tieba.baidu.com/f?kw="+tiebaname+"&ie=utf-8&pn="+a,filepath+"SourceCodes.txt");
		sc.getCodes();
		
		File file=new File(filepath+"SourceCodes.txt");
		FileInputStream fis =new FileInputStream(file);
		int length =fis.available();
		byte[] bt=new byte[length];
		fis.read(bt);
		String str=new String(bt);
		
		
		Position p=new Position("\" target=\"_blank\" class=\"j_th_tit \">","</a></div>", str);
		Position p1=new Position("<a href=\"/p/"," title=", str);
		
		try{
			while(true){
				String s;
				String s1;
				s=p.position();
				s1=p1.position();
				File filetarget=new File(filepath+s+".txt");
				if(!filetarget.exists())
				filetarget.createNewFile();
				SourceCodes scs=new SourceCodes("http://tieba.baidu.com/p/"+s1,filepath+"SourceCodes.txt");
				scs.getCodes();
				Collectengine ce=new Collectengine(filepath+s+".txt", filepath+"SourceCodes.txt");
				ce.collect();
			}
		}catch(Exception e){
			
		}
		fis.close();
		
	

	}
	}
	public void  Main2() throws Exception{
		String str="";
		ReadDate r=new ReadDate("http://blog.renren.com/share/329755067/12352774735");
		str=r.getCodes();
		
		Position p=new Position("<a href=\"http://wwv.renren.com/xn.do?ss=10791&rt=1\" rel=\"nofollow\">","</a>", str);
		String str1="";
		str1=p.position();
	}
	
	
	public static void main(String[] args) throws Exception {
		//Main m=new Main("Ã«ƒÚ≤°", "F:/≥Ã–Ú‘À––/Tieba/");
		
	//	ReadDate rd1=new ReadDate("http://tieba.baidu.com/f?kw=ª≠Õº");
	//	rd1.getCodes();
	}

}
