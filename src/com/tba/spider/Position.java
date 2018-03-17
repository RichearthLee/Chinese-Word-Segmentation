package com.tba.spider;

public class Position {
	private String index;
	private String lastindex;
	private String source;
	
	
	public Position(String index,String lastindex,String source){
		this.index=index;
		this.lastindex=lastindex;
		this.source=source;
	}
	
	
	public String position(){
		int c=0,b=0;
		String strNam=new String();
		try{
			int s=index.length();
		c=source.indexOf(index);
		String str1=new String(source.substring(c));
		b=str1.indexOf(lastindex);
		strNam=str1.substring(s, b);
	    //strNam = strNam.replaceAll("[^\u4e00-\u9fa5]", "");
		source=str1.substring(b);
		
		return strNam;
	}catch(Exception e){
		return null;
	}
	}
}
