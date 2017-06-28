package action.trainAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TrainApplyService;
import service.TrainServiceBean.TrainApplyServiceBean;
import dao.BaseDao;
import dao.trainDao.TrainDAO;

public class SearchAllTrains extends HttpServlet {
	private TrainDAO train = new TrainDAO();
	private TrainApplyService trainapplyService = new TrainApplyServiceBean();
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				doPost(req,resp);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, Object> params = (Map<String, Object>) train.findAll();
			BaseDao.findAllTrainLimit(params, train.getClass().getName());
			
		
	}
}
