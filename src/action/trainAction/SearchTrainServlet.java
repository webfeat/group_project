package action.trainAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TrainApplyService;
import service.TrainService;
import service.TrainServiceBean.TrainApplyServiceBean;
import service.TrainServiceBean.TrainServiceBean;
import dao.trainDao.Train;
import dao.trainDao.TrainDAO;

public class SearchTrainServlet extends HttpServlet {
	private TrainDAO trainDAO = new TrainDAO();
	private TrainService trainService = new TrainServiceBean();
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String trainid = req.getParameter("trainid");
		Train train = this.trainService.search(trainid);
		
		if(train!= null){
			req.setAttribute("train", train);
		}else{
			resp.sendError(500, "error");
		}
		
	}

	
}
