package trazabilidad.beans.abms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import trazabilidad.common.beans.generico.GenericBean;
import trazabilidad.exceptions.abms.EntregasException;
import trazabilidad.exceptions.abms.MateriaPrimaException;
import trazabilidad.exceptions.abms.ProveedorException;
import trazabilidad.services.abms.EntregasServices;
import trazabilidad.services.abms.MateriasPrimasServices;
import trazabilidad.services.abms.ProveedorServices;
import trazabilidad.vo.abms.EntregaVO;
import trazabilidad.vo.abms.ProveedorVO;
import trazabilidad.vo.abms.MateriaPrimaVO;

public class EntregasBackingBean extends GenericBean {

	private Date fechaIngreso;
	private ProveedorVO proveedor;
	private MateriaPrimaVO tipoMateriaPrima;
	private Double cantidad;
	private BigDecimal tempIngreso;
	private Date fechaElaboracion;
	private Date fechaVencimiento;
	private Integer cantidadPallets;
	private Integer cantidadCajas;
	private String unidad;
	
	private List<ProveedorVO> proveedores;
	private ProveedorServices proveedorServices;

	private List<MateriaPrimaVO> tiposMateriaPrima;
	private MateriasPrimasServices materiaPrimaService;
	
	private List<EntregaVO> entregas; 
	private EntregasServices entregasService;
	
	private Date fechaDesde;
	private Date fechaHasta;
	
	private boolean hayErrores;
	
	public EntregasBackingBean(){
		
		System.out.println("creando el backing bean");
		
		proveedorServices = new ProveedorServices();
		materiaPrimaService = new MateriasPrimasServices();
		entregasService = new EntregasServices();
		
		try {
			proveedores = proveedorServices.obtenerTodosLosProveedores();
			tiposMateriaPrima = materiaPrimaService.obtenerTodosLosTipos();
			
			entregas = entregasService.obtenerTodasLasEntregas();
		
		} catch (ProveedorException e) {
			proveedores = new ArrayList<ProveedorVO>();
			e.printStackTrace();
		
		} catch (MateriaPrimaException e) {
			tiposMateriaPrima = new ArrayList<MateriaPrimaVO>();
			e.printStackTrace();
		
		} catch (EntregasException e) {
			entregas = new ArrayList<EntregaVO>();
			e.printStackTrace();
		}
	}
	
	public void aceptar(){
		
		hayErrores = true;
		
		EntregaVO nuevaEntrega = new EntregaVO(fechaIngreso, proveedor, tipoMateriaPrima, cantidad, tempIngreso, fechaElaboracion, fechaVencimiento, cantidadPallets, cantidadCajas, unidad);
		
		entregasService = new EntregasServices();
		try {
			entregasService.ingresarNuevaEntrega(nuevaEntrega);
			hayErrores = false;
			
			// limpio los campos de iingreso
			fechaIngreso = null;
			proveedor = null;
			tipoMateriaPrima = null;
			cantidad = null;
			tempIngreso = null;
			fechaElaboracion = null;
			fechaVencimiento = null;
			cantidadPallets = null;
			cantidadCajas = null;
			unidad = null;
			
		} catch (EntregasException e) {
			
			e.printStackTrace();
		}
	}

	public void buscar(){
		
		try {
			entregas = entregasService.buscarConFiltro(getFechaDesde(), getFechaHasta(), getProveedor(), getTipoMateriaPrima());
		} catch (EntregasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<ProveedorVO> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedorVO> proveedores) {
		this.proveedores = proveedores;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getCantidadCajas() {
		return cantidadCajas;
	}

	public void setCantidadCajas(Integer cantidadCajas) {
		this.cantidadCajas = cantidadCajas;
	}

	public Integer getCantidadPallets() {
		return cantidadPallets;
	}

	public void setCantidadPallets(Integer cantidadPallets) {
		this.cantidadPallets = cantidadPallets;
	}

	public Date getFechaElaboracion() {
		return fechaElaboracion;
	}

	public void setFechaElaboracion(Date fechaElaboracion) {
		this.fechaElaboracion = fechaElaboracion;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public ProveedorVO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorVO proveedor) {
		this.proveedor = proveedor;
	}

	public MateriaPrimaVO getTipoMateriaPrima() {
		return tipoMateriaPrima;
	}

	public void setTipoMateriaPrima(MateriaPrimaVO tipoMateriaPrima) {
		this.tipoMateriaPrima = tipoMateriaPrima;
	}

	public List<MateriaPrimaVO> getTiposMateriaPrima() {
		return tiposMateriaPrima;
	}

	public void setTiposMateriaPrima(List<MateriaPrimaVO> tiposMateriaPrima) {
		this.tiposMateriaPrima = tiposMateriaPrima;
	}

	public BigDecimal getTempIngreso() {
		return tempIngreso;
	}

	public void setTempIngreso(BigDecimal tempIngreso) {
		this.tempIngreso = tempIngreso;
	}
	
	public boolean isHayErrores() {
		return hayErrores;
	}

	public void setHayErrores(boolean hayErrores) {
		this.hayErrores = hayErrores;
	}

	public List<EntregaVO> getEntregas() {
		return entregas;
	}

	public void setEntregas(List<EntregaVO> entregas) {
		this.entregas = entregas;
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

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
}