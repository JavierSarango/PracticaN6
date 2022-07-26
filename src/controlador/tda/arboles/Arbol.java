package controlador.tda.arboles;

import controlador.tda.lista.ListaEnlazada;

/**
 *
 * @author javisarango
 */
public class Arbol {

    private NodoArbol raiz;
    private Integer size;
    private Integer altura;
    private ListaEnlazada<ListaEnlazada<NodoArbol>> niveles;
    private ListaEnlazada<NodoArbol> orden;

    public Arbol() {
        raiz = null;
        altura = 0;
        size = 0;
        niveles = new ListaEnlazada<>();
        orden = new ListaEnlazada<>();
    }

    public Boolean insertar(Integer valor) {
        if (raiz == null) { //Si no hay raiz 
            raiz = new NodoArbol(valor); //se la crea con el valor del parametro 
            size++; //y se aumenta el tamaño del arbol en 1
            this.altura = calcularAltura(raiz); //calculamos la altura de la raiz
            niveles = new ListaEnlazada<>();
            for (int i = 0; i <= this.altura; i++) {
                try {
                    niveles.insertarCabecera(new ListaEnlazada<>());
                } catch (Exception e) {
                    System.out.println("Error aqui (derecha) " + e.toString());
                }
            }
            calcularNvl(raiz, 0);
            try {
                niveles.eliminarDato(niveles.getSize() - 1);
            } catch (Exception e) {
                System.out.println("Error en borrado (derecha)");
            }
            return true; // retorna true, y la raiz se inserta.
        } else { // sino (Si, sí hay una raiz, entonces

            NodoArbol nuevo = new NodoArbol(valor); //Se crea un nuevo nodo con el valor enviado por parametro
            NodoArbol actual = raiz; // El nodo actual es el nodo de partida, es decir, la raiz
            NodoArbol padre; // se designa una variable nodoarbol "padre" para designar que nodo sera el padre
            while (true) { //mientras sea verdad
                padre = actual; // AL momento de iniciar, el nodo padre es el nodo actual que a su vez es el valor de la raiz
                if (valor.intValue() == actual.getValor().intValue()) { // Si el valor que se le envia, es igual a la raiz. 
                    return false; //Entonces no lo acepta, retorna false y no se inserta nada.
                } else if (valor.intValue() < actual.getValor().intValue()) {// Si el valor, es menor a la raiz, entonces se inserta el nodo hacia la izquierda. (Siempre se inicia desde la izquierda)
                    actual = actual.getIzquierda(); //Actual toma el valor del nodo hacia la izquierda
                    if (actual == null) { //Si el valor tomado (getIzquierda), es nulo, entonces
                        nuevo.setPadre(padre); //De los nodos a la izquierda, el nuevo nodo sera el padre 
                        padre.setIzquierda(nuevo); // Entonces padre se irá hacia la izquierda
                        size++; //se incrementa el tamaño del arbol en 1
                        this.altura = calcularAltura(raiz);
                        niveles = new ListaEnlazada<>();
                        for (int i = 0; i <= this.altura; i++) {
                            try {
                                niveles.insertarCabecera(new ListaEnlazada<>());// Crea un nuevo nivel por si acaso
                            } catch (Exception e) {
                                System.out.println("Error aqui (izquierda) " + e.toString());
                            }
                        }
                        calcularNvl(raiz, 0);
                        try {
                            niveles.eliminarDato(niveles.getSize() - 1);
                        } catch (Exception e) {
                            System.out.println("Error en borrado (izquierda) " + e.toString());
                        }
                        return true;          //retorna true, y el nodo se inserta.              
                    }

                } else { //SIno, se hace todo lo contrario al metodo anterior.
                    actual = actual.getDerecha();
                    if (actual == null) {
                        nuevo.setPadre(padre);
                        padre.setDerecha(nuevo);
                        size++;
                        this.altura = calcularAltura(raiz); //calculamos la altura de la raiz
                        niveles = new ListaEnlazada<>();
                        for (int i = 0; i <= this.altura; i++) {
                            try {
                                niveles.insertarCabecera(new ListaEnlazada<>());
                            } catch (Exception e) {
                                System.out.println("Error aqui (derecha) " + e.toString());
                            }
                        }
                        calcularNvl(raiz, 0);
                        try {
                            niveles.eliminarDato(niveles.getSize() - 1);
                        } catch (Exception e) {
                            System.out.println("Error en borrado (derecha)");
                        }
                        return true;
                    }
                }
            }

        }

    }

