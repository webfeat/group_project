package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import service.LoginService;
import service.serviceBean.LoginServiceBean;
import dao.operatorDao.Operator;
import dao.staffDao.Staff;

public class LoginAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private LoginService loginService = new LoginServiceBean();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = (String) request.getParameter("userName");
		String password = (String) request.getParameter("passwrod");
		//以键值对的方式存储登陆人信息
		Map<String, Object> loginUser = new HashMap<String, Object>();
		//安全，做md5加密
		password = DigestUtils.md5Hex(password);
		//身份验证
		Operator operator = loginService.validatLoginUser(userName, password);
		Staff staff = operator.getStaff();
		if(staff != null){
			//将人员放到前台request
			request.setAttribute("login", staff);
			//将人员保存到后台session
			request.getSession().setAttribute("loginUser", loginUser);
			//定向到首页
			response.sendRedirect("index.html");
		}else{
			//登陆验证未通过时，重定向
			response.sendRedirect("login.html");
		}
	}
}
