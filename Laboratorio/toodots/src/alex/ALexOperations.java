package alex;

import asint.ClaseLexica;
import errors.GestionErroresTiny;

public class ALexOperations {
private AnalizadorLexicoTiny alex;
    public ALexOperations(AnalizadorLexicoTiny alex) {
        this.alex = alex;
    }
    public UnidadLexica unidadObjects() {
        return new UnidadLexica(alex.fila(),ClaseLexica.OBJECTS,"OBJECTS");
    }
    public UnidadLexica unidadProcess() {
        return new UnidadLexica(alex.fila(),ClaseLexica.PROCESS,"PROCESS");
    }
    public UnidadLexica unidadInt() {
        return new UnidadLexica(alex.fila(),ClaseLexica.INT,"Int");
    }
    public UnidadLexica unidadFloat() {
        return new UnidadLexica(alex.fila(),ClaseLexica.FLOAT,"Float");
    }
    public UnidadLexica unidadBool() {
        return new UnidadLexica(alex.fila(),ClaseLexica.BOOL,"Bool");
    }
    public UnidadLexica unidadChar() {
        return new UnidadLexica(alex.fila(),ClaseLexica.CHAR,"Char");
    }
    public UnidadLexica unidadVoid() {
        return new UnidadLexica(alex.fila(),ClaseLexica.VOID,"Void");
    }
    public UnidadLexica unidadEnum() {
        return new UnidadLexica(alex.fila(),ClaseLexica.ENUM,"Enum");
    }
    public UnidadLexica unidadStruct() {
        return new UnidadLexica(alex.fila(),ClaseLexica.STRUCT,"Struct");
    }
    public UnidadLexica unidadMas() {
       return new UnidadLexica(alex.fila(),ClaseLexica.MAS,"+");
    }
    public UnidadLexica unidadMenos() {
       return new UnidadLexica(alex.fila(),ClaseLexica.MENOS,"-");
    }
    public UnidadLexica unidadBucle() {
        return new UnidadLexica(alex.fila(),ClaseLexica.BUCLE,"@");
    }
    public UnidadLexica unidadAsignacion() {
        return new UnidadLexica(alex.fila(),ClaseLexica.ASIGN,":=");
    }
    public UnidadLexica unidadIdentificador() {
        return new UnidadLexica(alex.fila(),ClaseLexica.ID,alex.lexema());
    }
    public UnidadLexica unidadEntero() {
        return new UnidadLexica(alex.fila(),ClaseLexica.ENT,alex.lexema());
    }
    public UnidadLexica unidadReal() {
        return new UnidadLexica(alex.fila(),ClaseLexica.REAL,alex.lexema());
    }
    public UnidadLexica unidadTrue() {
        return new UnidadLexica(alex.fila(),ClaseLexica.TRUE,"true");
    }
    public UnidadLexica unidadFalse() {
        return new UnidadLexica(alex.fila(),ClaseLexica.FALSE,"false");
    }
    public UnidadLexica unidadCaracter() {
        return new UnidadLexica(alex.fila(),ClaseLexica.CAR,alex.lexema());
    }
    public UnidadLexica unidadNegacion() {
        return new UnidadLexica(alex.fila(),ClaseLexica.NOT,"not");
    }
    public UnidadLexica unidadMultiplicacion() {
        return new UnidadLexica(alex.fila(),ClaseLexica.MULT,"*");
    }
    public UnidadLexica unidadDivision() {
        return new UnidadLexica(alex.fila(),ClaseLexica.DIV,"/");
    }
    public UnidadLexica unidadModulo() {
        return new UnidadLexica(alex.fila(),ClaseLexica.MOD,"%");
    }
    public UnidadLexica unidadMenor() {
        return new UnidadLexica(alex.fila(),ClaseLexica.MENOR,"<");
    }
    public UnidadLexica unidadMayor() {
        return new UnidadLexica(alex.fila(),ClaseLexica.MAYOR,">");
    }
    public UnidadLexica unidadMenorIgual() {
        return new UnidadLexica(alex.fila(),ClaseLexica.MENORIGUAL,"<=");
    }
    public UnidadLexica unidadMayorIgual() {
        return new UnidadLexica(alex.fila(),ClaseLexica.MAYORIGUAL,">=");
    }
    public UnidadLexica unidadIgual() {
        return new UnidadLexica(alex.fila(),ClaseLexica.IGUAL,"==");
    }
    public UnidadLexica unidadDistinto() {
        return new UnidadLexica(alex.fila(),ClaseLexica.DISTINTO,"><");
    }
    public UnidadLexica unidadConjuncion() {
        return new UnidadLexica(alex.fila(),ClaseLexica.AND,"and");
    }
    public UnidadLexica unidadDisyuncion() {
        return new UnidadLexica(alex.fila(),ClaseLexica.OR,"or");
    }
    public UnidadLexica unidadParentesisApertura() {
        return new UnidadLexica(alex.fila(),ClaseLexica.PA,"(");
    }
    public UnidadLexica unidadParentesisCierre() {
        return new UnidadLexica(alex.fila(),ClaseLexica.PC,")");
    }
    public UnidadLexica unidadCorcheteApertura() {
        return new UnidadLexica(alex.fila(),ClaseLexica.CA,"[");
    }
    public UnidadLexica unidadCorcheteCierre() {
        return new UnidadLexica(alex.fila(),ClaseLexica.CC,"]");
    }
    public UnidadLexica unidadLlaveCierre() {
        return new UnidadLexica(alex.fila(),ClaseLexica.LC,"}");
    }
    public UnidadLexica unidadInterrogacionCierre() {
        return new UnidadLexica(alex.fila(),ClaseLexica.INTC,"?");
    }
    public UnidadLexica unidadExclamacionCierre() {
        return new UnidadLexica(alex.fila(),ClaseLexica.EXCC,"!");
    }
    public UnidadLexica unidadComa() {
        return new UnidadLexica(alex.fila(),ClaseLexica.C,",");
    }
    public UnidadLexica unidadPunto() {
        return new UnidadLexica(alex.fila(),ClaseLexica.P,".");
    }
    public UnidadLexica unidadDosPuntos() {
        return new UnidadLexica(alex.fila(),ClaseLexica.DP,":");
    }
    public UnidadLexica unidadImprimir() {
        return new UnidadLexica(alex.fila(),ClaseLexica.PRINT,">>");
    }
    public UnidadLexica unidadEof() {
        return new UnidadLexica(alex.fila(),ClaseLexica.EOF,"EOF");
    }
    public void error() {
        GestionErroresTiny.errorLexico(alex.fila(),alex.lexema());
    }
}
