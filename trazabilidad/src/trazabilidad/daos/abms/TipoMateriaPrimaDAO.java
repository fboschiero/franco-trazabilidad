package trazabilidad.daos.abms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trazabilidad.daos.generic.AbstractDAO;
import trazabilidad.exceptions.abms.TipoMateriaPrimaException;
import trazabilidad.exceptions.generic.DeleteException;
import trazabilidad.exceptions.generic.IdException;
import trazabilidad.vo.abms.TipoMateriaPrimaVO;

public class TipoMateriaPrimaDAO extends AbstractDAO {

	public static String TABLE_NAME = "tipo_materia_prima";
	private String SQL_SELECT_ALL_TIPOS = " SELECT * FROM tipo_materia_prima "; 
	private String SQL_INSERT_TIPO_MATERIA_PRIMA = "INSERT INTO tipo_materia_prima VALUES (?, ?, ?, ?)";
	private String SQL_UPDATE_MATERIA_PRIMA =  " UPDATE materias_primas " +
											   " SET codigo = ?," +
											   "     nombre = ?," +
											   "     descripcion = ? " +
											   " WHERE id = ? ";
	
	public List<TipoMateriaPrimaVO> obtenerTodosLosTipos() throws TipoMateriaPrimaException {
		
		List<TipoMateriaPrimaVO> tipos = new ArrayList<TipoMateriaPrimaVO>();
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try {

			pstm = getConenction().prepareStatement(SQL_SELECT_ALL_TIPOS);
			
			result = pstm.executeQuery();
			
			while(result.next()){
				
				TipoMateriaPrimaVO mp = new TipoMateriaPrimaVO();
				
				mp.setId(result.getInt(1));
				mp.setCodigo(result.getString(2));
				mp.setNombre(result.getString(3));
				mp.setDescripcion(result.getString(4));
				
				tipos.add(mp);
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al obtener todos los tipos de materias primas" );
			e.printStackTrace();
			throw new TipoMateriaPrimaException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		
		return tipos;
	}

	public void nuevo(TipoMateriaPrimaVO nuevoTipo) throws TipoMateriaPrimaException {
		
		PreparedStatement pstm = null;
		int result = -1;
		String sql = SQL_INSERT_TIPO_MATERIA_PRIMA;
		
		try {

			int id = getNextId(TABLE_NAME);
			pstm = getConenction().prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setString(2, nuevoTipo.getCodigo());
			pstm.setString(3, nuevoTipo.getNombre());
			pstm.setString(4, nuevoTipo.getDescripcion());
			
			result = pstm.executeUpdate();
			
			if (result == 0) {
				System.out.println("No se inserto ningun registro");
			} else {
				System.out.println("Insert tipo materia prima " + result + " ok!");
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al insertar un nuevo tipo de materia prima" );
			e.printStackTrace();
			throw new TipoMateriaPrimaException(e.getMessage());
		
		} catch (IdException e) {
			throw new TipoMateriaPrimaException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
	}

	public void borrar(TipoMateriaPrimaVO tipo) throws DeleteException {
		delete(TABLE_NAME, tipo.getId());
		
	}

	public void modificar(TipoMateriaPrimaVO tipo) throws TipoMateriaPrimaException {
		
		PreparedStatement pstm = null;
		String sql = SQL_UPDATE_MATERIA_PRIMA;
		
		try {

			pstm = getConenction().prepareStatement(sql);
			pstm.setString(1, tipo.getCodigo());
			pstm.setString(2, tipo.getNombre());
			pstm.setString(3, tipo.getDescripcion());
			pstm.setInt(4, tipo.getId());
			
			pstm.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al actualizar el tipo de materia prima id: " + tipo.getId());
			e.printStackTrace();
			throw new TipoMateriaPrimaException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
	}
}