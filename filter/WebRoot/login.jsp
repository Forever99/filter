<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
 <form action="/filter/login" method="post">
用户名：<input type="text" name="name"><br/>
密     码：<input type="password" name="password"><br/>
自动登录<input type="checkbox" name="aotoLogin" value="on"><br/>
<input type="submit" value="登录">&nbsp;&nbsp;&nbsp; <input type="reset" value="重置"> 
 </form>
  </body>
</html>
