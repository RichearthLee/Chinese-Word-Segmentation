 import java.io.BufferedReader;
  import java.io.BufferedWriter;
  import java.io.File;
  import java.io.FileInputStream;
  import java.io.FileNotFoundException;
  import java.io.FileOutputStream;
  import java.io.InputStreamReader;
  import java.io.OutputStreamWriter;
  import java.util.*;
 
 import org.ictclas4j.bean.SegResult;  
 import org.ictclas4j.segment.SegTag;
 //import ICTCLAS.I3S.AC.ICTCLAS50;
 
 public class FileExcludeStopWord {
     //停用词词表
     public static final String stopWordTable = "." + File.separator + "srcFile" + File.separator + "StopWordTable.txt";
 
     public static void main(String[] args) {
 
         //源文件和目的文件
         String srcFile = "." + File.separator + "srcFile" + File.separator + "新闻5.txt";
         String destFile = "." + File.separator + "destFile" + File.separator + "新闻5.txt";
         new FileExcludeStopWord().fileExcludeStopWord(srcFile, destFile);
     }
     
     public void fileExcludeStopWord(String srcFile,String destFile){
         try {
             //读取原文件和停用词表
             BufferedReader srcFileBr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(srcFile))));
             BufferedReader StopWordFileBr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(stopWordTable))));
             
             //将去除停用词的文本信息存入输出文件
             BufferedWriter destFileBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(destFile))));
             
             //用来存放停用词的集合
             Set<String> stopWordSet = new HashSet<String>();
             
             //初始化停用词集
             String stopWord = null;
             for(; (stopWord = StopWordFileBr.readLine()) != null;){
                 stopWordSet.add(stopWord);        
             }            
             
             String paragraph = null;
             for(; (paragraph = srcFileBr.readLine()) != null;){
                 //对读入的文本进行分词
                 SegTag segTag = new SegTag(1);// 分词路径的数目          
                 SegResult segResult = segTag.split(paragraph);
                 String spiltResultStr = segResult.getFinalResult();    
                 //得到分词后的词汇数组，以便后续比较
                 String[] resultArray = spiltResultStr.split(" ");
                                 
                 //过滤停用词            
                 for(int i = 0; i< resultArray.length; i++){
                     //System.out.println(resultArray[i]);
                     if(stopWordSet.contains(resultArray[i])){
                         resultArray[i] = null;
                     }
                     //System.out.println(resultArray[i]);    
                 }
                 
                 //把过滤后的字符串数组存入到一个字符串中
                 StringBuffer finalStr = new StringBuffer();
                 for(int i = 0; i< resultArray.length; i++){
                     if(resultArray[i] != null){
                         finalStr = finalStr.append(resultArray[i]).append(" ");
                     }
                 }
                 
                 //将过滤后的文本信息写入到指定文件中
                 destFileBw.write(finalStr.toString());
                 destFileBw.newLine();
                 //输出最后的去停用词之后的结果
                 System.out.println(finalStr);
             }
             
             //关闭输入流
             destFileBw.close();
             StopWordFileBr.close();
             srcFileBr.close();            
             
         } catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch(Exception e){
             e.printStackTrace();
        }
     }
}