package trazabilidad.daos.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import trazabilidad.vo.abms.ProveedorVO;
import trazabilidad.vo.abms.MateriaPrimaVO;
import trazabilidad.vo.abms.TipoMateriaPrimaVO;

public class DAOUtils {

	public MateriaPrimaVO buildMateriaPrima(ResultSet result){
		
		MateriaPrimaVO mp = new MateriaPrimaVO();
		try {
			if(result.next()){
				mp.setId(result.getInt(1));
				mp.setCodigo(result.getString(2));
				mp.setNombre(result.getString(3));
				mp.setDescripcion(result.getString(4));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mp;
	}
	
	public TipoMateriaPrimaVO buildTipoMateriaPrima(ResultSet result){
		
		TipoMateriaPrimaVO tmp = new TipoMateriaPrimaVO();
		try {
			if(result.next()){
				tmp.setId(result.getInt(1));
				tmp.setCodigo(result.getString(2));
				tmp.setNombre(result.getString(3));
				tmp.setDescripcion(result.getString(4));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tmp;
	}

	public ProveedorVO buildProveedor(ResultSet result) {
		
		ProveedorVO proveedor = new ProveedorVO();
		
		try {
			if(result.next()){
				proveedor.setId(result.getInt(1));
				proveedor.setCodigo(result.getString(2));
				proveedor.setNombre(result.getString(3));
				proveedor.setDireccion(result.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proveedor;
	}
}
