package trazabilidad.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import trazabilidad.exceptions.abms.MateriaPrimaException;
import trazabilidad.services.abms.MateriasPrimasServices;
import trazabilidad.vo.abms.MateriaPrimaVO;

public class MateriaPrimaConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		MateriasPrimasServices materiaPrimaServices = new MateriasPrimasServices();
		
		MateriaPrimaVO resu = null;
		
		try {
			List<MateriaPrimaVO> materiasPrimas = materiaPrimaServices.obtenerTodosLosTipos();
			
			for(MateriaPrimaVO mp: materiasPrimas){
				String nombre = mp.getCodigo() + " - " + mp.getNombre(); 
				if(nombre.equals(value)){
					resu = mp;
					break;
				}
			}
		
		} catch (MateriaPrimaException e) {
			e.printStackTrace();
		}
		
		return resu;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof MateriaPrimaVO){
			MateriaPrimaVO mp = (MateriaPrimaVO)value;
			return mp.getCodigo() + " - " + mp.getNombre();
		}
		return null;
	}

}
