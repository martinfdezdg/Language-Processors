package asem;

import ast.*;
import ast.Instrucciones.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class TablaSimbolos {
    private List<Map<String,Nodo>> tabla = new ArrayList();
    
    public void abreBloque() {
        tabla.add(new HashMap<>());
    }
    public void cierraBloque() {
        tabla.remove(tabla.size()-1);
    }
    public boolean insertaId(String id, Nodo nodo) {
        if (!tabla.get(tabla.size()-1).containsKey(id)) {
            tabla.get(tabla.size()-1).put(id,nodo);
            return true;
        }
        else return false;
    }
    public Nodo buscaId(String id) {
        for (int i = tabla.size()-1; i >= 0; --i) {
            if (tabla.get(i).containsKey(id)) {
                return tabla.get(i).get(id);
            }
        }
        return null;
    }
}
   
