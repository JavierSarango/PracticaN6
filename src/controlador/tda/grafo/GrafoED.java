/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.tda.grafo;

import controlador.tda.grafo.exception.VerticeException;
import controlador.tda.lista.ListaEnlazada;
import java.util.HashMap;

/**
 *
 * @author javisarango
 */
public class GrafoED<E> extends GrafoD {

    protected Class<E> clazz;
    protected E etiquetas[];
    protected HashMap<E, Integer> dirVertices;

    public GrafoED(Integer numV, Class clazz) {
        super(numV);
        this.clazz = clazz;
        etiquetas = (E[]) java.lang.reflect.Array.newInstance(this.clazz, numV + 1);
        dirVertices = new HashMap<>(numV);

    }
    public Boolean modificar(E anterior, E nuevo) throws Exception{
    Integer pos = obtenerCodigo(anterior);
    etiquetas[pos] = nuevo;
    dirVertices.remove(anterior);
    dirVertices.put(nuevo, pos);
    return  true;
    }
    public Object[] existeArista(E i, E j) throws Exception {
        return this.existeArista(obtenerCodigo(i), obtenerCodigo(j));
    }

    public void insertarArista(E i, E j, Double peso) throws Exception {
        this.insertarArista(obtenerCodigo(i), obtenerCodigo(j), Double.NaN);

    }

    public Integer obtenerCodigo(E etiqueta) throws Exception {
        Integer key = dirVertices.get(etiqueta);
        if (key != null) {
            return key;
        } else {
            throw new VerticeException("No se encuentra la etiqueta Correspondiente");
        }

    }

    public E obtenerEtiqueta(Integer codigo) throws Exception {
        return etiquetas[codigo];

    }

    public ListaEnlazada<Adyacencia> adyacentesDEE(E i) throws Exception {
        return adyacentes(obtenerCodigo(i));

    }

    public void etiquetarVertice(Integer codigo, E etiqueta) {
        etiquetas[codigo] = etiqueta;
        dirVertices.put(etiqueta, codigo);

    }

    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder();
        try {
            for (int i = 1; i <= numVertices(); i++) {
                grafo.append("VERTICE " + i + "- E - " + obtenerEtiqueta(i).toString());
                try {
                    ListaEnlazada<Adyacencia> lista = adyacentes(i);
                    for (int j = 0; j < lista.getSize(); j++) {
                        Adyacencia aux = lista.obtenerDato(j);
                        if (aux.getPeso().toString().equalsIgnoreCase(String.valueOf(Double.NaN))) {
                            grafo.append("---VERTICE DESTINO----- " + aux.getDestino() + "-E- " + obtenerEtiqueta(i).toString());
                        } else {
                            grafo.append("---VERTICE DESTINO----- " + aux.getDestino() + "-E- " + obtenerEtiqueta(i).toString() + "--peso-- " + aux.getPeso());

                        }
                    }
                    grafo.append("\n");
                } catch (Exception e) {
                    System.out.println("Error en to String " + e);
                }
            }
        } catch (Exception e) {
        }
        return grafo.toString();
    }
// public ListaEnlazada[] dijkStra(String inicio, String fin, boolean todosDatos) throws Exception {
//
//        int codigoInicio = obtenerCodigo((E) inicio);
//        int finx = obtenerCodigo((E) fin);
//        boolean[] isVisited = new boolean[this.numVertices() + 1];
//        double[] distance = new double[this.numVertices()];
//        String[] path = new String[numVertices()];
//        for (int i = 0; i < numVertices(); i++) {
//            distance[i] = Double.POSITIVE_INFINITY;
//            path[i] = "";
//        }
//       
//        distance[codigoInicio - 1] = 0.0;
//        int CO;
//        int headIndex = codigoInicio;
//       
//        while (!isVisited[headIndex - 1]) {
//
//           CO = primerCO(headIndex);
//            while (isVisited[CO - 1]) {
//                CO = siguienteCO(headIndex, CO);
//            }
//
//            if (CO == numVertices() + 1) {
//                isVisited[headIndex - 1] = true;
//            } 
//            else {
//
//                while (!isVisited[CO - 1] && CO < this.numVertices() + 1) {
//                    isVisited[headIndex - 1] = true;
//                    Object[] resultado = existeArista(obtenerEtiqueta(headIndex), obtenerEtiqueta(CO));
//                    double currentDis = distance[headIndex - 1] + (double) resultado[1];                   
//                    if (currentDis < distance[CO - 1]) {
//                        distance[CO - 1] = currentDis;
//
//                        path[CO - 1] = path[headIndex - 1] + " " + this.obtenerEtiqueta(headIndex);
//                    }
//
//                    CO = siguienteCO(headIndex, CO);
//
//                }
//            }
//            System.out.println("HE " + headIndex);
//            System.out.println("CO " + CO);
//            headIndex = indexGet(distance, isVisited);
//           
//
//        }
//        for (int i = 0; i < this.numVertices(); i++) {
//            path[i] = path[i] + " " + this.obtenerEtiqueta(i + 1);
//        }
//
//        if (todosDatos) {
//            ListaEnlazada[] listaDAtos = new ListaEnlazada[numVertices()];
//            for (int i = 0; i < this.numV; i++) {
//                listaDAtos[i] = new ListaEnlazada<>();
//            }
//            for (int i = 0; i < numVertices(); i++) {
//                listaDAtos[i].insertarCabecera(path[i]);
//                listaDAtos[i].insertarCabecera(distance[i]);
//                listaDAtos[i].insertarCabecera(this.obtenerEtiqueta(i + 1));
//            }
//            return listaDAtos;
//        } else {
//            ListaEnlazada[] listaDAtos = new ListaEnlazada[1];
//            listaDAtos[0] = new ListaEnlazada<>();
//            listaDAtos[0].insertarCabecera(path[finx - 1]);
//            listaDAtos[0].insertarCabecera(distance[finx - 1]);
//            listaDAtos[0].insertarCabecera(this.obtenerEtiqueta(finx));
//            return listaDAtos;
//        }
//
//    }
//
//  public int indexGet(double[] distance, boolean[] isVisited) {
//        int j = 0;
//        double mindis = Double.POSITIVE_INFINITY;
//        for (int i = 0; i < distance.length; i++) {
//            if (!isVisited[i]) {
//                if (distance[i] < mindis) {
//                    mindis = distance[i];
//                    j = i;
//                }
//            }
//        }
//        return j + 1;
//    }
//    
//    public int primerCO(int index) throws VerticeException, Exception {
//        for (int i = 1; i <= this.numVertices(); i++) {
//            Object[] resultado = existeArista(obtenerEtiqueta(index), obtenerEtiqueta(i));
//           if ((Double) resultado[1] > 0) {
//                return i;
//            }
//        }
//        return this.numVertices() + 1;
//    }
//
//   public int siguienteCO(int index, int firstCO) throws VerticeException, Exception {
//        for (int i = firstCO + 1; i <= this.numVertices(); i++) {
//
//            Object[] resultado = existeArista(obtenerEtiqueta(index), obtenerEtiqueta(i));
//           if ((Double) resultado[1] > 0) {
//             return i;
//            }
//        }
//        return this.numVertices() + 1;
//    }

 
}
