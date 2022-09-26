package Compilador;


import util.*;
import Simbolo.*;
import Semantico.*;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class Compilador {

	public static void main(String[] args) throws Exception{
		
	}
	
	private static void iniciarPalabrasReservadas(){
		TablaPalabrasReservadas.clear();
		TablaPalabrasReservadas.agregar("if", Parser.if);
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
	

