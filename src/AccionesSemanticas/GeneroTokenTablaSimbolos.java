package AccionesSemanticas;

import Automata.*;
import Simbolo.*;

public class GeneroTokenTablaSimbolos extends AccionSemantica{
	
	private Automata automata;
	private short token;
	private TablaSimbolos tablaSimbolos;
	
	public GeneroTokenTablaSimbolos(Automata automata, short token, TablaSimbolos tablaSimbolos){
		this.automata = automata;
		this.token = token;
		this.tablaSimbolos = tablaSimbolos;
	}
	
	@Override
	public void ejecutarAccionSemantica(){
		String lexema = getString();
		if(!tablaSimbolos.existeLexema(lexema)){
			tablaSimbolos.agregarEntrada(token, lexema, "");
		}
		automata.setVarSintactico(token, lexema);
	}
}
