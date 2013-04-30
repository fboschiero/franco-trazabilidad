package trazabilidad.vo.stock;

import java.io.Serializable;
import java.math.BigDecimal;

import trazabilidad.vo.abms.ProveedorVO;
import trazabilidad.vo.abms.MateriaPrimaVO;
import trazabilidad.vo.abms.TipoMateriaPrimaVO;

public class StockVO   implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private ProveedorVO proveedor;
	private MateriaPrimaVO materiaPrima;
	private TipoMateriaPrimaVO tipoMateriaPrima;
	private BigDecimal cantidad;
	
	public StockVO() {
		super();
	}

	public StockVO(ProveedorVO proveedor, MateriaPrimaVO materiaPrima, BigDecimal cantidad) {
		super();
		this.proveedor = proveedor;
		this.materiaPrima = materiaPrima;
		this.cantidad = cantidad;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
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

	public TipoMateriaPrimaVO getTipoMateriaPrima() {
		return tipoMateriaPrima;
	}

	public void setTipoMateriaPrima(TipoMateriaPrimaVO tipoMateriaPrima) {
		this.tipoMateriaPrima = tipoMateriaPrima;
	}
}