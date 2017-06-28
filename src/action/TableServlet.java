package action;

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

//学生列表的编写
public class TableServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
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
 	    response.getWriter().write(rets);
	}
}


/*
 * curentPage:
 * rowNum
 * 
 * rows:list
 * 
 * */
