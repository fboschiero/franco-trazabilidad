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

	public void guardarEtapaBombo(Integer id, String horaIngresoBombo, String horaSalidaBombo, String usuarioBombo) throws JamonArtesanalException {
		jamonDAO = new JamonArtesanalDAO();
		jamonDAO.guardarEtapaBombo(id, horaIngresoBombo, horaSalidaBombo, usuarioBombo);
	}

	public void guardarCoccion(Integer id, String horaIngresoCoccion, BigDecimal tempIngresoCoccion, String nroTacho, String horaSalidaCoccion, BigDecimal tempSalidaCoccion, String usuarioCoccion) throws JamonArtesanalException {
		jamonDAO = new JamonArtesanalDAO();
		jamonDAO.guardarCoccion(id, horaIngresoCoccion, tempIngresoCoccion, nroTacho, horaSalidaCoccion, tempSalidaCoccion, usuarioCoccion);
	}

	public void guardarEnfriado(Integer id, Integer nroTachoEnfriado, String horaSalidaACamara, BigDecimal tempSalidaACamara) throws JamonArtesanalException {
		jamonDAO = new JamonArtesanalDAO();
		jamonDAO.guardarEnfriado(id, nroTachoEnfriado, horaSalidaACamara, tempSalidaACamara);
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