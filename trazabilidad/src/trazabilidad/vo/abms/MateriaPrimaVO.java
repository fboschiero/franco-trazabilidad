package trazabilidad.vo.abms;

import java.io.Serializable;

import trazabilidad.vo.codiguera.CodigueraVO;

public class MateriaPrimaVO extends CodigueraVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TipoMateriaPrimaVO tipo;
	
	public MateriaPrimaVO() {
		super();
	}

	public MateriaPrimaVO(String codigo, String nombre, String descripcion, TipoMateriaPrimaVO tipo) {
		super(codigo, nombre, descripcion);
		this.tipo = tipo;
	}
	
	public TipoMateriaPrimaVO getTipo() {
		return tipo;
	}

	public void setTipo(TipoMateriaPrimaVO tipo) {
		this.tipo = tipo;
	}
}