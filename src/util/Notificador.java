package util;

import java.util.ArrayList;
import java.util.List;

public class Notificador {

	private static final List<String> errores = new ArrayList<>();
    private static final List<String> warnings =new ArrayList<>();
	
    public Notificador(){
    	
    }
    
    public static void addWarning(int linea, String warning){
    	warnings.add("WARNING: " + warning + " en la linea N° " + linea);
    }
    
    public static void addError(int linea, String error){
    	errores.add("ERROR " + error + " en la linea N° " + linea);
    }
    
    public static String getErrores(){
    	if(errores.isEmpty())
    		return "No hay Errores";
    	StringBuilder strBuilder = new StringBuilder();
    	for(String error: errores)
    		strBuilder.append(error).append("\n");
    	return strBuilder.toString();
    }
    
    public static String getWarnings(){
    	if(warnings.isEmpty())
    		return "No hay Warnings";
    	StringBuilder strBuilder = new StringBuilder();
    	for(String warning: warnings)
    		strBuilder.append(warning);
    	return strBuilder.toString();
    }
    
}
