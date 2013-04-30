package trazabilidad.daos.abms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trazabilidad.daos.generic.AbstractDAO;
import trazabilidad.exceptions.abms.ProveedorException;
import trazabilidad.exceptions.generic.DeleteException;
import trazabilidad.exceptions.generic.IdException;
import trazabilidad.vo.abms.ProveedorVO;

public class ProveedorDAO extends AbstractDAO {
	
	public static String TABLE_NAME = "proveedores";
	private String SQL_SELECT_ALL_PROVEEDORES = " SELECT * FROM proveedores "; 
	private String SQL_INSERT_PROVEEDOR = "INSERT INTO proveedores VALUES (?, ?, ?, ?)";
	private String SQL_UPDATE_PROVEEDOR = "UPDATE proveedores " +
										  " SET codigo = ?," +
										  "     nombre = ?," +
										  "     direccion = ? " +
										  " WHERE id = ? ";
	
	public List<ProveedorVO> obtenerTodosLosProveedores() throws ProveedorException {
		
		List<ProveedorVO> proveedores = new ArrayList<ProveedorVO>();
		
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try {

			pstm = getConenction().prepareStatement(SQL_SELECT_ALL_PROVEEDORES);
			
			result = pstm.executeQuery();
			
			while(result.next()){
				
				ProveedorVO proveedor = new ProveedorVO();
				
				proveedor.setId(result.getInt(1));
				proveedor.setCodigo(result.getString(2));
				proveedor.setNombre(result.getString(3));
				proveedor.setDireccion(result.getString(4));
				
				proveedores.add(proveedor);
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al obtener todos los proveedores" );
			e.printStackTrace();
			throw new ProveedorException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
			closeResultSet(result);
		}
		
		return proveedores;
	}

	public void ingresarNuevoProveedor(ProveedorVO nuevoProveedor) throws ProveedorException {
		
		PreparedStatement pstm = null;
		int result = -1;
		String sql = SQL_INSERT_PROVEEDOR;
		
		try {

			int id = getNextId(TABLE_NAME);
			pstm = getConenction().prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setString(2, nuevoProveedor.getCodigo());
			pstm.setString(3, nuevoProveedor.getNombre());
			pstm.setString(4, nuevoProveedor.getDireccion());
			
			result = pstm.executeUpdate();
			
			if (result == 0) {
				System.out.println("No se inserto ningun registro");
			} else {
				System.out.println("Insert proveedor " + result + " ok!");
			}

		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al insertar un nuevo proveedor" );
			e.printStackTrace();
			throw new ProveedorException(e.getMessage());
		
		} catch (IdException e) {
			throw new ProveedorException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
	}

	public void borrarProveedor(ProveedorVO proveedor) throws ProveedorException {
		
		try {
			delete(TABLE_NAME, proveedor.getId());
		
		} catch (DeleteException e) {
			System.out.println("Se produjo una excepción al borrar el proveedor" );
			e.printStackTrace();
			throw new ProveedorException(e.getMessage());
		}
	}

	public void actualizarProveedor(ProveedorVO proveedor) throws ProveedorException {
		
		PreparedStatement pstm = null;
		String sql = SQL_UPDATE_PROVEEDOR;
		
		try {

			pstm = getConenction().prepareStatement(sql);
			pstm.setString(1, proveedor.getCodigo());
			pstm.setString(2, proveedor.getNombre());
			pstm.setString(3, proveedor.getDireccion());
			pstm.setInt(4, proveedor.getId());
			
			pstm.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Se produjo una excepción al actualizar el proveedor" );
			e.printStackTrace();
			throw new ProveedorException(e.getMessage());
		
		} finally {
			closePreparedStatement(pstm);
		}
		
	}
}