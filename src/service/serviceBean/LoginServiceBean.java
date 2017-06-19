package service.serviceBean;

import java.util.List;

import service.LoginService;
import dao.operatorDao.Operator;
import dao.operatorDao.OperatorDAO;
import dao.staffDao.StaffDAO;

public class LoginServiceBean implements LoginService{

	private StaffDAO staffDAO = new StaffDAO();
	
	private OperatorDAO operatorDAO = new OperatorDAO();
	
	@Override
	public Operator validatLoginUser(String userName,String password) {
		List rets = operatorDAO.validatLoginUser(userName, password);
		if(rets.size() == 0 ){
			return null;
		}else{
			return (Operator) rets.get(0);
		}
	}
	
}
