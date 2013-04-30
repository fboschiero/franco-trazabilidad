package trazabilidad.services.abms;

import java.util.List;

import trazabilidad.daos.abms.MateriasPrimasDAO;
import trazabilidad.exceptions.abms.MateriaPrimaException;
import trazabilidad.exceptions.generic.DeleteException;
import trazabilidad.vo.abms.MateriaPrimaVO;

public class MateriasPrimasServices {
	
	private MateriasPrimasDAO materiasPrimasDAO;

	public List<MateriaPrimaVO> obtenerTodosLosTipos() throws MateriaPrimaException {
		materiasPrimasDAO = new MateriasPrimasDAO();
		return materiasPrimasDAO.obtenerTodosLosTipos();
	}

	public void ingresarNuevoTipo(MateriaPrimaVO nuevoTipo) throws MateriaPrimaException {
		materiasPrimasDAO = new MateriasPrimasDAO();
		materiasPrimasDAO.ingresarNuevoTipo(nuevoTipo);
	}

	public void borrarTipoMateriaPrima(MateriaPrimaVO materiaPrima) throws DeleteException {
		materiasPrimasDAO = new MateriasPrimasDAO();
		materiasPrimasDAO.borrarTipoMateriaPrima(materiaPrima);
	}

	public void actualizarMateriaPrima(MateriaPrimaVO materiaPrima) throws MateriaPrimaException {
		materiasPrimasDAO = new MateriasPrimasDAO();
		materiasPrimasDAO.actualizarMateriaPrima(materiaPrima);
	}
}