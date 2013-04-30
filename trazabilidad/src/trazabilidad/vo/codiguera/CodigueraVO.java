package trazabilidad.vo.codiguera;

import java.io.Serializable;

public class CodigueraVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String codigo;
	private String nombre;
	private String descripcion;
	
	public CodigueraVO() {
		super();
	}

	public CodigueraVO(String codigo, String nombre, String descripcion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}