package com.zhku.jsj144.zk.filter.requestFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//解决全站的请求数据的乱码问题
import javax.servlet.http.HttpServletResponse;
//解决全站的请求数据的乱码问题的过滤器

//获取参数的三种方式
//request.getParameter("");
//request.getParameterNames();
//request.getParameterMap();

public class RequestFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	@Override
	public void destroy() {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		System.out.println("开始工作。。。。。。。。。。。。。。。。");
		
//		String method = req.getMethod();//获取请求参数的方法
//		//get方式请求
//		if("get".equals(method)){
			
			
			//思路：获取参数有三种方式，我们的目的：就是对这三种方式进行改写：即增强它们的功能
			//装饰者模式，我们的思路时加强request,然后把加强后的request传递下去，这样，
			//在使用加强后的request的获得参数的三种方法，就可以获得设置编码后的，正确的中文参数了。
			
			//Servlet API 中提供了一个request对象的Decorator设计模式的默认实现类HttpServletRequestWrapper
			//注意：HttpServletRequestWrapper 类实现了request 接口中的所有方法，
			//但这些方法的内部实现都是仅仅调用了一下所包装的的 request 对象的对应方法
			
			//我们继承：HttpServletRequestWrapper，然后达到自己的目的
			MyHttpServletRequestWrapper myRequest =new MyHttpServletRequestWrapper(req);
			
//			req.setCharacterEncoding("utf-8");
//			chain.doFilter(req, resp);//放行
			
			//增强后的request：myRequest--然后放行
			chain.doFilter(myRequest, resp);//放行
//		}
//		//post方式请求
//		else{
//			req.setCharacterEncoding("utf-8");
//			chain.doFilter(req, resp);//放行
//		}
	}

	

}
