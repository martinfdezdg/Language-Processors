package code;

import ast.*;
import ast.Instrucciones.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.FileNotFoundException;

public class GeneradorCodigo {
    private final String cabecera = "code/Cabecera.txt";
    private final String cabeceraFuncion = "code/CabeceraFuncion.txt";
    private final String reserveStack = "code/reserveStack.txt";
    private final String freeStack = "code/freeStack.txt";
    
    private String nombreFicheroCodigo;
    private List<String> codigo;
    private File ficheroCodigo;
    private P programa;
    
    private TablaBloques tabla;
    
    public GeneradorCodigo(P programa, String nombreFicheroCodigo) throws IOException {
        this.programa = programa;
        this.nombreFicheroCodigo = nombreFicheroCodigo;
        ficheroCodigo = new File("../tests/codigo/" + nombreFicheroCodigo + "/" + nombreFicheroCodigo + ".wat");
        this.codigo = new ArrayList<>();
    }
    
    public void parse() throws IOException {
        List<Nodo> objetos = programa.getObjetos();
        List<Nodo> proceso = programa.getProceso();
        
        // Asignamos etiquetas a las declaraciones
        tabla = new TablaBloques();
        GeneradorEtiquetas generadorEtiquetas = new GeneradorEtiquetas(programa,tabla);
        generadorEtiquetas.etiqueta();
        
        // Maximo funcion
        int memoriaMaxima;
        AtomicInteger c = new AtomicInteger(0);
        AtomicInteger max = new AtomicInteger(0);
        for (Nodo nodo : proceso) memMax(nodo,c,max);
        memoriaMaxima = (max.get() + 2) * 4;
        
        FileWriter escritorFicheroCodigo = new FileWriter(ficheroCodigo);
        BufferedWriter flujoFicheroCodigo = new BufferedWriter(escritorFicheroCodigo);
            
        flujoFicheroCodigo.write(fileToString(cabecera));
        flujoFicheroCodigo.write("(func $process (type $_sig_void)\n");
        flujoFicheroCodigo.write("   (local $localsStart i32)\n");
        flujoFicheroCodigo.write("   i32.const " + memoriaMaxima + "\n");
        flujoFicheroCodigo.write(fileToString(cabeceraFuncion));
            
        // Traducimos las instrucciones a codigo wat
        GeneradorTipoI generadorTipoI = new GeneradorTipoI(tabla);
        for (Nodo nodo : objetos) {
            I instruccion = (I) nodo;
            codigo.addAll(generadorTipoI.code(instruccion));
        }
        for (Nodo nodo : proceso) {
            I instruccion = (I) nodo;
            codigo.addAll(generadorTipoI.code(instruccion));
        }
        for (String linea : codigo) {
            flujoFicheroCodigo.write("   " + linea + "\n");
        }
            
        flujoFicheroCodigo.write("   call $freeStack");
        flujoFicheroCodigo.write("\n)\n");
            
        flujoFicheroCodigo.write(fileToString(reserveStack));
        flujoFicheroCodigo.write(fileToString(freeStack));
        flujoFicheroCodigo.write(")");
            
        flujoFicheroCodigo.close();
    }
    
    
    private void memMax(Nodo nodo, AtomicInteger c, AtomicInteger max) {
        I instruccion = (I) nodo;
        AtomicInteger c1 = new AtomicInteger(0);
        AtomicInteger max1 = new AtomicInteger(0);
        
        switch (instruccion.getTipo()) {
            case BUCLE:
                Bucle bucle = (Bucle) instruccion;
                for (Nodo nodoBucle : bucle.getInstrucciones()) {
                    memMax(nodoBucle,c1,max1);
                    
                }
                if (c.get() + max1.get() > max.get()) {
                    max.set(c.get() + max1.get());
                }
                break;
            
            case CONDBAS:
                CondBasica condBasica = (CondBasica) instruccion;
                for (Nodo nodoCond : condBasica.getInstrucciones()) {
                    memMax(nodoCond,c1,max1);
                }
                if (c.get() + max1.get() > max.get()) {
                    max.set(c.get() + max1.get());
                }
                break;

            case CONDCOM:
                CondCompleja condCompleja = (CondCompleja) instruccion;
                for (Nodo nodoCond : condCompleja.getInstrucciones1()) {
                    memMax(nodoCond,c1,max1);
                }
                if (c.get() + max1.get() > max.get()) {
                    max.set(c.get() + max1.get());
                }
                c1.set(0);
                max1.set(0);
                for (Nodo nodoCond : condCompleja.getInstrucciones2()) {
                    memMax(nodoCond,c1,max1);
                }
                if (c.get() + max1.get() > max.get()) {
                    max.set(c.get() + max1.get());
                }
                break;
            
            case DVAR:
                DVariable dVariable = (DVariable) instruccion;
                switch(dVariable.getTipoDatos().getTipo()) {
                    case INT,FLOAT:
                        c.addAndGet(4);
                        break;
                    case BOOL,CHAR:
                        c.addAndGet(1);
                       break;
                    default: break;
                }
                if (c.get()>max.get()) max.set(c.get());
                break;
            
//            case DSTRUCT:
//                DStruct dStruct = (DStruct) instruccion;
//                for (Nodo nodoStruct : dStruct.getAtributos()) {
//                    memMax(nodoStruct,c,max);
//                }
//                break;
//
//            case DENUM:
//                break;

            case DVECTOR:
                DVector dVector = (DVector) instruccion;
                if (dVector.getContenido() != null) {
                    int tam = dVector.getContenido().size();
                    switch(((Vector) dVector.getTipoDatos()).getTipoDatos().getTipo()) {
                        case INT,FLOAT:
                            c.addAndGet(4*tam);;
                            break;
                        case BOOL,CHAR:
                            c.addAndGet(tam);
                           break;
                        default: break;
                    }
                }
                if (c.get()>max.get()) max.set(c.get());
                break;
//
//            case DPUNT:
//                c += 1;
//                max += 1;
//                break;
//
//            case DFUN:
//                DFuncion dFuncion = (DFuncion) instruccion;
//                for (Nodo nodoFuncion : dFuncion.getParametros()) {
//                    memMax(nodoFuncion,c,max);
//                }
//                c1 = 0;
//                max1 = 0;
//                for (Nodo nodoFuncion : dFuncion.getInstrucciones()) {
//                    memMax(nodoFuncion,c1,max1);
//                    if (c+max1 > max) {
//                        max = c + max1;
//                    }
//                }
//                break;
//
//            case DPARAM:
//                DParametro dParametro = (DParametro) instruccion;
//                switch(dParametro.getTipoDatos().getTipo()) {
//                    case INT:
//                        c += 4;
//                        max += 4;
//                        break;
//                    case FLOAT:
//                        c += 4;
//                        max += 4;
//                       break;
//                    case BOOL:
//                        c += 1;
//                        max += 1;
//                       break;
//                    case CHAR:
//                        c += 1;
//                        max += 1;
//                        break;
//                    default: break;
//                }
//                break;
                
            default: break;
        }
    }
    
    public String fileToString(String nombreFichero) {
        String fichero = "";
        
        try {
            BufferedReader flujoFichero = new BufferedReader(new InputStreamReader(new FileInputStream(nombreFichero)));
            
            String lineaFichero = flujoFichero.readLine();
            while (lineaFichero != null) {
                fichero += lineaFichero + "\n";
                lineaFichero = flujoFichero.readLine();
            }
            
            flujoFichero.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return fichero;
    }
}
