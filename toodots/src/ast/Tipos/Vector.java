package ast.Tipos;

public class Vector extends T {
    private T tipoDatos;
    
    public Vector(T tipoDatos) {
        this.tipoDatos = tipoDatos;
    }
    
    public T getTipoDatos() {
        return tipoDatos;
    }
    public TipoT getTipo() {
        return TipoT.VECTOR;
    }
    
    public String toString() {
        String str = "Tipo: ";
        switch(tipoDatos.getTipo()) {
            case VOID:
                str += "[Void]";
                break;
            case INT:
                str += "[Int]";
                break;
            case FLOAT:
                str += "[Float]";
                break;
            case CHAR:
                str += "[Char]";
                break;
            case BOOL:
                str += "[Bool]";
                break;
            case CUSTOM:
                str = "Tipo custom: " + "[" + tipoDatos.getNombre() + "]";
                break;
            default:
                str += "Unknown";
                break;
        }
        return str;
    }
}
