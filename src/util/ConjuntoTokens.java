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
	
	public static String getLexema(short token){
		if(tokens.get(token) != null)
			return tokens.get(token);
		throw new IllegalStateException("El token " + token +  "no se encuentra en los tokens almacenados");
	}
}
