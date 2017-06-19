package action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author samsung
 * @version 0.1
 * 过滤器，静态资源过滤，后台请求拦截，登陆验证
 */
public class FilterServlet extends HttpServlet implements Filter{

	private static final long serialVersionUID = 1L;

	//拦截器，请求拦截
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
	 	HttpServletRequest req = (HttpServletRequest)request;//获取请求
		String getRequestURI = req.getRequestURI();//获取URI
		String[] splis = getRequestURI.split("\\.");
		String url = splis[splis.length-1];
		
		//静态资源放过
		if("html".equals(url) || "js".equals(url)||"css".equals(url) || "svg".equals(url) || "woff".equals(url) || "woff2".equals(url) || "ttf".equals("url")){
			req.setAttribute("allow", true);
		}else if("LoginAction".equals(url)){
			req.setAttribute("allow", true);
		}else{
			//如果是后台请求的话就直接不允许�?�?
			if(req.getSession() != null && req.getSession().getAttribute("loginUser") != null){
				req.setAttribute("allow", true);
			}else{
				req.setAttribute("allow", false);
			}
		}
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
}
