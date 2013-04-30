package trazabilidad.common.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {

	public void init(FilterConfig arg0) throws ServletException {

	}

	public void destroy() {

	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		//AgentToInvokeInfo agentToInvokeInfo = (AgentToInvokeInfo) ((HttpSession)httpRequest.getSession(true)).getAttribute("agentToInvokeInfo");
		
		//if (agentToInvokeInfo != null)
			chain.doFilter(request, response);
		//else
		//	httpResponse.sendRedirect(httpRequest.getContextPath());
	}
	
}