package action.trainAction;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.mail.smtp.DigestMD5;

import service.TrainApplyService;
import service.TrainServiceBean.TrainApplyServiceBean;
import dao.applyDao.Trainapply;
import dao.applyDao.TrainapplyDAO;
import database.HibernateSessionFactory;

public class RegistTrainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TrainApplyService trainApplyService = new TrainApplyServiceBean();

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(req, resp);
			}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/*String operation = req.getParameter("operation");
		if(operation.equals("register")){
			register();
		}*/
		Map<String, Object> params = new HashMap<String, Object>();
		TrainapplyDAO trainapplyDAO = new TrainapplyDAO();
		Trainapply trainapply = new Trainapply();
		
		String applicant = req.getParameter("applicant");
		
		String password = req.getParameter("password");
		String managename = req.getParameter("managename");
		String email = req.getParameter("email");
		String managephone = req.getParameter("managephone");
		String trainname = req.getParameter("trainname");
		
		params.put("applicant", applicant);
		params.put("password", password);
		params.put("managename", managename);
		params.put("email", email);
		params.put("managephone", managephone);
		params.put("trainname", trainname);
		
		
		System.out.print(params.get("applicant"));
		boolean ret = this.trainApplyService.saveTrainApply(params);
		if(ret==false){//如果成功，重定向到登陆页面
			trainapply.setApplicant(params.get("applicant").toString());
			trainapply.setPassword(DigestUtils.md5Hex(params.get("password").toString()));
			trainapply.setManagename(params.get("managename").toString());
			trainapply.setEmail(params.get("email").toString());
			trainapply.setManagephone(params.get("managephone").toString());
			trainapply.setTrainname(params.get("trainname").toString());
			trainapply.setTrainapplyid(trainapplyDAO.generateEntityId());
			trainapply.setApplystate("审批中");
			trainapplyDAO.save(trainapply);
			resp.sendRedirect("/group_project/yara/loginForm.html");
		}
		else{
			resp.sendError(500, "注册机构失败，请联系管理员");
			}
	}
}
