package trazabilidad.services.abms;

import java.util.Date;
import java.util.List;

import trazabilidad.daos.abms.EntregasDAO;
import trazabilidad.exceptions.abms.EntregasException;
import trazabilidad.vo.abms.EntregaVO;
import trazabilidad.vo.abms.MateriaPrimaVO;
import trazabilidad.vo.abms.ProveedorVO;

public class EntregasServices {

	public void ingresarNuevaEntrega(EntregaVO nuevaEntrega) throws EntregasException {
		EntregasDAO entregasDAO = new EntregasDAO();
		entregasDAO.ingresarNuevaEntrega(nuevaEntrega);
	}

	public List<EntregaVO> obtenerTodasLasEntregas() throws EntregasException {
		EntregasDAO entregasDAO = new EntregasDAO();
		return entregasDAO.obtenerTodasLasEntregas();
	}

	public List<EntregaVO> buscarConFiltro(Date fechaDesde, Date fechaHasta, ProveedorVO proveedor, MateriaPrimaVO tipoMateriaPrima) throws EntregasException {
		EntregasDAO entregasDAO = new EntregasDAO();
		return entregasDAO.buscarConFiltro(fechaDesde, fechaHasta, proveedor, tipoMateriaPrima);
	}
}