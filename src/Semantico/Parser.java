//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "g11_gramatica.y"
	package Semantico;
	import util.*;
	import Compilador.*;
	import Simbolo.*;
	import java.util.Stack;

//#line 24 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IF=257;
public final static short then=258;
public final static short ELSE=259;
public final static short end_if=260;
public final static short out=261;
public final static short fun=262;
public final static short RETURN=263;
public final static short BREAK=264;
public final static short discard=265;
public final static short FOR=266;
public final static short CONTINUE=267;
public final static short AND=268;
public final static short OR=269;
public final static short ID=270;
public final static short CTE_INT=271;
public final static short CTE_FLOAT=272;
public final static short INT=273;
public final static short FLOAT=274;
public final static short CADENA=275;
public final static short MAYOR_IGUAL=276;
public final static short MENOR_IGUAL=277;
public final static short DISTINTO=278;
public final static short ASIGNACION=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    2,    2,    5,    5,    5,
    6,    6,    3,    3,    4,    4,   10,   10,   10,   10,
   10,   13,   12,   17,   17,   18,   19,   14,   14,   14,
   14,   16,   16,   16,   23,   23,   20,   24,   24,   22,
   25,   25,   25,   25,   25,   25,   11,   11,   11,   26,
   26,   27,   27,   27,   27,    9,    9,    9,   28,   28,
   28,   29,   29,   21,   21,    8,    8,   30,    7,    7,
    7,   31,   15,
};
final static short yylen[] = {                            2,
    4,    1,    1,    1,    2,    3,    3,    1,    1,    1,
    3,   16,    1,    2,    1,    2,    2,    1,    1,    1,
    1,    6,    7,    1,    3,    3,    2,   13,   11,   12,
   10,    1,    3,    1,    1,    1,    3,    1,    2,    3,
    1,    1,    1,    1,    1,    1,    3,    3,    3,    4,
    1,    3,    3,    2,    2,    3,    3,    1,    3,    3,
    1,    1,    1,    1,    1,    2,    1,    1,    1,    3,
    1,    2,    5,
};
final static short yydefred[] = {                         0,
    0,    0,    8,    9,   10,    0,    0,    2,    0,    0,
   13,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   14,   15,    0,   18,   19,   20,   21,    0,   69,    0,
   71,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   27,    7,   16,   17,    0,   11,    0,   68,    0,
    0,   67,    1,    0,   62,   64,   65,   44,   43,   45,
   41,   42,   46,    0,    0,   63,   32,   34,    0,    0,
   61,    0,    0,    0,    0,    0,    0,    0,   49,   48,
   51,    0,   70,   66,    0,    0,    0,    0,   35,   36,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   55,
    0,    0,    0,    0,    0,    0,    0,   33,   59,   60,
   73,    0,    0,    0,    0,   53,   52,    0,    0,    0,
    0,    0,   22,    0,    0,   50,    0,    0,    0,   23,
    0,    0,    0,    0,    0,   26,   25,    0,    0,    0,
    0,    0,    0,    0,   31,    0,    0,    0,    0,   29,
    0,   30,    0,    0,   28,    0,   12,
};
final static short yydgoto[] = {                          7,
   33,    8,    9,   20,   10,   11,   30,   51,   64,   22,
   23,   24,   25,   26,   27,   65,  121,  122,   28,  114,
   66,   67,   92,   68,   69,   80,   81,   70,   71,   52,
   31,
};
final static short yysindex[] = {                      -115,
 -242,  -74,    0,    0,    0, -150,    0,    0, -168, -184,
    0,   52, -168, -168,   60,   70, -141,   91,  -55, -113,
    0,    0,   78,    0,    0,    0,    0, -128,    0,   12,
    0, -159,   14, -168, -148, -148,   -1, -135,  101, -127,
  -13,    0,    0,    0,    0,  102,    0, -125,    0, -124,
  110,    0,    0, -148,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -10,  -25,    0,    0,    0, -205,   26,
    0,  113, -159, -123,  103,    6, -109,   38,    0,    0,
    0, -127,    0,    0,  107, -205, -205, -205,    0,    0,
  -92, -205,   38, -205, -205,  109,  129,  -98,  -40,    0,
  135,  122, -154,   26,   26,   38,   61,    0,    0,    0,
    0,  124,  -22,  126,  145,    0,    0,  -98,   64, -148,
  -72,  -70,    0, -205, -197,    0,  131, -150,  -88,    0,
   61,   38,  150, -197, -168,    0,    0, -148,  151, -162,
  -53, -148,  153,  136,    0,   17, -205,  137,  138,    0,
   85,    0,  139,   69,    0,  140,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   75,   76,  202,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   18,    0,    0,
    0,    0,    0,   80,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -19,  -41,
    0,    0,    0,    0,    0,   37,    0,  144,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   -7,    0,    0,    0,    0,    0,    0,    0,
  147,    0,    0,  -36,  -30,   -5,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -51,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  148,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    4,   29,  -18,   44,    0,  141,    3,   34,
    5,    0,    0,  169,    0,    0,    0,   84,    0,   98,
  -77,  127,    0,    0,  -23,    0,    0,   47,   41,    0,
    0,
};
final static int YYTABLESIZE=287;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         58,
  116,   58,   42,   58,   56,  145,   56,    6,   56,   14,
   57,   43,   57,   50,   57,   91,   34,   58,   58,   58,
   58,   38,   56,   56,   56,   56,   77,   12,   57,   57,
   57,   57,   86,   39,   87,   40,  136,   61,   63,   62,
   88,   35,   36,   78,   75,   99,  100,  133,   13,   61,
   63,   62,   21,   44,   50,   48,  139,   21,   61,   63,
   62,   72,   54,   42,   55,   56,   57,   94,   44,   44,
   47,   93,   95,   56,   57,  150,   72,   21,   62,   62,
   86,   62,   87,   62,  119,   29,  102,   44,   15,  124,
  106,   32,   16,    1,   15,   62,   17,   18,   16,   37,
  143,   19,   17,   18,    3,    4,    5,   19,   15,   38,
   49,    1,   16,    3,    4,    5,   17,   18,    3,    4,
    5,   19,    3,    4,    5,  154,  132,   86,   39,   87,
   40,  135,  104,  105,  109,  110,   45,   46,   53,   72,
   73,   82,   74,   15,   83,   84,    1,   16,  129,  151,
   85,   17,   18,   96,    2,   41,   19,    3,    4,    5,
  101,   98,   44,  140,  103,  107,  141,  111,   15,  112,
  146,  113,   16,   44,   44,  117,   17,   18,   21,   44,
  118,   19,  123,  120,  125,  126,  128,  130,  131,  134,
  138,  142,  147,  156,  148,  152,  153,  155,  157,    3,
    4,    6,   47,   15,    5,   54,   37,   16,   24,   79,
  144,   17,   18,   97,  137,  127,   19,    0,  108,    0,
    0,    0,    0,   41,    0,    0,   58,   58,    0,  115,
    0,   56,   56,    0,   58,   58,   58,   57,   57,   56,
   56,   56,   89,   90,    0,   57,   57,   57,   38,   38,
    0,    0,   18,   58,   59,   60,   76,   56,   57,    0,
   39,   39,   40,   40,    0,   58,   59,   60,   55,   56,
   57,    0,    0,   15,   58,   59,   60,   16,    0,    0,
  149,   17,   18,    0,    0,    0,   19,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   41,   43,   58,   45,   41,   59,   43,  123,   45,    6,
   41,  125,   43,   32,   45,   41,   13,   59,   60,   61,
   62,   41,   59,   60,   61,   62,   40,  270,   59,   60,
   61,   62,   43,   41,   45,   41,  125,   60,   61,   62,
   64,   13,   14,   41,   40,   40,   41,  125,  123,   60,
   61,   62,    9,   20,   73,   44,  134,   14,   60,   61,
   62,   44,   34,   58,  270,  271,  272,   42,   35,   36,
   59,   69,   47,  271,  272,   59,   59,   34,   42,   43,
   43,   45,   45,   47,  103,  270,   82,   54,  257,  113,
   88,   40,  261,  262,  257,   59,  265,  266,  261,   40,
  263,  270,  265,  266,  273,  274,  275,  270,  257,   40,
  270,  262,  261,  273,  274,  275,  265,  266,  273,  274,
  275,  270,  273,  274,  275,   41,  124,   43,  270,   45,
   40,  128,   86,   87,   94,   95,   59,  266,  125,  275,
   40,   40,  270,  257,  270,  270,  262,  261,  120,  147,
   41,  265,  266,   41,  270,  279,  270,  273,  274,  275,
  270,   59,  129,  135,   58,  258,  138,   59,  257,   41,
  142,  270,  261,  140,  141,   41,  265,  266,  135,  146,
   59,  270,   59,  123,   59,   41,  123,  260,  259,   59,
   41,   41,   40,  125,   59,   59,   59,   59,   59,  125,
  125,    0,   59,  257,  125,   59,   59,  261,  260,   41,
  264,  265,  266,   73,  131,  118,  270,   -1,   92,   -1,
   -1,   -1,   -1,  279,   -1,   -1,  268,  269,   -1,  270,
   -1,  268,  269,   -1,  276,  277,  278,  268,  269,  276,
  277,  278,  268,  269,   -1,  276,  277,  278,  268,  269,
   -1,   -1,  266,  276,  277,  278,  270,  271,  272,   -1,
  268,  269,  268,  269,   -1,  276,  277,  278,  270,  271,
  272,   -1,   -1,  257,  276,  277,  278,  261,   -1,   -1,
  264,  265,  266,   -1,   -1,   -1,  270,
};
}
final static short YYFINAL=7;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"IF","then","ELSE","end_if","out","fun",
"RETURN","BREAK","discard","FOR","CONTINUE","AND","OR","ID","CTE_INT",
"CTE_FLOAT","INT","FLOAT","CADENA","MAYOR_IGUAL","MENOR_IGUAL","DISTINTO",
"ASIGNACION",
};
final static String yyrule[] = {
"$accept : programa",
"programa : ID '{' conjunto_sentencias_programa '}'",
"programa : errores_programa",
"conjunto_sentencias_programa : conjunto_sentencias_declarativas",
"conjunto_sentencias_programa : conjunto_sentencias_ejecutables",
"conjunto_sentencias_programa : conjunto_sentencias_declarativas conjunto_sentencias_ejecutables",
"errores_programa : '{' conjunto_sentencias_declarativas conjunto_sentencias_ejecutables",
"errores_programa : conjunto_sentencias_declarativas conjunto_sentencias_ejecutables '}'",
"tipo : INT",
"tipo : FLOAT",
"tipo : CADENA",
"sentencia_declarativa : tipo lista_variables ';'",
"sentencia_declarativa : fun ID '(' parametro ')' ':' tipo '{' conjunto_sentencias_declarativas conjunto_sentencias_ejecutables RETURN '(' expresion ')' '}' ';'",
"conjunto_sentencias_declarativas : sentencia_declarativa",
"conjunto_sentencias_declarativas : conjunto_sentencias_declarativas sentencia_declarativa",
"conjunto_sentencias_ejecutables : ejecutable",
"conjunto_sentencias_ejecutables : conjunto_sentencias_ejecutables ejecutable",
"ejecutable : asignacion ';'",
"ejecutable : sentencia_if",
"ejecutable : sentencia_discard",
"ejecutable : sentencia_for",
"ejecutable : sentencia_salida",
"sentencia_discard : discard ID '(' parametro ')' ';'",
"sentencia_if : IF '(' condicion ')' then bloque_if end_if",
"bloque_if : sentencia_ejecutable_if",
"bloque_if : sentencia_ejecutable_if ELSE sentencia_ejecutable_if",
"sentencia_ejecutable_if : '{' conjunto_sentencias_ejecutables '}'",
"etiqueta : ID ':'",
"sentencia_for : etiqueta FOR '(' asignacion ';' condicion_for ';' constante ')' conjunto_sentencias_ejecutables BREAK ';' ';'",
"sentencia_for : etiqueta FOR '(' asignacion ';' condicion_for ';' constante ')' conjunto_sentencias_ejecutables ';'",
"sentencia_for : FOR '(' asignacion ';' condicion_for ';' constante ')' conjunto_sentencias_ejecutables BREAK ';' ';'",
"sentencia_for : FOR '(' asignacion ';' condicion_for ';' constante ')' conjunto_sentencias_ejecutables ';'",
"condicion : condicion_AUX",
"condicion : condicion operador condicion_AUX",
"condicion : errores_condicion",
"operador : AND",
"operador : OR",
"condicion_for : ID comparador expresion",
"errores_condicion : comparador",
"errores_condicion : comparador expresion",
"condicion_AUX : expresion comparador expresion",
"comparador : '<'",
"comparador : '>'",
"comparador : MENOR_IGUAL",
"comparador : MAYOR_IGUAL",
"comparador : DISTINTO",
"comparador : '='",
"asignacion : ID ASIGNACION expresion",
"asignacion : ID ASIGNACION invocacion_funcion",
"asignacion : ID ASIGNACION sentencia_for",
"invocacion_funcion : ID '(' ID ')'",
"invocacion_funcion : errores_invocacion_funcion",
"errores_invocacion_funcion : '(' ID ')'",
"errores_invocacion_funcion : ID '(' ')'",
"errores_invocacion_funcion : '(' ID",
"errores_invocacion_funcion : ID ')'",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"factor : ID",
"factor : constante",
"constante : CTE_INT",
"constante : CTE_FLOAT",
"parametro : tipo ID",
"parametro : errores_parametro",
"errores_parametro : ID",
"lista_variables : ID",
"lista_variables : lista_variables ',' ID",
"lista_variables : errores_lista_variables",
"errores_lista_variables : lista_variables ','",
"sentencia_salida : out '(' CADENA ')' ';'",
};

