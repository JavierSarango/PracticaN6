/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Grafo.ModeloTablas;

import controlador.tda.grafo.GrafoD;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author javisarango
 */
public class ModeloTablaGrafo extends AbstractTableModel {

    private GrafoD grafoD;
    private String[] columnas;

    public GrafoD getGrafoD() {
        return grafoD;
    }

    public void setGrafoD(GrafoD grafoD) {
        this.grafoD = grafoD;
        generarColumnas();
    }

    public String[] getColumnas() {
        return columnas;
    }

    public void setColumnas(String[] columnas) {
        this.columnas = columnas;
    }

    private String[] generarColumnas() {
        columnas = new String[grafoD.numVertices() + 1];
        columnas[0] = "/";
        for (int i = 1; i < columnas.length; i++) {
            columnas[i] = String.valueOf(i);
        }
        return columnas;
    }

    @Override
    public int getRowCount() {
        return grafoD.numVertices();
    }

    @Override
    public int getColumnCount() {
        return grafoD.numVertices() + 1;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        if (columna == 0) {
            return columnas[fila + 1];
        } else {
            try {
                Object[] aux = grafoD.existeArista((fila + 1), columna);
                if ((Boolean) aux[0]) {
                    return aux[1];
                } else {
                    return "------";
                }
            } catch (Exception e) {
                System.out.println("Error en tabla " + e);
                return "";
            }
        }
    }

}
