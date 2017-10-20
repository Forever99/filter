package com.zhku.jsj144.zk.filter.requestFilter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 成功原因：
 * 此时的server.xml配置是：
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" 
		   URIEncoding="UTF-8"/>
*/
public class RequestServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");// 获取参数
		
		// name 以iso8859-1的方式编码为字节数组【字符-->字节】 ， 然后以utf-8的方式解码为字符串【字节-->字符】
//		name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("解码后---name:" + name);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
