package alex;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol{
    private int fila;
    //private int columna=0;
   
    public UnidadLexica(int fila, int clase, String lexema) {
        super(clase, new TokenValue(lexema, fila, 0));
        this.fila = fila;
        //this.columna = columna;
    }
   
    public int clase () {return sym;}
    public int fila() {return fila;}
    //public int columna() {return columna;}
    public String lexema() {return ((TokenValue) value).getLexema();}
    //public String toString() {
    //    return "[clase:"+clase()+",fila:"+fila()+",col:"+columna()+",lexema:"+lexema()+"]";
    //}
}
