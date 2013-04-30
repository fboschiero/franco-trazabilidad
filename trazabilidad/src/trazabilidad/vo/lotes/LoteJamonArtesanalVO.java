package trazabilidad.vo.lotes;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoteJamonArtesanalVO extends LoteVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal kgCarne;
	private String horaIngresoInyeccion;
	private BigDecimal tempIngresoInyeccion;
	private String horaSalidaInyeccion;
	private BigDecimal tempSalidaInyeccion;
	private String operarioResponsableInyeccion;
	private String horaIngresoCoccion;
	private BigDecimal tempIngresoCoccion;
	private String horaSalidaCoccion;
	private BigDecimal tempSalidaCoccion;
	private String operarioResponsableCoccion;
	private int nroTacho;
	
	public LoteJamonArtesanalVO() {
		super();
	}

	public LoteJamonArtesanalVO(BigDecimal kgCarne, String horaIngresoInyeccion, BigDecimal tempIngresoInyeccion, String horaSalidaInyeccion, BigDecimal tempSalidaInyeccion, String operarioResponsableInyeccion) {
		super();
		this.kgCarne = kgCarne;
		this.horaIngresoInyeccion = horaIngresoInyeccion;
		this.tempIngresoInyeccion = tempIngresoInyeccion;
		this.horaSalidaInyeccion = horaSalidaInyeccion;
		this.tempSalidaInyeccion = tempSalidaInyeccion;
		this.operarioResponsableInyeccion = operarioResponsableInyeccion;
	}

	public LoteJamonArtesanalVO(LoteVO absLote) {
		super(absLote.getId(), absLote.getNroLote(), absLote.getFechaCreado(), absLote.getUsuario());
	}

	public String getHoraIngresoCoccion() {
		return horaIngresoCoccion;
	}

	public void setHoraIngresoCoccion(String horaIngresoCoccion) {
		this.horaIngresoCoccion = horaIngresoCoccion;
	}

	public String getHoraIngresoInyeccion() {
		return horaIngresoInyeccion;
	}

	public void setHoraIngresoInyeccion(String horaIngresoInyeccion) {
		this.horaIngresoInyeccion = horaIngresoInyeccion;
	}

	public String getHoraSalidaCoccion() {
		return horaSalidaCoccion;
	}

	public void setHoraSalidaCoccion(String horaSalidaCoccion) {
		this.horaSalidaCoccion = horaSalidaCoccion;
	}

	public String getHoraSalidaInyeccion() {
		return horaSalidaInyeccion;
	}

	public void setHoraSalidaInyeccion(String horaSalidaInyeccion) {
		this.horaSalidaInyeccion = horaSalidaInyeccion;
	}

	public BigDecimal getKgCarne() {
		return kgCarne;
	}

	public void setKgCarne(BigDecimal kgCarne) {
		this.kgCarne = kgCarne;
	}

	public int getNroTacho() {
		return nroTacho;
	}

	public void setNroTacho(int nroTacho) {
		this.nroTacho = nroTacho;
	}

	public String getOperarioResponsableCoccion() {
		return operarioResponsableCoccion;
	}

	public void setOperarioResponsableCoccion(String operarioResponsableCoccion) {
		this.operarioResponsableCoccion = operarioResponsableCoccion;
	}

	public String getOperarioResponsableInyeccion() {
		return operarioResponsableInyeccion;
	}

	public void setOperarioResponsableInyeccion(String operarioResponsableInyeccion) {
		this.operarioResponsableInyeccion = operarioResponsableInyeccion;
	}

	public BigDecimal getTempIngresoCoccion() {
		return tempIngresoCoccion;
	}

	public void setTempIngresoCoccion(BigDecimal tempIngresoCoccion) {
		this.tempIngresoCoccion = tempIngresoCoccion;
	}

	public BigDecimal getTempIngresoInyeccion() {
		return tempIngresoInyeccion;
	}

	public void setTempIngresoInyeccion(BigDecimal tempIngresoInyeccion) {
		this.tempIngresoInyeccion = tempIngresoInyeccion;
	}

	public BigDecimal getTempSalidaCoccion() {
		return tempSalidaCoccion;
	}

	public void setTempSalidaCoccion(BigDecimal tempSalidaCoccion) {
		this.tempSalidaCoccion = tempSalidaCoccion;
	}

	public BigDecimal getTempSalidaInyeccion() {
		return tempSalidaInyeccion;
	}

	public void setTempSalidaInyeccion(BigDecimal tempSalidaInyeccion) {
		this.tempSalidaInyeccion = tempSalidaInyeccion;
	}
}