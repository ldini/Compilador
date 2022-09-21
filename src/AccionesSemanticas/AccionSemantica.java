package AccionesSemanticas;

public abstract class AccionSemantica {
	
	public static StringBuilder stringTemporal = new StringBuilder("");
	
	public void inicializarString(){
		stringTemporal.delete(0, stringTemporal.length());
	}
	
	public void concatenaCharAccionSemantica(char nuevoChar){
		stringTemporal.append(nuevoChar);
	}
	
	public int tamanoString() {
		return stringTemporal.length();
	}
	
	public void truncarString(int maximo){
		stringTemporal.delete(maximo, stringTemporal.length());
	}
	
	public abstract void ejecutarAccionSemantica();
}
