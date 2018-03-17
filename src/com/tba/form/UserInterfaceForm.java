package com.tba.form;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.tba.db.DBTieba;
import com.tba.spider.ReadDate;

public class UserInterfaceForm extends JFrame {

	JTextArea textShow;
	JTextField textEdit;
	boolean flag = true ;
	public UserInterfaceForm() {
		super("我的窗口");
		this.setBounds(100, 100, 560, 390);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setResizable(false);
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JTextField textField = new JTextField();
		textField.setBorder(null);
		panel1.add(textField);
		panel1.add(new JLabel("贴吧："));
		textEdit = new JTextField(20);
		textEdit.setText("请输入贴吧名称");
		panel1.add(textEdit);
		JButton btn = new JButton("搜索");

		panel1.add(btn);
		this.add(panel1,"North");
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		textShow = new JTextArea(16,50);
		textShow.setLineWrap(true);
		textShow.setWrapStyleWord(true);
		panel2.add(new JScrollPane(textShow));
		this.add(panel2,"Center");
		btn.requestFocus(true);
		btn.addActionListener(new MyActionListenser());
		textEdit.addFocusListener(new MyFocusListenser());
		this.setVisible(true);
	}
	//转为URL
	private String strToURL(String str){
		str = str.replace("贴", "");
		str = str.replace("吧", "");
		return "http://tieba.baidu.com/f?kw="+str;
	}
	//检查输入是否合法
	private boolean checkStr(String str){
		int length = str.length();
		for(int i=0;i<length;i++){
			char ch = str.charAt(i);
			if (!( ch >= 'A' && ch <= 'z' // 字母
					|| ch >= '\u4E00' && ch <= '\u9FA5'//汉字
					|| ch >= '0' && ch <= '9')){
				return false;
			}
		}
		return true;
	}
	//检查URL是否存在
	private boolean URLexists(String URLName) {
		try {

			//设置此类是否应该自动执行 HTTP 重定向（响应代码为 3xx 的请求）。

			HttpURLConnection.setFollowRedirects(false);

			//到 URL 所引用的远程对象的连接

			HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();

			/* 设置 URL 请求的方法， GET POST HEAD OPTIONS PUT DELETE TRACE 以上方法之一是合法的，具体取决于协议的限制。*/

			con.setRequestMethod("HEAD");

			//从 HTTP 响应消息获取状态码(HttpURLConnection.HTTP_OK=200,说明正常打开URL)

			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);

		} catch (Exception e) {

			e.printStackTrace();

			return false;

		}
	}
	//点击清空文本框
	private class MyFocusListenser implements FocusListener{

		public void focusGained(FocusEvent e) {
			if (!textEdit.getText().equals("") && flag) {
				textEdit.setText("");
				flag = false ;
			}

		}

		
		public void focusLost(FocusEvent e) {
			if (textEdit.getText().equals("")) {
				textEdit.setText("请输入贴吧名称");
				flag = true ;
			}
		}

	}
    //爬虫 收集贴吧信息  存入DBTieba表中
	private class MyActionListenser implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			if (!checkStr(textEdit.getText())) {
				JOptionPane.showMessageDialog(null,
						"只能输入汉字、数字和字母", "提示信息", JOptionPane.INFORMATION_MESSAGE);
			}else {
				String URL = strToURL(textEdit.getText());
				if (URLexists(URL)) {
//					JOptionPane.showMessageDialog(null,"贴吧存在", "提示信息",
//							JOptionPane.INFORMATION_MESSAGE);
					
					//DBTieba dbt=new DBTieba();
					try {
						//dbt.Build();
						//dbt.Input(textEdit.getText().toString());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null,"贴吧不存在", "提示信息",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

	}
	
	public  void showtext(String s){
    	
    	
    	textShow.append(s+"\n");
    	
    	
    }


}
  
