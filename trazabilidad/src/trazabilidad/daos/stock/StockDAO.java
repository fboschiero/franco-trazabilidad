package trazabilidad.daos.stock;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trazabilidad.daos.abms.MateriasPrimasDAO;
import trazabilidad.daos.abms.ProveedorDAO;
import trazabilidad.daos.abms.TipoMateriaPrimaDAO;
import trazabilidad.daos.generic.AbstractDAO;
import trazabilidad.daos.util.DAOUtils;
import trazabilidad.exceptions.abms.EntregasException;
import trazabilidad.exceptions.generic.IdException;
import trazabilidad.exceptions.stock.StockException;
import trazabilidad.vo.abms.EntregaVO;
import trazabilidad.vo.abms.ProveedorVO;
import trazabilidad.vo.abms.MateriaPrimaVO;
import trazabilidad.vo.stock.StockVO;

public class StockDAO extends AbstractDAO {

	public static String TABLE_NAME = "stock";
	private String SQL_INSERT_STOCK = "INSERT INTO stock VALUES (?, ?, ?, ?, ?)";
	private String SQL_SELECT_PRODUCTO = "SELECT * FROM stock WHERE materia_prima_id = ? AND proveedor_id = ?";
	private String SQL_UPDATE_CANTIDAD = "UPDATE stock SET cantidad = ? WHERE id = ? ";
	private String SQL_SELECT_ALL = "SELECT * FROM stock ";
	private String SQL_UPDATE_CANTIDAD_POR_TIPO_Y_PROVEEDOR = "UPDATE stock SET cantidad = ? WHERE materia_prima_id = ? AND proveedor_id = ? ";
	private String SQL_SELECT_CANTIDAD_POR_TIPO_Y_PROVEEDOR = "SELECT cantidad FROM stock WHERE materia_prima_id = ? AND proveedor_id = ? ";

