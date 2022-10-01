package Simbolo;

import java.util.*;
import Semantico.Parser;

public class TablaSimbolos {
	public static Parser parser;
	private final Map<String, Celda> tablaSimbolos;
	private List<String> nombreFunciones = new ArrayList<String>();
	
	public TablaSimbolos(){
		tablaSimbolos = new Hashtable<>();
	}
	
	public void remove(String lexema){
		if(this.tablaSimbolos.containsKey(lexema)){
			this.tablaSimbolos.remove(lexema);
		}
	}
	
	public void cambiarNegativo(String valor){
		Float v = Float.valueOf(valor); 
		if(-v >= -3.40282347E+38 && -v <= -1.17549435E-38){
			this.remove(valor);
			this.agregarEntrada(parser.FLOAT, ("-" + valor), "FLOAT");
		}
		else{
			
		}
	}
	
	public void agregarEntrada(int token, String lexema, String tipo) {
		Celda celda;
		if(tablaSimbolos.containsKey(lexema)){
			celda = getEntrada(lexema);
		}
		else{
			celda = new Celda(token, lexema, tipo);
			tablaSimbolos.put(lexema, celda);
		}	
	}
	
	public Celda getEntrada(String lexema){
		Celda celda = tablaSimbolos.get(lexema); 
		return celda;		
	}
	
	public String toString(){
		if(tablaSimbolos.isEmpty())
			return "Tabla de Simbolos vacia";
		StringBuilder strBuilder = new StringBuilder();
		for(Celda celda: tablaSimbolos.values())
			strBuilder.append(celda.imprimirCelda());
		return strBuilder.toString();
	}
	
	public boolean existeLexema(String lexema){
		if(tablaSimbolos.containsKey(lexema))
			return true;
		return false;
	}
}
