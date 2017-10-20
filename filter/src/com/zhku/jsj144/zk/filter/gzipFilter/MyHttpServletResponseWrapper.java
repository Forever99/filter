package com.zhku.jsj144.zk.filter.gzipFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
/*
	思路:
	通过filter向目标页面传递一个自定义的response对象。
	在自定义的response对象中，重写getOutputStream方法和getWriter方法，
	使目标资源调用此方法输出页面内容时，获得的是我们自定义的ServletOutputStream对象。
	在我们自定义的ServletOuputStream对象中，重写write方法，使写出的数据写出到一个buffer中。
	当页面完成输出后，在filter中就可得到页面写出的数据，
	从而我们可以调用GzipOuputStream对数据进行压缩后再写出给浏览器，以此完成响应正文件压缩功能。
*/
public class MyHttpServletResponseWrapper extends HttpServletResponseWrapper {

	private HttpServletResponse response;
	
	private ByteArrayOutputStream bout = new ByteArrayOutputStream();//数据写入到字节输出流中
	private PrintWriter pw;
	
	public MyHttpServletResponseWrapper(HttpServletResponse response) {
		super(response);
		this.response=response;
	}
	//获得压缩前的数据
	public byte[] getOldData(){
		if(pw!=null){
			pw.close();//将缓冲区中的数据都写到bout中
		}
		return bout.toByteArray();//字节数据中的数据
	}
	
	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		
//		ServletOutputStream outputStream = response.getOutputStream();
//		outputStream.write(b);//原来的写方法
		
		//目的：重写outputStream里面的write方法，达到目的
		//增强后的outputStream，里面的write方法已经改写了
		
		//数据写入到字节输出流中
		 MyServletOutputStream myoutputStream=new MyServletOutputStream(bout);
		 return myoutputStream;
//		return super.getOutputStream();
	}
	@Override
	public PrintWriter getWriter() throws IOException {
		//字符输出流，有缓缓从去
		if(pw==null){
			//PrintWriter(OutputStream out)
			//pw=new PrintWriter(bout);//没有设置编码，失败
			
			//字符流-->字节流
			pw=new PrintWriter(new OutputStreamWriter(bout,"utf-8"));
		}
		return pw;
//		return super.getWriter();
	}

}
