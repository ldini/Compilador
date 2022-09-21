package Compilador;

import Automata.*;

public class AnalizadorLexico {
	
	private final Automata automata;
	
	public AnalizadorLexico(){
        this.automata = new Automata();
    }
	
	public int getLineaActual(){
		return automata.getLineaActual();
	}
}
