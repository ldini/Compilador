package Automata;

public class Entradas {
	public static int SUMA = 0;
	public static int RESTA = 1;
	public static int MULTIPLICACION = 2;
	public static int DIVISION = 3;
	public static int PARENTESIS_A = 4;
	public static int PARENTESIS_C = 5;
	public static int LLAVE_A = 6;
	public static int LLAVE_C = 7;
	public static int COMA = 8;
	public static int PUNTO_COMA = 9;
	public static int LETRA_MINUSCULA = 10;
	public static int LETRA_MAYUSCULA = 11;
	public static int DIGITO = 12;
	public static int GUION_BAJO = 13;
	public static int DESCARTABLE = 14;
	public static int SALTO_LINEA = 15;
	public static int MENOR = 16;
	public static int MAYOR = 17;
	public static int IGUAL = 18;
	public static int SIGNO_EXCLAMACION = 19;
	public static int PUNTO = 20;
	public static int F_EXPONENTE = 21;
	public static int DOS_PUNTOS = 22;
	public static int OTRO = 23;
	
	public static int totalEntrada = 24;
	
	public static int charToInt(char c){
		switch(c){
			case '+':
				return SUMA;
			case '-':
				return RESTA;
			case '*':
				return MULTIPLICACION;
			case '/':
				return DIVISION;
			case '(':
				return PARENTESIS_A;
			case ')':
				return PARENTESIS_C;	
			case '{':
				return LLAVE_A;
			case '}':
				return LLAVE_C;
			case ',':
				return COMA;
			case ';':
				return PUNTO_COMA;
			case '_':
				return GUION_BAJO;
			case ' ':
				return DESCARTABLE;
			case '\n':
				return SALTO_LINEA;
			case '<':
				return MENOR;
			case '>':
				return MAYOR;
			case '=':
				return IGUAL;	
			case '!':
				return SIGNO_EXCLAMACION;
			case '.':
				return PUNTO;
			case 'F':
				return F_EXPONENTE;
			case ':':
				return DOS_PUNTOS;
		}
		
		if(c >= 'a' && c <= 'z')
			return LETRA_MINUSCULA;
		if(c >= 'A' && c <= 'Z')
			return LETRA_MAYUSCULA;
		if(c >= '0' && c <= '9')
			return DIGITO;
		
		return OTRO;
	}
}
