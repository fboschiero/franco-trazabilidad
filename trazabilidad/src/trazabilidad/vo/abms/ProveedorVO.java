package trazabilidad.vo.abms;

import java.io.Serializable;

public class ProveedorVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String codigo;
	private String nombre;
	private String direccion;
	
	public ProveedorVO() {
		super();
	}
	
	public ProveedorVO(String codigo, String nombre, String direccion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}