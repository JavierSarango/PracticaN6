package controlador.tda.arboles;

/**
 *
 * @author javisarango
 */
public class NodoArbol {

    private NodoArbol padre;
    private NodoArbol izquierda;
    private NodoArbol derecha;
    private Integer valor;

    public NodoArbol(Integer valor) {
    this.valor = valor;
    padre = null;
    izquierda = null;
    derecha = null;
    
    }

    
    
    
    public NodoArbol getPadre() {
        return padre;
    }

    public void setPadre(NodoArbol padre) {
        this.padre = padre;
    }

    public NodoArbol getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoArbol izquierda) {
        this.izquierda = izquierda;
    }

    public NodoArbol getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoArbol derecha) {
        this.derecha = derecha;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "|" + valor + "|";
    }
    
    
}
