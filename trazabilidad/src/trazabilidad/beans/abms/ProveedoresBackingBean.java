package trazabilidad.beans.abms;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;

import trazabilidad.common.beans.generico.GenericBean;
import trazabilidad.exceptions.abms.ProveedorException;
import trazabilidad.services.abms.ProveedorServices;
import trazabilidad.vo.abms.ProveedorVO;

public class ProveedoresBackingBean extends GenericBean {

	private String codigo;
	private String nombre;
	private String direccion;

	private ProveedorVO proveedorSeleccionado;
	
	private boolean cerrarPopUp;
	
	private List<ProveedorVO> proveedores;
	private ProveedorServices proveedorServices;
	
	public ProveedoresBackingBean(){
		
		proveedorServices = new ProveedorServices();
		
		try {
			proveedores = proveedorServices.obtenerTodosLosProveedores();
		
		} catch (ProveedorException e) {
			proveedores = new ArrayList<ProveedorVO>();
			e.printStackTrace();
		}
	}
	
	public void nuevoProveedor(){
		
		cerrarPopUp = true;
		
		ProveedorVO nuevoProveedor = new ProveedorVO();
		nuevoProveedor.setCodigo(codigo);
		nuevoProveedor.setNombre(nombre);
		nuevoProveedor.setDireccion(direccion);
		
		try {
			proveedorServices.ingresarNuevoProveedor(nuevoProveedor);
			
			// Actualizo la tabla
			proveedores = proveedorServices.obtenerTodosLosProveedores();
			
			limpiarDatos();
			
		} catch (ProveedorException e1) {
			cerrarPopUp = false;
			e1.printStackTrace();
		}
	}
	
	public void seleccionarProveedor(ActionEvent event){
		HtmlAjaxCommandButton btn = (HtmlAjaxCommandButton) event.getComponent();
		
		proveedorSeleccionado = (ProveedorVO) btn.getData();
		
		codigo = proveedorSeleccionado.getCodigo();
		nombre = proveedorSeleccionado.getNombre();
		direccion = proveedorSeleccionado.getDireccion();
		
		cerrarPopUp = false;
		
	}
	
	public void borrarProveedor(){
		
		try {
			proveedorServices.borrarProveedor(proveedorSeleccionado);
			
			// Actualizo la tabla
			proveedores = proveedorServices.obtenerTodosLosProveedores();
			
			limpiarDatos();
			
			cerrarPopUp = true;
		
		} catch (ProveedorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarProveedor(){
		
		try {
			
			proveedorSeleccionado.setCodigo(codigo);
			proveedorSeleccionado.setNombre(nombre);
			proveedorSeleccionado.setDireccion(direccion);
			
			proveedorServices.actualizarProveedor(proveedorSeleccionado);
			
			// Actualizo la tabla
			proveedores = proveedorServices.obtenerTodosLosProveedores();
			
			limpiarDatos();
			
			cerrarPopUp = true;
		
		} catch (ProveedorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void limpiarDatos(){
		codigo = null;
		nombre = null;
		direccion = null;
		proveedorSeleccionado = null; 
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ProveedorVO> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedorVO> proveedores) {
		this.proveedores = proveedores;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean isCerrarPopUp() {
		return cerrarPopUp;
	}

	public void setCerrarPopUp(boolean cerrarPopUp) {
		this.cerrarPopUp = cerrarPopUp;
	}
}