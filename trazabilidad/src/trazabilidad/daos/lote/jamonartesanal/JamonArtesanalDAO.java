package trazabilidad.daos.lote.jamonartesanal;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import trazabilidad.daos.generic.AbstractDAO;
import trazabilidad.daos.lote.LoteDAO;
import trazabilidad.exceptions.jamonartesanal.JamonArtesanalException;
import trazabilidad.exceptions.jamonartesanal.LoteException;
import trazabilidad.vo.lotes.LoteJamonArtesanalVO;
import trazabilidad.vo.lotes.LoteVO;

public class JamonArtesanalDAO extends AbstractDAO {

	private String SQL_INSERT_LOTE_JAMON_ARTESANAL = "INSERT INTO lotejamonartesanal VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private String SQL_INGRESAR_COCCION = "UPDATE lotejamonartesanal " +
										  " SET horaIngresoCoccion = ?," +
										  "     tempIngresoCoccion = ?," +
										  "     horaSalidaCoccion = ?," +
										  "     tempSalidaCoccion = ?, " +
										  "     nroTacho = ?, " +
										  "     operadorCoccion = ? " +
										  " WHERE id = ? ";
	
	private String SQL_UPDATE_FINALIZAR_INYECCION = " UPDATE lotejamonartesanal " +
												    " 	SET horaSalidaInyeccion = ?, tempSalidaInyeccion = ? " +
													" WHERE id = ? ";
	private String SQL_UPDATE_INICIO_COCCION = " UPDATE lotejamonartesanal " +
												    " 	SET horaIngresoCoccion = ?, tempIngresoCoccion = ?, nroTacho = ? " +
													" WHERE id = ? ";
	
	private String SQL_UPDATE_FIN_COCCION = " UPDATE lotejamonartesanal " +
											   " 	SET horaSalidaCoccion = ?, tempSalidaCoccion = ? " +
											   " WHERE id = ? ";
	
	private String SQL_SELECT_LOTE_BY_ID = "SELECT * FROM lotejamonartesanal WHERE id = ?";
	
	private String SQL_SELECT_ALL_LOTES = "SELECT * FROM lotejamonartesanal ORDER BY id DESC limit 50 ";
	
	private LoteDAO loteDao;
	
