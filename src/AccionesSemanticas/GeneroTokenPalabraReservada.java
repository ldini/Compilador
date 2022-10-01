package AccionesSemanticas;

import Automata.*;
import util.*;


public class GeneroTokenPalabraReservada extends AccionSemantica{
	private Automata automata;
	
	public GeneroTokenPalabraReservada(Automata automata){
		this.automata = automata;
	}
	
	@Override
	public void ejecutarAccionSemantica(){
		String palabra = getString();
		if(TablaPalabrasReservadas.esReservada(palabra)){
			automata.setVarSintactico(TablaPalabrasReservadas.getToken(palabra), "");
		}
		else{
			Notificador.addError(automata.getLineaActual(), "No existe palabra reservada: " + palabra);
			automata.reiniciarAutomata();
		}
	}
}
