package Compilador;


import util.*;
import Simbolo.*;
import Semantico.Parser;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class Compilador {
	public static Parser parser;
	public static void main(String[] args) throws Exception{
		
	}
	
	private static void iniciarPalabrasReservadas(){
		TablaPalabrasReservadas.clear();
		TablaPalabrasReservadas.agregar("IF", Parser.IF);
		TablaPalabrasReservadas.agregar("then", Parser.then);
		TablaPalabrasReservadas.agregar("ELSE", Parser.ELSE);
		TablaPalabrasReservadas.agregar("end_if", Parser.end_if);
		TablaPalabrasReservadas.agregar("out", Parser.out);
		TablaPalabrasReservadas.agregar("fun", Parser.fun);
		TablaPalabrasReservadas.agregar("RETURN", Parser.RETURN);
		TablaPalabrasReservadas.agregar("BREAK", Parser.BREAK);
		TablaPalabrasReservadas.agregar("discard", Parser.discard);
		TablaPalabrasReservadas.agregar("FOR", Parser.FOR);
		TablaPalabrasReservadas.agregar("CONTINUE", Parser.CONTINUE);
		TablaPalabrasReservadas.agregar("ID", Parser.ID);
		TablaPalabrasReservadas.agregar("INT", Parser.INT);
		TablaPalabrasReservadas.agregar("FLOAT", Parser.FLOAT);
		TablaPalabrasReservadas.agregar("CADENA", Parser.FLOAT);
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
	
	public void imprimir(TablaSimbolos tablaSimbolos){
		System.out.println("-- TABLA DE SIMBOLOS --");
		System.out.println(tablaSimbolos.toString());
		System.out.println();
		System.out.println("-- ERRORES --");
		System.out.println(Notificador.getErrores());
		System.out.println();
		System.out.println("-- WARNINGS --");
		System.out.println(Notificador.getWarnings());
		System.out.println();		
	}
	
	public void ejecutar(String path_name, String path){
		TablaSimbolos tablaSimbolos = new TablaSimbolos();
		if(ManejadorArch.existeArchivo(path_name)){
			inicializarTokens();
			iniciarPalabrasReservadas();
			CodigoFuente codigoFuente = new CodigoFuente(ManejadorArch.getFuente(path_name));
			AnalizadorLexico lexico = new AnalizadorLexico(codigoFuente, tablaSimbolos);
			parser = new Parser(lexico, tablaSimbolos);
			parser.run();
			imprimir(tablaSimbolos);
			
		}
	}
}
	
/*	public static void main(String[] args) {
        try{
            //Abro stream, crea el fichero si no existe
            FileWriter fw=new FileWriter("\fichero1.txt");
            //Escribimos en el fichero un String y un caracter 97 (a)
            fw.write("Esto es una prueb");
            fw.write(97);
            //Cierro el stream
            fw.close(); 
                //Abro el stream, el fichero debe existir
            FileReader fr=new FileReader("\fichero1.txt");
            //Leemos el fichero y lo mostramos por pantalla
            int valor=fr.read();
            while(valor!=-1){
                System.out.print((char)valor);
                valor=fr.read();
            }
            //Cerramos el stream
            fr.close();
        }catch(IOException e){
            System.out.println("Error E/S: "+e);
        }
    }
		
	}*/
	

