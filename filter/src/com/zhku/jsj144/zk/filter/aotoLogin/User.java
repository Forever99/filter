package com.zhku.jsj144.zk.filter.aotoLogin;
/*
 --数据库及其数据表的相关创建语句
 create database filter;
 use filter;
 create table user(
   id int,
   name varchar(30),
   password varchar(30)
 );
insert into user values(1,'张三','123');
insert into user values(1,'zk','123');
insert into user values(1,'123','123');
 */
public class User {
	private String name;//用户名
	private String password;//密码
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
