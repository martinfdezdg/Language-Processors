package asem;

import ast.*;
import ast.Instrucciones.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import errors.GestionErroresTiny;
import java.util.List;
import java.util.ArrayList;

public class VinculadorTipoE {
    private TablaSimbolos tabla;
    
    public VinculadorTipoE(TablaSimbolos tabla) {
        this.tabla = tabla;
    }
    
    public void vinculacion(Nodo nodoEntrada) {
        E expresion = (E) nodoEntrada;
        
        switch (expresion.getTipo()) {
            case OR:
                EBin or = (EBin) expresion;
                vinculacion(or.getOpnd1());
                vinculacion(or.getOpnd2());
                break;
            
            case AND:
                EBin and = (EBin) expresion;
                vinculacion(and.getOpnd1());
                vinculacion(and.getOpnd2());
                break;
            
            case IGUAL:
                EBin igual = (EBin) expresion;
                vinculacion(igual.getOpnd1());
                vinculacion(igual.getOpnd2());
                break;
            
            case DISTINTO:
                EBin distinto = (EBin) expresion;
                vinculacion(distinto.getOpnd1());
                vinculacion(distinto.getOpnd2());
                break;
                
            case MENOR:
                EBin menor = (EBin) expresion;
                vinculacion(menor.getOpnd1());
                vinculacion(menor.getOpnd2());
                break;
                
            case MAYOR:
                EBin mayor = (EBin) expresion;
                vinculacion(mayor.getOpnd1());
                vinculacion(mayor.getOpnd2());
                break;
                
            case MENORIGUAL:
                EBin menorigual = (EBin) expresion;
                vinculacion(menorigual.getOpnd1());
                vinculacion(menorigual.getOpnd2());
                break;
                
            case MAYORIGUAL:
                EBin mayorigual = (EBin) expresion;
                vinculacion(mayorigual.getOpnd1());
                vinculacion(mayorigual.getOpnd2());
                break;
                
            case SUMA:
                EBin suma = (EBin) expresion;
                vinculacion(suma.getOpnd1());
                vinculacion(suma.getOpnd2());
                break;
                
            case RESTA:
                EBin resta = (EBin) expresion;
                vinculacion(resta.getOpnd1());
                vinculacion(resta.getOpnd2());
                break;
                
            case MULT:
                EBin mult = (EBin) expresion;
                vinculacion(mult.getOpnd1());
                vinculacion(mult.getOpnd2());
                break;
                
            case DIV:
                EBin div = (EBin) expresion;
                vinculacion(div.getOpnd1());
                vinculacion(div.getOpnd2());
                break;
                
            case MOD:
                EBin mod = (EBin) expresion;
                vinculacion(mod.getOpnd1());
                vinculacion(mod.getOpnd2());
                break;
                
            case NOT:
                EUn not = (EUn) expresion;
                vinculacion(not.getOpnd());
                break;
                
            case ID:
                Id id = (Id) expresion;
                Nodo rId = tabla.buscaId(id.getNombre());
                if (rId != null) {
                    I instruccion = (I) rId;
                    switch(instruccion.getTipo()) {
                        case DVAR:
                            id.setTipoDatos(((DVariable) instruccion).getTipoDatos());
                            break;
                        case DSTRUCT:
                            id.setTipoDatos(((DStruct) instruccion).getTipoDatos());
                            break;
                        case DENUM:
                            id.setTipoDatos(((DEnum) instruccion).getTipoDatos());
                            break;
                        case DVECTOR:
                            id.setTipoDatos(((DVector) instruccion).getTipoDatos());
                            break;
                        case DPUNT:
                            id.setTipoDatos(((DPuntero) instruccion).getTipoDatos());
                            id.setEsPuntero();
                            break;
                        case DFUN:
                            id.setTipoDatos(((DFuncion) instruccion).getTipoDatos());
                            break;
                        case DPARAM:
                            id.setTipoDatos(((DParametro) instruccion).getTipoDatos());
                        default:
                            break;
                    }
                }
                else GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),id.getNombre(),"El identificador no habia sido declarado");
                break;
                
            /*
            case ENT:
                break;
            
            case REAL:
                break;
            
            case TRUE:
                break;
            
            case FALSE:
                break;
            
            case CAR:
                break;
             */
        
            case ARRAY:
                Array array = (Array) expresion;
                for (Nodo nodo : array.getExpresiones()) {
                    E expresionArray = (E) nodo;
                    vinculacion(expresionArray);
                }
                break;
          
