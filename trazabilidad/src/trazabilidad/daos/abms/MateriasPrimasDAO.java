package trazabilidad.daos.abms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trazabilidad.daos.generic.AbstractDAO;
import trazabilidad.daos.util.DAOUtils;
import trazabilidad.exceptions.abms.MateriaPrimaException;
import trazabilidad.exceptions.generic.DeleteException;
import trazabilidad.exceptions.generic.IdException;
import trazabilidad.vo.abms.MateriaPrimaVO;

public class MateriasPrimasDAO extends AbstractDAO {

	public static String TABLE_NAME = "materias_primas";
	private String SQL_SELECT_ALL_MATERIAS_PRIMAS = " SELECT * FROM materias_primas "; 
	private String SQL_INSERT_MATERIA_PRIMA = "INSERT INTO materias_primas VALUES (?, ?, ?, ?, ?)";
	private String SQL_UPDATE_MATERIA_PRIMA = "UPDATE materias_primas " +
												   " SET codigo = ?," +
												   "     nombre = ?," +
												   "     descripcion = ?, " +
												   "     tipo_materia_prima_id = ? " +
												   " WHERE id = ? ";
	
	public List<MateriaPrimaVO> obtenerTodosLosTipos() throws MateriaPrimaException {
		
		List<MateriaPrimaVO> materiasPrimas = new ArrayList<MateriaPrimaVO>();
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try {

			pstm = getConenction().prepareStatement(SQL_SELECT_ALL_MATERIAS_PRIMAS);
			
			result = pstm.executeQuery();
			
			DAOUtils utils = new DAOUtils();
			
			while(result.next()){
				
				MateriaPrimaVO mp = new MateriaPrimaVO();
				
				mp.setId(result.getInt(1));
				mp.setCodigo(result.getString(2));
				mp.setNombre(result.getString(3));
				mp.setDescripcion(result.getString(4));
				
				
				ResultSet resultTipo = findById(TipoMateriaPrimaDAO.TABLE_NAME, result.getInt(5));
				mp.setTipo(utils.buildTipoMateriaPrima(resultTipo)); 
				closeResultSet(resultTipo);
				
				materiasPrimas.add(mp);
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al obtener todas las materias primas" );
			e.printStackTrace();
			throw new MateriaPrimaException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		
		return materiasPrimas;
	}

	public void ingresarNuevoTipo(MateriaPrimaVO nuevaMateriaPrima) throws MateriaPrimaException {
		
		PreparedStatement pstm = null;
		int result = -1;
		String sql = SQL_INSERT_MATERIA_PRIMA;
		
		try {

			int id = getNextId(TABLE_NAME);
			pstm = getConenction().prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setString(2, nuevaMateriaPrima.getCodigo());
			pstm.setString(3, nuevaMateriaPrima.getNombre());
			pstm.setString(4, nuevaMateriaPrima.getDescripcion());
			pstm.setInt(5, nuevaMateriaPrima.getTipo().getId());
			
			result = pstm.executeUpdate();
			
			if (result == 0) {
				System.out.println("No se inserto ningun registro");
			} else {
				System.out.println("Insert materia prima " + result + " ok!");
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al insertar una nueva materia prima" );
			e.printStackTrace();
			throw new MateriaPrimaException(e.getMessage());
		
		} catch (IdException e) {
			throw new MateriaPrimaException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
	}

	public void borrarTipoMateriaPrima(MateriaPrimaVO materiaPrima) throws DeleteException {
		
		delete(TABLE_NAME, materiaPrima.getId());
		
	}

	public void actualizarMateriaPrima(MateriaPrimaVO materiaPrima) throws MateriaPrimaException {
			
		PreparedStatement pstm = null;
		String sql = SQL_UPDATE_MATERIA_PRIMA;
		
		try {

			pstm = getConenction().prepareStatement(sql);
			pstm.setString(1, materiaPrima.getCodigo());
			pstm.setString(2, materiaPrima.getNombre());
			pstm.setString(3, materiaPrima.getDescripcion());
			pstm.setInt(4, materiaPrima.getTipo().getId());
			pstm.setInt(5, materiaPrima.getId());
			
			pstm.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al actualizar la materia prima id: " + materiaPrima.getId());
			e.printStackTrace();
			throw new MateriaPrimaException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
	}
}