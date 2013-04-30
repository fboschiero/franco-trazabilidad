package trazabilidad.common.beans.generico;

import java.util.Enumeration;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

public class LoginBean extends GenericBean {
	
	private String user="usuario";

	private String password="usuario";

	public String login() {

		System.out.println("Login:  " + user);
		
		getSession().setAttribute("loginUser", user);
		
		return "irPrincipal";

	}

	public String logout() {
		System.out.println("Logout:  " + user);
		if (getSession() != null) {
			getSession().invalidate();
			setSession(((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)));
		}

		return "irInicio";
	}

	@SuppressWarnings("unchecked")
	public void limpiarSesion(ActionEvent event) {

		Enumeration<String> names = getSession().getAttributeNames();

		while (names.hasMoreElements()) {
			String key = names.nextElement();
			if (key.indexOf("AjaxState") == -1) {
					getSession().removeAttribute(key);
			}
		}
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
