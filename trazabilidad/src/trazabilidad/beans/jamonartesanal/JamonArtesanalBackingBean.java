package trazabilidad.beans.jamonartesanal;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.component.html.HtmlSelectOneRadio;

import trazabilidad.common.beans.generico.GenericBean;
import trazabilidad.exceptions.jamonartesanal.JamonArtesanalException;
import trazabilidad.exceptions.jamonartesanal.LoteException;
import trazabilidad.services.jamonartesanal.JamonArtesanalServices;
import trazabilidad.vo.lotes.LoteJamonArtesanalVO;

public class JamonArtesanalBackingBean extends GenericBean {

	private JamonArtesanalServices jamonServices;
	private String nroLote;
	private LoteJamonArtesanalVO lote;
	
	private HtmlSelectOneRadio radioEtapa;
	
	// DATOS DE LA INYECCION
	private BigDecimal kgCarne;
	private String horaIngresoInyeccion;
	private BigDecimal tempIngresoInyeccion;
	private String horaSalidaInyeccion;
	private BigDecimal tempSalidaInyeccion;
	private String usuarioInyeccion;
	private String contraseniaInyeccion;
	// DATOS DE LA COCCION
	private String horaIngresoCoccion;
	private BigDecimal tempIngresoCoccion;
	private String horaSalidaCoccion;
	private BigDecimal tempSalidaCoccion;
	private String usuarioCoccion;
	private String contraseniaCoccion;
	private String nroTacho;
	
	public JamonArtesanalBackingBean() {
		
	}
	
