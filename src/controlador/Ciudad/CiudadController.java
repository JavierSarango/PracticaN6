package controlador.Ciudad;

import controlador.DAO.AdaptadorDao;
import controlador.tda.grafo.GrafoED;
import controlador.tda.grafo.GrafoEND;
import controlador.tda.lista.ListaEnlazada;
import controlador.tda.lista.ListaEnlazadaServices;
import modelo.Ciudad;
import modelo.Ubicacion;

/**
 *
 * @author Gigabyte
 */
public class CiudadController extends AdaptadorDao<Ciudad> {

    private Ciudad ciudad;
    private GrafoED grafoED = new GrafoED<>(0, Ciudad.class);
    private ListaEnlazadaServices<Ciudad> listaCiudad;   
 

    public CiudadController() {
        super(Ciudad.class);
                
    }
    
   

    public CiudadController(Integer numV) {
        super(Ciudad.class);
        grafoED = new GrafoEND<>(numV, Ciudad.class);
        for (int i = 1; i <= numV; i++) {
            Ciudad c = new Ciudad();
            c.setId(i);
            c.setNomCiudad("Ciudad " + i);
            c.setDescripcion("");
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setId(i);
            ubicacion.setLongitud(0.0);
            ubicacion.setLatitud(0.0);
            c.setUbicacion(ubicacion);
            grafoED.etiquetarVertice(i, c);            
            
        }
        listado();
        
    }
    public GrafoED temp(){
        GrafoED aux = grafoED;
    return aux;
    }
    

    public Ciudad getCiudad() {
        if (ciudad == null) {
            ciudad = new Ciudad();
        }
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public GrafoED<Ciudad> getGrafoED() {
        return grafoED;
    }

    public void setGrafoED(GrafoED<Ciudad> grafoED) {
        this.grafoED = grafoED;
    }

    public ListaEnlazadaServices<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    public void setListaCiudad(ListaEnlazadaServices<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;
    }

    public Boolean guardar() {
        try {
            
            getCiudad().setId(listaCiudad.getSize()+1);
            guardar(getCiudad());
            return true;
        } catch (Exception e) {
            System.out.println("Error en guardar Ciudad" + e);
        }
        return false;
    }

    public Boolean modificar(Integer pos) {
        try {

            modificar(getCiudad(), pos);
            return true;
        } catch (Exception e) {
            System.out.println("Error en modificar Ciudad" + e);
        }
        return false;
    }

    public Boolean eliminarDato(Integer pos) {
        try {
            eliminar(pos);
            return true;
        } catch (Exception e) {
            System.out.println("Error al dar de baja ");
        }
        return false;

    }

    public ListaEnlazadaServices<Ciudad> listado() {
        setListaCiudad(listar());
        return listaCiudad;
    }
  public Double calcularDistancia(Ciudad co, Ciudad cd){
    Double dis = 0.0;
    //DIstancia = raiz cuadrada de x1 - x2 al cuadrado, más y1 - y2 al cuagrado.
    Double y = co.getUbicacion().getLongitud() - cd.getUbicacion().getLongitud();
    Double x = co.getUbicacion().getLatitud() - cd.getUbicacion().getLatitud();
    dis = Math.sqrt((x*x)+(y*y));
    
    return dis;
    }
}
