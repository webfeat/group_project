package action.trainAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TrainService;
import service.TrainServiceBean.TrainServiceBean;
public class DeleteTrainServlet extends HttpServlet {
	private TrainService trainService = new TrainServiceBean();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String trainid = req.getParameter("trainid");
		boolean ret = this.trainService.delete(trainid);
		if(ret == true){  
			//请求重定向
			resp.sendRedirect("index.html");
		}
		resp.sendRedirect("#");
		
	}

	

}
