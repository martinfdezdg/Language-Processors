package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class ASInstrucciones {
    
    public I dVariable(Id id, T tipo, int fila, int columna)
    {return new DVariable(id,tipo,fila,columna);}
    public I dVariable(Id id, T tipo, E exp, int fila, int columna)
    {return new DVariable(id,tipo,exp,fila,columna);}
    
    public I dStruct(Id id, List<Nodo> variables, int fila, int columna)
    {return new DStruct(id,variables,fila,columna);}
    
    public I dEnum(Id id, List<Nodo> identificadores, int fila, int columna)
    {return new DEnum(id,identificadores,fila,columna);}
    
    public I dVector(Id id, T tipo, int fila, int columna)
    {return new DVector(id,tipo,fila,columna);}
    public I dVector(Id id, T tipo, E longitud, int fila, int columna)
    {return new DVector(id,tipo,longitud,fila,columna);}
    public I dVector(Id id, T tipo, List<Nodo> contenido, int fila, int columna)
    {return new DVector(id,tipo,contenido,fila,columna);}
    
    public I dPuntero(Id id, T tipo, int fila, int columna)
    {return new DPuntero(id,tipo,fila,columna);}
    public I dPuntero(Id id, T tipo, E exp, int fila, int columna)
    {return new DPuntero(id,tipo,exp,fila,columna);}
    
    public I dFuncion(Id id, T tipo, List<Nodo> parametros, List<Nodo> instrucciones, E exp, int fila, int columna)
    {return new DFuncion(id,tipo,parametros,instrucciones,exp,fila,columna);}
    public I dFuncion(Id id, T tipo, List<Nodo> parametros, List<Nodo> instrucciones, int fila, int columna)
    {return new DFuncion(id,tipo,parametros,instrucciones,fila,columna);}
    
    public I dParametro(Id id, T tipo, int fila, int columna)
    {return new DParametro(id,tipo,fila,columna);}

    public I llamada(Id id, List<Nodo> argumentos, int fila, int columna)
    {return new Llamada(id,argumentos,fila,columna);}
    
    public I asignacion(Id id, E exp, int fila, int columna)
    {return new Asignacion(id,exp,fila,columna);}
    public I asignacion(E acceso, E exp, int fila, int columna)
    {return new Asignacion(acceso,exp,fila,columna);}
    
    public I condBasica(E exp, List<Nodo> instrucciones, int fila, int columna)
    {return new CondBasica(exp,instrucciones,fila,columna);}
    public I condCompleja(E exp, List<Nodo> instrucciones1, List<Nodo> instrucciones2, int fila, int columna)
    {return new CondCompleja(exp,instrucciones1,instrucciones2,fila,columna);}
    
    public I bucle(E exp, List<Nodo> instrucciones, int fila, int columna)
    {return new Bucle(exp,instrucciones,fila,columna);}
    
    public I imprimir(E exp, int fila, int columna)
    {return new Imprimir(exp,fila,columna);}
}