            case ALLAM:
                ALlamada aLlamada = (ALlamada) expresion;
                Nodo rLlamada = tabla.buscaId(aLlamada.getNombre());
                I dLlamada = (I) rLlamada;
                if (dLlamada == null) {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aLlamada.getNombre(),"Llamada a una funcion no declarada");
                }
                else if (dLlamada.getTipo() == TipoI.DFUN) {
                    aLlamada.setFuncion((DFuncion) dLlamada);
                    for (Nodo nodo : aLlamada.getArgumentos()){
                        E argumento = (E) nodo;
                        vinculacion(argumento);
                    }
                }
                else {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aLlamada.getNombre(),"El identificador no corresponde con una funcion");
                }
                break;
            
            case AVECTOR:
                AVector aVector = (AVector) expresion;
                if (aVector.getNombre() != null) {
                    Nodo rVector = tabla.buscaId(aVector.getNombre());
                    I dVector = (I) rVector;
                    if (dVector == null) {
                        GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aVector.getNombre(),"Acceso a vector no declarado");
                    }
                    else if (dVector.getTipo() == TipoI.DVECTOR) {
                        aVector.setVector((DVector) dVector);
                        vinculacion(aVector.getPosicion());
                    }
                    else if (dVector.getTipo() == TipoI.DPARAM) {
                        DParametro dParametro = (DParametro) dVector;
                        aVector.setVector(new DVector(dParametro.getId(),dParametro.getTipoDatos(),dParametro.getFila(),dParametro.getColumna()));
                        vinculacion(aVector.getPosicion());
                    }
                    else {
                        GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aVector.getNombre(),"El identificador no corresponde a un vector");
                    }
                }
                else {
                    ACustom accesoCustom = (ACustom) aVector.getAcceso();
                    Nodo rVVector = tabla.buscaId(accesoCustom.getNombre());
                    I dVVector = (I) rVVector;
                    if (dVVector == null) {
                        GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),accesoCustom.getNombre(),"Acceso a tipo personal no declarado");
                    }
                    else if (dVVector.getTipo() != TipoI.DVAR) {
                        GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),accesoCustom.getNombre(),"Acceso a variable no accesible");
                    }
                    else {
                        Nodo rVector = tabla.buscaId(((DVariable) dVVector).getTipoDatos().getNombre());
                        I dVector = (I) rVector;
                        if (dVector == null) {
                            GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),accesoCustom.getNombre(),"Acceso a vector del tipo personal no declarado");
                        }
                        else {
                            I atributoAcceso = null;
                            for (Nodo nodo : ((DStruct) dVector).getAtributos()) {
                                I atributo = (I) nodo;
                                if (atributo.getTipo() == TipoI.DVECTOR) {
                                    if (accesoCustom.getAtributo().getNombre().equals(((DVector) atributo).getId().getNombre())) {
                                        atributoAcceso = atributo;
                                        accesoCustom.getAtributo().setTipoDatos(((Vector) ((DVector) atributoAcceso).getTipoDatos()).getTipoDatos());
                                    }
                                }
                            }
                            if (atributoAcceso == null)
                                GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),accesoCustom.getAtributo().getNombre(),"El identificador no corresponde con ninguna declaracion del tipo personal");
                            else {
                                aVector.setVector((DVector) atributoAcceso);
                                vinculacion(aVector.getAcceso());
                                vinculacion(aVector.getPosicion());
                            }
                        }
                    }
                }
                break;
            
            case ACUSTOM:
                ACustom aCustom = (ACustom) expresion;
                if (aCustom.getNombre() != null) {
                    Nodo rVCustom = tabla.buscaId(aCustom.getNombre());
                    I dVCustom = (I) rVCustom;
                    if (rVCustom == null || (dVCustom.getTipo() == TipoI.DVAR && ((DVariable) dVCustom).getTipoDatos().getNombre() == null)) {
                        GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aCustom.getNombre(),"El identificador no corresponde con ninguna declaracion del tipo personal");
                    }
                    else {
                        Nodo rCustom;
                        if (dVCustom.getTipo() == TipoI.DVAR) rCustom = tabla.buscaId(((DVariable) dVCustom).getTipoDatos().getNombre());
                        else rCustom = tabla.buscaId(((DEnum) dVCustom).getTipoDatos().getNombre());
                        I dCustom = (I) rCustom;
                        if (dCustom == null) {
                            GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aCustom.getNombre(),"Acceso a tipo personal no declarado");
                        }
                        else if (dCustom.getTipo() == TipoI.DSTRUCT) {
                            aCustom.setCustom((DStruct) dCustom);
                            I atributoAcceso = null;
                            for (Nodo nodo : ((DStruct) dCustom).getAtributos()) {
                                I atributo = (I) nodo;
                                if (atributo.getTipo() == TipoI.DVAR) {
                                    if (aCustom.getAtributo().getNombre().equals(((DVariable) atributo).getId().getNombre())) {
                                        atributoAcceso = atributo;
                                        aCustom.getAtributo().setTipoDatos(((DVariable) atributoAcceso).getTipoDatos());
                                    }
                                }
                                else {
                                    if (aCustom.getAtributo().getNombre().equals(((DVector) atributo).getId().getNombre())) {
                                        atributoAcceso = atributo;
                                        aCustom.getAtributo().setTipoDatos(((DVector) atributoAcceso).getTipoDatos());
                                    }
                                }
                            }
                            if (atributoAcceso == null)
                                GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aCustom.getAtributo().getNombre(),"El identificador no corresponde con ninguna declaracion del tipo personal");
                        }
                        else if (dCustom.getTipo() == TipoI.DENUM) {
                            aCustom.setCustom((DEnum) dCustom);
                        }
                        else GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aCustom.getNombre(),"El identificador no corresponde con ninguna declaracion del tipo personal");
                    }
                }
                else {
                    AVector accesoVector = (AVector) aCustom.getAcceso();
                    Nodo rVCustom = tabla.buscaId(accesoVector.getNombre());
                    if (rVCustom != null) {
                        I dVCustom = (I) rVCustom;
                        Nodo rCustom = tabla.buscaId(((Vector) ((DVector) dVCustom).getTipoDatos()).getTipoDatos().getNombre());
                        I dCustom = (I) rCustom;
                        if (dCustom == null) {
                            GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),accesoVector.getNombre(),"Acceso a tipo personal del vector no declarado");
                        }
                        else {
                            I atributoAcceso = null;
                            for (Nodo nodo : ((DStruct) dCustom).getAtributos()) {
                                I atributo = (I) nodo;
                                if (atributo.getTipo() == TipoI.DVAR) {
                                    if (aCustom.getAtributo().getNombre().equals(((DVariable) atributo).getId().getNombre())) {
                                        atributoAcceso = atributo;
                                        aCustom.getAtributo().setTipoDatos(((DVariable) atributoAcceso).getTipoDatos());
                                    }
                                }
                                else {
                                    if (aCustom.getAtributo().getNombre().equals(((DVector) atributo).getId().getNombre())) {
                                        atributoAcceso = atributo;
                                        aCustom.getAtributo().setTipoDatos(((DVector) atributoAcceso).getTipoDatos());
                                    }
                                }
                            }
                            if (atributoAcceso == null)
                                GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),accesoVector.getNombre(),"El identificador no corresponde con ninguna declaracion del tipo personal");
                        }
                    }
                    else GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aCustom.getNombre(),"El identificador no corresponde con ninguna declaracion de vector");
                }
                break;
            
            case APUNT:
                APuntero aPuntero = (APuntero) expresion;
                Nodo rPuntero = tabla.buscaId(aPuntero.getNombre());
                I dPuntero = (I) rPuntero;
                if (dPuntero == null) {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aPuntero.getNombre(),"Acceso a puntero no declarado");
                }
                else if (dPuntero.getTipo() == TipoI.DPUNT) {
                    aPuntero.setPuntero((DPuntero) dPuntero);
                }
                else {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aPuntero.getNombre(),"El identificador no corresponde a un puntero");
                }
                break;
           
            case ADIR:
                ADireccion aDireccion = (ADireccion) expresion;
                Nodo rDireccion = tabla.buscaId(aDireccion.getNombre());
                I dVDireccion = (I) rDireccion;
                if (dVDireccion == null) {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aDireccion.getNombre(),"Acceso a direccion no declarado");
                }
                else if (dVDireccion.getTipo() == TipoI.DVAR) {
                    aDireccion.setVariable((DVariable) dVDireccion);
                }
                else {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),aDireccion.getNombre(),"El identificador no corresponde a una variable simple");
                }
                break;
            
            default:
                break;
        }
    }
}
