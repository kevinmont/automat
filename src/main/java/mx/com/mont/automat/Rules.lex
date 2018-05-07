package com.lexico;
import java_cup.runtime.Symbol;
%%
%int
%char
%unicode
%public
%cup
/*algunos literales*/
NEW_LINE = [\n|\r|\r\n]
ID = [a-zA-Z$_] [a-zA-Z0-9$_]*
NUM = 0|[1-9][0-9]*
WHITE_SPACE = {NEW_LINE} | [ \t\f]

%%

/* mis keywords */
"vali"							{ return new Symbol(sym.INT); }
"valf"							{ return new Symbol(sym.FLOAT); }
"valc"							{ return new Symbol(sym.CHAR); }
"vals"							{ return new Symbol(sym.STRING); }
"+"								{ return new Symbol(sym.PLUS); }
"-"								{ return new Symbol(sym.MINUS); }
"*"								{ return new Symbol(sym.MULT); }
"/"								{ return new Symbol(sym.DIV); }
"{"								{ return new Symbol(sym.COR_IZQ); }
"}"								{ return new Symbol(sym.COR_DER); }
"("								{ return new Symbol(sym.PAR_IZQ); }
")"								{ return new Symbol(sym.PAR_DER); }
"#"								{ return new Symbol(sym.IMPORT); }
"void"							{ return new Symbol(sym.VOID); }
"return"						{ return new Symbol(sym.RETURN); }
";"								{ return new Symbol(sym.FIN_LINEA); }
"'"								{ return new Symbol(sym.COM_SIMPLE); }
"=>"							{ return new Symbol(sym.ASIGNACION); }
"fout"							{ return new Symbol(sym.OUT); }
"fin"							{ return new Symbol(sym.IN); }
{NEW_LINE}						{/*NO SE HACE NADA*/ }
{WHITE_SPACE}					{ return new Symbol(sym.SPACE); }
{ID}                    		{  return new Symbol(sym.ID, yytext()); }
{NUM}                    	{  return new Symbol(sym.NUMERO, new Integer(yytext())); }
"."								{  return new Symbol(sym.PUNTO); }
","								{ s return new Symbol(sym.COMA); }
. 			{ System.err.println("Error entrada desconocida"); }