package trazabilidad.services.abms;

import java.util.List;

import trazabilidad.daos.abms.ProveedorDAO;
import trazabilidad.exceptions.abms.ProveedorException;
import trazabilidad.vo.abms.ProveedorVO;

public class ProveedorServices {
	
	public List<ProveedorVO> obtenerTodosLosProveedores() throws ProveedorException {
		ProveedorDAO proveedorDAO = new ProveedorDAO();
		return proveedorDAO.obtenerTodosLosProveedores();
	}

	public void ingresarNuevoProveedor(ProveedorVO nuevoProveedor) throws ProveedorException {
		ProveedorDAO proveedorDAO = new ProveedorDAO();
		proveedorDAO.ingresarNuevoProveedor(nuevoProveedor);
	}

	public void borrarProveedor(ProveedorVO proveedor) throws ProveedorException {
		ProveedorDAO proveedorDAO = new ProveedorDAO();
		proveedorDAO.borrarProveedor(proveedor);
	}

	public void actualizarProveedor(ProveedorVO proveedor) throws ProveedorException {
		ProveedorDAO proveedorDAO = new ProveedorDAO();
		proveedorDAO.actualizarProveedor(proveedor);
	}
}