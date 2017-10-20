package com.zhku.jsj144.zk.filter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("世界如此美丽");
		
//		sdf  
		//如果此行代码不注释，即servlet3代码报错，
		//因为目标资源是通过声明式异常处理机制调用时，那么该过滤器：filter6将被调用
		
		//反过来说：一旦注释，filter6不会工作。
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
