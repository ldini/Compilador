package Automata;

import AccionesSemanticas.*;

public class Automata {
    
	private final CuentaSaltoLinea cuentaSaltoLinea = new CuentaSaltoLinea(); 
    
	public Automata() {
	}
	
    public int getLineaActual(){
    	return this.cuentaSaltoLinea.getCantidadLineas();
    }
}
