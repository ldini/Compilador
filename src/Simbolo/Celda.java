package Simbolo;

public class Celda {

	private final int token;
	private String lexema;
	private String tipo;
	
	public Celda(int token, String lexema, String tipo){
		this.token = token;
		this.lexema = lexema;
		this.tipo = tipo;
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
}
