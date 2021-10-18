package ast.Tipos;

public class Tipo extends T {
    private TipoT tipo;
    private String nombre;
    
    public Tipo(TipoT tipo) {
        this.tipo = tipo;
    }
    public Tipo(TipoT tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }
  
    public TipoT getTipo() {
        return tipo;
    }
    public String getNombre() {
        return nombre;
    }
    
    public String toString() {
        String str = "Tipo: ";
        switch(tipo) {
            case VOID:
                str += "Void";
                break;
            case INT:
                str += "Int";
                break;
            case FLOAT:
                str += "Float";
                break;
            case CHAR:
                str += "Char";
                break;
            case BOOL:
                str += "Bool";
                break;
            case CUSTOM:
                str = "Tipo custom: " + nombre;
                break;
            default:
                str += "Unknown";
                break;
        }
        return str;
    }
    
}
