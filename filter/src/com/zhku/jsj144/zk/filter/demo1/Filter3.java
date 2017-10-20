package com.zhku.jsj144.zk.filter.demo1;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Filter3 implements Filter {

	private FilterConfig config;//定义成员变量-->  目的：获得过滤器配置参数

	@Override
	public void destroy() {
		System.out.println("filter3 destroy....");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		//拦截代码
		System.out.println("filter3 ... start work  ....");
		
		String name=config.getInitParameter("name");
		String encoding=config.getInitParameter("encoding");
		System.out.println("name:"+name);
		System.out.println("encoding:"+encoding);
		
		//放行代码
		chain.doFilter(request, response);//放行
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter3 init....");
		this.config=filterConfig;
		
		
	}

}
