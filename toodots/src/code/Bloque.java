package code;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Bloque {
    private int posicion; // posicion en el vector de bloques
    private Map<String,Integer> etiquetas;
    private int tam;
    private int direccion;
    private int direccionMarco;
    
    private Bloque bloquePadre;
    
    public Bloque(int posicion, int direccion, Bloque bloquePadre) {
        this.posicion = posicion;
        this.etiquetas = new HashMap<>();
        this.tam = 0;
        this.direccion = direccion;
        this.direccionMarco = direccionMarco;
        this.bloquePadre = bloquePadre;
    }
    
    public void addEtiqueta(String id, int tam) {
        etiquetas.put(id,direccion);
        this.tam += tam;
        direccion += tam;
    }
    
    // SET
    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }
    public void setDireccionSiguiente(int tam) {
        this.direccion += tam;
    }
    
    // GET
    public int getPosicion(){
        return posicion;
    }
    public int getTam() {
        return tam;
    }
    public Integer getDireccion(String id) {
        return etiquetas.get(id);
    }
    public int getDireccionMarco() {
        return direccionMarco;
    }
    public int getDireccionSiguiente() {
        return direccion;
    }
    public Bloque getBloquePadre() {
        return bloquePadre;
    }
    
    // PRINT
    public String toString() {
        return etiquetas.toString();
    }
}
   
