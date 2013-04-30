package trazabilidad.beans.abms;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;

import trazabilidad.beans.codiguera.CodigueraBackingBean;
import trazabilidad.exceptions.abms.TipoMateriaPrimaException;
import trazabilidad.exceptions.generic.DeleteException;
import trazabilidad.services.abms.TipoMateriaPrimaServices;
import trazabilidad.vo.abms.TipoMateriaPrimaVO;

public class TipoMateriasPrimasBackingBean extends CodigueraBackingBean {

	private TipoMateriaPrimaServices tipoServices;
	private List<TipoMateriaPrimaVO> tipos;
	private TipoMateriaPrimaVO tipoSeleccionado;
	
	public TipoMateriasPrimasBackingBean() {
		super();
		
		tipoServices = new TipoMateriaPrimaServices();
		
		try {
			tipos = tipoServices.obtenerTodosLosTipos();
		} catch (TipoMateriaPrimaException e) {
			e.printStackTrace();
			tipos = new ArrayList<TipoMateriaPrimaVO>();
		}
	}

	@Override
	public void borrar() {
		
		try {
			tipoServices.borrar(tipoSeleccionado);
			
			// Actualizo la tabla
			tipos = tipoServices.obtenerTodosLosTipos();
			
			limpiarDatos();
			
			setCerrarPopUp(true);
		
		} catch (DeleteException e) {
			setCerrarPopUp(false);
			e.printStackTrace();
		
		} catch (TipoMateriaPrimaException e) {
			setCerrarPopUp(false);
			e.printStackTrace();
		}
	}

	@Override
	public void modificar() {

		try {
			
			tipoSeleccionado.setCodigo(getCodigo());
			tipoSeleccionado.setNombre(getNombre());
			tipoSeleccionado.setDescripcion(getDescripcion());
			
			tipoServices.modificar(tipoSeleccionado);
			
			// Actualizo la tabla
			tipos = tipoServices.obtenerTodosLosTipos();
			
			limpiarDatos();
			
			setCerrarPopUp(true);
		
		} catch (TipoMateriaPrimaException e) {
			setCerrarPopUp(false);
			e.printStackTrace();
		}
	}

	@Override
	public void nuevo() {
		
		TipoMateriaPrimaVO nuevoTipo = new TipoMateriaPrimaVO();
		nuevoTipo.setCodigo(getCodigo());
		nuevoTipo.setNombre(getNombre());
		nuevoTipo.setDescripcion(getDescripcion());
		
		try {
			tipoServices.nuevo(nuevoTipo);
			
			// Actualizo la tabla
			tipos = tipoServices.obtenerTodosLosTipos();
			
			limpiarDatos();
			setCerrarPopUp(true);
			
		} catch (TipoMateriaPrimaException e) {
			setCerrarPopUp(false);
			e.printStackTrace();
		}
	}

	@Override
	public void seleccionar(ActionEvent event) {
		HtmlAjaxCommandButton btn = (HtmlAjaxCommandButton) event.getComponent();
		
		tipoSeleccionado = (TipoMateriaPrimaVO) btn.getData();
		
		setCodigo(tipoSeleccionado.getCodigo());
		setNombre(tipoSeleccionado.getNombre());
		setDescripcion(tipoSeleccionado.getDescripcion());
		
		setCerrarPopUp(false);
	}
	
	public void limpiarDatos(){
		super.limpiarDatos();
		tipoSeleccionado = null;
	}

	public List<TipoMateriaPrimaVO> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoMateriaPrimaVO> tipos) {
		this.tipos = tipos;
	}
	
}