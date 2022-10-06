package Automata;

import java.util.Arrays;
import java.util.List;
import AccionesSemanticas.*;

public class Arista {
	private final int siguienteEstado;
	private List<AccionSemantica> accionesSemanticas;
	
	public Arista(int siguienteEstado, AccionSemantica... accionesSemanticas){
		this.siguienteEstado = siguienteEstado;
		this.accionesSemanticas = Arrays.asList(accionesSemanticas);
	}
	
	public int getSiguienteEstado(){
		return this.siguienteEstado;
	}
	
	public void getAristas(){
		for(AccionSemantica a: accionesSemanticas)
			System.out.println("Arista.java -- " + a);
	}
	
	public void ejecutarAccionesSemanticas(){
		for(AccionSemantica accionSemantica: accionesSemanticas)
			accionSemantica.ejecutarAccionSemantica();
	}
}
