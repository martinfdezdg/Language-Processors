package code;

import java.util.List;
import java.util.ArrayList;
import ast.*;
import ast.Instrucciones.*;
import ast.Expresiones.*;
import ast.Tipos.*;

public class GeneradorEtiquetas {
    private P programa;
    private TablaBloques tabla;
    
    public GeneradorEtiquetas(P programa, TablaBloques tabla) {
        this.programa = programa;
        this.tabla = tabla;
    }
    
    public void etiqueta() {
        List<Nodo> objetos = programa.getObjetos();
        List<Nodo> proceso = programa.getProceso();
        
        tabla.abreBloque();
        for (Nodo nodo : proceso) {
            I instruccion = (I) nodo;
            etiquetaIntruccion(instruccion);
        }
        tabla.cierraBloque();
    }
    
    private void etiquetaIntruccion(I instruccion) {
        int tam = 0;
        switch (instruccion.getTipo()) {
            case BUCLE:
                Bucle bucle = (Bucle) instruccion;
                tabla.abreBloque();
                for (Nodo nodo : bucle.getInstrucciones()) {
                    I instruccionBucle = (I) nodo;
                    etiquetaIntruccion(instruccionBucle);
                }
                tabla.cierraBloque();
                break;
            
            case CONDBAS:
                CondBasica condBasica = (CondBasica) instruccion;
                tabla.abreBloque();
                for (Nodo nodo : condBasica.getInstrucciones()) {
                    I instruccionCond = (I) nodo;
                    etiquetaIntruccion(instruccionCond);
                }
                tabla.cierraBloque();
                break;
                
            case CONDCOM:
                CondCompleja condCompleja = (CondCompleja) instruccion;
                tabla.abreBloque();
                for (Nodo nodo : condCompleja.getInstrucciones1()) {
                    I instruccionCond = (I) nodo;
                    etiquetaIntruccion(instruccionCond);
                }
                tabla.cierraBloque();
                tabla.abreBloque();
                for (Nodo nodo : condCompleja.getInstrucciones2()) {
                    I instruccionCond = (I) nodo;
                    etiquetaIntruccion(instruccionCond);
                }
                tabla.cierraBloque();
                break;
            
            case DVAR:
                DVariable dVariable = (DVariable) instruccion;
                switch(dVariable.getTipoDatos().getTipo()) {
                    case INT,FLOAT:
                        tam = 4;
                        break;
                    case BOOL,CHAR:
                        tam = 1;
                        break;
                    default: break;
                }
                tabla.addEtiqueta(dVariable.getId().getNombre(),tam);
                break;
            
            case DSTRUCT:
                DStruct dStruct = (DStruct) instruccion;
                break;
            
            case DENUM:
                break;
            
            case DVECTOR:
                DVector dVector = (DVector) instruccion;
                if (dVector.getContenido() != null) {
                    switch(((Vector) dVector.getTipoDatos()).getTipoDatos().getTipo()) {
                        case INT,FLOAT:
                            tam = 4;
                            break;
                        case BOOL,CHAR:
                            tam = 1;
                            break;
                        default: break;
                    }
                    
                    tabla.addEtiqueta(dVector.getId().getNombre(),dVector.getContenido().size()*tam);
                }
                break;
            
            case DPUNT:
                DPuntero dPuntero = (DPuntero) instruccion;
                break;
            
//            case DFUN:
//                DFuncion dFuncion = (DFuncion) instruccion;
//                tabla.abreBloque();
//                for (Nodo nodoParam : dFuncion.getParametros()) etiquetaIntruccion((I) nodoParam);
//                for (Nodo nodoInstruc : dFuncion.getInstrucciones()) etiquetaIntruccion((I) nodoInstruc);
//                tabla.cierraBloque();
//                break;
            
            case DPARAM:
                
                
                break;
                
            default: break;
        }
    }
}
