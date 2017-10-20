package com.zhku.jsj144.zk.filter.gzipFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//全站数据的压缩
//压缩的对象：服务器写给浏览器的数据，进行压缩后，然后才写给浏览器
//思考：
//1.如何获取压缩的数据源？
//2.压缩针对的是什么？repsonse对象
//3.压缩针对的是什么方法？response对象的：response.getOutputStream().write()  以及   response.getWriter().write()  
//				  因为写给浏览器的数据，都是通过它们实现的。
import javax.servlet.http.HttpServletResponse;

//分析：
//1.我们要获得改造后的response,为此，我们要使用装饰者模式：装饰response，重写里面的getOutputStream()  以及   getWriter()
//	主要目的是为了：获得压缩的数据源
//2.然后调用压缩相关对象：GZIPOutputStream:此类为使用 GZIP 文件格式写入压缩数据--【压缩】，进行压缩操作
//3.将压缩后的数据发送给浏览器端

public class GzipFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		//目的：装饰resp
		MyHttpServletResponseWrapper myResponse=new MyHttpServletResponseWrapper(resp);
		
		chain.doFilter(req, myResponse);//放行
		//目标资源得到执行
		
		//获取压缩的原始数据---------------------------------------------
		//重点！！！！
		byte[] b=myResponse.getOldData();//获得压缩前的原始数据
		System.out.println("压缩前---数据的长度:"+b.length);
		
		//压缩代码----------------------------------------------
		//底层流 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//采用gzip压缩 
		
		//这里 压缩的时候, 需要的是一个 输出流, 这里的输出流是一个底层流 
		GZIPOutputStream gout= new GZIPOutputStream(baos);
		gout.write(b);
		gout.close();  //确保 数据可以写到 底层流 baos中去 
		
		//这里 要注意: 由于 数据是写到 底层流 baos中去的,  gout 默认的是有 缓冲区的 .
		b = baos.toByteArray();
		System.out.println("压缩后---数据的长度:"+b.length);
		
		//告诉浏览器 需要 解压缩 数据-- 通过 http的响应头 实现
		resp.setHeader("content-encoding", "gzip");
		resp.setContentLength(b.length);  //数据的长度 
		
		//将压缩后的数据写给浏览器
		response.getOutputStream().write(b);
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	@Override
	public void destroy() {}
}
