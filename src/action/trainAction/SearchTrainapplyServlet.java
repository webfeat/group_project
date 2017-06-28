package action.trainAction;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.applyDao.Trainapply;
import service.TrainApplyService;
import service.TrainServiceBean.TrainApplyServiceBean;

public class SearchTrainapplyServlet extends HttpServlet {

	private TrainApplyService trainapplyService = new TrainApplyServiceBean();
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String managename = req.getParameter("managename");
		List ta = this.trainapplyService.search(managename);
		
		
		if(ta != null){
			req.setAttribute("trainapply", ta);
		}else{
			resp.sendError(500, "error");
		}
		
	}

}
