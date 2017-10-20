package com.zhku.jsj144.zk.filter.aotoLogin;
//业务层
public class UserService {
	public User login(String name, String password) {
		UserDao userDao=new UserDao();
		return userDao.login(name,password);
	}

}
