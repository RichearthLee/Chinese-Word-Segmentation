package com.tba.analyzer;

/*
 对已分词的字段  进行属性提取  可提取名词,动词,形容词等词
 
  我/r 有/v 100/m 个/q 苹果/n "
   m
   100
 */
public class AttributePick {

	private String resultstr = "";
	private String midstr = "";
	private String judgestr = "";
	private int former = 0;
	private int later = 0;

	public String picking(String Sourcestr, String attribute) {

		resultstr = "";
		while (Sourcestr.indexOf("/") != -1) {

			former = Sourcestr.indexOf("/");
			midstr = Sourcestr.substring(0, former + 1);

			Sourcestr = Sourcestr.substring(former);
			later = Sourcestr.indexOf(" ");

			judgestr = Sourcestr.substring(0, later);

			if (judgestr.contains(attribute)) {
				resultstr = resultstr + midstr;
			}
			Sourcestr = Sourcestr.substring(later + 1);

		}
		return resultstr;

	}
}
