package trazabilidad.common.beans.generico;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GenericBean {
	
	protected HttpSession session;
		
	private String LOGIN_USER = "loginUser";
	
	public GenericBean() {
		session = ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false));
		limpiarCache();
	}
	
	public String getLoginUser(){
		return (String)session.getAttribute(LOGIN_USER);
	}
	
	public void setLoginUser(String loginUser){
		session.setAttribute(LOGIN_USER, loginUser);
	}
	
	private void limpiarCache() {
		System.out.println("limpiando cache .........");
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
}