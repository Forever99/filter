package com.zhku.jsj144.zk.filter.aotoLogin;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

//持久层
public class UserDao {
	public User login(String name, String password) {
		QueryRunner qr=new QueryRunner(JdbcUtils.getDatasourse());
		
		try {
			User user=qr.query("select * from user where name=? and password=?",new BeanHandler(User.class),name,password);
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
