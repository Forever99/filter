package com.zhku.jsj144.zk.filter.aotoLogin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//自动登录过滤器
//弄清楚的一点是：对于一个网站，曾经登录过，下次再登录时，可以自动登录
//结论：就是第二次或者第二次以上使用该网站，处于未登录状态时，准备自动登录这样一个时刻
public class AutoLoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//总的思路：3步分析
		//1.是否已经登录，如果登录，直接放行；如果没有登录，则进入2
		//2.获得带过来的cookie,如果没有找到，则直接放行；如果找到了，则进入3
		//3.获得cookie里的信息：用户名和密码,进行登录，如果登录失败，则直接放行，如果登录成功，则存入session中
		
		//代码实现
		
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		//1.是否已经登录，如果登录，直接放行；如果没有登录，则进入2
		User user=(User) req.getSession().getAttribute("user");
		if(user!=null){
			chain.doFilter(req, resp);//放行
		}
		else{
			//2.获得带过来的cookie,如果没有找到，则直接放行；如果找到了，则进入3
			Cookie[] cookies = req.getCookies();
			for (Cookie cookie : cookies) {
				if(!cookie.getName().equals("user")){
					chain.doFilter(req, resp);//放行
				}
				else{
					//3.获得cookie里的信息：用户名和密码,进行登录，如果登录失败，则直接放行，如果登录成功，则存入session中
					String value = cookie.getValue();
//					Cookie("user",name+"---"+password)//user : name+"---"+password
					String[] split = value.split("---");
					String name=split[0];
					String password=split[1];
					UserService userService=new UserService();
					User loginUser=userService.login(name, password);
					if(loginUser==null){//登录失败
						chain.doFilter(req, resp);//放行
					}
					else{//登录成功
						//登录并保存session信息
						req.getSession().setAttribute("user",loginUser);
						req.getRequestDispatcher("/loginMessage.jsp").forward(req, resp);
						chain.doFilter(req, resp);//放行
					}
				}
			}
		}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	@Override
	public void destroy() {}
}
