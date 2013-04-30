package trazabilidad.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import trazabilidad.exceptions.abms.TipoMateriaPrimaException;
import trazabilidad.services.abms.TipoMateriaPrimaServices;
import trazabilidad.vo.abms.TipoMateriaPrimaVO;

public class TipoMateriaPrimaConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoMateriaPrimaServices tipoServices = new TipoMateriaPrimaServices();
		
		TipoMateriaPrimaVO resu = null;
		
		try {
			List<TipoMateriaPrimaVO> tipos = tipoServices.obtenerTodosLosTipos();
			
			for(TipoMateriaPrimaVO mp: tipos){
				String nombre = mp.getCodigo() + " - " + mp.getNombre(); 
				if(nombre.equals(value)){
					resu = mp;
					break;
				}
			}
		
		} catch (TipoMateriaPrimaException e) {
			e.printStackTrace();
		}
		
		return resu;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof TipoMateriaPrimaVO){
			TipoMateriaPrimaVO mp = (TipoMateriaPrimaVO)value;
			return mp.getCodigo() + " - " + mp.getNombre();
		}
		return null;
	}

}
