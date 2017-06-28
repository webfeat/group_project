package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.fastjson.JSONObject;

import service.LoginService;
import service.serviceBean.LoginServiceBean;
import dao.moduleDao.Module;
import dao.moduleDao.ModuleDAO;
import dao.operatorDao.Operator;
import dao.staffDao.Staff;

/**
 * 登陆操作
 * @author samsung
 *
 */
public class LoginAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private LoginService loginService = new LoginServiceBean();
	private ModuleDAO moduleDAO = new ModuleDAO();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    String isAllowed = request.getParameter("allow");
	    if("false".equals(isAllowed)){
			response.sendRedirect("/group_project/yara/loginForm.html");
	    }
		String userName = (String) request.getParameter("userName");
		String password = (String) request.getParameter("password");
		//以键值对的方式存储登陆人信息
		Map<String, Object> loginUser = new HashMap<String, Object>();
		//安全，做md5加密
		password = DigestUtils.md5Hex(password);
		//身份验证
		Operator operator = loginService.validatLoginUser(userName, password);
		//查询所有的模块
		if(operator == null){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("state",false);
			jsonObject.put("errorMessage", "密码错误");
			PrintWriter out = response.getWriter();
			Cookie cookie = new Cookie("message", "504");
			response.addCookie(cookie);
			response.getWriter().write(jsonObject.toJSONString());
		}else{
			Staff staff = operator.getStaff();
			if(staff != null){
				JSONObject jsonObject = new JSONObject();
				//将人员保存到后台session
				List modules = this.moduleDAO.findMenuModule(operator.getOperatorid().toString());
				request.getSession().setAttribute("modules", modules);
				request.getSession().setAttribute("loginUser", staff);
				//定向到首页
				response.getWriter().write(jsonObject.toJSONString());
			}else{
				//登陆验证未通过时，重定向
				response.sendRedirect("/group_project/yara/loginForm.html");
			}
		}
	}
	
}
