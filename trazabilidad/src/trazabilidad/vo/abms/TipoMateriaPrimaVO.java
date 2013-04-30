package trazabilidad.vo.abms;

import java.io.Serializable;

import trazabilidad.vo.codiguera.CodigueraVO;

public class TipoMateriaPrimaVO extends CodigueraVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public TipoMateriaPrimaVO() {
		super();
	}

	public TipoMateriaPrimaVO(String codigo, String nombre, String descripcion) {
		super(codigo, nombre, descripcion);
	}
}