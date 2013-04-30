package trazabilidad.beans.abms;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;

import trazabilidad.beans.codiguera.CodigueraBackingBean;
import trazabilidad.exceptions.abms.MateriaPrimaException;
import trazabilidad.exceptions.abms.TipoMateriaPrimaException;
import trazabilidad.exceptions.generic.DeleteException;
import trazabilidad.services.abms.MateriasPrimasServices;
import trazabilidad.services.abms.TipoMateriaPrimaServices;
import trazabilidad.vo.abms.MateriaPrimaVO;
import trazabilidad.vo.abms.TipoMateriaPrimaVO;

public class MateriasPrimasBackingBean extends CodigueraBackingBean {
	
	private MateriaPrimaVO materiaPrimaSeleccionada;
	
	private List<MateriaPrimaVO> materiasPrimas;
	private MateriasPrimasServices materiaPrimaServices;
	
	private List<TipoMateriaPrimaVO> tipos;
	private TipoMateriaPrimaVO tipoSeleccionado;
	private TipoMateriaPrimaServices tiposServices;
		
	public MateriasPrimasBackingBean(){
		
		materiaPrimaServices = new MateriasPrimasServices();
		tiposServices = new TipoMateriaPrimaServices();
		
		try {
			materiasPrimas = materiaPrimaServices.obtenerTodosLosTipos();
			tipos = tiposServices.obtenerTodosLosTipos();
		
		} catch (MateriaPrimaException e) {
			materiasPrimas = new ArrayList<MateriaPrimaVO>();
			e.printStackTrace();
		
		} catch (TipoMateriaPrimaException e) {
			tipos = new ArrayList<TipoMateriaPrimaVO>();
			e.printStackTrace();
		}
	}
	
	public void nuevo(){
		
		MateriaPrimaVO nuevoTipo = new MateriaPrimaVO();
		nuevoTipo.setCodigo(getCodigo());
		nuevoTipo.setNombre(getNombre());
		nuevoTipo.setDescripcion(getDescripcion());
		nuevoTipo.setTipo(tipoSeleccionado);
		
		try {
			materiaPrimaServices.ingresarNuevoTipo(nuevoTipo);
			
			// Actualizo la tabla
			materiasPrimas = materiaPrimaServices.obtenerTodosLosTipos();
			
			limpiarDatos();
			
			setCerrarPopUp(true);
			
		} catch (MateriaPrimaException e) {
			setCerrarPopUp(false);
			e.printStackTrace();
		}
	}
	
	public void borrar(){
		
		try {
			materiaPrimaServices.borrarTipoMateriaPrima(materiaPrimaSeleccionada);
			
			// Actualizo la tabla
			materiasPrimas = materiaPrimaServices.obtenerTodosLosTipos();
			
			limpiarDatos();
			
			setCerrarPopUp(true);
		
		} catch (DeleteException e) {
			setCerrarPopUp(false);
			e.printStackTrace();
		
		} catch (MateriaPrimaException e) {
			setCerrarPopUp(false);
			e.printStackTrace();
		}
	}
	
	public void modificar(){
		
		try {
			
			materiaPrimaSeleccionada.setCodigo(getCodigo());
			materiaPrimaSeleccionada.setNombre(getNombre());
			materiaPrimaSeleccionada.setDescripcion(getDescripcion());
			materiaPrimaSeleccionada.setTipo(tipoSeleccionado);
			
			materiaPrimaServices.actualizarMateriaPrima(materiaPrimaSeleccionada);
			
			// Actualizo la tabla
			materiasPrimas = materiaPrimaServices.obtenerTodosLosTipos();
			
			limpiarDatos();
			
			setCerrarPopUp(true);
		
		} catch (MateriaPrimaException e) {
			setCerrarPopUp(false);
			e.printStackTrace();
		}
	}
	
	public void seleccionar(ActionEvent event){
		HtmlAjaxCommandButton btn = (HtmlAjaxCommandButton) event.getComponent();
		
		materiaPrimaSeleccionada = (MateriaPrimaVO) btn.getData();
		
		setCodigo(materiaPrimaSeleccionada.getCodigo());
		setNombre(materiaPrimaSeleccionada.getNombre());
		setDescripcion(materiaPrimaSeleccionada.getDescripcion());
		setTipoSeleccionado(materiaPrimaSeleccionada.getTipo());
		
		setCerrarPopUp(false);
		
	}
	
	public void limpiarDatos(){
		super.limpiarDatos();
		tipoSeleccionado = null;
		materiaPrimaSeleccionada = null; 
	}

	public void setMateriaPrimaSeleccionada(
			MateriaPrimaVO materiaPrimaSeleccionada) {
		this.materiaPrimaSeleccionada = materiaPrimaSeleccionada;
	}

	public List<MateriaPrimaVO> getMateriasPrimas() {
		return materiasPrimas;
	}

	public void setMateriasPrimas(List<MateriaPrimaVO> materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}

	public List<TipoMateriaPrimaVO> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoMateriaPrimaVO> tipos) {
		this.tipos = tipos;
	}

	public TipoMateriaPrimaVO getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	public void setTipoSeleccionado(TipoMateriaPrimaVO tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
	}

	public MateriaPrimaVO getMateriaPrimaSeleccionada() {
		return materiaPrimaSeleccionada;
	}
}