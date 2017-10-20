package com.zhku.jsj144.zk.filter.requestFilter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//解决全站请求数据的乱码问题

/*
 * 成功：
 * 此时的server.xml配置是：
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443"/>
*/
public class RequestServlet3 extends HttpServlet {
	//get请求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name=request.getParameter("name");//获取参数
		System.out.println("解码前---name:"+name);
		
		//解码：字节-->字符  //编码：字符-->字节
		
		//成功：因为tomcat的默认编码：由iso-8859-1
		
		//name 以iso8859-1的方式编码为字节数组【字符-->字节】 ， 然后以utf-8的方式解码为字符串【字节-->字符】
		name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
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
