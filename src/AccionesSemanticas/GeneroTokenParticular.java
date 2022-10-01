package AccionesSemanticas;

import Automata.*;
import util.*;

public class GeneroTokenParticular extends AccionSemantica {
	
	private Automata automata;
	private short token;
	
	public GeneroTokenParticular(Automata automata, short token){
		this.automata = automata;
		this.token = token;
	}
	
	@Override
	public void ejecutarAccionSemantica(){
		automata.setVarSintactico(token, ConjuntoTokens.getLexema(token));
	}
}
