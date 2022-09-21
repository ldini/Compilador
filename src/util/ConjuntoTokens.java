package util;

import java.util.HashMap;
import java.util.Map;

public class ConjuntoTokens {
	private static Map<Short, String> tokens = new HashMap<>();
	
	public static void clear(){
		tokens.clear();
	}
	
	public static void agregar(Short t, String nombre){
		tokens.put(t, nombre);
	}
}
