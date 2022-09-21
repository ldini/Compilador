package Compilador;

import java.util.Scanner;
import util.*;
import Simbolo.*;

import java.io.File;
import java.io.FileReader;

public class Compilador {

	public static void main(String[] args) throws Exception{
		
	}
	
	private static void iniciarPalabrasReservadas(){
		TablaPalabrasReservadas.clear();
		TablaPalabrasReservadas.agregar("if", token);
		TablaPalabrasReservadas.agregar("then", token);
		TablaPalabrasReservadas.agregar("else", token);
		TablaPalabrasReservadas.agregar("end-if", token);
		TablaPalabrasReservadas.agregar("out", token);
		TablaPalabrasReservadas.agregar("fun", token);
		TablaPalabrasReservadas.agregar("return", token);
		TablaPalabrasReservadas.agregar("break", token);
		TablaPalabrasReservadas.agregar("discard", token);
		TablaPalabrasReservadas.agregar("break", token);
		TablaPalabrasReservadas.agregar("for", token);
		TablaPalabrasReservadas.agregar("continue", token);
	}
	
	private static void inicializarTokens(){
		ConjuntoTokens.clear();
		ConjuntoTokens.agregar((short) ':', ":");
		ConjuntoTokens.agregar((short) '(', "(");
		ConjuntoTokens.agregar((short) ')', ")");
		ConjuntoTokens.agregar((short) '<', "<");
		ConjuntoTokens.agregar((short) '>', ">");
		ConjuntoTokens.agregar((short) '=', "=");
		ConjuntoTokens.agregar((short) ',', ",");
		ConjuntoTokens.agregar((short) '!', "!");
		ConjuntoTokens.agregar(Parser.MENOR_IGUAL,"<=");
		ConjuntoTokens.agregar(Parser.MAYOR_IGUAL,">=");
		ConjuntoTokens.agregar(Parser.DISTINTO,"=!");
		ConjuntoTokens.agregar(Parser.ASIGNACION,"=:");
		
	}
	
	private static void ejecutar(String path_name, String path){
		TablaSimbolos tablaSimbolos = new TablaSimbolos();
		if(ManejadorArch.existeArchivo(path_name)){
			inicializarTokens();
			iniciarPalabrasReservadas();
			CodigoFuente codigoFuente = new CodigoFuente(ManejadorArch.getFuente(path_name));
			
		}
		
	}
	

}
