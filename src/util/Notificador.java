package util;
//modificado por lu 01/10
import java.util.ArrayList;
import java.util.List;
//import Compilador.AnalizadorLexico;
//import CodigoFuente;

public class Notificador {

	private static final List<String> errores = new ArrayList<>();
    private static final List<String> warnings =new ArrayList<>();
	
    public Notificador(){
    	
    }

	/*public Notificador(String simbolo_sin_reconocer, AnalizadorLexico analizadorLexico, CodigoFuente codigoFuente){
    }*/
    
    public static void addWarning(int linea, String warning){
    	warnings.add("WARNING: " + warning + " en la linea " + linea);
    }
    
    public static void addError(int linea, String error){
    	errores.add("ERROR " + error + " en la linea " + linea);
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
    
	/*public static String getWarningsYErrores() {
        return (String)"Warnings: "+'\n'+ getWarnings() + '\n'+"Errores: "+'\n'+getErrores();
    }*/

}
