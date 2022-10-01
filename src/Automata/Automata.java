package Automata;

import AccionesSemanticas.*;
import util.*;
import Compilador.*;
import Semantico.Parser;
import Simbolo.*;
public class Automata {
    
	private final CuentaSaltoLinea cuentaSaltoLinea = new CuentaSaltoLinea(); 
    private int estadoActual = Estados.INICIAL;
    private final AnalizadorLexico lexico;
    private Arista[][] automata = new Arista[Estados.totalEstados][Entradas.totalEntrada];
	public Automata(AnalizadorLexico lexico, CodigoFuente codigoFuente, TablaSimbolos tablaSimbolos, TablaPalabrasReservadas tablaPalabrasReservadas) {
		this.lexico = lexico;
	}
	
    public int getLineaActual(){
    	return this.cuentaSaltoLinea.getCantidadLineas();
    }
    
    public void reiniciarAutomata(){
    	estadoActual = 0;
    }
    
    public boolean esEstadoFinal(){
    	if(estadoActual == Estados.FINAL)
    		return true;
    	return false;    		
    }
    
    public void cambiarEstado(char c){
    	int entrada = Entradas.charToInt(c);
    }
    
    public void setVarSintactico(short token, String lexema){
    	lexico.setVariablesSintactico(token, lexema);
    }
    
    public AnalizadorLexico getAnalizadoLexico(){
    	return lexico;
    }
    
    private void inicializarAutomata(CodigoFuente codigoFuente, TablaSimbolos tablaSimbolos, TablaPalabrasReservadas tablaPalabrasReservadas){
    	
    	/*AS0 Genero Token Particular*/
    	GeneroTokenLiteral generoTokenLiteral = new GeneroTokenLiteral(codigoFuente, this);
    	
    	/*AS1 Inicializa String vacio*/
    	InicStringVacio inicStringVacio = new InicStringVacio();
    	
    	/*AS2 Concatena Char*/
    	ConcatenaChar concatenaChar = new ConcatenaChar(codigoFuente);
    	
    	/*AS3 Cuenta Salto de Linea*/
    	CuentaSaltoLinea cuentaSaltoLinea = new CuentaSaltoLinea();
    	
    	/*AS4 Truncar Identificador*/
    	TruncarIdentificador truncarIdentificador = new TruncarIdentificador(lexico);
    	
    	/*AS5 Chequeo si es Palabra Reservada*/
    	ChequeoPalabraReservada chequeoPalabraReservada = new ChequeoPalabraReservada(this, tablaSimbolos, codigoFuente);
    	
    	/*AS6 Genero Token de Palabra Reservada*/
    	GeneroTokenPalabraReservada generoTokenPalabraReservada = new GeneroTokenPalabraReservada(this);
    	
    	/*AS7 Ignora ultimo Caracter leido*/
    	IgnoraUltimoCaracter ignoraUltimoCaracter = new IgnoraUltimoCaracter();
    	
    	/*AS8 Retrocede en el Coddigo Fuente*/
    	RetrocedeCodigoFuente retrocedeCodigoFuente = new RetrocedeCodigoFuente(codigoFuente);
    	
    	/*AS9 Agrega un nueva entrada a la Tabla de Simbolos*/
    	GeneroTokenTablaSimbolos generoTokenTablaSimbolos = new GeneroTokenTablaSimbolos(this, Parser.ID, tablaSimbolos);
    }
    
    private void inicializaTransiciones(int origen, int destino, AccionSemantica... accionSemantica){
    	for(int entrada = 0; entrada < Entradas.totalEntrada; entrada++){
    		automata[origen][entrada] = new Arista(destino, accionSemantica);
    	}
    }
    
    private void inicializoCaminoLiterales(CodigoFuente codigoFuente){
    	GeneroTokenLiteral generoTokenLiteral = new GeneroTokenLiteral(codigoFuente, this);
    	automata[Estados.INICIAL][Entradas.SUMA] = new Arista(Estados.FINAL, generoTokenLiteral);
    	automata[Estados.INICIAL][Entradas.RESTA] = new Arista(Estados.FINAL, generoTokenLiteral);
    	automata[Estados.INICIAL][Entradas.MULTIPLICACION] = new Arista(Estados.FINAL, generoTokenLiteral);
    	automata[Estados.INICIAL][Entradas.DIVISION] = new Arista(Estados.FINAL, generoTokenLiteral);
    	automata[Estados.INICIAL][Entradas.PARENTESIS_A] = new Arista(Estados.FINAL, generoTokenLiteral);
    	automata[Estados.INICIAL][Entradas.PARENTESIS_C] = new Arista(Estados.FINAL, generoTokenLiteral);
    	automata[Estados.INICIAL][Entradas.LLAVE_A] = new Arista(Estados.FINAL, generoTokenLiteral);
    	automata[Estados.INICIAL][Entradas.LLAVE_C] = new Arista(Estados.FINAL, generoTokenLiteral);
    	automata[Estados.INICIAL][Entradas.COMA] = new Arista(Estados.FINAL, generoTokenLiteral);
    	automata[Estados.INICIAL][Entradas.PUNTO_COMA] = new Arista(Estados.FINAL, generoTokenLiteral);
    }
   