	public String crearLote(LoteJamonArtesanalVO nuevoLote) {
		
		loteDao = new LoteDAO();
		String nroLote = "";
		int id = -1;
		
		try {
			
			nroLote = generarNroLote();
			nuevoLote.setNroLote(Long.valueOf(nroLote));
			
			getConenction().setAutoCommit(false);
			id = loteDao.crearLote(nuevoLote);
			crearLoteJamonArtesanal(nuevoLote, id);
			getConenction().commit();
			getConenction().setAutoCommit(true);
		
		} catch (LoteException e) {
			try {
				getConenction().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} catch (SQLException e) {
			try {
				getConenction().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return nroLote;
	}
	
	private String generarNroLote() {
		String resu = String.valueOf(System.currentTimeMillis());
		if(resu.length() > 9){
			resu = resu.substring(0, 8);
		}
		return resu;
	}

	private boolean crearLoteJamonArtesanal(LoteJamonArtesanalVO nuevoLote, int id) throws LoteException {
		
		PreparedStatement pstm = null;
		String sql;
		int result;
		sql = SQL_INSERT_LOTE_JAMON_ARTESANAL;
		
		try {

			pstm = getConenction().prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setBigDecimal(2, nuevoLote.getKgCarne());
			pstm.setString(3, nuevoLote.getHoraIngresoInyeccion());
			pstm.setBigDecimal(4, nuevoLote.getTempIngresoInyeccion() );
			pstm.setString(5, nuevoLote.getHoraSalidaInyeccion());
			pstm.setBigDecimal(6, nuevoLote.getTempSalidaInyeccion());
			pstm.setString(7, nuevoLote.getOperarioResponsableInyeccion());
			pstm.setString(8, null);
			pstm.setBigDecimal(9, null );
			pstm.setString(10, null);
			pstm.setBigDecimal(11, null);
			pstm.setBigDecimal(12, null);
			pstm.setString(13, null);
			
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
		
		} finally {
			closePreparedStatement(pstm);
		}
		
		return true;
	}

	public void ingresarCoccion(LoteJamonArtesanalVO lote) {
		
		PreparedStatement pstm = null;
		String sql = SQL_INGRESAR_COCCION;
		
		try {
			//horaIngresoCoccion, tempIngresoCoccion, horaSalidaCoccion, tempSalidaCoccion, nroTacho, operarioCoccion
			pstm = getConenction().prepareStatement(sql);
			pstm.setString(1, lote.getHoraIngresoCoccion());
			pstm.setBigDecimal(2, lote.getTempIngresoCoccion());
			pstm.setString(3, lote.getHoraSalidaCoccion());
			pstm.setBigDecimal(4, lote.getTempSalidaCoccion());
			pstm.setInt(5, lote.getNroTacho());
			pstm.setString(6, lote.getOperarioResponsableCoccion());
			pstm.setInt(7, lote.getId());
			
			pstm.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al ingresar los datos de la coccion al lote id: " + lote.getId());
			e.printStackTrace();
		
		} finally {
			closePreparedStatement(pstm);
		}
	}

	public LoteJamonArtesanalVO findLoteByNro(String nroLote) throws LoteException {
		
		loteDao = new LoteDAO();
		LoteVO absLote = loteDao.findLoteByNroLote(nroLote);
		LoteJamonArtesanalVO resu = new LoteJamonArtesanalVO(absLote);
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		String sql = SQL_SELECT_LOTE_BY_ID;
		
		try {

			pstm = getConenction().prepareStatement(sql);
			pstm.setInt(1, resu.getId());
			
			result = pstm.executeQuery();
			
			if(result.next()){
				resu.setKgCarne(result.getBigDecimal(2));
				resu.setHoraIngresoInyeccion(result.getString(3));
				resu.setTempIngresoInyeccion(result.getBigDecimal(4));
				resu.setHoraSalidaInyeccion(result.getString(5));
				resu.setTempSalidaInyeccion(result.getBigDecimal(6));
				resu.setOperarioResponsableInyeccion(result.getString(7));
				resu.setHoraIngresoCoccion(result.getString(8));
				resu.setTempIngresoCoccion(result.getBigDecimal(9));
				resu.setHoraSalidaCoccion(result.getString(10));
				resu.setTempSalidaCoccion(result.getBigDecimal(11));
				resu.setNroTacho(result.getInt(12));
				resu.setOperarioResponsableCoccion(result.getString(13));
				
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al consultar si existe el numero de lote: " + nroLote );
			e.printStackTrace();
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		return resu;
		
	}

	public void guardarFinInyeccion(Integer id, String horaSalidaInyeccion, BigDecimal tempSalidaInyeccion, String usuarioInyeccion) throws JamonArtesanalException {
		
		PreparedStatement pstm = null;
		String sql = SQL_UPDATE_FINALIZAR_INYECCION;
		
		try {
			pstm = getConenction().prepareStatement(sql);
			pstm.setString(1, horaSalidaInyeccion);
			pstm.setBigDecimal(2, tempSalidaInyeccion);
			pstm.setInt(3, id);
			
			pstm.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al ingresar los datos de la inyeccion (etapa 2) al lote id: " + id);
			e.printStackTrace();
			throw new JamonArtesanalException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
	}

	public void guardarInicioCoccion(Integer id, String horaIngresoCoccion, BigDecimal tempIngresoCoccion, String nroTacho, String usuarioCoccion) throws JamonArtesanalException {
		
		PreparedStatement pstm = null;
		String sql = SQL_UPDATE_INICIO_COCCION;
		
		try {
			pstm = getConenction().prepareStatement(sql);
			pstm.setString(1, horaIngresoCoccion);
			pstm.setBigDecimal(2, tempIngresoCoccion);
			pstm.setString(3, nroTacho);
			pstm.setInt(4, id);
			
			pstm.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al ingresar los datos de la Coccion (etapa 3) al lote id: " + id);
			e.printStackTrace();
			throw new JamonArtesanalException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
	}

	public void guardarFinCoccion(Integer id, String horaSalidaCoccion, BigDecimal tempSalidaCoccion, String usuarioCoccion) throws JamonArtesanalException {
		
		PreparedStatement pstm = null;
		String sql = SQL_UPDATE_FIN_COCCION;
		
		try {
			pstm = getConenction().prepareStatement(sql);
			pstm.setString(1, horaSalidaCoccion);
			pstm.setBigDecimal(2, tempSalidaCoccion);
			pstm.setInt(3, id);
			
			pstm.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al ingresar los datos de la Coccion (etapa 4) al lote id: " + id);
			e.printStackTrace();
			throw new JamonArtesanalException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
	}
	
	/**
	 * retorna los ultimos 20 lotes
	 * @throws JamonArtesanalException 
	 * */
	public List<LoteJamonArtesanalVO> obtenerTodosLosLotes() throws JamonArtesanalException {
		
		List<LoteJamonArtesanalVO> resu = new ArrayList<LoteJamonArtesanalVO>();
		LoteJamonArtesanalVO lote = null;
				
		PreparedStatement pstm = null;
		ResultSet result = null;
		String sql = SQL_SELECT_ALL_LOTES;
		
		try {

			pstm = getConenction().prepareStatement(sql);
			
			result = pstm.executeQuery();
			
			while (result.next()){
				lote = new LoteJamonArtesanalVO();
				lote.setId(result.getInt(1));
				lote.setKgCarne(result.getBigDecimal(2));
				lote.setHoraIngresoInyeccion(result.getString(3));
				lote.setTempIngresoInyeccion(result.getBigDecimal(4));
				lote.setHoraSalidaInyeccion(result.getString(5));
				lote.setTempSalidaInyeccion(result.getBigDecimal(6));
				lote.setOperarioResponsableInyeccion(result.getString(7));
				lote.setHoraIngresoCoccion(result.getString(8));
				lote.setTempIngresoCoccion(result.getBigDecimal(9));
				lote.setHoraSalidaCoccion(result.getString(10));
				lote.setTempSalidaCoccion(result.getBigDecimal(11));
				lote.setNroTacho(result.getInt(12));
				lote.setOperarioResponsableCoccion(result.getString(13));
				
				// Cargo los datos de la tabla 'lotes'
				loteDao = new LoteDAO();
				LoteVO absLote = loteDao.findLoteById(lote.getId());
				lote.setNroLote(absLote.getNroLote());
				lote.setFechaCreado(absLote.getFechaCreado());
				
				resu.add(lote);
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al obtener todos los lotes ");
			e.printStackTrace();
			throw new JamonArtesanalException(e.getMessage());
		
		} catch (LoteException e) {
			e.printStackTrace();
			throw new JamonArtesanalException(e.getMessage());
			
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		return resu;
		
	}

	public List<LoteJamonArtesanalVO> buscarConFiltros(Date fechaDesde, Date fechaHasta) throws JamonArtesanalException {
		List<LoteJamonArtesanalVO> resu = new ArrayList<LoteJamonArtesanalVO>();
		LoteJamonArtesanalVO lote = null;
				
		PreparedStatement pstm = null;
		ResultSet result = null;
		String sql = SQL_SELECT_ALL_LOTES ;
		
		try {

			pstm = getConenction().prepareStatement(sql);
			
			result = pstm.executeQuery();
			
			while (result.next()){
				lote = new LoteJamonArtesanalVO();
				lote.setId(result.getInt(1));
				lote.setKgCarne(result.getBigDecimal(2));
				lote.setHoraIngresoInyeccion(result.getString(3));
				lote.setTempIngresoInyeccion(result.getBigDecimal(4));
				lote.setHoraSalidaInyeccion(result.getString(5));
				lote.setTempSalidaInyeccion(result.getBigDecimal(6));
				lote.setOperarioResponsableInyeccion(result.getString(7));
				lote.setHoraIngresoCoccion(result.getString(8));
				lote.setTempIngresoCoccion(result.getBigDecimal(9));
				lote.setHoraSalidaCoccion(result.getString(10));
				lote.setTempSalidaCoccion(result.getBigDecimal(11));
				lote.setNroTacho(result.getInt(12));
				lote.setOperarioResponsableCoccion(result.getString(13));
				
				// Cargo los datos de la tabla 'lotes'
				loteDao = new LoteDAO();
				LoteVO absLote = loteDao.findLoteById(lote.getId());
				lote.setNroLote(absLote.getNroLote());
				lote.setFechaCreado(absLote.getFechaCreado());
				
				if(lote.getFechaCreado().compareTo(fechaDesde) >=0 && lote.getFechaCreado().compareTo(fechaHasta) <= 0){
					resu.add(lote);
				}
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al obtener todos los lotes ");
			e.printStackTrace();
			throw new JamonArtesanalException(e.getMessage());
		
		} catch (LoteException e) {
			e.printStackTrace();
			throw new JamonArtesanalException(e.getMessage());
			
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		return resu;
	}
}