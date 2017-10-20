package com.zhku.jsj144.zk.filter.practice;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//禁止浏览器缓存数据
//对于 实时性要求高的数据了, 禁止 缓存 .
//例如: 实时的打印 最新的时间 .
import javax.servlet.http.HttpServletResponse;
public class NoCacheFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//一般写过滤器时，通常上来就会看到这两行代码，就是方便后面去调用与协议相关的api
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;

		//servlet中写法
//		可以将 这三行 代码挪到 过滤器 中, 在过滤器中去解决 禁止缓存, 这样 再把 过滤器配置 一下, 那么 就可以了. 
//		response.setHeader("Cache-Control","no-cache"); //或者setHeader("cache-control","no-cache")
//		response.setHeader("Pragma","no-cache");	    //或者 setHeader("pragma","no-cache");
//		response.setDateHeader("Expires",-1);    	    //或者setDateHeader("expires",-1);

		//拦截后的统一设置
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", 0);
		
		chain.doFilter(req, resp);//放行
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	@Override
	public void destroy() {}
}
