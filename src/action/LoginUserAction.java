package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class LoginUserAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			JSONObject jsonObject = new JSONObject();	
			Object object = req.getSession().getAttribute("loginUser");
			Object modules =req.getSession().getAttribute("modules");
			jsonObject.put("loginUser", object);
			jsonObject.put("modules", modules);
		  resp.getWriter().write(jsonObject.toJSONString());
	}
}
