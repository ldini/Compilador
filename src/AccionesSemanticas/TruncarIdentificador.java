package AccionesSemanticas;

import Compilador.*;
import util.*;

public class TruncarIdentificador extends AccionSemantica{
		
	public final static int tamanoMaximo = 25;
	private final AnalizadorLexico lexico;
	
	public TruncarIdentificador(AnalizadorLexico lexico){
		this.lexico = lexico;
	}
	
	public void ejecutarAccionSemantica(){
		if(tamanoMaximo < tamanoString()){
			truncarString(tamanoMaximo);
			Notificador.addWarning(lexico.getLineaActual(), "Maxima cantidad de caracteres superada, se tomaron los primeros 25 caracteres");
		}
	}
	
}
