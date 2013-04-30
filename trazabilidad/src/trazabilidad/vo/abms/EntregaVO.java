package trazabilidad.vo.abms;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EntregaVO  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private Date fechaIngreso;
	private ProveedorVO proveedor;
	private MateriaPrimaVO materiaPrima;
	private Double cantidad;
	private BigDecimal tempIngreso;
	private Date fechaElaboracion;
	private Date fechaVencimiento;
	private Integer cantidadPallets;
	private Integer cantidadCajas;
	
	public EntregaVO(Date fechaIngreso, ProveedorVO proveedor, MateriaPrimaVO materiaPrima, Double cantidad, BigDecimal tempIngreso, Date fechaElaboracion, Date fechaVencimiento, Integer cantidadPallets, Integer cantidadCajas) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.proveedor = proveedor;
		this.materiaPrima = materiaPrima;
		this.cantidad = cantidad;
		this.tempIngreso = tempIngreso;
		this.fechaElaboracion = fechaElaboracion;
		this.fechaVencimiento = fechaVencimiento;
		this.cantidadPallets = cantidadPallets;
		this.cantidadCajas = cantidadCajas;
	}

	public EntregaVO() {
		super();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProveedorVO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorVO proveedor) {
		this.proveedor = proveedor;
	}

	public MateriaPrimaVO getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrimaVO materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public BigDecimal getTempIngreso() {
		return tempIngreso;
	}

	public void setTempIngreso(BigDecimal tempIngreso) {
		this.tempIngreso = tempIngreso;
	}
}