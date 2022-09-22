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
    
}
