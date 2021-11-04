package code;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class TablaBloques {
    private List<Bloque> tabla = new ArrayList<Bloque>();
    private Bloque bloqueActual;
    
    public TablaBloques() {
        bloqueActual = new Bloque(0,0,null);
        tabla.add(bloqueActual);
    }
    
    public void abreBloque() {
        Bloque bloque = new Bloque(tabla.size(),bloqueActual.getDireccionSiguiente(),bloqueActual);
        tabla.add(bloque);
        bloqueActual = bloque;
    }
    public void cierraBloque() {
        bloqueActual = bloqueActual.getBloquePadre();
    }
    
    public void addEtiqueta(String id, int tam) {
        bloqueActual.addEtiqueta(id,tam);
    }
    
    // GET
    public int getPosicionBloquePadre(int posicionBloque) {
        return tabla.get(posicionBloque).getBloquePadre().getPosicion();
    }
    public int getDireccion(int posicionBloque, String id) {
        Integer direccion = tabla.get(posicionBloque).getDireccion(id);
        int posicionBloqueActual = posicionBloque;
        while (direccion == null) {
            Bloque bloquePadre = tabla.get(posicionBloqueActual).getBloquePadre();
            posicionBloqueActual = bloquePadre.getPosicion();
            direccion = bloquePadre.getDireccion(id);
        }
        return direccion;
    }
    public int getDireccionMarco(int posicionBloque) {
        return tabla.get(posicionBloque).getDireccionMarco();
    }
    
    // PRINT
    public String toString() {
        return tabla.toString();
    }
}