//#line 148 "g11_gramatica.y"
public AnalizadorLexico lexico;
private TablaSimbolos tablaSimbolos;

public Parser(AnalizadorLexico lexico, TablaSimbolos tablaSimbolos){
	this.tablaSimbolos = tablaSimbolos;
	this.lexico = lexico;
}

public int yylex(){
	int token = lexico.tokenGenerado();
	yylval = new ParserVal(lexico.ultimoLexemaGenerado);
	return token;
}

private void yyerror(String error){
	Notificador.addError(lexico.getLineaActual(), error);
}
//#line 393 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 6:
//#line 23 "g11_gramatica.y"
{Notificador.addError(lexico.getLineaActual(), ("SINTACTICO en la Linea N°" + lexico.getLineaActual() + "- Falta la '{' del programa"));}
break;
case 7:
//#line 24 "g11_gramatica.y"
{Notificador.addError(lexico.getLineaActual(), ("SINTACTICO en la Linea N°" + lexico.getLineaActual() + "- Falta la '}' del programa"));}
break;
case 52:
//#line 106 "g11_gramatica.y"
{Notificador.addError(lexico.getLineaActual(), ("SINTACTICO en la Linea N°" + lexico.getLineaActual() + "- Falta el ID que hace referencia al nombre de la Funcion"));}
break;
case 53:
//#line 107 "g11_gramatica.y"
{Notificador.addError(lexico.getLineaActual(), ("SINTACTICO en la Linea N°" + lexico.getLineaActual() + "- Falta el ID que hace referencia al nombre de la Funcion"));}
break;
case 54:
//#line 108 "g11_gramatica.y"
{Notificador.addError(lexico.getLineaActual(), ("SINTACTICO en la Linea N°" + lexico.getLineaActual() + "- Falta el ')' de la Funcion"));}
break;
case 55:
//#line 109 "g11_gramatica.y"
{Notificador.addError(lexico.getLineaActual(), ("SINTACTICO en la Linea N°" + lexico.getLineaActual() + "- Falta el '(' de la Funcion"));}
break;
//#line 566 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
