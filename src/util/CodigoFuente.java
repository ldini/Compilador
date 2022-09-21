package util;

public class CodigoFuente {
	private String codigoFuente;
	private int posicion = 0;
	
	public CodigoFuente(String codigoFuente){
		this.codigoFuente = codigoFuente;
	}
	
	public boolean endOfFile(){
		if(posicion == codigoFuente.length())
			return true;
		return false;
	}
	
	public void avanzaPosicion(){
		if(!endOfFile()){
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