	public String crearNuevoLote(){
		
		// Validar usuario y contrasenia
		
		LoteJamonArtesanalVO nuevoLote = new LoteJamonArtesanalVO(getKgCarne(), getHoraIngresoInyeccion(), getTempIngresoInyeccion(), getHoraSalidaInyeccion(), getTempSalidaInyeccion(), getUsuarioInyeccion());
		nuevoLote.setFechaCreado(new Date());
		nuevoLote.setOperarioResponsableInyeccion(getUsuarioInyeccion());
				
		jamonServices = new JamonArtesanalServices();
		try {
			nroLote = jamonServices.crearLote(nuevoLote);
			
			limpiar();
			
		} catch (LoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "imprimirNroLote";
	}
	
	public void confirmarEtapa2(){
		// FIN de la inyeccion
		jamonServices = new JamonArtesanalServices();
		try {
			jamonServices.guardarFinInyeccion(lote.getId(), getHoraSalidaInyeccion(), getTempSalidaInyeccion(), getUsuarioInyeccion());
			limpiar();
		} catch (JamonArtesanalException e) {
			e.printStackTrace();
		}
	}
	
	public void confirmarEtapa3(){
		//	INICIO de la coccion
		jamonServices = new JamonArtesanalServices();
		try {
			jamonServices.guardarInicioCoccion(lote.getId(), getHoraIngresoCoccion(), getTempIngresoCoccion(), getNroTacho(), getUsuarioCoccion());
			limpiar();
		} catch (JamonArtesanalException e) {
			e.printStackTrace();
		}
	}
	
	
	public void confirmarEtapa4(){
		//	FIN de la coccion
		jamonServices = new JamonArtesanalServices();
		try {
			jamonServices.guardarFinCoccion(lote.getId(), getHoraSalidaCoccion(), getTempSalidaCoccion(), getUsuarioCoccion());
			limpiar();
		} catch (JamonArtesanalException e) {
			e.printStackTrace();
		}
	}
	public void buscarLote(){
		
		try {
			jamonServices = new JamonArtesanalServices();
			lote = jamonServices.findLoteByNro(nroLote);
		
		
		} catch (LoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void limpiar() {
		//nroLote = null;
		lote = null;
		
		// DATOS DE LA INYECCION
		kgCarne = null;
		horaIngresoInyeccion = null;
		tempIngresoInyeccion = null;
		horaSalidaInyeccion = null;
		tempSalidaInyeccion = null;
		usuarioInyeccion = null;
		contraseniaInyeccion = null;
		// DATOS DE LA COCCION
		horaIngresoCoccion = null;
		tempIngresoCoccion = null;
		horaSalidaCoccion = null;
		tempSalidaCoccion = null;
		usuarioCoccion = null;
		contraseniaCoccion = null;
		nroTacho = null;
	}
	
	public String getContraseniaInyeccion() {
		return contraseniaInyeccion;
	}

	public void setContraseniaInyeccion(String contraseniaInyeccion) {
		this.contraseniaInyeccion = contraseniaInyeccion;
	}

	public String getHoraIngresoInyeccion() {
		return horaIngresoInyeccion;
	}

	public void setHoraIngresoInyeccion(String horaIngresoInyeccion) {
		this.horaIngresoInyeccion = horaIngresoInyeccion;
	}

	public String getHoraSalidaInyeccion() {
		return horaSalidaInyeccion;
	}

	public void setHoraSalidaInyeccion(String horaSalidaInyeccion) {
		this.horaSalidaInyeccion = horaSalidaInyeccion;
	}

	public BigDecimal getKgCarne() {
		return kgCarne;
	}

	public void setKgCarne(BigDecimal kgCarne) {
		this.kgCarne = kgCarne;
	}

	public BigDecimal getTempIngresoInyeccion() {
		return tempIngresoInyeccion;
	}

	public void setTempIngresoInyeccion(BigDecimal tempIngresoInyeccion) {
		this.tempIngresoInyeccion = tempIngresoInyeccion;
	}

	public BigDecimal getTempSalidaInyeccion() {
		return tempSalidaInyeccion;
	}

	public void setTempSalidaInyeccion(BigDecimal tempSalidaInyeccion) {
		this.tempSalidaInyeccion = tempSalidaInyeccion;
	}

	public String getUsuarioInyeccion() {
		return usuarioInyeccion;
	}

	public void setUsuarioInyeccion(String usuarioInyeccion) {
		this.usuarioInyeccion = usuarioInyeccion;
	}

	public LoteJamonArtesanalVO getLote() {
		return lote;
	}

	public void setLote(LoteJamonArtesanalVO lote) {
		this.lote = lote;
	}
	
	public HtmlSelectOneRadio getRadioEtapa() {
		return radioEtapa;
	}

	public void setRadioEtapa(HtmlSelectOneRadio radioEtapa) {
		this.radioEtapa = radioEtapa;
	}

	public String getNroLote() {
		return nroLote;
	}

	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}

	public String getContraseniaCoccion() {
		return contraseniaCoccion;
	}

	public void setContraseniaCoccion(String contraseniaCoccion) {
		this.contraseniaCoccion = contraseniaCoccion;
	}

	public String getHoraIngresoCoccion() {
		return horaIngresoCoccion;
	}

	public void setHoraIngresoCoccion(String horaIngresoCoccion) {
		this.horaIngresoCoccion = horaIngresoCoccion;
	}

	public String getHoraSalidaCoccion() {
		return horaSalidaCoccion;
	}

	public void setHoraSalidaCoccion(String horaSalidaCoccion) {
		this.horaSalidaCoccion = horaSalidaCoccion;
	}

	public String getNroTacho() {
		return nroTacho;
	}

	public void setNroTacho(String nroTacho) {
		this.nroTacho = nroTacho;
	}

	public BigDecimal getTempIngresoCoccion() {
		return tempIngresoCoccion;
	}

	public void setTempIngresoCoccion(BigDecimal tempIngresoCoccion) {
		this.tempIngresoCoccion = tempIngresoCoccion;
	}

	public BigDecimal getTempSalidaCoccion() {
		return tempSalidaCoccion;
	}

	public void setTempSalidaCoccion(BigDecimal tempSalidaCoccion) {
		this.tempSalidaCoccion = tempSalidaCoccion;
	}

	public String getUsuarioCoccion() {
		return usuarioCoccion;
	}

	public void setUsuarioCoccion(String usuarioCoccion) {
		this.usuarioCoccion = usuarioCoccion;
	}
}