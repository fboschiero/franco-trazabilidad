package trazabilidad.services.jamonartesanal;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import trazabilidad.daos.lote.jamonartesanal.JamonArtesanalDAO;
import trazabilidad.exceptions.jamonartesanal.JamonArtesanalException;
import trazabilidad.exceptions.jamonartesanal.LoteException;
import trazabilidad.vo.lotes.LoteJamonArtesanalVO;

public class JamonArtesanalServices {

	private JamonArtesanalDAO jamonDAO;
	
	public JamonArtesanalServices(){
		
	}

	public String crearLote(LoteJamonArtesanalVO nuevoLote) throws LoteException {
		jamonDAO = new JamonArtesanalDAO();
		return jamonDAO.crearLote(nuevoLote);
	}

	public void ingresarCoccion(LoteJamonArtesanalVO lote) {
		jamonDAO = new JamonArtesanalDAO();
		jamonDAO.ingresarCoccion(lote);
		
	}

	public LoteJamonArtesanalVO findLoteByNro(String nroLote) throws LoteException {
		jamonDAO = new JamonArtesanalDAO();
		return jamonDAO.findLoteByNro(nroLote);
	}

	public void guardarFinInyeccion(Integer id, String horaSalidaInyeccion, BigDecimal tempSalidaInyeccion, String usuarioInyeccion) throws JamonArtesanalException {
		jamonDAO = new JamonArtesanalDAO();
		jamonDAO.guardarFinInyeccion(id, horaSalidaInyeccion, tempSalidaInyeccion, usuarioInyeccion);
	}

	public void guardarInicioCoccion(Integer id, String horaIngresoCoccion, BigDecimal tempIngresoCoccion, String nroTacho, String usuarioCoccion) throws JamonArtesanalException {
		jamonDAO = new JamonArtesanalDAO();
		jamonDAO.guardarInicioCoccion(id, horaIngresoCoccion, tempIngresoCoccion, nroTacho, usuarioCoccion);
	}

	public void guardarFinCoccion(Integer id, String horaSalidaCoccion, BigDecimal tempSalidaCoccion, String usuarioCoccion) throws JamonArtesanalException {
		jamonDAO = new JamonArtesanalDAO();
		jamonDAO.guardarFinCoccion(id, horaSalidaCoccion, tempSalidaCoccion, usuarioCoccion);
	}
	
	public List<LoteJamonArtesanalVO> obtenerTodosLosLotes() throws JamonArtesanalException{
		jamonDAO = new JamonArtesanalDAO();
		return jamonDAO.obtenerTodosLosLotes();
	}

	public List<LoteJamonArtesanalVO> buscarConFiltros(Date fechaDesde, Date fechaHasta) throws JamonArtesanalException {
		jamonDAO = new JamonArtesanalDAO();
		return jamonDAO.buscarConFiltros(fechaDesde, fechaHasta);
	}
}