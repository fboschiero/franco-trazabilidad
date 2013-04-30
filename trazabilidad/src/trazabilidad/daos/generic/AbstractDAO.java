package trazabilidad.daos.generic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import trazabilidad.exceptions.generic.DeleteException;
import trazabilidad.exceptions.generic.IdException;

public abstract class AbstractDAO {

	private static Connection conn;
	
	private String SQL_MAX_ID = "SELECT max(id) FROM ";
	private String SQL_DELETE = "DELETE FROM table WHERE id = ?";
	private String SQL_FIND_BY_ID = "SELECT * FROM table WHERE ID = ? ";
	
	public static Connection getConenction() {
		if(conn == null){
			conn = BDConeccion.getConnection(); 
		}
		return conn;
	}
	
	protected void closeResultSet(ResultSet rs) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (Exception e){
				System.out.println("Se produjo un error al cerrar el resultSet");
				e.printStackTrace();
			}
		}
	}
	
	protected void closePreparedStatement(PreparedStatement pstm){
		if(pstm != null){
			try{
				pstm.close();
			}catch(SQLException s){
				System.out.println("Se produjo una exception al cerrar el prepared statement");
				s.printStackTrace();
			}
		}
	}
	
	protected void closeStatement(Statement stmt){
		if(stmt != null){
			try{
				stmt.close();
			}catch(SQLException s){
				System.out.println("Se produjo una exception al cerrar el  statement");
				s.printStackTrace();
			}
		}
	}
	
	protected int getNextId(String tableName) throws IdException{
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		String sql = SQL_MAX_ID + tableName;
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
				throw new IdException("No Se pudo Obtener el Id de la tabla: " + tableName);
			}
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al insertar un nuevo proveedor" );
			e.printStackTrace();
			throw new IdException("No Se pudo Obtener el Id proveedor");
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		
		return nextId;
	}
	
	protected void delete(String tableName, int id) throws DeleteException {
		
		PreparedStatement pstm = null;
		String sql = SQL_DELETE.replace("table", tableName);
		
		try {

			pstm = getConenction().prepareStatement(sql);
			pstm.setInt(1, id);
			
			pstm.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al borrar tabla: " + tableName + " id: " + id );
			e.printStackTrace();
			throw new DeleteException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
	}
	
	protected ResultSet findById(String tableName, int id) throws SQLException{
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		String sql = SQL_FIND_BY_ID.replace("table", tableName);
		
		try {

			pstm = getConenction().prepareStatement(sql);
			pstm.setInt(1, id);
			result = pstm.executeQuery();
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción hacer el select por ID" );
			e.printStackTrace();
			throw new SQLException("No Se pudo Obtener el objeto por ID");
		
		} 		
		return result;
	}
}