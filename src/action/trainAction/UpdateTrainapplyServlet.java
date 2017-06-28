package action.trainAction;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import service.TrainApplyService;
import service.TrainService;
import service.TrainServiceBean.TrainApplyServiceBean;
import service.TrainServiceBean.TrainServiceBean;
import dao.applyDao.Trainapply;
import dao.applyDao.TrainapplyDAO;
import dao.trainDao.Train;
import dao.trainDao.TrainDAO;
import database.HibernateSessionFactory;

public class UpdateTrainapplyServlet extends HttpServlet {
	private TrainApplyService trainapplyservice = new TrainApplyServiceBean();
	//调用服务层，实现培训机构的更新操作
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		Train train = new Train();
		
		String manageid = req.getParameter("manageid");
		String managename = req.getParameter("managename");
		String managephone = req.getParameter("managephone");
		
		params.put("manageid", manageid);
		params.put("managename", managename);
		params.put("managephone", managephone);
		
		boolean ret = this.trainapplyservice.update(params);
		
		if(ret == false){
			//判断是否已经拥有这个培训机构
			Session session = HibernateSessionFactory.getSession();
			Transaction transaction = session.beginTransaction();
			train.setManageid(Integer.parseInt(params.get("manageid").toString()));
			train.setManagename(params.get("managename").toString());
			train.setManagephone(params.get("managephone").toString());
			session.save(train);
			transaction.commit();
	        session.close();
	        resp.sendRedirect("table.html");
		}else{
			resp.sendError(500, "error");
		}
	}

	
}