	public void agregarAlStock(EntregaVO nuevaEntrega) throws EntregasException {
		
		// Veo primero si el producto existe
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try {

			pstm = getConenction().prepareStatement(SQL_SELECT_PRODUCTO);
			pstm.setInt(1, nuevaEntrega.getMateriaPrima().getId());
			pstm.setInt(2, nuevaEntrega.getProveedor().getId());
			
			result = pstm.executeQuery();
			
			if(result.next()){
				// El producto ya existe entonces le sumo la cantidad
				StockVO stock = new StockVO();
				
				stock.setId(result.getInt(1));
				BigDecimal cantidadStock = (result.getBigDecimal(4));
				
				//le sumo lo entregado
				stock.setCantidad(new BigDecimal(cantidadStock.doubleValue() + nuevaEntrega.getCantidad().doubleValue()));
				
				// Actualizo la base
				pstm = null;
				String sql = SQL_UPDATE_CANTIDAD;
				
				pstm = getConenction().prepareStatement(sql);
				pstm.setBigDecimal(1, stock.getCantidad());
				pstm.setInt(2, stock.getId());
					
				pstm.executeUpdate();
				
			} else {
				// No existe el producto en el stock, lo ingreso por primera vez
				pstm = null;
				String sql = SQL_INSERT_STOCK;
				
				int id = getNextId(TABLE_NAME);
				pstm = getConenction().prepareStatement(sql);
				pstm.setInt(1, id);
				pstm.setInt(2, nuevaEntrega.getMateriaPrima().getId());
				pstm.setInt(3, nuevaEntrega.getProveedor().getId());
				pstm.setDouble(4, nuevaEntrega.getCantidad());
				pstm.setDouble(5, nuevaEntrega.getMateriaPrima().getTipo().getId());
								
				pstm.executeUpdate();
			}		
				
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al actualizar el stock" );
			e.printStackTrace();
			throw new EntregasException(e.getMessage());
		
		} catch (IdException e) {
			System.out.println("Se produjo una excepción al actualizar el stock" );
			e.printStackTrace();
			throw new EntregasException(e.getMessage());
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
	}

	public List<StockVO> obtenerListadoStock() throws StockException {
		List<StockVO> resu = new ArrayList<StockVO>();
			
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try {

			pstm = getConenction().prepareStatement(SQL_SELECT_ALL);
			
			result = pstm.executeQuery();
			
			DAOUtils util = new DAOUtils();
			
			while(result.next()){
				
				StockVO s = new StockVO();
				
				s.setId(result.getInt(1));
				
				ResultSet resultMateriaPrimao = findById(MateriasPrimasDAO.TABLE_NAME, result.getInt(2));
				s.setMateriaPrima(util.buildMateriaPrima(resultMateriaPrimao)); 
				closeResultSet(resultMateriaPrimao);
				
				ResultSet resultProveedor = findById(ProveedorDAO.TABLE_NAME, result.getInt(3));
				s.setProveedor(util.buildProveedor(resultProveedor));
				closeResultSet(resultProveedor);
				
				s.setCantidad(result.getBigDecimal(4));
				
				ResultSet resultTipo = findById(TipoMateriaPrimaDAO.TABLE_NAME, result.getInt(5));
				s.setTipoMateriaPrima(util.buildTipoMateriaPrima(resultTipo)); 
				closeResultSet(resultTipo);
				
				resu.add(s);
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al obtener el listado de stock" );
			e.printStackTrace();
			throw new StockException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		
		return resu;
	}

	public void bajaStock(MateriaPrimaVO tipoMateriaPrima, ProveedorVO proveedor, BigDecimal cantidad) throws StockException {
		
		// Obtengo la cantidad actual
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try {

			pstm = getConenction().prepareStatement(SQL_SELECT_CANTIDAD_POR_TIPO_Y_PROVEEDOR);
			pstm.setInt(1, tipoMateriaPrima.getId());
			pstm.setInt(2, proveedor.getId());
			
			result = pstm.executeQuery();
			
			if(result.next()){
				BigDecimal cantidadActual = result.getBigDecimal(1);  
				
				//le resto la cantidad que retiran
				BigDecimal nuevaCantidad = new BigDecimal(cantidadActual.doubleValue() - cantidad.doubleValue());
				
				// Actualizo la base
				pstm = null;
				String sql = SQL_UPDATE_CANTIDAD_POR_TIPO_Y_PROVEEDOR;
				
				pstm = getConenction().prepareStatement(sql);
				pstm.setBigDecimal(1, nuevaCantidad);
				pstm.setInt(2, tipoMateriaPrima.getId());
				pstm.setInt(3, proveedor.getId());
					
				pstm.executeUpdate();
				
			}
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al restar mercaderia al stock" );
			e.printStackTrace();
			throw new StockException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
	}

	public List<StockVO> buscarConFiltros(ProveedorVO proveedor, MateriaPrimaVO tipoMateriaPrima) throws StockException {
		List<StockVO> resu = new ArrayList<StockVO>();
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try {
			
			String sql = SQL_SELECT_ALL + " WHERE ";
			
			
			if(proveedor != null){
				sql = sql + " proveedor_id = " + proveedor.getId(); 
				
				if( tipoMateriaPrima != null){
					sql = sql + " and "; 
				}
			}
			
			if( tipoMateriaPrima != null){
				sql = sql + " materia_prima_id = " + tipoMateriaPrima.getId(); 
			}
			System.out.println(sql);
			pstm = getConenction().prepareStatement(sql);
			
			result = pstm.executeQuery();
			
			DAOUtils util = new DAOUtils();
			
			while(result.next()){
				
				StockVO s = new StockVO();
				
				s.setId(result.getInt(1));
				
				ResultSet resultMateriaPrimao = findById(MateriasPrimasDAO.TABLE_NAME, result.getInt(2));
				s.setMateriaPrima(util.buildMateriaPrima(resultMateriaPrimao)); 
				closeResultSet(resultMateriaPrimao);
				
				ResultSet resultProveedor = findById(ProveedorDAO.TABLE_NAME, result.getInt(3));
				s.setProveedor(util.buildProveedor(resultProveedor));
				closeResultSet(resultProveedor);
				
				s.setCantidad(result.getBigDecimal(4));
				
				ResultSet resultTipo = findById(TipoMateriaPrimaDAO.TABLE_NAME, result.getInt(5));
				s.setTipoMateriaPrima(util.buildTipoMateriaPrima(resultTipo)); 
				closeResultSet(resultTipo);
				
				resu.add(s);
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al obtener el listado de stock" );
			e.printStackTrace();
			throw new StockException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		
		return resu;
	}
}