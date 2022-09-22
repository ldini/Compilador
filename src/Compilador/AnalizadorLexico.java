package Compilador;

import Automata.*;
import Simbolo.*;
import util.*;

public class AnalizadorLexico {
	
	private final Automata automata;
	private CodigoFuente codigoFuente;
	
	
	public AnalizadorLexico(CodigoFuente codigoFuente, TablaSimbolos tablaSimbolos){
        this.automata = new Automata();
    }
	
	public int getLineaActual(){
		return automata.getLineaActual();
	}
}
