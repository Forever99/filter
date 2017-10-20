package com.zhku.jsj144.zk.filter.gzipFilter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data="时间是说或或或或或或或或或或或或或或或或或或或asdflajsdlfjasldf"
				+ "asdfajsdofawfewwwwwwwwwwwww阿诗丹顿多多多多多多多多多多多多多多"
				+ "阿诗丹顿多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多多多多多多asdfasdfasdf"
				+ "说话吧思考对方还未发布猥琐地发生的不可发红丝带发斯蒂芬哈斯蒂法是否合适地方";
		response.getWriter().write(data);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
