package AccionesSemanticas;

import Automata.*;
import util.ConjuntoTokens;
import util.*;

public class GeneroTokenLiteral extends AccionSemantica{
	private Automata automata;
	private CodigoFuente codigoFuente;
	
	public GeneroTokenLiteral(CodigoFuente codigoFuente, Automata automata){
		this.automata = automata;
		this.codigoFuente = codigoFuente;
	}
	
	@Override
	public void ejecutarAccionSemantica(){
		int token = codigoFuente.simboloActual();
		automata.setVarSintactico((short) token, "");
	}
}
