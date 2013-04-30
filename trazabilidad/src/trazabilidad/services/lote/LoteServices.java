package trazabilidad.services.lote;

import trazabilidad.daos.lote.LoteDAO;
import trazabilidad.exceptions.jamonartesanal.LoteException;

public class LoteServices {

private LoteDAO loteDAO;
	
	public LoteServices(){
		
	}

	public Integer findIdLoteByNroLote(String nroLote) throws LoteException {
		loteDAO = new LoteDAO();
		return loteDAO.findIdLoteByNroLote(nroLote);
	}
}