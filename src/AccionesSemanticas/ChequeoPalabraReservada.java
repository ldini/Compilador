package AccionesSemanticas;

import util.*;
import Automata.*;
import Semantico.Parser;
import Simbolo.*;

public class ChequeoPalabraReservada extends AccionSemantica {
	private Automata automata;
	private TablaSimbolos tablaSimbolos;
	private CodigoFuente codigoFuente;
	
	public ChequeoPalabraReservada(Automata automata, TablaSimbolos tablaSimbolos, CodigoFuente codigoFuente){
		this.automata = automata;
		this.codigoFuente = codigoFuente;
		this.tablaSimbolos = tablaSimbolos;
	}
	
	@Override 
	public void ejecutarAccionSemantica(){
		RetrocedeCodigoFuente retrocedeCodigoFuente = new RetrocedeCodigoFuente(codigoFuente);
		if(TablaPalabrasReservadas.esReservada(getString())){
			GeneroTokenPalabraReservada generoTokenPalabraReservada = new GeneroTokenPalabraReservada(automata);
			generoTokenPalabraReservada.ejecutarAccionSemantica();
			retrocedeCodigoFuente.ejecutarAccionSemantica();
		}
		else{
			retrocedeCodigoFuente.ejecutarAccionSemantica();
			TruncarIdentificador truncarIdentificador = new TruncarIdentificador(automata.getAnalizadoLexico());
			truncarIdentificador.ejecutarAccionSemantica();
			GeneroTokenTablaSimbolos generoTokenTablaSimbolos = new	GeneroTokenTablaSimbolos(automata, Parser.ID, tablaSimbolos);
			generoTokenTablaSimbolos.ejecutarAccionSemantica();
		}
	}
	
}