    private void inicializoCaminoIdentificadoresyPalabrasR(AccionSemantica inicStringVacio, AccionSemantica concatenaChar){
    	//Estado 0 --> Estado 1
    	automata[Estados.INICIAL][Entradas.LETRA_MAYUSCULA] = new Arista(Estados.DETECTA_PR_ID, inicStringVacio, concatenaChar);
    	automata[Estados.INICIAL][Entradas.LETRA_MINUSCULA] = new Arista(Estados.DETECTA_PR_ID, inicStringVacio, concatenaChar);
    	  	
    	//Estado 1 --> Estado 1
    	automata[Estados.DETECTA_PR_ID][Entradas.LETRA_MAYUSCULA] = new Arista(Estados.DETECTA_PR_ID, concatenaChar);
	   	automata[Estados.DETECTA_PR_ID][Entradas.LETRA_MINUSCULA] = new Arista(Estados.DETECTA_PR_ID, concatenaChar);
	   	automata[Estados.DETECTA_PR_ID][Entradas.GUION_BAJO] = new Arista(Estados.DETECTA_PR_ID, concatenaChar);
	   	automata[Estados.DETECTA_PR_ID][Entradas.DIGITO] = new Arista(Estados.DETECTA_PR_ID, concatenaChar);
	   	automata[Estados.DETECTA_PR_ID][Entradas.F_EXPONENTE] = new Arista(Estados.DETECTA_PR_ID, concatenaChar);
    }
    
    private void inicializoCaminoComparadores(AccionSemantica retrocedeCodigoFuente){
    	GeneroTokenParticular generoMenorIgual = new GeneroTokenParticular(this, Parser.MENOR_IGUAL);
    	GeneroTokenParticular generoMayorIgual = new GeneroTokenParticular(this, Parser.MAYOR_IGUAL);
    	GeneroTokenParticular generoDistinto = new GeneroTokenParticular(this, Parser.DISTINTO);
    	
    	//Estado 0 --> Estado 2 - TOKEN <
    	automata[Estados.INICIAL][Entradas.MENOR] = new Arista(Estados.MENOR_IGUAL);
    	
    	//Estado 0 --> Estado 2 - TOKEN >
    	automata[Estados.INICIAL][Entradas.MAYOR] = new Arista(Estados.MAYOR_IGUAL);
    	
    	//Estado 0 --> Estado 2 - TOKEN =
    	automata[Estados.INICIAL][Entradas.IGUAL] = new Arista(Estados.DISTINTO);
    	
    	//Estado 2
    	GeneroTokenParticular generoTokenParticularMenor = new GeneroTokenParticular(this, (short) '<');
    	inicializaTransiciones(Estados.MENOR_IGUAL, Estados.FINAL, retrocedeCodigoFuente, generoTokenParticularMenor);
    	automata[Estados.MENOR_IGUAL][Entradas.IGUAL] = new Arista(Estados.FINAL, generoMenorIgual);
    	
    	//Estado 3
    	GeneroTokenParticular generoTokenParticularMayor = new GeneroTokenParticular(this, (short) '>');
    	inicializaTransiciones(Estados.MAYOR_IGUAL, Estados.FINAL, retrocedeCodigoFuente, generoTokenParticularMayor);
    	automata[Estados.MAYOR_IGUAL][Entradas.IGUAL] = new Arista(Estados.FINAL, generoMayorIgual);
    	
    	//Estado 4
    	GeneroTokenParticular generoTokenParticularIgual = new GeneroTokenParticular(this, (short) '=');
    	inicializaTransiciones(Estados.DISTINTO, Estados.FINAL, retrocedeCodigoFuente, generoTokenParticularIgual);
    	automata[Estados.DISTINTO][Entradas.SIGNO_EXCLAMACION] = new Arista(Estados.FINAL, generoDistinto);
    	
    }
}
