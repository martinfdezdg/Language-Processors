package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class ASExpresiones {
    
    public E or(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.OR,vExp1,vExp2,fila,columna);}
    
    public E and(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.AND,vExp1,vExp2,fila,columna);}
    
    public E igual(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.IGUAL,vExp1,vExp2,fila,columna);}
    public E distinto(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.DISTINTO,vExp1,vExp2,fila,columna);}
  
    public E menor(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.MENOR,vExp1,vExp2,fila,columna);}
    public E mayor(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.MAYOR,vExp1,vExp2,fila,columna);}
    public E menorigual(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.MENORIGUAL,vExp1,vExp2,fila,columna);}
    public E mayorigual(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.MAYORIGUAL,vExp1,vExp2,fila,columna);}
  
    public E suma(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.SUMA,vExp1,vExp2,fila,columna);}
    public E resta(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.RESTA,vExp1,vExp2,fila,columna);}
  
    public E multiplicacion(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.MULT,vExp1,vExp2,fila,columna);}
    public E division(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.DIV,vExp1,vExp2,fila,columna);}
    public E modulo(E vExp1, E vExp2, int fila, int columna)
    {return new EBin(TipoE.MOD,vExp1,vExp2,fila,columna);}
  
    public E not(E vExp, int fila, int columna)
    {return new EUn(TipoE.NOT,vExp,fila,columna);}

    public E id(String nombre, int fila, int columna)
    {return new Id(nombre,fila,columna);}
    public E entero(String nombre, int fila, int columna)
    {return new Entero(nombre,fila,columna);}
    public E real(String nombre, int fila, int columna)
    {return new Real(nombre,fila,columna);}
    public E bool(String nombre, int fila, int columna)
    {return new Bool(nombre,fila,columna);}
    public E caracter(String nombre, int fila, int columna)
    {return new Caracter(nombre,fila,columna);}
    public E array(List<Nodo> expresiones, int fila, int columna)
    {return new Array(expresiones,fila,columna);}
    
    public E aCustom(E acceso, Id atributo, int fila, int columna)
    {return new ACustom(acceso,atributo,fila,columna);}
    public E aCustom(String nombre, Id atributo, int fila, int columna)
    {return new ACustom(nombre,atributo,fila,columna);}
    public E aVector(String nombre, E posicion, int fila, int columna)
    {return new AVector(nombre,posicion,fila,columna);}
    public E aVector(E acceso, E posicion, int fila, int columna)
    {return new AVector(acceso,posicion,fila,columna);}
    public E aLlamada(String nombre, List<Nodo> argumentos, int fila, int columna)
    {return new ALlamada(nombre,argumentos,fila,columna);}
    public E aPuntero(String nombre, int fila, int columna)
    {return new APuntero(nombre,fila,columna);}
    public E aDireccion(String nombre, int fila, int columna)
    {return new ADireccion(nombre,fila,columna);}
}
