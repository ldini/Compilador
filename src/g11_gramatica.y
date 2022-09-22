%token if then else end-if out fun return break discard for continue ID CTE_INT CTE_FLOAT INT FLOAT CADENA MAYOR_IGUAL MENOR_IGUAL DISTINTO ASIGNACION
%start programa

programa: ID '{' conjunto_sentencias_declarativas conjunto_sentencias_ejecutables '}'
		  | errores_programa
		  ;

errores_programa: '{' conjunto_sentencias_declarativas conjunto_sentencias_ejecutables {Notificador.addError(lexico.getLineaActual(), ("SINTACTICO en la Linea N°" + lexico.getLineaActual() + "- Falta la '{' del programa")}
				  | conjunto_sentencias_declarativas conjunto_sentencias_ejecutables '}' {Notificador.addError(lexico.getLineaActual(), ("SINTACTICO en la Linea N°" + lexico.getLineaActual() + "- Falta la '}' del programa")}

tipo: INT
      | FLOAT
      | CADENA
      ;

sentencia_declarativa: tipo lista_variables ';'
		             | fun ID '(' parametro ')' ':' tipo '{' conjunto_sentencias_declarativas conjunto_sentencias_ejecutables return '(' expresion ')' '}' ';'
		             | errores_sentencia_declarativa
					 ;

conjunto_sentencias_declarativas: sentencia_declarativa
				  | conjunto_sentencias_declarativas sentencia_declarativa
				  ;

conjunto_sentencias_ejecutables: ejecutable
								 | conjunto_sentencias_ejecutables ejecutable
								 ;

ejecutable: asignacion ';'
	        | sentencia_if
			| sentencia_discard
			| sentencia_for
			| sentencia_salida
	        ;

sentencia_discard: discard ID '(' parametro ') ';'
				   | errores_sentencia_discard
				   ;

sentencia_if: if '(' condicion ')' then bloque_if end_if
			  | errores_sentencia_if
	          ;

bloque_if: sentencia_ejecutable_if
		   | sentencia_ejecutable_if else sentencia_ejecutable_if

sentencia_ejecutable_if: '{' conjunto_sentencias_ejecutables '}'

etiqueta: ID ':' ;

sentencia_for: etiqueta for '(' asignacion ';' condicion_for ';' constante ')' conjunto_sentencias_ejecutables break';' ';'
			   | etiqueta for '(' asignacion ';' condicion_for ';' constante ')' conjunto_sentencias_ejecutables ';'
			   | for '(' asignacion ';' condicion_for ';' constante ')' conjunto_sentencias_ejecutables break';' ';'
			   | for '(' asignacion ';' condicion_for ';' constante ')' conjunto_sentencias_ejecutables ';'
 			   | errores_sentencia_for
	           ;

condicion: condicion_AUX
		   | condicion operador condicion_AUX
		   | errores_condicion
		   ;

condicio_for: ID comparador expresion
			  ;

errores_condicion: comparador
		           | comparador expresion
		           ;

condicion_AUX: expresion comparador expresion
	       ;  

comparador: '<'
			| '>'
			| 'MENOR_IGUAL'
			| 'MAYOR_IGUAL'
			| 'DISTINTO'
			| 'IGUAL_IGUAL'
			;

asignacion: ID ASIGNACION expresion
	        | ID ASIGNACION invocacion_funcion
			| ID ASIGNACION sentencia_for
	        ;	
			
invocacion_funcion: ID '(' ID ')'
		    | errores_invocacion_funcion
	            ;

errores_invocacion_funcion: '(' ID ')' {Notificador.addError(lexico.getLineaActual(), ("SINTACTICO en la Linea N°" + lexico.getLineaActual() + "- Falta el ID que hace referencia al nombre de la Funcion")}
		VER	    | ID '(' ')' {Notificador.addError(lexico.getLineaActual(), ("SINTACTICO en la Linea N°" + lexico.getLineaActual() + "- Falta el ID que hace referencia al nombre de la Funcion")}
			    | '(' ID {Notificador.addError(lexico.getLineaActual(), ("SINTACTICO en la Linea N°" + lexico.getLineaActual() + "- Falta el ')' de la Funcion")}
			    | ID ')' {Notificador.addError(lexico.getLineaActual(), ("SINTACTICO en la Linea N°" + lexico.getLineaActual() + "- Falta el '(' de la Funcion")}
			    ;

expresion: expresion '+' termino
	   | expresion '-' termino
	   | termino
	   ;

termino: termino '*' factor
	 | termino '/' factor
	 | factor
	 ;

factor: ID
	| constante

constante: CTE_INT
	   | CTE_FLOAT
	   ;

parametro: tipo ID
	   | errores_parametro
	   ; 

errores_parametro: ID {Accion para notificar el ERROR Semantico en la Linea X}
		   ;

lista_variables: ID
		 | lista_variables ',' ID
		 | errores_lista_variables
		 ; 

errores_lista_variables: lista_variables ',' {Accion para notificar el ERROR Semantico en la Linea X}
			 ;

sentencia_salida: out '(' CADENA ')' ';'
				  | errores_sentencia_salida
