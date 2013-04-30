package trazabilidad.beans.codiguera;

import javax.faces.event.ActionEvent;

import trazabilidad.common.beans.generico.GenericBean;

public abstract class CodigueraBackingBean extends GenericBean{

	private String codigo;
	private String nombre;
	private String descripcion;
	
	private boolean cerrarPopUp;
			
	public CodigueraBackingBean() {
		super();
	}
	
	public abstract void nuevo();
	public abstract void modificar();
	public abstract void borrar();
	public abstract void seleccionar(ActionEvent event);

	public void limpiarDatos(){
		codigo = null;
		nombre = null;
		descripcion = null;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isCerrarPopUp() {
		return cerrarPopUp;
	}

	public void setCerrarPopUp(boolean cerrarPopUp) {
		this.cerrarPopUp = cerrarPopUp;
	}
}