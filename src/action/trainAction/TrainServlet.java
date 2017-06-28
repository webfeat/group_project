package action.trainAction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TrainService;
import service.TrainServiceBean.TrainServiceBean;

import com.alibaba.fastjson.JSONObject;

import dao.parkDao.Park;
import dao.parkDao.ParkDAO;
import dao.trainDao.Train;
import dao.trainDao.TrainDAO;

public class TrainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TrainService trainService = new TrainServiceBean();
	private Map<String, Object> jsonObject = new HashMap<String, Object>();
	private TrainDAO trainDAO = new TrainDAO();
	private ParkDAO parkDAO = new ParkDAO();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String operate = req.getParameter("operate");
		JSONObject jsonObject = new JSONObject();
		if("add".equals(operate)){//新增人员
			
		}else if("update".equals(operate)){//更新方法
			jsonObject = this.trainEditParams(req, resp);
		}else if("delete".equals(operate)){//删除方法
			jsonObject = this.trainDeleteParams(req, resp);
		}else if("table".equals(operate)){//表格数据查询
			jsonObject = trainTableAllParams(req, resp);
		}else if("edit".equals(operate)){
			String staffid = (String)req.getParameter("staffId");
			if(!"".equals(staffid)){
				jsonObject = this.trainFindOneParams(req, resp);
			}else{
				jsonObject.put("state", false);
				jsonObject.put("errorMessage", "查找失败");
			}
		}else if("findAll".equals(operate)){
			List ret = this.trainService.findAll();
			if(ret == null){
				jsonObject.put("state", false);
				jsonObject.put("errorMessage", "查找失败");
			}else{
				jsonObject.put("data", ret);
			}
		}
		
		try {
			resp.getWriter().write(jsonObject.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	public JSONObject staffAddParams(HttpServletRequest req, HttpServletResponse resp){
		JSONObject jsonObject = new JSONObject();
		return jsonObject;
	}
	
	public JSONObject trainEditParams(HttpServletRequest req, HttpServletResponse resp){
		
		String trainname = req.getParameter("trainname");
		String trainid = req.getParameter("trainid");
		String parkid = req.getParameter("parkid");
		String managename = req.getParameter("managename");
		String manageid = req.getParameter("manageid");
		String managephone = req.getParameter("managephone");
		
		//修改个人信息
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("trainname", trainname);
				map.put("trainid", trainid);
				map.put("parkid", parkid);
				map.put("managename", managename);
				map.put("manageid", manageid);
				map.put("managephone", managephone);
				
				
				Train train = this.trainService.search(trainid);
				train.setTrainname(trainname);
				train.setTrainid(Integer.parseInt(trainid));
				train.setParkid(Integer.parseInt(parkid));
				train.setManagename(managename);
				train.setManageid(Integer.parseInt(manageid));
				train.setManagephone(managephone);
				Train saveed = this.trainService.update(train);
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
	
	public JSONObject trainDeleteParams(HttpServletRequest req, HttpServletResponse resp){
		String trainid = req.getParameter("trainid");
		JSONObject jsonObject = new JSONObject();
		int num = this.trainService.delete(trainid);
		if(num == 0){
			jsonObject.put("state", false);
			jsonObject.put("errorMessage", "删除失败！");
		}
		jsonObject.put("staffId", trainid);
		jsonObject.put("state", true);
		jsonObject.put("errorMessage", "删除成功！");
		
		return jsonObject;
	}
	
	public JSONObject trainFindOneParams(HttpServletRequest req, HttpServletResponse resp){
		String trainid = req.getParameter("trainid");
		Train train = this.trainService.search(trainid);
		
		Park park = null;
		if(train.getParkid()!= null){
			park = this.parkDAO.findById(train.getParkid());
		}else if(train.getPark() != null){
			park = this.parkDAO.findById(train.getPark().getParkid());
		}
		train.setPark(park);
		JSONObject jsonObject = new JSONObject();
		if(train == null){
			jsonObject.put("state", false);
			jsonObject.put("errorMessage", "查找失败");
		}
		jsonObject.put("state", true);
		jsonObject.put("meesage", "查找成功");
		jsonObject.put("data", train);
		return jsonObject;
	} 
	
	//查找所有条数
	public JSONObject trainFindAllParams(HttpServletRequest req, HttpServletResponse resp){
		JSONObject jsonObject = new JSONObject();
		return jsonObject;
	}
	
	//表格所有数据
		public JSONObject trainTableAllParams(HttpServletRequest request, HttpServletResponse response){
			
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
			map.put("initCondition", "train");
			
		    JSONObject jsonObject = new JSONObject();
		    TrainService service = new TrainServiceBean();
	    	List list =  service.findAllTrainLimit(map);
	    	
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