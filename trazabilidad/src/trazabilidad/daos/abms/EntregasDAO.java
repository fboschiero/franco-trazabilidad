package trazabilidad.daos.abms;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trazabilidad.daos.generic.AbstractDAO;
import trazabilidad.daos.stock.StockDAO;
import trazabilidad.daos.util.DAOUtils;
import trazabilidad.exceptions.abms.EntregasException;
import trazabilidad.exceptions.generic.IdException;
import trazabilidad.vo.abms.EntregaVO;
import trazabilidad.vo.abms.MateriaPrimaVO;
import trazabilidad.vo.abms.ProveedorVO;

public class EntregasDAO extends AbstractDAO {

	private String TABLE_NAME = "entregas";
	private String SQL_INSERT_ENTREGA = "INSERT INTO entregas VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private String SQL_SELECT_ALL_ENTREGAS = " SELECT * FROM entregas "; 
	
	public void ingresarNuevaEntrega(EntregaVO nuevaEntrega) throws EntregasException {
			
		try {
			getConenction().setAutoCommit(false);
		
			// INSERTO LA ENTREGA
			insertEntrega(nuevaEntrega);
			
			// AGREGO AL STOCK
			agregarAlStock(nuevaEntrega);
			
			getConenction().commit();
			getConenction().setAutoCommit(true);
			
		} catch (SQLException e) {
			
			try {
				getConenction().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			System.out.println("Se produjo una excepción al insertar una nueva entrega" );
			e.printStackTrace();
			throw new EntregasException(e.getMessage());
		}
	}

	private void agregarAlStock(EntregaVO nuevaEntrega) throws EntregasException {
		
		StockDAO stockDAO = new StockDAO();
		stockDAO.agregarAlStock(nuevaEntrega);
		
	}

	private void insertEntrega(EntregaVO nuevaEntrega) throws EntregasException {
		PreparedStatement pstm = null;
		int result = -1;
		String sql = SQL_INSERT_ENTREGA;
		
		try {

			int id = getNextId(TABLE_NAME);
			pstm = getConenction().prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setDate(2, new Date(nuevaEntrega.getFechaIngreso().getTime()));
			pstm.setInt(3, nuevaEntrega.getMateriaPrima().getId());
			pstm.setInt(4, nuevaEntrega.getProveedor().getId());
			pstm.setDouble(5, nuevaEntrega.getCantidad());
			pstm.setBigDecimal(6, nuevaEntrega.getTempIngreso());
			if(nuevaEntrega.getFechaElaboracion() == null){
				pstm.setNull(7, java.sql.Types.DATE);
			} else {
				pstm.setDate(7, new Date(nuevaEntrega.getFechaElaboracion().getTime()));
			}
			if(nuevaEntrega.getFechaVencimiento() == null){
				pstm.setNull(8, java.sql.Types.DATE);
			} else {
				pstm.setDate(8, new Date(nuevaEntrega.getFechaVencimiento().getTime()));
			}
			
			pstm.setInt(9, nuevaEntrega.getCantidadPallets());
			pstm.setInt(10, nuevaEntrega.getCantidadCajas());
							
			result = pstm.executeUpdate();
			
			if (result == 0) {
				System.out.println("No se inserto ningun registro");
			} else {
				System.out.println("Insert entregas " + result + " ok!");
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al insertar una nueva entrega" );
			e.printStackTrace();
			throw new EntregasException(e.getMessage());
		
		} catch (IdException e) {
			throw new EntregasException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
	}

	public List<EntregaVO> obtenerTodasLasEntregas() throws EntregasException {
		
		List<EntregaVO> entregas = new ArrayList<EntregaVO>();
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		DAOUtils util = new DAOUtils();
		
		try {

			pstm = getConenction().prepareStatement(SQL_SELECT_ALL_ENTREGAS);
			
			result = pstm.executeQuery();
			
			while(result.next()){
				
				EntregaVO entrega = new EntregaVO();
				
				entrega.setId(result.getInt(1));
				entrega.setFechaIngreso(result.getDate(2));
				
				ResultSet resultMateriaPrima = findById(MateriasPrimasDAO.TABLE_NAME, result.getInt(3));
				entrega.setMateriaPrima(util.buildMateriaPrima(resultMateriaPrima)); 
				closeResultSet(resultMateriaPrima);
				
				ResultSet resultProveedor = findById(ProveedorDAO.TABLE_NAME, result.getInt(4));
				entrega.setProveedor(util.buildProveedor(resultProveedor)); 
				closeResultSet(resultProveedor);
				
				entrega.setCantidad(result.getDouble(5));
				entrega.setTempIngreso(result.getBigDecimal(6));
				entrega.setFechaElaboracion(result.getDate(7));
				entrega.setFechaVencimiento(result.getDate(8));
				entrega.setCantidadPallets(result.getInt(9));
				entrega.setCantidadCajas(result.getInt(10));
				
				entregas.add(entrega);
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al obtener todas las entregas" );
			e.printStackTrace();
			throw new EntregasException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		
		return entregas;
	}

	public List<EntregaVO> buscarConFiltro(java.util.Date fechaDesde, java.util.Date fechaHasta, ProveedorVO proveedor, MateriaPrimaVO tipoMateriaPrima) throws EntregasException {
		
		List<EntregaVO> entregas = new ArrayList<EntregaVO>();
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		DAOUtils util = new DAOUtils();
		
		try {

			String sql = SQL_SELECT_ALL_ENTREGAS + " WHERE 1=1 ";
			
			if(fechaDesde != null && fechaHasta != null){
				sql = sql + " AND FechaIngreso >= ? AND FechaIngreso <= ?";
			}
			
			if(proveedor != null){
				sql = sql + " AND Proveedor = " + proveedor.getId();
			}
			
			if(tipoMateriaPrima != null){
				sql = sql + " AND TipoMateriaPrima = " + tipoMateriaPrima.getId();
			}
			
			pstm = getConenction().prepareStatement(sql);
			if(fechaDesde != null && fechaHasta != null){
				pstm.setDate(1, new Date(fechaDesde.getTime()));
				pstm.setDate(2, new Date(fechaHasta.getTime()));
			}
			
			result = pstm.executeQuery();
			
			System.out.println(sql);
			
			while(result.next()){
				
				EntregaVO entrega = new EntregaVO();
				
				entrega.setId(result.getInt(1));
				entrega.setFechaIngreso(result.getDate(2));
				
				ResultSet resultMateriaPrima = findById(MateriasPrimasDAO.TABLE_NAME, result.getInt(3));
				entrega.setMateriaPrima(util.buildMateriaPrima(resultMateriaPrima)); 
				closeResultSet(resultMateriaPrima);
				
				ResultSet resultProveedor = findById(ProveedorDAO.TABLE_NAME, result.getInt(4));
				entrega.setProveedor(util.buildProveedor(resultProveedor)); 
				closeResultSet(resultProveedor);
				
				entrega.setCantidad(result.getDouble(5));
				entrega.setTempIngreso(result.getBigDecimal(6));
				entrega.setFechaElaboracion(result.getDate(7));
				entrega.setFechaVencimiento(result.getDate(8));
				entrega.setCantidadPallets(result.getInt(9));
				entrega.setCantidadCajas(result.getInt(10));
				
				entregas.add(entrega);
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al obtener todas las entregas" );
			e.printStackTrace();
			throw new EntregasException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		
		return entregas;
	}	
}