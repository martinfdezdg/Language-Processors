package ast.Tipos;

import ast.*;
import ast.Instrucciones.*;
import ast.Expresiones.*;

public class ASTipos {
    
    public T tVoid()
    {return new Tipo(TipoT.VOID);}
    public T tInt()
    {return new Tipo(TipoT.INT);}
    public T tFloat()
    {return new Tipo(TipoT.FLOAT);}
    public T tBool()
    {return new Tipo(TipoT.BOOL);}
    public T tChar()
    {return new Tipo(TipoT.CHAR);}
    
    public T tVector(T tipo)
    {return new Vector(tipo);}
    
    public T tCustom(String id)
    {return new Tipo(TipoT.CUSTOM,id);}
    
}
