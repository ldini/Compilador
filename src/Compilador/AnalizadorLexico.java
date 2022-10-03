package Compilador;

import Automata.*;
import Simbolo.*;
import util.*;

public class AnalizadorLexico {
	
	private final Automata automata;
	private CodigoFuente codigoFuente;
	public short ultimoTokenGenerado = -1;
	public String ultimoLexemaGenerado;
	public TablaSimbolos tablaSimbolos;
	public static final short T_EOF = 0;
	
	public AnalizadorLexico(CodigoFuente codigoFuente, TablaSimbolos tablaSimbolos){
        this.automata = new Automata(this, codigoFuente, tablaSimbolos, iniciarTablaPalabrasReservadas());
        this.codigoFuente = codigoFuente;
        this.tablaSimbolos = tablaSimbolos;
    }
	
	private TablaPalabrasReservadas iniciarTablaPalabrasReservadas(){
		return null;
	}
	
	public int getLineaActual(){
		return automata.getLineaActual();
	}
	
	public void setVariablesSintactico(short token, String lexema){
        this.ultimoTokenGenerado = token;
        this.ultimoLexemaGenerado = lexema;
    }
	
	public CodigoFuente getCodigoFuente() {
		return codigoFuente;
	}	

	public Automata getAutomata(){
		return automata;
	}
	
	public int tokenGenerado(){
		automata.reiniciarAutomata();
		if(!automata.esEstadoFinal()){
			if(codigoFuente.esEndOfFile()){
				automata.cambiarEof();
				//finalizar = true;
			}
			else {
				automata.cambiarEstado(codigoFuente.simboloActual());
				codigoFuente.avanzaPosicion();
			}
		}
		return ultimoTokenGenerado;
	}
}
