package com.zhku.jsj144.zk.filter.gzipFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//在实际开发过程中，你访问的资源文件，输出数据时，是会对数据进行压缩的。
//为什么要压缩？
//是为了节省带宽，提高速度，增强用户体验【很多时候，对于网站来说，也需要进行数据压缩】
//如何进行压缩？
//写Java代码压缩，目前市场用的最多的压缩方式是gzip压缩。  gzip  ,tar--bzip
//Javaweb（压缩）--浏览器去看数据时，当数据时被压缩了，name就会去做戒烟所的动作

//完成压缩和解压缩的功能
//压缩：读取服务器端要发给客户端的信息，进行压缩后，再写给客户端。
//原理：对于要压缩的数据，以某种输出流的形式进行压缩

public class GzipServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//需要压缩的数据源
		String data="时间是说或或或或或或或或或或或或或或或或或或或asdflajsdlfjasldf"
				+ "asdfajsdofawfewwwwwwwwwwwww阿诗丹顿多多多多多多多多多多多多多多"
				+ "阿诗丹顿多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多多多多多多asdfasdfasdf"
				+ "说话吧思考对方还未发布猥琐地发生的不可发红丝带发斯蒂芬哈斯蒂法是否合适地方";
		byte[] b=data.getBytes();
		System.out.println("压缩前数据长度："+b.length);
		
		//字节数组流（底层流）
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		
		//采用gzip压缩,这里压缩是，需要的是一个输出流，这里的输出流是一个底层流
		
		//GZIPOutputStream:此类为使用 GZIP 文件格式写入压缩数据--【压缩】
		//GZIPInputStream :此类为读取 GZIP 文件格式的压缩数据    --【解压缩】

		GZIPOutputStream gos=new GZIPOutputStream(baos);
		
		gos.write(data.getBytes());
		gos.close();//确保数据可以写到底层流中去
		
		//说明：由于数据是写到底层流中baos中去的，gos默认的是有缓冲区的
		b = baos.toByteArray();
		System.out.println("压缩后数据长度："+b.length);
		
		//注意，如果数据压缩了，一定要告诉浏览器，因为浏览器要知道怎样读取数据
		//告诉浏览器 需要 解压缩 数据-- 通过 http的响应头 实现
		
		response.setHeader("content-encoding", "gzip");//压缩格式告诉浏览器
		response.setContentLength(b.length);  //数据的长度 
		response.getOutputStream().write(b);//压缩后的数据写给浏览器
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
