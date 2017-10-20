package com.zhku.jsj144.zk.filter.aotoLogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取登录信息
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String aotoLogin = request.getParameter("aotoLogin");//选中为：on,未选中为：null
		System.out.println("autoLogin:"+aotoLogin);
		
		UserService userservice=new UserService();
		User user=userservice.login(name,password);//进行登录判断
		if(user==null){//登录失败
			request.setAttribute("msg", "登录失败，用户名或者密码错误");
			request.getRequestDispatcher("/loginMessage.jsp").forward(request, response);
		}
		else{//登录成功
			
			//选中自动登录，才进行设置cookie信息
			if("on".equals(aotoLogin)){
				//设置登录成功的cookie
				Cookie cookie=new Cookie("user",name+"---"+password);//将用户名，密码保存到
				
				//name="张三"+password="123" ---失败
				//抛出异常：Control character in cookie value or attribute.
				//原因是有关中文编码的问题，中文采用的是unicode编码，而英文采用的是ASCII编码，
				//所以当COOkie保存中文的时候需要对中文进行编码，而且从Cookie中取出内容的时候也要进行解码，编码和解码可以使用
				//URLEncoder.encode(name, "utf-8");
				//URLDecoder.decode(cookies[i].getName(),"utf-8")
				
				cookie.setMaxAge(60*60*24);//一天
				cookie.setPath("/");//路径？？？？？
				response.addCookie(cookie);//服务器恢复给浏览器信息的时候，把cookie带过去
			}
			
			//设置登录成功的session
			request.getSession().setAttribute("user", user);
			//设置成功信息
			request.setAttribute("msg", "登录成功");
			request.getRequestDispatcher("/loginMessage.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
