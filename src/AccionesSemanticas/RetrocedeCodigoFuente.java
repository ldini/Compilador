package AccionesSemanticas;

import util.*;

public class RetrocedeCodigoFuente extends AccionSemantica {
	private CodigoFuente codigoFuente;
	
	public RetrocedeCodigoFuente(CodigoFuente codigoFuente){
		this.codigoFuente = codigoFuente;
	}
	
	@Override
	public void ejecutarAccionSemantica(){
		codigoFuente.retrocedePosicion();
	}
}
