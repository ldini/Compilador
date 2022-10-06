package AccionesSemanticas;

import util.Notificador;
import Automata.*;
import Semantico.Parser;
import Simbolo.*;

public class GeneraTokenFloat extends AccionSemantica{
	
	private final double valorInferior = 1.17549435; 
	private final double valorSuperior = 3.40282347;
	private final double exponenteValido = 38;
	private double base;
	private double exponente;
	private char signo = '+';
	private Automata automata;
	private short token;
	private TablaSimbolos tablaSimbolos;
	
	public GeneraTokenFloat(Automata automata, short token, TablaSimbolos tablaSimbolos){
		this.automata = automata;
		this.token = token;
		this.tablaSimbolos = tablaSimbolos;
	}
		
	private void getFloat(){
		String valorFloat = getString();
		String auxFloat = new String();
		String auxExponente = new String();
		int pos= 0;
		for(int i=0; i < valorFloat.length(); i++){
			if(valorFloat.charAt(i) != 'F'){
				auxFloat += valorFloat.charAt(i);
				pos = i;
			}
			else{
				pos = i;
				this.base = Double.valueOf(auxFloat);
				break;
			}
		}
		this.base = Double.valueOf(auxFloat);
		for(int i=pos+1; i<valorFloat.length(); i++){
			if(valorFloat.charAt(i) == '-' || valorFloat.charAt(i) == '+')
				this.signo = valorFloat.charAt(i);
			else{
				auxExponente += valorFloat.charAt(i);
			}
		}
		if(!auxExponente.isEmpty())
			this.exponente = Double.valueOf(auxExponente);	
	}
	
	private boolean baseValida(){
		if((this.base >= valorInferior && this.base <= valorSuperior) || (this.base> ((-1)*valorSuperior) && this.base < ((-1)*valorInferior))){
			return true;
		}
		else{
			Notificador.addError(automata.getLineaActual(), "La base " + this.base + " se encuentra fuera de rango");
			return false;
		}
	}
	
	private boolean exponenteValido(){
		if(this.exponente >= ((-1)*exponenteValido) && this.exponente <= exponenteValido){
			return true;
		}
		else{
			Notificador.addError(automata.getLineaActual(), "El exponente " + this.exponente + " se encuentra fuera de rango");
			return false;
		}
	}
	
	@Override
	public void ejecutarAccionSemantica(){
		getFloat();
		Double numero;
		if(baseValida() && exponenteValido()){
			if(this.signo == '-'){
				 numero = Math.pow(this.base, -this.exponente);
			}
			else{
				numero = Math.pow(this.base, this.exponente);
			}	
			automata.setVarSintactico(Parser.CTE_FLOAT, String.valueOf(numero));
			tablaSimbolos.agregarEntrada(Parser.CTE_FLOAT, String.valueOf(numero), "FLOAT");
		}
		else{
			automata.reiniciarAutomata();
		}
	}	
	
}
