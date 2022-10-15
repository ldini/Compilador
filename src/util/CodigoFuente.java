package util;

public class CodigoFuente {
	private String codigoFuente;
	private int posicion = 0;
	
	public String getString() { //check eliminar mas adelante
		return this.codigoFuente;
	}
	
	public CodigoFuente(String codigoFuente){
		this.codigoFuente = codigoFuente;
	}
	
	public boolean esEndOfFile(){
		return posicion == codigoFuente.length();
	}
	
	public void avanzaPosicion(){
		if(!esEndOfFile()){
			posicion++;
		}
	}
	
	public void retrocedePosicion(){
		if(posicion>0){
			posicion--;
		}
	}
	
	public char simboloActual(){
		return codigoFuente.charAt(posicion);
	}
	
	public char simboloAnterior(){
		if(posicion == 0)
			throw new IllegalStateException("No es posible leer el simbolo anterior ya que se encuentra en el inicio");
		return codigoFuente.charAt(posicion-1);
	}
	
}
