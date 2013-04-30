package trazabilidad.beans.lote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;

import trazabilidad.common.beans.generico.GenericBean;
import trazabilidad.exceptions.jamonartesanal.JamonArtesanalException;
import trazabilidad.services.jamonartesanal.JamonArtesanalServices;
import trazabilidad.vo.lotes.LoteJamonArtesanalVO;

public class LoteBackingBean extends GenericBean {

	private JamonArtesanalServices jamonServices;
	
	private List<LoteJamonArtesanalVO> lotes;
	private LoteJamonArtesanalVO loteSeleccionado;
	
	// FILTROS
	private Date fechaDesde;
	private Date fechaHasta;
	
	public LoteBackingBean() {
		
		jamonServices = new JamonArtesanalServices();
		try {
			lotes = jamonServices.obtenerTodosLosLotes();
			
			loteSeleccionado = lotes.get(0);
		} catch (JamonArtesanalException e) {
			lotes = new ArrayList<LoteJamonArtesanalVO>();
		}
	}
	
	public void seleccionarLote(ActionEvent event){
		HtmlAjaxCommandButton btn = (HtmlAjaxCommandButton) event.getComponent();
		
		loteSeleccionado = (LoteJamonArtesanalVO) btn.getData();
		
		System.out.println("");
		
	}
	
	public void buscar(){
		
		try {
			lotes = jamonServices.buscarConFiltros(getFechaDesde(), getFechaHasta());
		} catch (JamonArtesanalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<LoteJamonArtesanalVO> getLotes() {
		return lotes;
	}

	public void setLotes(List<LoteJamonArtesanalVO> lotes) {
		this.lotes = lotes;
	}

	public LoteJamonArtesanalVO getLoteSeleccionado() {
		return loteSeleccionado;
	}

	public void setLoteSeleccionado(LoteJamonArtesanalVO loteSeleccionado) {
		this.loteSeleccionado = loteSeleccionado;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}