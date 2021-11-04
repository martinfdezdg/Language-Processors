package asem;

import ast.*;
import ast.Instrucciones.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import errors.GestionErroresTiny;
import java.util.List;
import java.util.ArrayList;

public class ComprobadorTipoE {
    
    public TipoT comprobacion(E expresion) {
        TipoT opnd = null, opnd1 = null, opnd2 = null;
        boolean comprueba = true, encuentra = false;
        
        switch (expresion.getTipo()) {
            case OR:
                EBin or = (EBin) expresion;
                opnd1 = comprobacion(or.getOpnd1());
                opnd2 = comprobacion(or.getOpnd2());
                if (opnd1 == TipoT.BOOL && opnd2 == TipoT.BOOL) {
                    return TipoT.BOOL;
                }
                else GestionErroresTiny.errorSemantico(or.getFila(),"or","Los tipos no son correctos");
                break;
            
            case AND:
                EBin and = (EBin) expresion;
                opnd1 = comprobacion(and.getOpnd1());
                opnd2 = comprobacion(and.getOpnd2());
                if (opnd1 == TipoT.BOOL && opnd2 == TipoT.BOOL) {
                    return TipoT.BOOL;
                }
                else GestionErroresTiny.errorSemantico(and.getFila(),"and","Los tipos no son correctos");
                break;
            
            case IGUAL:
                EBin igual = (EBin) expresion;
                opnd1 = comprobacion(igual.getOpnd1());
                opnd2 = comprobacion(igual.getOpnd2());
                if ((opnd1 == opnd2) || (opnd1 == TipoT.FLOAT && opnd2 == TipoT.INT) || (opnd2 == TipoT.FLOAT && opnd1 == TipoT.INT)) {
                    return TipoT.BOOL;
                }
                else GestionErroresTiny.errorSemantico(igual.getFila(),"igual","Los tipos no son correctos");
                break;
            
            case DISTINTO:
                EBin distinto = (EBin) expresion;
                opnd1 = comprobacion(distinto.getOpnd1());
                opnd2 = comprobacion(distinto.getOpnd2());
                if ((opnd1 == opnd2) || (opnd1 == TipoT.FLOAT && opnd2 == TipoT.INT) || (opnd2 == TipoT.FLOAT && opnd1 == TipoT.INT)) {
                    return TipoT.BOOL;
                }
                else GestionErroresTiny.errorSemantico(distinto.getFila(),"distinto","Los tipos no son correctos");
                break;
                
            case MENOR:
                EBin menor = (EBin) expresion;
                opnd1 = comprobacion(menor.getOpnd1());
                opnd2 = comprobacion(menor.getOpnd2());
                if ((opnd1 == opnd2) || (opnd1 == TipoT.FLOAT && opnd2 == TipoT.INT) || (opnd2 == TipoT.FLOAT && opnd1 == TipoT.INT)) {
                    return TipoT.BOOL;
                }
                else GestionErroresTiny.errorSemantico(menor.getFila(),"menor","Los tipos no son correctos");
                break;
                
            case MAYOR:
                EBin mayor = (EBin) expresion;
                opnd1 = comprobacion(mayor.getOpnd1());
                opnd2 = comprobacion(mayor.getOpnd2());
                if ((opnd1 == opnd2) || (opnd1 == TipoT.FLOAT && opnd2 == TipoT.INT) || (opnd2 == TipoT.FLOAT && opnd1 == TipoT.INT)) {
                    return TipoT.BOOL;
                }
                else GestionErroresTiny.errorSemantico(mayor.getFila(),"mayor","Los tipos no son correctos");
                
            case MENORIGUAL:
                EBin menorigual = (EBin) expresion;
                opnd1 = comprobacion(menorigual.getOpnd1());
                opnd2 = comprobacion(menorigual.getOpnd2());
                if ((opnd1 == opnd2) || (opnd1 == TipoT.FLOAT && opnd2 == TipoT.INT) || (opnd2 == TipoT.FLOAT && opnd1 == TipoT.INT)) {
                    return TipoT.BOOL;
                }
                else GestionErroresTiny.errorSemantico(menorigual.getFila(),"menorigual","Los tipos no son correctos");
                break;
                
            case MAYORIGUAL:
                EBin mayorigual = (EBin) expresion;
                opnd1 = comprobacion(mayorigual.getOpnd1());
                opnd2 = comprobacion(mayorigual.getOpnd2());
                if ((opnd1 == opnd2) || (opnd1 == TipoT.FLOAT && opnd2 == TipoT.INT) || (opnd2 == TipoT.FLOAT && opnd1 == TipoT.INT)) {
                    return TipoT.BOOL;
                }
                else GestionErroresTiny.errorSemantico(mayorigual.getFila(),"mayorigual","Los tipos no son correctos");
                break;
                
            case SUMA:
                EBin suma = (EBin) expresion;
                opnd1 = comprobacion(suma.getOpnd1());
                opnd2 = comprobacion(suma.getOpnd2());
                if (opnd1 == opnd2 && (opnd1 == TipoT.INT || opnd1 == TipoT.FLOAT)) {
                    return opnd1;
                }
                else if (opnd1 == TipoT.FLOAT && opnd2 == TipoT.INT) {
                    return TipoT.FLOAT;
                }
                else if (opnd2 == TipoT.FLOAT && opnd1 == TipoT.INT) {
                    return TipoT.FLOAT;
                }
                else GestionErroresTiny.errorSemantico(suma.getFila(),"suma","Los tipos no son correctos");
                break;
                
            case RESTA:
                EBin resta = (EBin) expresion;
                opnd1 = comprobacion(resta.getOpnd1());
                opnd2 = comprobacion(resta.getOpnd2());
                if (opnd1 == opnd2 && (opnd1 == TipoT.INT || opnd1 == TipoT.FLOAT)) {
                    return opnd1;
                }
                else if (opnd1 == TipoT.FLOAT && opnd2 == TipoT.INT) {
                    return TipoT.FLOAT;
                }
                else if (opnd2 == TipoT.FLOAT && opnd1 == TipoT.INT) {
                    return TipoT.FLOAT;
                }
                else GestionErroresTiny.errorSemantico(resta.getFila(),"resta","Los tipos no son correctos");
                break;
                
            case MULT:
                EBin mult = (EBin) expresion;
                opnd1 = comprobacion(mult.getOpnd1());
                opnd2 = comprobacion(mult.getOpnd2());
                if (opnd1 == opnd2 && (opnd1 == TipoT.INT || opnd1 == TipoT.FLOAT)) {
                    return opnd1;
                }
                else if (opnd1 == TipoT.FLOAT && opnd2 == TipoT.INT) {
                    return TipoT.FLOAT;
                }
                else if (opnd2 == TipoT.FLOAT && opnd1 == TipoT.INT) {
                    return TipoT.FLOAT;
                }
                else GestionErroresTiny.errorSemantico(mult.getFila(),"mult","Los tipos no son correctos");
                break;
                
            case DIV:
                EBin div = (EBin) expresion;
                opnd1 = comprobacion(div.getOpnd1());
                opnd2 = comprobacion(div.getOpnd2());
                if (opnd1 == opnd2 && (opnd1 == TipoT.INT || opnd1 == TipoT.FLOAT)) {
                    return opnd1;
                }
                else if (opnd1 == TipoT.FLOAT && opnd2 == TipoT.INT) {
                    return TipoT.FLOAT;
                }
                else if (opnd2 == TipoT.FLOAT && opnd1 == TipoT.INT) {
                    return TipoT.FLOAT;
                }
                else GestionErroresTiny.errorSemantico(div.getFila(),"div","Los tipos no son correctos");
                break;
                
            case MOD:
                EBin mod = (EBin) expresion;
                opnd1 = comprobacion(mod.getOpnd1());
                opnd2 = comprobacion(mod.getOpnd2());
                if (opnd1 == TipoT.INT && opnd2 == TipoT.INT) {
                    return TipoT.INT;
                }
                else GestionErroresTiny.errorSemantico(mod.getFila(),"mod","Los tipos no son correctos");
                break;
                
            case NOT:
                EUn not = (EUn) expresion;
                opnd = comprobacion(not.getOpnd());
                if (opnd == TipoT.BOOL) {
                    return TipoT.BOOL;
                }
                else GestionErroresTiny.errorSemantico(not.getFila(),"not","El tipo no es correcto");
                break;
                
            case ID:
                Id id = (Id) expresion;
                if (id.getEsPuntero()) return TipoT.INT;
                else return id.getTipoDatos().getTipo();
            
            case ENT:
                return TipoT.INT;
            
            case REAL:
                return TipoT.FLOAT;
            
            case TRUE:
                return TipoT.BOOL;
            
            case FALSE:
                return TipoT.BOOL;
            
            case CAR:
                return TipoT.CHAR;
        
            case ARRAY:
                Array array = (Array) expresion;
                List<Nodo> expresiones = array.getExpresiones();
                if (expresiones.isEmpty()) return TipoT.VECTOR;
                else {
                    E expresionInicial = (E) expresiones.get(0);
                    TipoT tipoArray = comprobacion(expresionInicial);
                    for (int i = 1; i < expresiones.size(); ++i) {
                        E expresionArray = (E) expresiones.get(i);
                        if (comprobacion(expresionArray) != tipoArray) comprueba = false;
                    }
                    if (comprueba) return TipoT.VECTOR;
                    else GestionErroresTiny.errorSemantico(array.getFila(),"array","Los elementos son de distinto tipo");
                }
                break;
          
            case ALLAM:
                ALlamada aLlamada = (ALlamada) expresion;
                DFuncion dFuncion = aLlamada.getFuncion();
                List<Nodo> argumentos = aLlamada.getArgumentos();
                List<Nodo> parametros = dFuncion.getParametros();
                if (argumentos.size() == parametros.size()) {
                    for (int i = 0; i < argumentos.size(); ++i) {
                        E argumento = (E) argumentos.get(i);
                        DParametro dParametro = (DParametro) parametros.get(i);
                        if (comprobacion(argumento) != dParametro.getTipoDatos().getTipo()) comprueba = false;
                    }
                    if (comprueba) {
                        return dFuncion.getTipoDatos().getTipo();
                    }
                    else {
                        GestionErroresTiny.errorSemantico(aLlamada.getFila(),"acceso llamada","El argumento es de distinto tipo");
                        return dFuncion.getTipoDatos().getTipo();
                    }
                }
                else {
                    GestionErroresTiny.errorSemantico(aLlamada.getFila(),"acceso llamada","El numero de argumentos es distinto");
                    return dFuncion.getTipoDatos().getTipo();
                }
            
            case AVECTOR:
                AVector aVector = (AVector) expresion;
                DVector dVector = aVector.getVector();
                if (comprobacion(aVector.getPosicion()) == TipoT.INT) return ((Vector) dVector.getTipoDatos()).getTipoDatos().getTipo();
                else {
                    GestionErroresTiny.errorSemantico(aVector.getFila(),"acceso vector","La posicion no es un entero");
                    return ((Vector) dVector.getTipoDatos()).getTipoDatos().getTipo();
                }
            
            case ACUSTOM:
                ACustom aCustom = (ACustom) expresion;
                // Enum
                if (aCustom.getEnum() != null) {
                    DEnum dEnum = aCustom.getEnum();
                    for (Nodo nodo : dEnum.getIdentificadores()) {
                        Id identificador = (Id) nodo;
                        if (((Id) aCustom.getAtributo()).getNombre().equals(identificador.getNombre())) encuentra = true;
                    }
                    if (encuentra) return TipoT.CUSTOM;
                    else {
                        GestionErroresTiny.errorSemantico(aCustom.getFila(),"acceso custom","El identificador del tipo personal no existe");
                        return TipoT.CUSTOM;
                    }
                }
                // Struct
                else {
                    if (((Id) aCustom.getAtributo()).getTipoDatos() != null ) return ((Id) aCustom.getAtributo()).getTipoDatos().getTipo();
                }
                break;
            
            case APUNT:
                APuntero aPuntero = (APuntero) expresion;
                DPuntero dPuntero = aPuntero.getPuntero();
                return dPuntero.getTipoDatos().getTipo();
           
            case ADIR:
                return TipoT.INT;

            default:
                break;
        }
        return null;
    }
}
   
