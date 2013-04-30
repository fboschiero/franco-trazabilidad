package trazabilidad.beans.stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import trazabilidad.common.beans.generico.GenericBean;
import trazabilidad.exceptions.abms.MateriaPrimaException;
import trazabilidad.exceptions.abms.ProveedorException;
import trazabilidad.exceptions.stock.StockException;
import trazabilidad.services.abms.MateriasPrimasServices;
import trazabilidad.services.abms.ProveedorServices;
import trazabilidad.services.stock.StockServices;
import trazabilidad.vo.abms.ProveedorVO;
import trazabilidad.vo.abms.MateriaPrimaVO;
import trazabilidad.vo.stock.StockVO;

public class StockBackingBean extends GenericBean {

	private StockServices stockServices;
	private List<StockVO> listadoStock;
	
	private ProveedorVO proveedor;
	private List<ProveedorVO> proveedores;
	private ProveedorServices proveedorServices;

	private MateriaPrimaVO tipoMateriaPrima;
	private List<MateriaPrimaVO> tiposMateriaPrima;
	private MateriasPrimasServices materiaPrimaService;
	
	private BigDecimal cantidad;
	
	private boolean cerrarPopUp;
	
	public StockBackingBean(){
		
		stockServices = new StockServices();
		proveedorServices = new ProveedorServices();
		materiaPrimaService = new MateriasPrimasServices();
		setCerrarPopUp(true);
		
		try {
			listadoStock = stockServices.obtenerListadoStock();
			proveedores = proveedorServices.obtenerTodosLosProveedores();
			tiposMateriaPrima = materiaPrimaService.obtenerTodosLosTipos();
		
		} catch (StockException e) {
			listadoStock = new ArrayList<StockVO>();
			e.printStackTrace();
		
		} catch (ProveedorException e) {
			proveedores = new ArrayList<ProveedorVO>();
			e.printStackTrace();
		
		} catch (MateriaPrimaException e) {
			tiposMateriaPrima = new ArrayList<MateriaPrimaVO>();
			e.printStackTrace();
		
		}
	}
	
	public void bajaStock(){
		try {
			stockServices.bajaStock(tipoMateriaPrima, proveedor, cantidad);
			listadoStock = stockServices.obtenerListadoStock();
			setCerrarPopUp(true);
		
		} catch (StockException e) {
			setCerrarPopUp(false);
			e.printStackTrace();
		}
	}
	
	public void buscar(){
		try {
			listadoStock = stockServices.buscarConFiltros(getProveedor(), getTipoMateriaPrima());
		} catch (StockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void limpiarCampos(){
		tipoMateriaPrima = null;
		proveedor = null;
		cantidad = null;
	}

	public List<StockVO> getListadoStock() {
		return listadoStock;
	}

	public void setListadoStock(List<StockVO> listadoStock) {
		this.listadoStock = listadoStock;
	}

	public ProveedorVO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorVO proveedor) {
		this.proveedor = proveedor;
	}

	public List<ProveedorVO> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedorVO> proveedores) {
		this.proveedores = proveedores;
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
	
	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	
	public boolean isCerrarPopUp() {
		return cerrarPopUp;
	}

	public void setCerrarPopUp(boolean cerrarPopUp) {
		this.cerrarPopUp = cerrarPopUp;
	}
}