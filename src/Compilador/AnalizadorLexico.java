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
		while(!automata.esEstadoFinal()){
			
			if(codigoFuente.esEndOfFile()){
				System.out.println("Entra esEndOfFIle");
				System.out.println(automata.getEstado());
				automata.cambiarEof();
				//finalizar = true;
			}
			else {
				System.out.println("AnalizadoLexico.java -- tokenGenerado(): " + this.codigoFuente.simboloActual());
				automata.cambiarEstado(codigoFuente.simboloActual());
				codigoFuente.avanzaPosicion();
				System.out.println("AnalizadoLexico.java -- Estado Siguiente " + automata.getEstado());
			}
		}
		System.out.println("Lexema Generado: " + ultimoLexemaGenerado + " - " + "Token Generado:" + ultimoTokenGenerado );
		return ultimoTokenGenerado;
	}
}
