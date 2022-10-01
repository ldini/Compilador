package Simbolo;

public class Celda {

	private final int token;
	private String lexema;
	private String tipo;
	private boolean isDeclarada;
	
	public Celda(int token, String lexema, String tipo){
		this.token = token;
		this.lexema = lexema;
		this.tipo = tipo;
		this.isDeclarada = false;
	}
	
	public int getToken(){
        return token;
    }
	
	public void setLexema(String lexema){
		this.lexema = lexema;
	}
	
	public String getLexema(){
		return lexema;
	}
	
	public void setTipo(String tipo){
		this.tipo =tipo;
	}

	public String getTipo(){
		return tipo;
	}
	
	public void setDeclarada(boolean isDeclarada){
		this.isDeclarada = isDeclarada;
	}
	
	public boolean isDeclarada(){
		return this.isDeclarada;
	}
	
	public String imprimirCelda(){
		String celda = "( lexema: " + lexema 
				      +" tipo: " + tipo
				      +" declarada: " + isDeclarada + ")";
		return celda;	
	}
}
