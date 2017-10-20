package com.zhku.jsj144.zk.filter.demo1;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Filter2 implements Filter {

	@Override
	public void destroy() {
		System.out.println("filter2 destroy....");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		//拦截代码
		System.out.println("filter2 ... start work   之前....");
		
		//放行代码
		chain.doFilter(request, response);//放行
		
		System.out.println("filter2 ... start work   之后....");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter2 init....");
	}

}
