package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StaffService;
import service.StaffServiceBean.StaffServiceBean;

//注册人员，提交申请
public class RegistsStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StaffService staffService = new StaffServiceBean();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//从request获取请求参数
		Map<String, Object> params = new HashMap<String, Object>();
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		//申请人
		String applicant = req.getParameter("applicant");
		String email = req.getParameter("staffEmail");
		String phone = req.getParameter("staffPhone");
		String trainName = req.getParameter("trainName");
		//放入一个map
		params.put("userName", userName);
		params.put("password", password);
		params.put("applicant", applicant);
		params.put("staffEmail", email);
		params.put("staffPhone", phone);
		params.put("trainName", trainName);
		
		boolean ret = staffService.registStaff(params);
		if(ret){//如果成功，重定向到登陆页面
			resp.sendRedirect("login.html");
		}
		resp.sendError(500, "注册失败，请联系管理员");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
