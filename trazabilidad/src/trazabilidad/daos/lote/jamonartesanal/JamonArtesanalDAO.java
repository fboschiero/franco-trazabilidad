package trazabilidad.daos.lote.jamonartesanal;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import trazabilidad.daos.abms.ProveedorDAO;
import trazabilidad.daos.generic.AbstractDAO;
import trazabilidad.daos.lote.LoteDAO;
import trazabilidad.daos.util.DAOUtils;
import trazabilidad.exceptions.jamonartesanal.JamonArtesanalException;
import trazabilidad.exceptions.jamonartesanal.LoteException;
import trazabilidad.vo.lotes.LoteJamonArtesanalVO;
import trazabilidad.vo.lotes.LoteVO;

public class JamonArtesanalDAO extends AbstractDAO {

	private String SQL_INSERT_LOTE_JAMON_ARTESANAL = "INSERT INTO lotejamonartesanal VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private String SQL_INGRESAR_COCCION = "UPDATE lotejamonartesanal " +
										  " SET horaIngresoCoccion = ?," +
										  "     tempIngresoCoccion = ?," +
										  "     horaSalidaCoccion = ?," +
										  "     tempSalidaCoccion = ?, " +
										  "     nroTacho = ?, " +
										  "     operadorCoccion = ? " +
										  " WHERE id = ? ";
	
	private String SQL_UPDATE_ETAPA_BOMBO = " UPDATE lotejamonartesanal " +
												    " 	SET horaIngresoBombo = ?, horaSalidaBombo = ? " +
													" WHERE id = ? ";
	
	private String SQL_UPDATE_COCCION = " UPDATE lotejamonartesanal " +
												    " 	SET horaIngresoCoccion = ?, tempIngresoCoccion = ?, nroTacho = ?, horaSalidaCoccion = ?, tempSalidaCoccion = ?, operadorCoccion = ? " +
													" WHERE id = ? ";
	
	private String SQL_UPDATE_ENFRIAMIENTO = " UPDATE lotejamonartesanal " +
											   " 	SET nroTachoEnfriamiento = ?, horaSalidaACamara = ?, tempSalidaACamara = ? " +
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
			pstm.setInt(14, nuevoLote.getProveedor().getId());
			pstm.setBigDecimal(15, nuevoLote.getTempSalmuera());
			pstm.setString(16, null);
			pstm.setString(17, null);
			pstm.setInt(18, Types.INTEGER);
			pstm.setString(19, null);
			pstm.setString(20, null);
						
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
		DAOUtils util = new DAOUtils();
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
				
				ResultSet resultProveedor = findById(ProveedorDAO.TABLE_NAME, result.getInt(14));
				resu.setProveedor(util.buildProveedor(resultProveedor)); 
				closeResultSet(resultProveedor);
				
				resu.setTempSalmuera(result.getBigDecimal(15));
				resu.setHoraIngresoBombo(result.getString(16));
				resu.setHoraSalidaBombo(result.getString(17));
				
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

	public void guardarEtapaBombo(Integer id, String horaIngresoBombo, String horaSalidaBombo, String usuarioBombo) throws JamonArtesanalException {
		
		PreparedStatement pstm = null;
		String sql = SQL_UPDATE_ETAPA_BOMBO;
		
		try {
			pstm = getConenction().prepareStatement(sql);
			pstm.setString(1, horaIngresoBombo);
			pstm.setString(2, horaSalidaBombo);
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

	public void guardarCoccion(Integer id, String horaIngresoCoccion, BigDecimal tempIngresoCoccion, String nroTacho, String horaSalidaCoccion, BigDecimal tempSalidaCoccion, String usuarioCoccion) throws JamonArtesanalException {
		
		PreparedStatement pstm = null;
		String sql = SQL_UPDATE_COCCION;
		
		try {
			pstm = getConenction().prepareStatement(sql);
			pstm.setString(1, horaIngresoCoccion);
			pstm.setBigDecimal(2, tempIngresoCoccion);
			pstm.setString(3, nroTacho);
			pstm.setString(4, horaSalidaCoccion);
			pstm.setBigDecimal(5, tempSalidaCoccion);
			pstm.setString(6, usuarioCoccion);
			pstm.setInt(7, id);
			
			pstm.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al ingresar los datos de la Coccion (etapa 3) al lote id: " + id);
			e.printStackTrace();
			throw new JamonArtesanalException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
	}

	public void guardarEnfriado(Integer id, Integer nroTachoEnfriado, String horaSalidaACamara, BigDecimal tempSalidaACamara) throws JamonArtesanalException {
		
		PreparedStatement pstm = null;
		String sql = SQL_UPDATE_ENFRIAMIENTO;
		
		try {
			pstm = getConenction().prepareStatement(sql);
			pstm.setInt(1, nroTachoEnfriado);
			pstm.setString(2, horaSalidaACamara);
			pstm.setBigDecimal(3, tempSalidaACamara);
			pstm.setInt(4, id);
			
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
		DAOUtils util = new DAOUtils();
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
				
				ResultSet resultProveedor = findById(ProveedorDAO.TABLE_NAME, result.getInt(14));
				lote.setProveedor(util.buildProveedor(resultProveedor)); 
				closeResultSet(resultProveedor);
				
				lote.setTempSalmuera(result.getBigDecimal(15));
				lote.setHoraIngresoBombo(result.getString(16));
				lote.setHoraSalidaBombo(result.getString(17));
				
				lote.setNroTachoEnfriado(result.getInt(18));
				lote.setHoraSalidaACamara(result.getString(19));
				lote.setTempSalidaACamara(result.getBigDecimal(20));
				
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
		DAOUtils util = new DAOUtils();	
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
				
				ResultSet resultProveedor = findById(ProveedorDAO.TABLE_NAME, result.getInt(14));
				lote.setProveedor(util.buildProveedor(resultProveedor)); 
				closeResultSet(resultProveedor);
				
				lote.setTempSalmuera(result.getBigDecimal(15));
				lote.setHoraIngresoBombo(result.getString(16));
				lote.setHoraSalidaBombo(result.getString(17));
				
				lote.setNroTachoEnfriado(result.getInt(18));
				lote.setHoraSalidaACamara(result.getString(19));
				lote.setTempSalidaACamara(result.getBigDecimal(20));
				
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