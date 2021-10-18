package code;

import ast.*;
import ast.Instrucciones.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class GeneradorTipoE {
    TablaBloques tabla;
    
    public GeneradorTipoE(TablaBloques tabla) {
        this.tabla = tabla;
    }
    
    public List<String> code(E expresion, int bloque) {
        List<String> codigo = new ArrayList<String>();
        
        GeneradorTipoD generadorTipoD = new GeneradorTipoD(tabla);
        GeneradorTipoI generadorTipoI = new GeneradorTipoI(tabla);
        
        switch (expresion.getTipo()) {
            case OR:
                EBin or = (EBin) expresion;
                codigo.addAll(code(or.getOpnd1(),bloque));
                codigo.addAll(code(or.getOpnd2(),bloque));
                codigo.add("i32.or");
                break;
            
            case AND:
                EBin and = (EBin) expresion;
                codigo.addAll(code(and.getOpnd1(),bloque));
                codigo.addAll(code(and.getOpnd2(),bloque));
                codigo.add("i32.and");
                break;
            
            case IGUAL:
                EBin igual = (EBin) expresion;
                codigo.addAll(code(igual.getOpnd1(),bloque));
                codigo.addAll(code(igual.getOpnd2(),bloque));
                codigo.add("i32.eq");
                break;
            
            case DISTINTO:
                EBin distinto = (EBin) expresion;
                codigo.addAll(code(distinto.getOpnd1(),bloque));
                codigo.addAll(code(distinto.getOpnd2(),bloque));
                codigo.add("f32.neg");
                break;
                
            case MENOR:
                EBin menor = (EBin) expresion;
                codigo.addAll(code(menor.getOpnd1(),bloque));
                codigo.addAll(code(menor.getOpnd2(),bloque));
                codigo.add("i32.lt_s");
                break;
                
            case MAYOR:
                EBin mayor = (EBin) expresion;
                codigo.addAll(code(mayor.getOpnd1(),bloque));
                codigo.addAll(code(mayor.getOpnd2(),bloque));
                codigo.add("i32.gt_s");
                break;
                
            case MENORIGUAL:
                EBin menorigual = (EBin) expresion;
                codigo.addAll(code(menorigual.getOpnd1(),bloque));
                codigo.addAll(code(menorigual.getOpnd2(),bloque));
                codigo.add("i32.le_s");
                break;
                
            case MAYORIGUAL:
                EBin mayorigual = (EBin) expresion;
                codigo.addAll(code(mayorigual.getOpnd1(),bloque));
                codigo.addAll(code(mayorigual.getOpnd2(),bloque));
                codigo.add("i32.ge_s");
                break;
                
            case SUMA:
                EBin suma = (EBin) expresion;
                codigo.addAll(code(suma.getOpnd1(),bloque));
                codigo.addAll(code(suma.getOpnd2(),bloque));
                codigo.add("i32.add");
                break;
                
            case RESTA:
                EBin resta = (EBin) expresion;
                codigo.addAll(code(resta.getOpnd1(),bloque));
                codigo.addAll(code(resta.getOpnd2(),bloque));
                codigo.add("i32.sub");
                break;
                
            case MULT:
                EBin mult = (EBin) expresion;
                codigo.addAll(code(mult.getOpnd1(),bloque));
                codigo.addAll(code(mult.getOpnd2(),bloque));
                codigo.add("i32.mul");
                break;
                
            case DIV:
                EBin div = (EBin) expresion;
                codigo.addAll(code(div.getOpnd1(),bloque));
                codigo.addAll(code(div.getOpnd2(),bloque));
                codigo.add("i32.div_s");
                break;
                
            case MOD:
                EBin mod = (EBin) expresion;
                codigo.addAll(code(mod.getOpnd1(),bloque));
                codigo.addAll(code(mod.getOpnd2(),bloque));
                codigo.add("i32.rem_u");
                break;
                
            case NOT:
                EUn not = (EUn) expresion;
                // not.getOpnd()
                // ?
                break;
                
            case ID:
                Id id = (Id) expresion;
                codigo.addAll(generadorTipoD.code((Id) expresion,bloque));
                codigo.add("i32.load");
                break;
            
            case ENT:
                codigo.add("i32.const " + ((Entero) expresion).getNombre());
                break;
            
            case REAL:
                // ?
                break;
            
            case TRUE:
                codigo.add("i32.const 1");
                break;
            
            case FALSE:
                codigo.add("i32.const 0");
                break;
            
            case CAR:
                // -
                break;
        
            case ARRAY:
                Array array = (Array) expresion;
                // -
                break;
          
            case ALLAM:
                ALlamada aLlamada = (ALlamada) expresion;
                // -
                break;
            
            case AVECTOR:
                AVector aVector = (AVector) expresion;
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
                codigo.addAll(generadorTipoD.code(aVector.getVector().getId(),aVector.getPosicion(),tamDatos,bloque));
                codigo.add("i32.load");
                break;
            
            case ACUSTOM:
                ACustom aCustom = (ACustom) expresion;
                // -
                break;
            
            case APUNT:
                APuntero aPuntero = (APuntero) expresion;
                // -
                break;
           
            case ADIR:
                // -
                break;

            default:
                break;
        }
        return codigo;
    }
}
