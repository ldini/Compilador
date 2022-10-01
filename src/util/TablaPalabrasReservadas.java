package util;

import java.util.HashMap;
import java.util.Map;

public class TablaPalabrasReservadas {
    private static final Map<String, Short> palabrasReserv = new HashMap<>();
    
    public static boolean esReservada(String palabra){
    	return palabrasReserv.containsKey(palabra);
    }
    
    public static short getToken(String palabra){
    	return palabrasReserv.get(palabra);
    }
    
    public static void agregar(String palabra, short token){
    	//palabrasReserv.putIfAbsent(palabra, token);
    }
    
    public static void agregarTest(String palabra, short token){
    	
    }
    
    public static void clear(){
    	palabrasReserv.clear();
    }
}
