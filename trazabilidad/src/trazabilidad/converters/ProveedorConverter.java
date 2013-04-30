package trazabilidad.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import trazabilidad.exceptions.abms.ProveedorException;
import trazabilidad.services.abms.ProveedorServices;
import trazabilidad.vo.abms.ProveedorVO;

public class ProveedorConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ProveedorServices proveedorServices = new ProveedorServices();
		
		ProveedorVO resu = null;
		
		try {
			List<ProveedorVO> proveedores = proveedorServices.obtenerTodosLosProveedores();
			
			for(ProveedorVO p: proveedores){
				String nombre = p.getCodigo() + " - " + p.getNombre(); 
				if(nombre.equals(value)){
					resu = p;
					break;
				}
			}
		
		} catch (ProveedorException e) {
			e.printStackTrace();
		}
		
		return resu;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof ProveedorVO){
			ProveedorVO proveedor = (ProveedorVO)value;
			return proveedor.getCodigo() + " - " + proveedor.getNombre();
		}
		return null;
	}

}
