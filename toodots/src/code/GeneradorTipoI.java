package code;

import java.util.List;
import java.util.ArrayList;
import ast.*;
import ast.Instrucciones.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.io.*;

public class GeneradorTipoI {
    TablaBloques tabla;
    int numBloques = 1;
    int bloqueActual = 1;
    
    public GeneradorTipoI(TablaBloques tabla) {
        this.tabla = tabla;
    }
    
    public List<String> code(I instruccion) {
        List<String> codigo = new ArrayList<String>();
        
        GeneradorTipoE generadorTipoE = new GeneradorTipoE(tabla);
        GeneradorTipoD generadorTipoD = new GeneradorTipoD(tabla);
        
        switch (instruccion.getTipo()) {
            case BUCLE:
                Bucle bucle = (Bucle) instruccion;
                codigo.add("block");
                codigo.add("loop");
                codigo.addAll(generadorTipoE.code(bucle.getCondicion(),bloqueActual));
                
                numBloques++;
                bloqueActual = numBloques;
                
                codigo.add("i32.eqz");
                codigo.add("br_if 1");
                for (Nodo nodo : bucle.getInstrucciones()) {
                    I instruccionBucle = (I) nodo;
                    codigo.addAll(code(instruccionBucle));
                }
                codigo.add("br 0");
                codigo.add("end");
                codigo.add("end");
                
                bloqueActual = tabla.getPosicionBloquePadre(bloqueActual);
                break;
            
            case ASIGN:
                Asignacion asignacion = (Asignacion) instruccion;
                if (asignacion.getId() != null) {
                    codigo.addAll(generadorTipoD.code(asignacion.getId(),bloqueActual));
                }
                else {
                    AVector aVector = (AVector) asignacion.getAcceso();
                    int tamDatos = 0;
                    switch(((Vector) aVector.getVector().getTipoDatos()).getTipoDatos().getTipo()) {
                        case INT,FLOAT:
                            tamDatos = 4;
                            break;
                        case BOOL,CHAR:
                            tamDatos = 1;
                            break;
                        default: break;
                    }
                    codigo.addAll(generadorTipoD.code(aVector.getVector().getId(),aVector.getPosicion(),tamDatos,bloqueActual));
                }
                codigo.addAll(generadorTipoE.code(asignacion.getExp(),bloqueActual));
                codigo.add("i32.store");
                break;
            
            case CONDBAS:
                CondBasica condBasica = (CondBasica) instruccion;
                codigo.addAll(generadorTipoE.code(condBasica.getCondicion(),bloqueActual));
                
                numBloques++;
                bloqueActual = numBloques;
                
                codigo.add("if");
                for (Nodo nodo : condBasica.getInstrucciones()) {
                    I instruccionCond = (I) nodo;
                    codigo.addAll(code(instruccionCond));
                }
                codigo.add("end");
                
                bloqueActual = tabla.getPosicionBloquePadre(bloqueActual);
                break;
                
            case CONDCOM:
                CondCompleja condCompleja = (CondCompleja) instruccion;
                
                codigo.addAll(generadorTipoE.code(condCompleja.getCondicion(),bloqueActual));
                
                numBloques++;
                bloqueActual = numBloques;
                
                codigo.add("if");
                for (Nodo nodo : condCompleja.getInstrucciones1()) {
                    I instruccionCond = (I) nodo;
                    codigo.addAll(code(instruccionCond));
                }
                
                numBloques++;
                bloqueActual = numBloques;
                
                codigo.add("else");
                for (Nodo nodo : condCompleja.getInstrucciones2()) {
                    I instruccionCond = (I) nodo;
                    codigo.addAll(code(instruccionCond));
                }
                codigo.add("end");
                
                bloqueActual = tabla.getPosicionBloquePadre(bloqueActual);
                break;
                
            case LLAM:
                Llamada llamada = (Llamada) instruccion;
                break;
            
            case DVAR:
                DVariable dVariable = (DVariable) instruccion;
                E exp = dVariable.getExp();
                if (exp != null) {
                    codigo.addAll(generadorTipoD.code(dVariable.getId(),bloqueActual));
                    codigo.addAll(generadorTipoE.code(exp,bloqueActual));
                    codigo.add("i32.store");
                }
                break;
            
            case DSTRUCT:
                DStruct dStruct = (DStruct) instruccion;
                break;
            
            case DENUM:
                break;
            
            case DVECTOR:
                DVector dVector = (DVector) instruccion;
                if (dVector.getContenido() != null) {
                    int tamDatos = 0, iterador = 0;
                    switch(((Vector) dVector.getTipoDatos()).getTipoDatos().getTipo()) {
                        case INT,FLOAT:
                            tamDatos = 4;
                            break;
                        case BOOL,CHAR:
                            tamDatos = 1;
                            break;
                        default: break;
                    }
                    for (Nodo nodoVector : dVector.getContenido()) {
                        codigo.addAll(generadorTipoD.code(dVector.getId(),iterador,bloqueActual));
                        codigo.addAll(generadorTipoE.code((E) nodoVector,bloqueActual));
                        codigo.add("i32.store");
                        iterador += tamDatos;
                    }
                }
                break;
            
            case DPUNT:
                DPuntero dPuntero = (DPuntero) instruccion;
                break;
            
            case DFUN:
                DFuncion dFuncion = (DFuncion) instruccion;
                break;
            
            case DPARAM:
                break;
                
            case PRINT:
                Imprimir imprimir = (Imprimir) instruccion;
                codigo.addAll(generadorTipoE.code(imprimir.getExp(),bloqueActual));
                codigo.add("call $print");
                break;
                
            default: break;
        }
        return codigo;
    }
    
}
