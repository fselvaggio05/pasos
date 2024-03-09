package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class FiltroLogin
 */

@WebFilter (urlPatterns="/Pasos/")
public class FiltroLogin extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FiltroLogin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private FilterConfig config;
    private String urlLogin;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpSession reqSession = ((HttpServletRequest)request).getSession();
//		HttpSession session = reqSession.getSession();
		
		if(reqSession.getAttribute("usuario")!=null)
		{
			chain.doFilter(request, response);	
		}
		
		else
		{
			((HttpServletResponse)response).sendRedirect("/Pasos/index.jsp");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = config;
	}

}
