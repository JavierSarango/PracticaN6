/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ModeloTablas;

import controlador.tda.grafo.GrafoED;
import javax.swing.table.AbstractTableModel;
import modelo.Ciudad;

/**
 *
 * @author sebastian
 */
public class ModeloTablaCiudad extends AbstractTableModel {

    private GrafoED<Ciudad> grafo;

    public GrafoED<Ciudad> getGrafo() {
        return grafo;
    }

    public void setGrafo(GrafoED<Ciudad> grafo) {
        this.grafo = grafo;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return grafo.numVertices();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nro";
            case 1:
                return "Nombres";
            case 2:
                return "Descripcion";
            case 3:
                return "Ubicacion";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        try {
            Ciudad p = grafo.obtenerEtiqueta(arg0 + 1);
            switch (arg1) {
                case 0:
                    return (arg0+1);
                case 1:
                    return p.getNomCiudad();
                case 2:
                    return p.getDescripcion();
                case 3:
                    return (p.getUbicacion() == null) ? "NO TIENE" : p.getUbicacion().toString();
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Error en listado");
            return null;
        }
    }

}
