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
		inicializarAutomata(codigoFuente, tablaSimbolos);
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
    	Arista arista = automata[estadoActual][entrada];
    	arista.getAristas();
    	estadoActual = arista.getSiguienteEstado();
    	arista.ejecutarAccionesSemanticas();
    }
    
    public void setVarSintactico(short token, String lexema){
    	lexico.setVariablesSintactico(token, lexema);
    }
    
    public int getEstado(){
    	return this.estadoActual;
    }
    
    public AnalizadorLexico getAnalizadoLexico(){
    	return lexico;
    }
    
    public void cambiarEof(){
    	Arista arista = automata[estadoActual][Entradas.EOF];
    	arista.ejecutarAccionesSemanticas();
    	lexico.setVariablesSintactico(AnalizadorLexico.T_EOF, "");
    	estadoActual = Estados.FINAL;
    }
    
    private void inicializarAutomata(CodigoFuente codigoFuente, TablaSimbolos tablaSimbolos){
    	
    	/*AS0 Genero Token Particular*/
    	GeneroTokenLiteral generoTokenLiteral = new GeneroTokenLiteral(codigoFuente, this);
    	
    	/*AS1 Inicializa String vacio*/
    	InicStringVacio inicStringVacio = new InicStringVacio();
    	
    	/*AS2 Concatena Char*/
    	ConcatenaChar concatenaChar = new ConcatenaChar(codigoFuente);
    	
    	/*AS3 Cuenta Salto de Linea*/
    	//CuentaSaltoLinea cuentaSaltoLinea = new CuentaSaltoLinea();
    	
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
    	
    	/*Genero Token Float*/
    	GeneraTokenFloat generoTokenFloat = new GeneraTokenFloat(this, Parser.FLOAT, tablaSimbolos);
    	
    	/*Genero Token Entero Corto*/
    	GeneroEnteroCorto generoTokenEnteroCorto = new GeneroEnteroCorto(this, Parser.INT, tablaSimbolos);
    	
    	/*Inicializo Errores y Warnings*/
    	GeneroError generoError = new GeneroError("Simbolo no reconocido", lexico);
    	GeneroWarning generoWarning = new GeneroWarning("Warning generado", lexico);
    	GeneroTokenParticular generoAsignacion = new GeneroTokenParticular(this, Parser.ASIGNACION);
    	GeneroTokenParticular generoEOF = new GeneroTokenParticular(this, AnalizadorLexico.T_EOF);
    	/*Inicializo Trancisiones*/
    	automata[Estados.INICIAL][Entradas.DESCARTABLE] = new Arista(Estados.INICIAL);
    	automata[Estados.INICIAL][Entradas.SALTO_LINEA] = new Arista(Estados.INICIAL, cuentaSaltoLinea);
    	inicializoCaminoLiterales(codigoFuente);
    	inicializoCaminoIdentificadoresyPalabrasR(inicStringVacio, concatenaChar, chequeoPalabraReservada);
    	
    	//FALTA INICIALIZAR COMENTARIO
    	inicializoCaminoComparadores(retrocedeCodigoFuente, generoAsignacion);
    	inicializoCaminoNumeros(inicStringVacio, concatenaChar, generoTokenEnteroCorto, generoTokenFloat, cuentaSaltoLinea, retrocedeCodigoFuente);
    	inicializoCaminoAsignacion(codigoFuente, retrocedeCodigoFuente);
    	
    	automata[Estados.INICIAL][Entradas.EOF] = new Arista(Estados.FINAL, generoEOF);
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
   
    private void inicializoCaminoIdentificadoresyPalabrasR(AccionSemantica inicStringVacio, AccionSemantica concatenaChar, AccionSemantica chequeoPalabraReservada){
    	//Estado 0 --> Estado 1
    	automata[Estados.INICIAL][Entradas.LETRA_MAYUSCULA] = new Arista(Estados.DETECTA_PR_ID, inicStringVacio, concatenaChar);
    	automata[Estados.INICIAL][Entradas.LETRA_MINUSCULA] = new Arista(Estados.DETECTA_PR_ID, inicStringVacio, concatenaChar);
    	  	
    	//Estado 1 --> Estado 1
    	inicializaTransiciones(Estados.DETECTA_PR_ID, Estados.FINAL, chequeoPalabraReservada);
    	automata[Estados.DETECTA_PR_ID][Entradas.LETRA_MAYUSCULA] = new Arista(Estados.DETECTA_PR_ID, concatenaChar);
	   	automata[Estados.DETECTA_PR_ID][Entradas.LETRA_MINUSCULA] = new Arista(Estados.DETECTA_PR_ID, concatenaChar);
	   	automata[Estados.DETECTA_PR_ID][Entradas.GUION_BAJO] = new Arista(Estados.DETECTA_PR_ID, concatenaChar);
	   	automata[Estados.DETECTA_PR_ID][Entradas.DIGITO] = new Arista(Estados.DETECTA_PR_ID, concatenaChar);
	   	automata[Estados.DETECTA_PR_ID][Entradas.F_EXPONENTE] = new Arista(Estados.DETECTA_PR_ID, concatenaChar);
	   	
	   	//Estado 1 --> Estado F
	   	//automata[Estados.DETECTA_PR_ID][Entradas.OTRO] = new Arista(Estados.FINAL, chequeoPalabraReservada);
	   	//inicializaTransiciones(Estados.DETECTA_PR_ID, Estados.FINAL, chequeoPalabraReservada);
    }
    
    private void inicializoCaminoComparadores(AccionSemantica retrocedeCodigoFuente, AccionSemantica generoAsignacion){
    	GeneroTokenParticular generoMenorIgual = new GeneroTokenParticular(this, Parser.MENOR_IGUAL);
    	GeneroTokenParticular generoMayorIgual = new GeneroTokenParticular(this, Parser.MAYOR_IGUAL);
    	GeneroTokenParticular generoDistinto = new GeneroTokenParticular(this, Parser.DISTINTO);
    	
    	//Estado 0 --> Estado 2 - TOKEN <
    	automata[Estados.INICIAL][Entradas.MENOR] = new Arista(Estados.MENOR_IGUAL);
    	
    	//Estado 0 --> Estado 2 - TOKEN >
    	automata[Estados.INICIAL][Entradas.MAYOR] = new Arista(Estados.MAYOR_IGUAL);
    	
    	//Estado 0 --> Estado 2 - TOKEN =
    	automata[Estados.INICIAL][Entradas.IGUAL] = new Arista(Estados.DISTINTO);
    	
    	//Estado 2 --> Estado F
    	GeneroTokenParticular generoTokenParticularMenor = new GeneroTokenParticular(this, (short) '<');
    	inicializaTransiciones(Estados.MENOR_IGUAL, Estados.FINAL, retrocedeCodigoFuente, generoTokenParticularMenor);
    	automata[Estados.MENOR_IGUAL][Entradas.IGUAL] = new Arista(Estados.FINAL, generoMenorIgual);
    	
    	//Estado 3 --> Estado F
    	GeneroTokenParticular generoTokenParticularMayor = new GeneroTokenParticular(this, (short) '>');
    	inicializaTransiciones(Estados.MAYOR_IGUAL, Estados.FINAL, retrocedeCodigoFuente, generoTokenParticularMayor);
    	automata[Estados.MAYOR_IGUAL][Entradas.IGUAL] = new Arista(Estados.FINAL, generoMayorIgual);
    	
    	//Estado 4 --> Estado F
    	GeneroTokenParticular generoTokenParticularIgual = new GeneroTokenParticular(this, (short) '=');
    	inicializaTransiciones(Estados.DISTINTO, Estados.FINAL, retrocedeCodigoFuente, generoTokenParticularIgual);
    	automata[Estados.DISTINTO][Entradas.SIGNO_EXCLAMACION] = new Arista(Estados.FINAL, generoDistinto);	
    	
    }
    
    private void inicializoCaminoAsignacion(CodigoFuente codigoFuente, AccionSemantica retrocedeCodigoFuente){
    	GeneroTokenLiteral generoTokenL = new GeneroTokenLiteral(codigoFuente, this);
    	inicializaTransiciones(Estados.DISTINTO, Estados.FINAL, generoTokenL, retrocedeCodigoFuente);
    	automata[Estados.DISTINTO][Entradas.DOS_PUNTOS] = new Arista(Estados.FINAL);
    }
    private void inicializoCaminoNumeros(AccionSemantica inicStringVacio, AccionSemantica concatenaChar, AccionSemantica generoTokenEnteroCorto, AccionSemantica generoTokenFloat, AccionSemantica cuentaSaltoLinea, AccionSemantica retrocedeCodigoFuente){
    	//Estado 0 --> Estado 6
    	automata[Estados.INICIAL][Entradas.DIGITO] = new Arista(Estados.DIGITO_1, inicStringVacio, concatenaChar);
    	
    	//Estado 0 --> Estado 7
    	automata[Estados.INICIAL][Entradas.PUNTO] = new Arista(Estados.DIGITO_2, inicStringVacio, concatenaChar);
    	
      	inicializaTransiciones(Estados.DIGITO_1, Estados.FINAL, generoTokenEnteroCorto, retrocedeCodigoFuente);
    	
    	//Estado 6 --> Estado 6
    	automata[Estados.DIGITO_1][Entradas.DIGITO] = new Arista(Estados.DIGITO_1, concatenaChar);
    	
    	//Estado 6 --> Estado F 
    	automata[Estados.DIGITO_1][Entradas.SALTO_LINEA] = new Arista(Estados.FINAL, generoTokenEnteroCorto, cuentaSaltoLinea);
    	
    	//Estado 6 --> Estado 7
    	automata[Estados.DIGITO_1][Entradas.PUNTO] = new Arista(Estados.DIGITO_2, concatenaChar);
    	
    	inicializaTransiciones(Estados.DIGITO_2, Estados.FINAL, generoTokenFloat, retrocedeCodigoFuente);
    	
    	//Estado 7 --> Estado 7
    	automata[Estados.DIGITO_2][Entradas.DIGITO] = new Arista(Estados.DIGITO_2, concatenaChar);
    	
    	//Estado 7 --> Estado 8
    	automata[Estados.DIGITO_2][Entradas.F_EXPONENTE] = new Arista(Estados.DIGITO_3, concatenaChar);
    	
    	//Estado 7 --> Estado F
    	
    	automata[Estados.DIGITO_2][Entradas.OTRO] = new Arista(Estados.FINAL, retrocedeCodigoFuente, generoTokenEnteroCorto);
    	
    	inicializaTransiciones(Estados.DIGITO_3, Estados.FINAL, generoTokenFloat, retrocedeCodigoFuente);
    	//Estado 8 --> Estado 8
    	automata[Estados.DIGITO_3][Entradas.DIGITO] = new Arista(Estados.DIGITO_3, concatenaChar);
    	
    	//Estado 8 --> Estado 9
    	automata[Estados.DIGITO_3][Entradas.SUMA] = new Arista(Estados.DIGITO_4, concatenaChar);
    	automata[Estados.DIGITO_3][Entradas.RESTA] = new Arista(Estados.DIGITO_4, concatenaChar);
    	
    	//Estado 8 --> Estado F
    	
    	automata[Estados.DIGITO_3][Entradas.OTRO] = new Arista(Estados.FINAL, retrocedeCodigoFuente, generoTokenEnteroCorto);
    	
    	inicializaTransiciones(Estados.DIGITO_4, Estados.FINAL, generoTokenFloat, retrocedeCodigoFuente);
    	//Estado 9 --> Estado 9
    	automata[Estados.DIGITO_4][Entradas.DIGITO] = new Arista(Estados.DIGITO_4, concatenaChar);
    	
    	//Estado 9 --> Estado F
    	automata[Estados.DIGITO_4][Entradas.OTRO] = new Arista(Estados.FINAL, retrocedeCodigoFuente, generoTokenFloat);   	

    }
}
