package action.trainAction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TrainApplyService;
import service.TrainServiceBean.TrainApplyServiceBean;

import com.alibaba.fastjson.JSONObject;

import dao.applyDao.Trainapply;
import dao.applyDao.TrainapplyDAO;
import dao.parkDao.ParkDAO;
import dao.trainDao.TrainDAO;

public class TrainapplyVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TrainApplyService trainApplyService = new TrainApplyServiceBean();
	private Map<String, Object> jsonObject = new HashMap<String, Object>();
	private TrainDAO trainDAO = new TrainDAO();
	private TrainapplyDAO trainapplyDAO = new TrainapplyDAO();
	private ParkDAO parkDAO = new ParkDAO();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String operate = req.getParameter("operate");
		JSONObject jsonObject = new JSONObject();
		//审核方法
		String trainapplyid = (String)req.getParameter("trainapplyid");

		if("deal".equals(operate)){
			jsonObject = this.verifyTrainApply(req, resp);
		}else if("table".equals(operate)){//表格数据查询
			jsonObject = trainApplyTableAllParams(req, resp);
		}else if("find".equals(operate)){
			if(!"".equals(trainapplyid)){
				Trainapply trainapply = this.trainapplyDAO.findById(Integer.parseInt(trainapplyid));
				jsonObject.put("trainapply", trainapply);
			}else{
				jsonObject.put("state", false);
				jsonObject.put("errorMessage", "查找失败");
			}
		}
		
		try {
			System.out.println(jsonObject);
			resp.getWriter().write(jsonObject.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public JSONObject verifyTrainApply(HttpServletRequest req, HttpServletResponse resp){
		TrainapplyDAO trainapplydao = new TrainapplyDAO();
		String trainapplyid =req.getParameter("trainApplyId");
		String email = req.getParameter("email");
		String managename = req.getParameter("managename");
		String comment = req.getParameter("comment");
		
		JSONObject jsonObject = new JSONObject();//定义json串,用于返回该机构是否审核成功
		
		Trainapply trainapply = trainapplydao.findById(Integer.parseInt(trainapplyid));
		if("同意".equals(comment)){
			trainapply.setApplystate("已通过");
			this.trainApplyService.dealTrainApplye(trainapply);
			this.trainapplyDAO.save(trainapply);
			jsonObject.put("state", true);
	    	jsonObject.put("message","审核成功");
		}else{
			trainapply.setApplystate("failed");
			jsonObject.put("state", false);
			jsonObject.put("errorMessage", "审核失败");
		}
		jsonObject.put("data", trainapply);
		return jsonObject;
	}
	
	//表格所有数据
			public JSONObject trainApplyTableAllParams(HttpServletRequest request, HttpServletResponse response){
				
				String curentPage = request.getParameter("curentPage");
				String pageNumbers = request.getParameter("pageNumbers");
				String searchValue = request.getParameter("searchValue");
				String searchKey = request.getParameter("searchKey");
				String jumpPage = request.getParameter("jumpPage");
				Map<String,Object> map = new HashMap<String, Object>();
				
				map.put("curentPage", curentPage);
				map.put("pageNumbers", pageNumbers);
				map.put("searchValue", searchValue);
				map.put("searchKey", searchKey);
				map.put("jumpPage", jumpPage);
				map.put("initConditon", " applystate = '审批中' ");
				
			    JSONObject jsonObject = new JSONObject();
			    //TrainService service = new TrainServiceBean();
			    TrainApplyService service = new TrainApplyServiceBean();
		    	List list =  service.findAllTrainApplyLimit(map);
		    	
		    	if(list == null){
			    	jsonObject.put("state", false);
			    	jsonObject.put("errorMessage","查询失败");
			    }else{
			    	jsonObject.put("state", true);
			    	jsonObject.put("message","查询成功");
			    }
		    	
		    	jsonObject.put("data", list);
		 	    
				return jsonObject;
			}
}
