package trazabilidad.beans.jamonartesanal;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneRadio;

import trazabilidad.common.beans.generico.GenericBean;
import trazabilidad.exceptions.abms.ProveedorException;
import trazabilidad.exceptions.jamonartesanal.JamonArtesanalException;
import trazabilidad.exceptions.jamonartesanal.LoteException;
import trazabilidad.services.abms.ProveedorServices;
import trazabilidad.services.jamonartesanal.JamonArtesanalServices;
import trazabilidad.vo.abms.ProveedorVO;
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
	private ProveedorVO proveedor;
	private BigDecimal tempSalmuera;
	// DATOS DEL BOMBO
	private String horaIngresoBombo;
	private String horaSalidaBombo;
	private String usuarioBombo;
	// DATOS DE LA COCCION
	private String horaIngresoCoccion;
	private BigDecimal tempIngresoCoccion;
	private String horaSalidaCoccion;
	private BigDecimal tempSalidaCoccion;
	private String usuarioCoccion;
	private String contraseniaCoccion;
	private String nroTacho;
	// DATOS DEL ENFRIADO
	private Integer nroTachoEnfriado;
	private String horaSalidaACamara;
	private BigDecimal tempSalidaACamara;
	
	private List<ProveedorVO> proveedores;
	private ProveedorServices proveedoresServices;
	
	public JamonArtesanalBackingBean() {
		proveedoresServices = new ProveedorServices();
		try {
			proveedores = proveedoresServices.obtenerTodosLosProveedores();
		} catch (ProveedorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public String crearNuevoLote(){
		
		// Validar usuario y contrasenia
		
		LoteJamonArtesanalVO nuevoLote = new LoteJamonArtesanalVO(getKgCarne(), getHoraIngresoInyeccion(), getTempIngresoInyeccion(), getHoraSalidaInyeccion(), getTempSalidaInyeccion(), getUsuarioInyeccion(), getProveedor(), getTempSalmuera());
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
			jamonServices.guardarEtapaBombo(lote.getId(), getHoraIngresoBombo(), getHoraSalidaBombo(), getUsuarioBombo());
			limpiar();
		} catch (JamonArtesanalException e) {
			e.printStackTrace();
		}
	}
	
	public void confirmarEtapa3(){
		//	INICIO de la coccion
		jamonServices = new JamonArtesanalServices();
		try {
			jamonServices.guardarCoccion(lote.getId(), getHoraIngresoCoccion(), getTempIngresoCoccion(), getNroTacho(), getHoraSalidaCoccion(), getTempSalidaCoccion(), getUsuarioCoccion());
			limpiar();
		} catch (JamonArtesanalException e) {
			e.printStackTrace();
		}
	}
	
	
	public void confirmarEtapa4(){
		//	FIN de la coccion
		jamonServices = new JamonArtesanalServices();
		try {
			jamonServices.guardarEnfriado(lote.getId(), getNroTachoEnfriado(), getHoraSalidaACamara(), getTempSalidaACamara());
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
		proveedor = null;
		tempSalmuera = null;
		// DATOS DE LA COCCION
		horaIngresoCoccion = null;
		tempIngresoCoccion = null;
		horaSalidaCoccion = null;
		tempSalidaCoccion = null;
		usuarioCoccion = null;
		contraseniaCoccion = null;
		nroTacho = null;
		// DATOS DEL BOMBO
		horaIngresoBombo = null;
		horaSalidaBombo = null;
		usuarioBombo = null;
		
		// DATOS DEL ENFRIADO
		nroTachoEnfriado = null;
		horaSalidaACamara = null;
		tempSalidaACamara = null;
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

	public ProveedorVO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorVO proveedor) {
		this.proveedor = proveedor;
	}

	public BigDecimal getTempSalmuera() {
		return tempSalmuera;
	}

	public void setTempSalmuera(BigDecimal tempSalmuera) {
		this.tempSalmuera = tempSalmuera;
	}

	public List<ProveedorVO> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedorVO> proveedores) {
		this.proveedores = proveedores;
	}

	public String getHoraIngresoBombo() {
		return horaIngresoBombo;
	}

	public void setHoraIngresoBombo(String horaIngresoBombo) {
		this.horaIngresoBombo = horaIngresoBombo;
	}

	public String getHoraSalidaBombo() {
		return horaSalidaBombo;
	}

	public void setHoraSalidaBombo(String horaSalidaBombo) {
		this.horaSalidaBombo = horaSalidaBombo;
	}

	public String getUsuarioBombo() {
		return usuarioBombo;
	}

	public void setUsuarioBombo(String usuarioBombo) {
		this.usuarioBombo = usuarioBombo;
	}

	public String getHoraSalidaACamara() {
		return horaSalidaACamara;
	}

	public void setHoraSalidaACamara(String horaSalidaACamara) {
		this.horaSalidaACamara = horaSalidaACamara;
	}

	public Integer getNroTachoEnfriado() {
		return nroTachoEnfriado;
	}

	public void setNroTachoEnfriado(Integer nroTachoEnfriado) {
		this.nroTachoEnfriado = nroTachoEnfriado;
	}

	public BigDecimal getTempSalidaACamara() {
		return tempSalidaACamara;
	}

	public void setTempSalidaACamara(BigDecimal tempSalidaACamara) {
		this.tempSalidaACamara = tempSalidaACamara;
	}

	
}