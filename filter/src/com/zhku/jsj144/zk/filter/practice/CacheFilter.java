package com.zhku.jsj144.zk.filter.practice;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//控制浏览器缓存【对于一些不变的静态资源】--控制浏览器缓存页面中的静态资源的过滤器
public class CacheFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//目的：方便调用与协议有关的api
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		//注意：设置浏览器缓存多长时间的时候，两个值【cache-control 和 expires】最好统一，都表示对应的时间值就可以了
		
//		resp.setHeader("cache-control","1000*60*60");//错误
		
//		Cache-control: max-age=3600 ------ 控制具体的缓存 3600秒, 也就是一个小时
		resp.setHeader("cache-control","max-age=3600");//3600秒，一个小时
		
//		resp.setDateHeader("expires", 3600);//错误
		
//		Expires: 当前的时间值+缓存的时间--- 从当前时间开始,缓存 多长时间 
//		resp.setDateHeader("expires", System.currentTimeMillis()+1000*60*60);//有弊端，不好
		//这里，为了避免数值的乘积过大，导致查处了int范围，所以在这里加了一个L。这样乘积就成了一个long值，可以避免溢出
		resp.setDateHeader("expires", System.currentTimeMillis()+1000L*60*60);
		
		chain.doFilter(req, resp);//放行
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	@Override
	public void destroy() {}

}
