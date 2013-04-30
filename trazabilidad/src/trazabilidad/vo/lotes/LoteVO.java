package trazabilidad.vo.lotes;

import java.io.Serializable;
import java.util.Date;

public class LoteVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private long nroLote;
	private Date fechaCreado;
	private String usuario;
	
	public LoteVO() {
		super();
	}

	public LoteVO(int id, long nroLote, Date fechaCreado, String usuario) {
		super();
		this.id = id;
		this.nroLote = nroLote;
		this.fechaCreado = fechaCreado;
		this.usuario = usuario;
	}
	
	public Date getFechaCreado() {
		return fechaCreado;
	}
	
	public void setFechaCreado(Date fechaCreado) {
		this.fechaCreado = fechaCreado;
	}
	
	public long getNroLote() {
		return nroLote;
	}
	
	public void setNroLote(long nroLote) {
		this.nroLote = nroLote;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}