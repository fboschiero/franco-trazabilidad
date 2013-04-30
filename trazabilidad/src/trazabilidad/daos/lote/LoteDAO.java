package trazabilidad.daos.lote;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trazabilidad.daos.generic.AbstractDAO;
import trazabilidad.exceptions.jamonartesanal.IdLoteException;
import trazabilidad.exceptions.jamonartesanal.LoteException;
import trazabilidad.vo.lotes.LoteVO;
import trazabilidad.vo.lotes.LoteJamonArtesanalVO;

public class LoteDAO extends AbstractDAO {
	
	private String SQL_MAX_ID = "SELECT max(id) FROM lote";
	private String SQL_INSERT_LOTE = "INSERT INTO lote VALUES (?, ?, ?)";//id, nroLote, fechaCreado
	private String SQL_EXISTE_LOTE_BY_NRO_LOTE = "SELECT id FROM lote WHERE nrolote = ?";
	private String SQL_SELECT_LOTE_BY_NRO_LOTE = "SELECT * FROM lote WHERE nrolote = ?";
	private String SQL_SELECT_LOTE_BY_ID = "SELECT * FROM lote WHERE id = ?";
	
	public int crearLote(LoteJamonArtesanalVO nuevoLote) throws LoteException {
		
		PreparedStatement pstm = null;
		String sql;
		int result;
		int id = -1;
		sql = SQL_INSERT_LOTE;
		try {

			id = getNextId();
			pstm = getConenction().prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setString(2, String.valueOf(nuevoLote.getNroLote()));
			pstm.setDate(3, new Date(nuevoLote.getFechaCreado().getTime()));
			
			result = pstm.executeUpdate();
			
			if (result == 0) {
				System.out.println("No se inserto ningun registro");
			} else {
				System.out.println("Insert lote " + result + " ok!");
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al insertar un nuevo lote" );
			e.printStackTrace();
			throw new LoteException(e.getMessage());
		
		} catch (IdLoteException e) {
			throw new LoteException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
		
		return id;
	}
	
	private int getNextId() throws IdLoteException{
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		String sql = SQL_MAX_ID;
		int nextId = -1;
		
		try {

			pstm = getConenction().prepareStatement(sql);
			result = pstm.executeQuery();
			
			if(result.next()){
				nextId = result.getInt(1);
			} else {
				nextId = 1;
			}
			
			if(nextId >= 0 ){
				nextId ++;
			} else {
				throw new IdLoteException("No Se pudo Obtener el Id del Lote");
			}
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al insertar un nuevo lote" );
			e.printStackTrace();
			throw new IdLoteException("No Se pudo Obtener el Id del Lote");
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		
		return nextId;
	}
	
	public Integer findIdLoteByNroLote(String nroLote) throws LoteException {
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		String sql = SQL_EXISTE_LOTE_BY_NRO_LOTE;
		
		Integer id = null;
		
		try {

			pstm = getConenction().prepareStatement(sql);
			pstm.setString(1, nroLote);
			
			result = pstm.executeQuery();
			
			if(result.next()){
				id = result.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al consultar si existe el numero de lote: " + nroLote );
			e.printStackTrace();
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		
		return id;
	}
	
	public LoteVO findLoteByNroLote(String nroLote) throws LoteException {
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		String sql = SQL_SELECT_LOTE_BY_NRO_LOTE;
		
		LoteVO lote = new LoteVO();
		
		try {

			pstm = getConenction().prepareStatement(sql);
			pstm.setString(1, nroLote);
			
			result = pstm.executeQuery();
			
			if(result.next()){
				lote.setId(result.getInt(1));
				lote.setNroLote(new Long(result.getString(2)));
				lote.setFechaCreado(result.getDate(3));
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al consultar si existe el numero de lote: " + nroLote );
			e.printStackTrace();
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		
		return lote;
	}
	
	public LoteVO findLoteById(Integer id) throws LoteException {
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		String sql = SQL_SELECT_LOTE_BY_ID;
		
		LoteVO lote = new LoteVO();
		
		try {

			pstm = getConenction().prepareStatement(sql);
			pstm.setInt(1, id);
			
			result = pstm.executeQuery();
			
			if(result.next()){
				lote.setId(result.getInt(1));
				lote.setNroLote(new Long(result.getString(2)));
				lote.setFechaCreado(result.getDate(3));
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al consultar si existe el id de lote: " + id );
			e.printStackTrace();
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		
		return lote;
	}
}