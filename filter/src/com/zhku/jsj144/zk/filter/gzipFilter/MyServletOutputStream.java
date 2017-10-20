package com.zhku.jsj144.zk.filter.gzipFilter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

//增强后的outputStream
public class MyServletOutputStream extends ServletOutputStream {

//	private ServletOutputStream out;
	private OutputStream outputStream;
	
	public MyServletOutputStream(OutputStream outputStream){
		this.outputStream=outputStream;//获得原来的outputStream
	}
	
	//思考：如何改写write方法
	
	//这里没有一个接受字节数组的write方法
		
	//但是我们通过找ServletOutputStream的父类OutputStream,可以发现OutputStream里面的write(byte[] b)
	//里面的最终调用的方法是write(int b)
	
	//思考方式：当继承的父类方法受限，我们要考虑Java的多态机制，要找一个突破口：调用父类的父类的方法进行继承，
	//		来摆脱方法不够的限制
	
	//详细解释：
	//OutputStream 里面的write(int b)方法  : 
	//是一个抽象方法：public abstract void write(int b) throws IOException;
	
	//OutputStream 里面的write的字节数组的方法  
//	public void write(byte b[], int off, int len) throws IOException {
//        if (b == null) {
//            throw new NullPointerException();
//        } else if ((off < 0) || (off > b.length) || (len < 0) ||
//                   ((off + len) > b.length) || ((off + len) < 0)) {
//            throw new IndexOutOfBoundsException();
//        } else if (len == 0) {
//            return;
//        }
//        for (int i = 0 ; i < len ; i++) {
//            write(b[off + i]);            --------------->write(int b)
//        }
//    }
	@Override
	public void write(int b) throws IOException {
		outputStream.write(b);//调用父类的父类的方法
		
		//所有的数据 都进入 到了outputStream中去 了,
		//而这里outputStream又 是  MyHttpServletResponseWrapper里的 bout[ByteArrayOutputStream ]了, 
		//所以 所有的数据 都 在 bout[ByteArrayOutputStream:字节数组输出流]中了 
		
	}

}
