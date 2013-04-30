package trazabilidad.services.abms;

import java.util.List;

import trazabilidad.daos.abms.TipoMateriaPrimaDAO;
import trazabilidad.exceptions.abms.TipoMateriaPrimaException;
import trazabilidad.exceptions.generic.DeleteException;
import trazabilidad.vo.abms.TipoMateriaPrimaVO;

public class TipoMateriaPrimaServices {
	
	public List<TipoMateriaPrimaVO> obtenerTodosLosTipos() throws TipoMateriaPrimaException {
		TipoMateriaPrimaDAO tipoDAO = new TipoMateriaPrimaDAO();
		return tipoDAO.obtenerTodosLosTipos();
	}

	public void nuevo(TipoMateriaPrimaVO nuevoTipo) throws TipoMateriaPrimaException{
		TipoMateriaPrimaDAO tipoDAO = new TipoMateriaPrimaDAO();
		tipoDAO.nuevo(nuevoTipo);
	}

	public void borrar(TipoMateriaPrimaVO tipo) throws DeleteException {
		TipoMateriaPrimaDAO tipoDAO = new TipoMateriaPrimaDAO();
		tipoDAO.borrar(tipo);
	}

	public void modificar(TipoMateriaPrimaVO tipo) throws TipoMateriaPrimaException{
		TipoMateriaPrimaDAO tipoDAO = new TipoMateriaPrimaDAO();
		tipoDAO.modificar(tipo);
	}
}