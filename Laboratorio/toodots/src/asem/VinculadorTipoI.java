package asem;

import ast.*;
import ast.Instrucciones.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import errors.GestionErroresTiny;
import java.util.List;
import java.util.ArrayList;

public class VinculadorTipoI {
    private TablaSimbolos tabla;
    
    public VinculadorTipoI(TablaSimbolos tabla) {
        this.tabla = tabla;
    }
    
    public void vinculacion(Nodo nodoEntrada) {
        I instruccion = (I) nodoEntrada;
        VinculadorTipoE vinculadorTipoE = new VinculadorTipoE(tabla);
        
        switch(instruccion.getTipo()) {
            case BUCLE:
                Bucle bucle = (Bucle) instruccion;
                vinculadorTipoE.vinculacion(bucle.getCondicion());
                tabla.abreBloque();
                for (Nodo nodo : bucle.getInstrucciones()){
                    I instruccionBucle = (I) nodo;
                    vinculacion(instruccionBucle);
                }
                tabla.cierraBloque();
                break;
            
            case ASIGN:
                Asignacion asignacion = (Asignacion) instruccion;
                if (asignacion.getId() != null) {
                    Nodo nodo = tabla.buscaId(asignacion.getId().getNombre());
                    I instruccionDeclaracion = (I) nodo;
                    if (instruccionDeclaracion == null) {
                        GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),asignacion.getId().getNombre(), "Asignación a variable no declarada");
                    }
                    else if (instruccionDeclaracion.getTipo() == TipoI.DSTRUCT || instruccionDeclaracion.getTipo() == TipoI.DENUM) {
                        GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),asignacion.getId().getNombre(), "Asignación a identificador no asignable");
                    }
                    else {
                        vinculadorTipoE.vinculacion(asignacion.getId());
                    }
                }
                else vinculadorTipoE.vinculacion(asignacion.getAcceso());
                vinculadorTipoE.vinculacion(asignacion.getExp());
                break;
                
            case CONDBAS:
                CondBasica condBasica = (CondBasica) instruccion;
                vinculadorTipoE.vinculacion(condBasica.getCondicion());
                tabla.abreBloque();
                for (Nodo nodo : condBasica.getInstrucciones()){
                    I instruccionCond = (I) nodo;
                    vinculacion(instruccionCond);
                }
                tabla.cierraBloque();
                break;
                
            case CONDCOM:
                CondCompleja condCompleja = (CondCompleja) instruccion;
                vinculadorTipoE.vinculacion(condCompleja.getCondicion());
                tabla.abreBloque();
                for (Nodo nodo : condCompleja.getInstrucciones1()){
                    I instruccionCond = (I) nodo;
                    vinculacion(instruccionCond);
                }
                tabla.cierraBloque();
                tabla.abreBloque();
                for (Nodo nodo : condCompleja.getInstrucciones2()){
                    I instruccionCond = (I) nodo;
                    vinculacion(instruccionCond);
                }
                tabla.cierraBloque();
                break;
                
            case LLAM:
                Llamada llamada = (Llamada) instruccion;
                Nodo rLlamada = tabla.buscaId(llamada.getId().getNombre());
                I dLlamada = (I) rLlamada;
                if (dLlamada == null) {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),llamada.getId().getNombre(),"Llamada a una funcion no declarada");
                }
                else if (dLlamada.getTipo() == TipoI.DFUN) {
                    llamada.setFuncion((DFuncion) dLlamada);
                    for (Nodo nodo : llamada.getArgumentos()){
                        E argumento = (E) nodo;
                        vinculadorTipoE.vinculacion(argumento);
                    }
                }
                else {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),llamada.getId().getNombre(),"El identificador no corresponde con una funcion");
                }
                break;
                
            case DVAR:
                DVariable dVariable = (DVariable) instruccion;
                if (tabla.insertaId(dVariable.getId().getNombre(),dVariable)) {
                    if (dVariable.getTipoDatos().getTipo() == TipoT.CUSTOM) {
                        Tipo tipoVar = (Tipo) dVariable.getTipoDatos();
                        Nodo rTipoVar = tabla.buscaId(tipoVar.getNombre());
                        if (rTipoVar == null) GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),tipoVar.getNombre(),"El tipo de la variable declarada no existe");
                        else if (((I) rTipoVar).getTipo() != TipoI.DENUM && ((I) rTipoVar).getTipo() != TipoI.DSTRUCT) GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),tipoVar.getNombre(),"El tipo de la variable declarada no es valido");
                    }
                    E exp = dVariable.getExp();
                    if (exp != null) {
                        vinculadorTipoE.vinculacion(exp);
                    }
                }
                else {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),dVariable.getId().getNombre(),"El identificador ya habia sido declarado");
                }
                break;
                
            case DSTRUCT:
                DStruct dStruct = (DStruct) instruccion;
                if (tabla.insertaId(dStruct.getId().getNombre(),dStruct)) {
                    tabla.abreBloque();
                    for (Nodo nodo : dStruct.getAtributos()){
                        I atributo = (I) nodo;
                        vinculacion(atributo);
                    }
                    tabla.cierraBloque();
                }
                else {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),dStruct.getId().getNombre(),"El identificador ya habia sido declarado");
                }
                break;
                
            case DENUM:
                DEnum dEnum = (DEnum) instruccion;
                if (tabla.insertaId(dEnum.getId().getNombre(),dEnum)) {
                    for (Nodo nodo : dEnum.getIdentificadores()){
                        Id identificador = (Id) nodo;
                        tabla.abreBloque();
                        if (!tabla.insertaId(identificador.getNombre(),identificador)) {
                            GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),identificador.getNombre(),"El identificador ya habia sido declarado");
                        }
                        tabla.cierraBloque();
                    }
                }
                else {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),dEnum.getId().getNombre(),"El identificador ya habia sido declarado");
                }
                break;
                
            case DVECTOR:
                DVector dVector = (DVector) instruccion;
                if (tabla.insertaId(dVector.getId().getNombre(),dVector)) {
                    E longitud = dVector.getLongitud();
                    if (longitud != null) {
                        vinculadorTipoE.vinculacion(longitud);
                    }
                    List<Nodo> contenido = dVector.getContenido();
                    if (contenido != null) {
                        for (Nodo nodo : contenido){
                            E exp = (E) nodo;
                            vinculadorTipoE.vinculacion(exp);
                        }
                    }
                }
                else {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),dVector.getId().getNombre(),"El identificador ya habia sido declarado");
                }
                break;
                
            case DPUNT:
                DPuntero dPuntero = (DPuntero) instruccion;
                if (tabla.insertaId(dPuntero.getId().getNombre(),dPuntero)) {
                    if (dPuntero.getTipoDatos().getTipo() == TipoT.CUSTOM) {
                        Tipo tipoPunt = (Tipo) dPuntero.getTipoDatos();
                        Nodo rTipoPunt = tabla.buscaId(tipoPunt.getNombre());
                        if (rTipoPunt == null) GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),tipoPunt.getNombre(),"El tipo del puntero declarado no existe");
                        else if (((I) rTipoPunt).getTipo() != TipoI.DENUM && ((I) rTipoPunt).getTipo() != TipoI.DSTRUCT) GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),tipoPunt.getNombre(),"El tipo del puntero declarado no es valido");
                    }
                    E exp = dPuntero.getValor();
                    if (exp != null) {
                        vinculadorTipoE.vinculacion(exp);
                    }
                }
                else {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),dPuntero.getId().getNombre(),"El identificador ya habia sido declarado");
                }
                break;
                
            case DFUN:
                DFuncion dFuncion = (DFuncion) instruccion;
                if (tabla.insertaId(dFuncion.getId().getNombre(),dFuncion)) {
                    tabla.abreBloque();
                    List<Nodo> parametros = dFuncion.getParametros();
                    if (parametros != null) {
                        for (Nodo parametro : parametros){
                            I instruccionParametro = (I) parametro;
                            vinculacion(instruccionParametro);
                        }
                    }
                    for (Nodo nodo : dFuncion.getInstrucciones()){
                        I instruccionFuncion = (I) nodo;
                        vinculacion(instruccionFuncion);
                    }
                    if (dFuncion.getExp() != null) vinculadorTipoE.vinculacion(dFuncion.getExp());
                    tabla.cierraBloque();
                }
                else {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),dFuncion.getId().getNombre(),"El identificador ya habia sido declarado");
                }
                break;
                
            case DPARAM:
                DParametro dParametro = (DParametro) instruccion;
                if (tabla.insertaId(dParametro.getId().getNombre(),dParametro)) {
                    dParametro.getId().setTipoDatos(dParametro.getTipoDatos());
                }
                else {
                    GestionErroresTiny.errorSemantico(nodoEntrada.getFila(),dParametro.getId().getNombre(),"El parametro esta repetido");
                }
                break;
            
            case PRINT:
                Imprimir imprimir = (Imprimir) instruccion;
                vinculadorTipoE.vinculacion(imprimir.getExp());
                break;
                
            default: break;
        }
    }
}
   
