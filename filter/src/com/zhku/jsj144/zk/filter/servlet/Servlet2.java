package com.zhku.jsj144.zk.filter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//默认：request
		String name=request.getParameter("abc");
		System.out.println("abc:"+name);
		
		//include
		request.getRequestDispatcher("/index.jsp").include(request, response);
		request.getRequestDispatcher("/dispatcher.jsp").include(request, response);
		response.getWriter().write("你好");
		
//		//forward
//		request.getRequestDispatcher("/head.jsp").forward(request, response);
//		
//		//error错误的时候
//		//配置错误404 500错误等
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
