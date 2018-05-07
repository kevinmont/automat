package com.lexico;
import java.util.LinkedHashMap;
%%
%int
%char
%unicode
%public
%extends Tokens
%{
	LinkedHashMap<String,String> tipos= new LinkedHashMap();
	LinkedHashMap<String,Integer> repeticiones= new LinkedHashMap();
	private int conta=0;
	private void save(String tipo,String key, int value ){
		if(repeticiones.containsKey(key)){
			int val=repeticiones.get(key);
			repeticiones.put(key,val+1);
		}else{
			tipos.put(key,tipo);
			repeticiones.put(key,value);
		}
	}
	public void showData(){
		/*Declaramos la forma en que se mostraran los datos en el JTable*/
	}
%}
%class LexicalAnalyzer
/*algunos literales*/
NEW_LINE = [\n|\r|\r\n]
ID = [a-zA-Z$_] [a-zA-Z0-9$_]*
NUM = 0|[1-9][0-9]*
WHITE_SPACE = {NEW_LINE} | [ \t\f]
%%
/* mis keywords */
"vali"							{ save ("Tipo de dato Integer", yytext(),1); }
"valf"							{ save ("Tipo de dato Float", yytext(),1); }
"valc"							{ save ("Tipo de dato Character", yytext(),1); }
"vals"							{ save ("Tipo de dato String", yytext(),1); }
"+"								{ save ("Suma", yytext(),1); }
"-"								{ save ("Resta", yytext(),1); }
"*"								{ save ("Multiplicación", yytext(),1); }
"/"								{ save ("División", yytext(),1); }
"{"								{ save ("Corchete Izquierdo", yytext(),1); }
"}"								{ save ("Corchete Derecho", yytext(),1); }
"("								{ save ("Parentesis Izquierdo", yytext(),1); }
")"								{ save ("Parentesis Derecho", yytext(),1); }
"#"								{ save ("Importación", yytext(),1); }
"void"							{ save ("Tipo void", yytext(),1); }
"return"						{ save ("Tipo return", yytext(),1); }
";"								{ save ("Fin de linea", yytext(),1); }
"'"								{ save ("Comilla Simple", yytext(),1); }
"=>"							{ save ("Asignación", yytext(),1); }
"fread"							{ save ("Funcion de Lectura", yytext(),1); }
"fout"							{ save ("Funcion de Escritura", yytext(),1); }
{NEW_LINE}						{ /*No se realiza nada */ }
{WHITE_SPACE}					{/*no se realiza nada */}
{ID}                    		{ save("Identificador", yytext(),1); }
{NUM}                    	{ save("Numero", yytext(),1); }
"."								{ save("Punto",yytext(),1); }
","								{ save("Coma", yytext(), 1); }
. 			{ save ("Desconocidos",yytext(),1); }