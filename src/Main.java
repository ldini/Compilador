import java.util.Scanner;

import Compilador.Compilador;

public class Main {

	public static void main(String[] args) {
		Compilador compilador = new Compilador();
		/*System.out.println("Ingrese el path donde se encuentra el archivo:");
		Scanner in_path=new Scanner(System.in); 
		String path = in_path.next();
		System.out.println("Ingrese el nombre del archivo:");
		Scanner in_name=new Scanner(System.in); 
		String name = in_name.next();*/
		
		//Ejecutamos archivos individuales
		compilador.ejecutar("C:/Users/Leandro/Desktop/Compiladores/Compilador/src/Ejemplos/0.IdentificadorFueraDeRango.txt", "C:/Users/Leandro/Desktop/Compiladores/Compilador/src/Ejemplos");
	}
	
	
}
