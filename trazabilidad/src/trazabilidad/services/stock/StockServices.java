package trazabilidad.services.stock;

import java.math.BigDecimal;
import java.util.List;

import trazabilidad.daos.stock.StockDAO;
import trazabilidad.exceptions.stock.StockException;
import trazabilidad.vo.abms.ProveedorVO;
import trazabilidad.vo.abms.MateriaPrimaVO;
import trazabilidad.vo.stock.StockVO;

public class StockServices {

	public List<StockVO> obtenerListadoStock() throws StockException {
		StockDAO stockDAO = new StockDAO();
		return stockDAO.obtenerListadoStock();
	}

	public void bajaStock(MateriaPrimaVO tipoMateriaPrima, ProveedorVO proveedor, BigDecimal cantidad) throws StockException {
		StockDAO stockDAO = new StockDAO();
		stockDAO.bajaStock(tipoMateriaPrima, proveedor, cantidad);
	}

	public List<StockVO> buscarConFiltros(ProveedorVO proveedor, MateriaPrimaVO tipoMateriaPrima) throws StockException {
		StockDAO stockDAO = new StockDAO();
		return stockDAO.buscarConFiltros(proveedor, tipoMateriaPrima);
	}
}