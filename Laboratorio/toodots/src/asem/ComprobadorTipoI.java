package asem;

import ast.*;
import ast.Instrucciones.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import errors.GestionErroresTiny;
import java.util.List;
import java.util.ArrayList;

public class ComprobadorTipoI {
    
    public boolean comprobacion(I instruccion) {
        ComprobadorTipoE comprobadorTipoE = new ComprobadorTipoE();
        
        boolean comprueba = true;
        Id id;
        TipoT tipoDatos, tipoExp, tipoAcceso;
        DFuncion dFuncion;
        
        switch (instruccion.getTipo()) {
            case BUCLE:
                Bucle bucle = (Bucle) instruccion;
                tipoExp = comprobadorTipoE.comprobacion(bucle.getCondicion());
                if (tipoExp != null && tipoExp == TipoT.BOOL) {
                    for (Nodo nodo : bucle.getInstrucciones()) {
                        I instruccionBucle = (I) nodo;
                        if (!comprobacion(instruccionBucle)) comprueba = false;
                    }
                    return comprueba;
                }
                else GestionErroresTiny.errorSemantico(bucle.getFila(),"bucle","La condicion del bucle no es el tipo correcto");
                break;
            
            case ASIGN:
                Asignacion asignacion = (Asignacion) instruccion;
                id = asignacion.getId();
                E acceso = asignacion.getAcceso();
                E exp = asignacion.getExp();
                // Variable
                if (id != null) {
                    TipoT tipoId = null;
                    if (id.getEsPuntero()) tipoId = TipoT.INT;
                    else if (id.getTipoDatos() != null) tipoId = id.getTipoDatos().getTipo();
                    if (tipoId != null) {
                        tipoExp = comprobadorTipoE.comprobacion(exp);
                        if (tipoExp != null && tipoId == tipoExp) {
                            if (tipoId != TipoT.VECTOR) return true;
                            else {
                                
                                // Vector
                                if (exp.getTipo() == TipoE.ID && ((Vector) id.getTipoDatos()).getTipoDatos() == ((Vector) ((Id) exp).getTipoDatos()).getTipoDatos()) return true;
                                else if (exp.getTipo() == TipoE.ARRAY && ((Array) exp).getExpresiones().isEmpty()) return true;
                                else if (exp.getTipo() == TipoE.ARRAY && ((Vector) id.getTipoDatos()).getTipoDatos().getTipo() == comprobadorTipoE.comprobacion(((E)((Array) exp).getExpresiones().get(0)))) return true;
                                // Llamada a funcion que devuelve vector
                                else if (exp.getTipo() == TipoE.ALLAM && ((Vector) id.getTipoDatos()).getTipoDatos() == ((Vector) ((Id) ((ALlamada) exp).getFuncion().getId()).getTipoDatos()).getTipoDatos()) return true;
                                else GestionErroresTiny.errorSemantico(asignacion.getFila(),"asignacion","Los tipos de los vectores no son correctos");
                            }
                        }
                        else GestionErroresTiny.errorSemantico(asignacion.getFila(),"asignacion","Los tipos no son correctos");
                    }
                    else GestionErroresTiny.errorSemantico(asignacion.getFila(),"asignacion","El identificador no es correcto");
                }
                // Vector o struct
                else {
                    tipoAcceso = comprobadorTipoE.comprobacion(acceso);
                    tipoExp = comprobadorTipoE.comprobacion(exp);
                    if (tipoAcceso != null && tipoExp != null && tipoAcceso == tipoExp) {
                        if (tipoAcceso != TipoT.CUSTOM) return true;
                        else {
                            if (acceso.getTipo() == TipoE.AVECTOR && ((ACustom) ((AVector) acceso).getAcceso()).getNombre().equals(((ACustom) exp).getNombre())) return true;
                            else if (((ACustom) acceso).getNombre().equals(((ACustom) exp).getNombre())) return true;
                            else if (((ACustom) acceso).getAtributo().getTipoDatos().getTipo() == TipoT.CUSTOM && ((ACustom) acceso).getAtributo().getTipoDatos().getNombre().equals(((ACustom) exp).getNombre())) return true;
                            else GestionErroresTiny.errorSemantico(asignacion.getFila(),"asignacion","Los tipos no son correctos");
                        }
                    }
                    else GestionErroresTiny.errorSemantico(asignacion.getFila(),"asignacion","Los tipos no son correctos");
                }
                break;
            
            case CONDBAS:
                CondBasica condBasica = (CondBasica) instruccion;
                tipoExp = comprobadorTipoE.comprobacion(condBasica.getCondicion());
                if (tipoExp != null && tipoExp == TipoT.BOOL) {
                    for (Nodo nodo : condBasica.getInstrucciones()) {
                        I instruccionCond = (I) nodo;
                        if (!comprobacion(instruccionCond)) comprueba = false;
                    }
                    return comprueba;
                }
                else GestionErroresTiny.errorSemantico(condBasica.getFila(),"condicional","La condicion del condicional no es el tipo correcto");
                break;
                
            case CONDCOM:
                CondCompleja condCompleja = (CondCompleja) instruccion;
                tipoExp = comprobadorTipoE.comprobacion(condCompleja.getCondicion());
                if (tipoExp != null && tipoExp == TipoT.BOOL) {
                    for (Nodo nodo : condCompleja.getInstrucciones1()) {
                        I instruccionCond = (I) nodo;
                        if (!comprobacion(instruccionCond)) comprueba = false;
                    }
                    for (Nodo nodo : condCompleja.getInstrucciones2()) {
                        I instruccionCond = (I) nodo;
                        if (!comprobacion(instruccionCond)) comprueba = false;
                    }
                    return comprueba;
                }
                else GestionErroresTiny.errorSemantico(condCompleja.getFila(),"condicional","La condicion del condicional no es el tipo correcto");
                break;
                
            case LLAM:
                Llamada llamada = (Llamada) instruccion;
                id = llamada.getId();
                dFuncion = llamada.getFuncion();
                if (dFuncion.getTipoDatos().getTipo() == TipoT.VOID) {
                    List<Nodo> argumentos = llamada.getArgumentos();
                    List<Nodo> parametros = dFuncion.getParametros();
                    if (argumentos.size() == parametros.size()) {
                        for (int i = 0; i < argumentos.size(); ++i) {
                            E argumento = (E) argumentos.get(i);
                            DParametro dParametro = (DParametro) parametros.get(i);
                            tipoExp = comprobadorTipoE.comprobacion(argumento);
                            if (tipoExp != null && tipoExp != dParametro.getTipoDatos().getTipo()) comprueba = false;
                        }
                        if (comprueba) return true;
                        else GestionErroresTiny.errorSemantico(llamada.getFila(),"llamada","El argumento es de distinto tipo");
                    }
                    else GestionErroresTiny.errorSemantico(llamada.getFila(),"llamada","El numero de argumentos es distinto");
                }
                else GestionErroresTiny.errorSemantico(llamada.getFila(),"llamada","El tipo de la llamada no es Void");
                break;
            
            case DVAR:
                DVariable dVariable = (DVariable) instruccion;
                // Sin valor inicial
                if (dVariable.getExp() == null) {
                    return true;
                }
                // Con valor inicial
                else {
                    tipoExp = comprobadorTipoE.comprobacion(dVariable.getExp());
                    if (tipoExp != null && dVariable.getTipoDatos().getTipo() == tipoExp) {
                        if (dVariable.getTipoDatos().getTipo() != TipoT.CUSTOM) return true;
                        // Si son tipos personalizados hay que comprobar que el nombre coincide
                        else {
                            if (dVariable.getExp().getTipo() == TipoE.ID) {
                                if (dVariable.getTipoDatos().getNombre().equals(((Id) dVariable.getExp()).getTipoDatos().getNombre())) return true;
                                else GestionErroresTiny.errorSemantico(dVariable.getFila(),"declaracion de variable","El tipo de la declaracion no es correcto");
                            }
                            else {
                                if (dVariable.getTipoDatos().getNombre().equals(((ACustom) dVariable.getExp()).getNombre())) return true;
                                else GestionErroresTiny.errorSemantico(dVariable.getFila(),"declaracion de variable","El tipo de la declaracion no es correcto");
                            }
                        }
                    }
                    else GestionErroresTiny.errorSemantico(dVariable.getFila(),"declaracion de variable","El tipo de la declaracion no es correcto");
                }
                break;
            
            case DSTRUCT:
                DStruct dStruct = (DStruct) instruccion;
                for (Nodo nodo : dStruct.getAtributos()) {
                    I instruccionStruct = (I) nodo;
                    if (!comprobacion(instruccionStruct)) comprueba = false;
                }
                if (comprueba) return true;
                else GestionErroresTiny.errorSemantico(dStruct.getFila(),"declaracion de struct","Los atributos del Struct no son correctos");
                break;
            
            case DENUM:
                return true;
            
            case DVECTOR:
                DVector dVector = (DVector) instruccion;
                tipoDatos = ((Vector) dVector.getTipoDatos()).getTipoDatos().getTipo();
                // Vector con longitud y sin valor inicial
                if (dVector.getLongitud() != null && dVector.getContenido() == null) {
                    tipoExp = comprobadorTipoE.comprobacion(dVector.getLongitud());
                    if (tipoExp != null && tipoExp == TipoT.INT) return true;
                    else GestionErroresTiny.errorSemantico(dVector.getFila(),"declaracion de vector","La longitud no es un entero");
                }
                // Vector sin longitud y con valor inicial
                else if (dVector.getContenido() != null) {
                    for (Nodo nodo : dVector.getContenido()) {
                        E expresionVector = (E) nodo;
                        tipoExp = comprobadorTipoE.comprobacion(expresionVector);
                        if (tipoExp != null && tipoExp != tipoDatos) comprueba = false;
                    }
                    if (comprueba) return true;
                    else GestionErroresTiny.errorSemantico(dVector.getFila(),"declaracion de vector","El contenido del vector no es del tipo correcto");
                }
                else return true;
                break;
            
            case DPUNT:
                DPuntero dPuntero = (DPuntero) instruccion;
                if (dPuntero.getValor() == null) return true;
                else {
                    tipoExp = comprobadorTipoE.comprobacion(dPuntero.getValor());
                    tipoDatos = dPuntero.getTipoDatos().getTipo();
                    if (tipoExp != null && tipoDatos != tipoExp) {
                        GestionErroresTiny.errorSemantico(dPuntero.getFila(),"declaracion de puntero","El puntero no es del tipo correcto");
                    }
                }
                break;
            
            case DFUN:
                dFuncion = (DFuncion) instruccion;
                tipoDatos = dFuncion.getTipoDatos().getTipo();
                if (dFuncion.getExp() == null) {
                    if (tipoDatos != TipoT.VOID) {
                        GestionErroresTiny.errorSemantico(dFuncion.getFila(),"declaracion de funcion","La funcion no deberia devolver ningun tipo");
                        return false;
                    }
                }
                else {
                    tipoExp = comprobadorTipoE.comprobacion(dFuncion.getExp());
                    if (tipoExp != null && tipoDatos != tipoExp) {
                        GestionErroresTiny.errorSemantico(dFuncion.getFila(),"declaracion de funcion","El tipo de la funcion no corresponde con el tipo que devuelve");
                        return false;
                    }
                }
                for (Nodo nodo : dFuncion.getInstrucciones()) {
                    I instruccionFun = (I) nodo;
                    if (!comprobacion(instruccionFun)) comprueba = false;
                }
                return comprueba;
            
            case DPARAM:
                return true;
            
            case PRINT:
                Imprimir imprimir = (Imprimir) instruccion;
                tipoExp = comprobadorTipoE.comprobacion(imprimir.getExp());
                if (tipoExp == TipoT.INT || tipoExp == TipoT.FLOAT || tipoExp == TipoT.BOOL || tipoExp == TipoT.CHAR) return true;
                else GestionErroresTiny.errorSemantico(imprimir.getFila(),"imprimir","El tipo de la expresion a imprimir no es basico");
                break;
                
            default: break;
        }
        return false;
    }
}
   
