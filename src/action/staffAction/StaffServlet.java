package action.staffAction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StaffService;
import service.StaffServiceBean.StaffServiceBean;

import com.alibaba.fastjson.JSONObject;

import dao.staffDao.Staff;
import dao.trainDao.Train;
import dao.trainDao.TrainDAO;

public class StaffServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StaffService staffService = new StaffServiceBean();
	private Map<String, Object> jsonObject = new HashMap<String, Object>();
	private TrainDAO trainDAO = new TrainDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String operate = req.getParameter("operate");
		JSONObject jsonObject = new JSONObject();
		if("add".equals(operate)){//新增人员
			
		}else if("update".equals(operate)){//更新方法
			jsonObject = this.staffEditParams(req, resp);
		}else if("delete".equals(operate)){//删除方法
			jsonObject = this.staffDeleteParams(req, resp);
		}else if("table".equals(operate)){//表格数据查询
			jsonObject = staffTableAllParams(req, resp);
		}else if("edit".equals(operate)){
			String staffid = (String)req.getParameter("staffId");
			if(!"".equals(staffid)){
				jsonObject = this.statfFindOneParams(req, resp);
			}else{
				jsonObject.put("state", false);
				jsonObject.put("errorMessage", "查找失败");
			}
		}
		
		try {
			resp.getWriter().write(jsonObject.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public JSONObject staffAddParams(HttpServletRequest req, HttpServletResponse resp){
		JSONObject jsonObject = new JSONObject();
		return jsonObject;
	}
	
	//更新方法
	public JSONObject staffEditParams(HttpServletRequest req, HttpServletResponse resp){
		String starffId = req.getParameter("staffId");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		
		//修改个人信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("starffId", starffId);
		map.put("email", email);
		map.put("phone", phone);
		
		Staff staff = this.staffService.findAStaff(starffId);
		staff.setEmail(email);
		staff.setPhone(phone);
		Staff saveed = this.staffService.updateStaff(staff);
		JSONObject jsonObject = new JSONObject();

		if(saveed == null){
			jsonObject.put("state", false);
			jsonObject.put("errorMessage", "修改失败");
		}else{
			jsonObject.put("state", true);
			jsonObject.put("message", "成功");
		}
		return jsonObject;
	}
	//删除方法
	public JSONObject staffDeleteParams(HttpServletRequest req, HttpServletResponse resp){
		String staffId = req.getParameter("stafId");
		JSONObject jsonObject = new JSONObject();
		int num = this.staffService.deleteStaff(staffId);
		if(num == 0){
			jsonObject.put("state", false);
			jsonObject.put("errorMessage", "删除失败！");
		}
		jsonObject.put("staffId", staffId);
		jsonObject.put("state", true);
		jsonObject.put("errorMessage", "删除成功！");
		
		return jsonObject;
	}
	//查找单个方法
	public JSONObject statfFindOneParams(HttpServletRequest req, HttpServletResponse resp){
		String staffId = req.getParameter("staffId");
		Staff staff = this.staffService.findAStaff(staffId);
		Train train = null;
		if(staff.getTrainid() != null){
			train = this.trainDAO.findById(Integer.parseInt(staff.getTrainid()));
		}else if(staff.getTrain() != null){
			train = this.trainDAO.findById(staff.getTrain().getTrainid());
		}
		staff.setTrain(train);
		JSONObject jsonObject = new JSONObject();
		if(staff == null){
			jsonObject.put("state", false);
			jsonObject.put("errorMessage", "查找失败");
		}
		jsonObject.put("state", true);
		jsonObject.put("meesage", "查找成功");
		jsonObject.put("data", staff);
		return jsonObject;
	}
	//查找所有条数
	public JSONObject staffFindAllParams(HttpServletRequest req, HttpServletResponse resp){
		JSONObject jsonObject = new JSONObject();
		return jsonObject;
	}
	//表格所有数据
	public JSONObject staffTableAllParams(HttpServletRequest request, HttpServletResponse response){
		
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
		map.put("initCondition", "student");
		
	    JSONObject jsonObject = new JSONObject();
	    StaffService service = new StaffServiceBean();
    	List list =  service.findAllStudentLimit(map);
    	
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
	
}
