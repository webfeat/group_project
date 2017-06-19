package service;

import dao.operatorDao.Operator;

public interface LoginService {
	//查找用户是否合法
	public Operator validatLoginUser(String userName,String password);
}
