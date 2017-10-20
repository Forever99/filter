package com.zhku.jsj144.zk.filter.requestFilter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//解决全站请求数据的乱码问题

/*
 * 失败原因：
 * 此时的server.xml配置是：
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" 
		   URIEncoding="UTF-8"/>
*/
public class RequestServlet extends HttpServlet {

	//get请求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String name=request.getParameter("name");//获取参数
		System.out.println("解码前---name:"+name);
		//通过使用指定的 charset 解码指定的 byte 数组，构造一个新的 String
		
		//解码：字节-->字符  //编码：字符-->字节
		
		//失败1：tomcat的默认编码：由iso-8859-1该为了utf-8
		
//		name是通过utf-8进行编码的，而此时通过ISO-8859-1去进行解码，就会出现解码错误的情况，
//		而解码出错的情况下，有通过utf-8去进行编码，来获得传递过来的中文参数：name，就会出现乱码情况。
		
		//name 以iso8859-1的方式编码为字节数组【字符-->字节】 ， 然后以utf-8的方式解码为字符串【字节-->字符】
		name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		
		//失败2:不同编码方式进行编码解码，可能会失败
		//name 以 utf-8 的方式编码为字节数组【字符-->字节】 ， 然后以gbk的方式解码为字符串【字节-->字符】
		name = new String(name.getBytes("UTF-8"),"gbk");
		
		System.out.println("解码后---name:"+name);
		
		
	}
	
	//post请求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");//获取参数
		System.out.println("name:"+name);

	}
}
