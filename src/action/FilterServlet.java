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
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author samsung
 * @version 0.1
 * 过滤器，静态资源过滤，后台请求拦截，登陆验证
 */
public class FilterServlet extends HttpServlet implements Filter{

	private static final long serialVersionUID = 1L;


    /** 
     * The default character encoding to set for requests that pass through 
     * this filter. 
     */  
    protected String encoding = null;  
  
  
    /** 
     * The filter configuration object we are associated with.  If this value 
     * is null, this filter instance is not currently configured. 
     */  
    protected FilterConfig filterConfig = null;  
  
  
    /** 
     * Should a character encoding specified by the client be ignored? 
     */  
    protected boolean ignore = true;  
	
	//拦截器，请求拦截
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
	 	HttpServletRequest req = (HttpServletRequest)request;//获取请求
	 	HttpServletResponse rep = (HttpServletResponse)response;
		String getRequestURI = req.getRequestURI();//获取URI
		String[] splis = getRequestURI.split("\\.");
		String url = splis[splis.length-1];
		//设置字符集
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
		//静态资源放过
		if("jpg".equals(url)||"png".equals(url)||"html".equals(url) || "js".equals(url)||"css".equals(url) || "svg".equals(url) || "woff".equals(url) || "woff2".equals(url) || "ttf".equals("url")){
			req.setAttribute("allow", true);
		}else if(url != null && url.indexOf("LoginAction") > 0){
			req.setAttribute("allow", true);
		}else{
			//如果是后台请求，并且发现未登录的话就直接不允许，
			if(req.getSession() != null && req.getSession().getAttribute("loginUser") != null){
				req.setAttribute("allow", true);
			}else{
				req.setAttribute("allow", false);
			}
		}
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	protected String selectEncoding() {  
		  
        return (this.encoding);  
  
    }  
	
}
