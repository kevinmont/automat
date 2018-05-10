package mx.com.mont.automat;
import java_cup.runtime.*;
import java.io.*;
%%
%{
		private Table table;
		public LexerF(Reader in, Table table){
			this(in);
			this.table = table;
		}
		private void save(Data data){
			table.put(data);
		}
			
        private Symbol symbol(int type){
	  		return new Symbol(type, yyline, yycolumn);
		}
		private Symbol symbol(int type, Object value){
	  		return new Symbol(type, yyline, yycolumn, value);
		}	
%}
%eofval{
  return new java_cup.runtime.Symbol(sym.EOF);
%eofval}
%eofclose
%public 
%unicode
%line
%cup
%column
%class LexerF
/*algunos literales*/
NEW_LINE = [\n|\r|\r\n]
WHITE_SPACE = {NEW_LINE} | [ \t\f]
ID = [a-zA-Z][a-zA-Z0-9]*
SYM = [=<>]
INTNUM = (-([1-9]+))|(0|[1-9][0-9]*)
FLOATNUM = ((-([0]|[1-9][0-9]*))|(0|[1-9][0-9]*))"."(0|[0-9]*[1-9])f
COMMENT = "/*"[^.]*"*/"
%%
/* mis keywords */
"vali"				{ save(new Data("Integer",yytext(),"Reservada entero",1));									return symbol(sym.VALI); }
"valf"				{ save(new Data("Float",yytext(),"Reservada float",1));										return symbol(sym.VALF); }
"valc"				{ save(new Data("Character",yytext(),"Resercada caracter",1));								return symbol(sym.VALC); }
"vals"				{ save(new Data("String",yytext(),"Reservada cadenas",1));									return symbol(sym.VALS); }
"main"				{ save(new Data("Main",yytext(),"Reservada metodo principal",1));						    return symbol(sym.MAIN); }
{COMMENT}			{/*NO SE HACE NADA*/ System.out.println("un comentario");}
"+"					{ save(new Data("Operador",yytext(),"Para sumas",1));										return symbol(sym.SUMA); }
"-"					{ save(new Data("Operador",yytext(),"Para restar",1));										return symbol(sym.RESTA); }
"*"					{ save(new Data("Operador",yytext(),"Para multiplicar",1));									return symbol(sym.MULT); }
"/"					{ save(new Data("Operador",yytext(),"Para dividir",1));										return symbol(sym.DIVI); }
"{"					{ save(new Data("Corchete izq",yytext(),"Llave inicio de declaci�n",1));					return symbol(sym.COR_IZQ); }
"}"					{ save(new Data("Corchete der",yytext(),"Cierre de declaraci�n",1));						return symbol(sym.COR_DER); }
"("					{ save(new Data("Parent�sis izq",yytext(),"Parent�sis izq",1));								return symbol(sym.PAR_IZQ); }
")"					{ save(new Data("Parent�sis der",yytext(),"Parent�sis der",1));								return symbol(sym.PAR_DER); }
"#"					{ save(new Data("Importacion",yytext(),"Reservada",1));										return symbol(sym.IMPORT); }
"return"			{ save(new Data("Retorno",yytext(),"Reservada",1));											return symbol(sym.RETURN); }
"fout"				{ save(new Data("Salida",yytext(),"Salida de texto",1));									return symbol(sym.SALIDA); }
"fin"				{ save(new Data("Entrada",yytext(),"Entrada de texto",1));									return symbol(sym.ENTRADA); }
";"					{ save(new Data("Punto y coma",yytext(),"Simbolo fin de linea",1));							return symbol(sym.FIN_LINEA); }
"'"					{ save(new Data("Corchete simple",yytext(),"Simbolo comentario",1));						return symbol(sym.COM_SIMPLE); }
"=>"				{ save(new Data("Asignacion",yytext(),"Simbolo de asignacion",1));							return symbol(sym.ASIGNACION); }
{NEW_LINE}			{/*NO SE HACE NADA*/ }
{WHITE_SPACE}		{ 	System.out.println("Un espacio "); }
{ID}                { save(new Data("ID",yytext(),0,1,"Identificador",1));										return symbol(sym.ID, yytext()); /*COMPROBAR DESDE EL CODIGO PARA NUESTRA ESTRUCTURA DE DATOS UNA TABLA DE ERRORES PARA SER MAS EXACTOS*/ }
{FLOATNUM}			{ save(new Data("Numero float",yytext(), new Float(yytext()), 0, "Numero float",1));		return symbol(sym.NUMF, new Float(yytext())); }
{INTNUM}            { save(new Data("Numero entero",yytext(), new Integer(yytext()), 0,"Numero entero",1));		return symbol(sym.NUMI, new Integer(yytext())); }
{SYM}				{ save(new Data("Simbolo",yytext(),"Simbolos del alfabeto",1));								return symbol(sym.SYM); }
"."					{ save(new Data("Punto",yytext(),"Punto para numero",1));									return symbol(sym.PUNTO); }
","					{ save(new Data("Coma",yytext(),"Coma separacion",1));										return symbol(sym.COMA); }
. 					{ System.err.println("Error entrada desconocida"); }