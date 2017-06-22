package action.staffAction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StaffApplyService;
import service.StaffService;
import service.StaffServiceBean.StaffServiceBean;
import service.staffApplyService.StaffApplyServiceBean;

import com.alibaba.fastjson.JSONObject;

import dao.BaseDao;

public class StaffApplyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private StaffApplyService staffApplyService = new StaffApplyServiceBean();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String operate = req.getParameter("operate");
		JSONObject jsonObject = new JSONObject();
		addStaffApply(req, resp);
		if("add".equals(operate)){//新增人员
			jsonObject = addStaffApply(req, resp);
			this.staffApplyService.saveAStaffApply(jsonObject);
		}else if("deal".equals(operate)){
			String commont = req.getParameter("comment");
			jsonObject.put("comment", commont);
		}else if("table".equals(operate)){
			jsonObject = this.staffApplyTable(req, resp);
		}else if("find".equals(operate)){
			
		}
		resp.getWriter().write(jsonObject.toJSONString());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(req, resp);
	}
	
	public JSONObject staffApplyTable(HttpServletRequest req, HttpServletResponse resp){
		
		String curentPage = req.getParameter("curentPage");
		String pageNumbers = req.getParameter("pageNumbers");
		String searchValue = req.getParameter("searchValue");
		String searchKey = req.getParameter("searchKey");
		String jumpPage = req.getParameter("jumpPage");
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("curentPage", curentPage);
		map.put("pageNumbers", pageNumbers);
		map.put("searchValue", searchValue);
		map.put("searchKey", searchKey);
		map.put("jumpPage", jumpPage);
		
	    JSONObject jsonObject = new JSONObject();
	    StaffService service = new StaffServiceBean();
    	List list =  BaseDao.findAllTrainLimit(map, "staffApply");
    	
    	if(list == null){
	    	jsonObject.put("state", false);
	    	jsonObject.put("errorMessage", "查询失败");
	    }else{
	    	jsonObject.put("state", true);
	    	jsonObject.put("message", "查询成功");
	    }
    	
    	jsonObject.put("data", list);
 	    String rets = jsonObject.toJSONString(jsonObject);
 	    
		return jsonObject;
	}
	
	public JSONObject addStaffApply(HttpServletRequest req, HttpServletResponse resp){
		String staffName = req.getParameter("userName");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String trainName = req.getParameter("trainName");
		String password =req.getParameter("password");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("staffName", staffName);
		jsonObject.put("email", email);
		jsonObject.put("phone", phone);
		jsonObject.put("trainName", trainName);
		jsonObject.put("password", password);
		
		return jsonObject;
	}
	
	public JSONObject findOneStaffApply(HttpServletRequest req, HttpServletResponse resp){
		String staffApplyid = req.getParameter("staffApplyId");
		this.staffApplyService.
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("staffName", staffName);
		return jsonObject;
	}
	
}