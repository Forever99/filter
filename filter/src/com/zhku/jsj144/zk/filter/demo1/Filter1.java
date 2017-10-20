package com.zhku.jsj144.zk.filter.demo1;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Filter1 implements Filter {

	@Override
	public void destroy() {
		System.out.println("filter1 destroy....");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		//注意：来和回的拦截顺序思考：
		//拦截顺序与  在此过滤顺序是相反的
		//filter1 work 之前-->filter2 work 之前-->servlet1 work-->filter2 work 之后-->filter1 work 之后
		
		//拦截代码【拦截之后的一些公共处理办法】
		System.out.println("filter1 ... start work   之前....");
		
		//放行代码
		chain.doFilter(request, response);//放行
		
		System.out.println("filter1 ... start work   之后....");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter1 init....");
	}

}