    public void calcularNvl(NodoArbol arbol, Integer nivel) {
        try {
            if (arbol != null) {
                int pos = (niveles.obtenerDato(nivel).getSize() > 0) ? niveles.obtenerDato(nivel).getSize() - 1 : 0;
                niveles.obtenerDato(nivel).insertar(arbol, pos);
                nivel++;
                calcularNvl(arbol.getIzquierda(), nivel);
                calcularNvl(arbol.getDerecha(), nivel);
            } else if (nivel != getAltura()) {
                int pos = (niveles.obtenerDato(nivel).getSize() > 0) ? niveles.obtenerDato(nivel).getSize() - 1 : 0;
                niveles.obtenerDato(nivel).insertar(null, pos);
                nivel++;
                calcularNvl(null, nivel);
                calcularNvl(null, nivel);
            }

        } catch (Exception e) {
            System.out.println("Error en niveles " + e.toString());
        }

    }

    public Integer calcularAltura(NodoArbol arbol) {
        if (arbol == null) {
            return 0;
        } else {
            Integer izquierda = calcularAltura(arbol.getIzquierda());
            Integer derecha = calcularAltura(arbol.getDerecha());
            if (izquierda > derecha) {
                return (izquierda + 1);
            } else {
                return (derecha + 1);
            }
        }
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public Integer getTamanio() {
        return size;
    }

    public void setTamanio(Integer tamanio) {
        this.size = tamanio;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public ListaEnlazada<ListaEnlazada<NodoArbol>> getNiveles() {
        return niveles;
    }

    public void setNiveles(ListaEnlazada<ListaEnlazada<NodoArbol>> niveles) {
        this.niveles = niveles;
    }

    public ListaEnlazada<NodoArbol> getOrden() {
        return orden;
    }

    public void setOrden(ListaEnlazada<NodoArbol> orden) {
        this.orden = orden;
    }

    private void preOrden(NodoArbol arbol) throws Exception {
        if (arbol != null) {
            int pos = (orden.getSize() > 0) ? orden.getSize() - 1 : 0;
            orden.insertar(arbol, pos);
            preOrden(arbol.getIzquierda());
            preOrden(arbol.getDerecha());
        }

    }

    public ListaEnlazada<NodoArbol> preOrden() {
        orden = new ListaEnlazada<>();
        try {
            preOrden(raiz);
        } catch (Exception e) {
            System.out.println("Error en preOrden");
        }
        return orden;
    }

    private void posOrden(NodoArbol arbol) throws Exception {
        if (arbol != null) {
            posOrden(arbol.getIzquierda());
            posOrden(arbol.getDerecha());
            int pos = (orden.getSize() > 0) ? orden.getSize() - 1 : 0;
            orden.insertar(arbol, pos);
        }

    }

    public ListaEnlazada<NodoArbol> posOrden() {
        orden = new ListaEnlazada<>();
        try {
            posOrden(raiz);
        } catch (Exception e) {
            System.out.println("Error en posOrden");
        }

        return orden;
    }

    private void inOrden(NodoArbol arbol) throws Exception {
        if (arbol != null) {
            inOrden(arbol.getIzquierda());
            int pos = (orden.getSize() > 0) ? orden.getSize() - 1 : 0;
            orden.insertar(arbol, pos);
            inOrden(arbol.getDerecha());

        }

    }

    public ListaEnlazada<NodoArbol> inOrden() {
        orden = new ListaEnlazada<>();
        try {
            inOrden(raiz);
        } catch (Exception e) {
            System.out.println("Error en inOrden");
        }

        return orden;
    }

    public static void main(String[] args) {
        Arbol a = new Arbol();
//        a.insertar(56);
//        a.insertar(56);  //Nro de Nodos 1: Porque nodos iguales no se agregarán.
//        a.insertar(56);
//        a.insertar(56);
//        a.insertar(56);
        a.insertar(56);
        a.insertar(96);
        a.insertar(36);
        a.insertar(34);
        a.insertar(16);
        System.out.println("Numero de nodos: " + a.size);
        System.out.println("Niveles: " + a.getNiveles().getSize());
        System.out.println("Altura: " + a.getAltura());
        System.out.println("Imprimir niveles");
        try {
            a.getNiveles().imprimir();
            Integer posicion = 50;
            for (int i = 0; i < a.getNiveles().getSize(); i++) {

                ListaEnlazada<NodoArbol> aux = a.getNiveles().obtenerDato(i);
                Integer posA = posicion / aux.getSize();
                String cadena = a.posiciones(posA);
                System.out.println("");
                Integer espacios = posA;
                for (int j = 0; j < aux.getSize(); j++) {
                    if (aux.obtenerDato(j) != null) {
                        System.out.println(cadena + aux.obtenerDato(j) + "\t");
//                        System.out.println(cadena+ aux.obtenerDato(j)+"\t");
                    } else {
                        System.out.println(cadena);
                    }
                    espacios += espacios;
                    cadena = a.posiciones(espacios);

                }
                System.out.println("");

            }
        } catch (Exception e) {
            System.out.println("Error en listado " + e);
        }

    }

    public String posiciones(Integer aux) {
        StringBuilder cadena = new StringBuilder();
        for (int i = 0; i < aux; i++) {
            cadena.append(" ");
        }
        return cadena.toString();
    }
}
