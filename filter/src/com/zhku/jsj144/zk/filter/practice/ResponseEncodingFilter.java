package com.zhku.jsj144.zk.filter.practice;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//设置全站统一响应编码【解决乱码问题】
public class ResponseEncodingFilter implements Filter {

	private FilterConfig config;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config=filterConfig;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//拦截代码
//		response.setContentType("text/html;charset=utf-8");
		String encoding=this.config.getInitParameter("encoding");
		response.setContentType("text/html;charset="+encoding);
		//放行代码
		chain.doFilter(request, response);
	}
	@Override
	public void destroy() {}
}
