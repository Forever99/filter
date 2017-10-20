<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'GetAndPost.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<!--   get方式请求 -->
	get方式请求 <br/>
  <form action="/filter/requestServlet2" method="get">
	  姓名：<input type="text" name="name">
  	<input type="submit" value="提交"> 
  </form>
  
  <!--   post方式请求 -->
  post方式请求 <br/>
   <form action="/filter/requestServlet2" method="post">
	  姓名：<input type="text" name="name">
  	<input type="submit" value="提交"> 
  </form>
  
  </body>
</html>
