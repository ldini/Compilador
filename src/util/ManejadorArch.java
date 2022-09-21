package util;

import java.util.*;
import java.io.*;

public class ManejadorArch {
	public static boolean existeArchivo(String path){
		File file = new File(path);
		if(!file.isDirectory() && file.exists())
			return true;
		return false;
	}
	
	public static String getFuente(String path_name){
		File file;
		FileReader fileReader = null;
		BufferedReader bufferReader;
		StringBuilder strBuilder = new StringBuilder();
		try{
			file = new File(path_name);
			fileReader = new FileReader(file);
			bufferReader = new BufferedReader(fileReader);
			String linea;
			while((linea = bufferReader.readLine()) != null){
				strBuilder.append(linea).append("\n");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (null != fileReader)
					fileReader.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(strBuilder.length() == 0){
			System.out.println("El archivo \"" + path_name + "\" se encuentra vacio");
			return "";
		}
		return strBuilder.substring(0, strBuilder.length()-1); //Para quitar el ultimo salto de linea
	}
}

/*System.out.println("Ingrese el path donde se encuentra el archivo:");
Scanner in=new Scanner(System.in); 
String path = in.next();
System.out.println("El path es: " + path);

FileReader fileReader = new FileReader(path);
int caracterLeido = fileReader.read();
while (caracterLeido != -1){
	char caracter = (char) caracterLeido;
	System.out.print(caracter + " - ");
    caracterLeido = fileReader.read();
}*/