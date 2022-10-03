package AccionesSemanticas;

import Automata.Automata;
import Semantico.Parser;
import Simbolo.TablaSimbolos;
import util.Notificador;

public class GeneroEnteroCorto extends AccionSemantica{
	private Automata automata;
	private short token;
	private TablaSimbolos tablaSimbolos;
	
	public GeneroEnteroCorto(Automata automata, short token, TablaSimbolos tablaSimbolos){
		this.automata = automata;
		this.token = token;
		this.tablaSimbolos = tablaSimbolos;
	}
	
	private boolean validarEntero(int numero){
		if(numero > Math.pow(-2, 7) && numero < (Math.pow(2, 7)) - 1){
			return true;
		}
		else{
			Notificador.addError(automata.getLineaActual(), "El entero " + numero + " se encuentra fuera de rango");
			return false;
		}
	}
	
	@Override
	public void ejecutarAccionSemantica(){
		int numero = Integer.parseInt(this.getString());
		if(validarEntero(numero)){
			automata.setVarSintactico(Parser.CTE_INT, String.valueOf(numero));
			tablaSimbolos.agregarEntrada(Parser.CTE_INT, String.valueOf(numero), "i8");
		}
		else{
			automata.reiniciarAutomata();
		}
	}
}
