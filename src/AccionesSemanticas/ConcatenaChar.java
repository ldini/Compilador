package AccionesSemanticas;

import util.*;

public class ConcatenaChar extends AccionSemantica{
	
	private final CodigoFuente codigo;
	
	public ConcatenaChar(CodigoFuente codigo){
		this.codigo = codigo;
	}
	
	@Override
	public void ejecutarAccionSemantica(){
		concatenaCharAccionSemantica(codigo.simboloActual());
	}
	

}
