/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import controlador.Ciudad.CiudadController;
import controlador.tda.grafo.GrafoED;
import controlador.tda.grafo.GrafoEND;
import controlador.tda.lista.ListaEnlazada;
import javax.swing.JOptionPane;
import modelo.Ciudad;
import vista.Grafo.Ejemplo.FmrVistaGrafo;
import vista.ModeloTablas.ModeloTablaCiudad;

/**
 *
 * @author Gigabyte
 */
public class Frm_Ciudad extends javax.swing.JDialog {

    private CiudadController cc = new CiudadController();
    private ModeloTablaCiudad mtc = new ModeloTablaCiudad();
    private GrafoED gend = new GrafoED(0, Ciudad.class);

    /**
     * Creates new form Frm_Ciudad
     */
    public Frm_Ciudad(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

//        Limpiar();
    }

    private void cargarTabla() {
        mtc.setGrafo(cc.getGrafoED());
        mtc.fireTableStructureChanged();
        mtc.fireTableDataChanged();
        tblCiudad.setModel(mtc);
        tblCiudad.updateUI();
        // System.out.println(cc.getGrafoEND().toString());
    }

    private void Limpiar() {
        txtLatitud.setText("");
        txtLongitud.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        String[] aux = {""};
        ListCaminoMinimo.setListData(aux);
        ListDijkstra.setListData(aux);
        cc.setCiudad(null);
        btnModificar.setEnabled(false);
        cargarTabla();

    }

    public void CrearCiudad() {
        String nombre = txtNombre.getText();
        String des = txtDescripcion.getText();
        Double lat = Double.parseDouble(txtLatitud.getText());
        Double longi = Double.parseDouble(txtLongitud.getText());
        GrafoED nuevo = gend;
        gend = new GrafoEND<>(nuevo.numVertices() + 1, Ciudad.class);
        try {
            for (int i = 1; i <= nuevo.numVertices(); i++) {

                gend.etiquetarVertice(i, nuevo.obtenerEtiqueta(i));

            }
            for (int i = 1; i <= nuevo.numVertices(); i++) {
                for (int j = 0; j < nuevo.adyacentes(i).getSize(); j++) {
                    gend.insertarArista(nuevo.obtenerEtiqueta(i), nuevo.obtenerEtiqueta(nuevo.adyacentes(i).obtenerDato(j).getDestino()), (Double) nuevo.existeArista(nuevo.obtenerEtiqueta(i), nuevo.obtenerEtiqueta(nuevo.adyacentes(i).obtenerDato(j).getDestino()))[1]);
                }
            }
            gend.etiquetarVertice(gend.numVertices(), txtNombre.getText());
            Limpiar();
        } catch (Exception e) {
        }
        cargarcbxVertices();
        cargarTabla();

    }

//    private void crear() {
//
////        Integer nro = Integer.parseInt(cbxnro.getSelectedItem().toString());
//        Integer numV = Integer.parseInt(jspinnerNro.getValue().toString());
//        cc = new CiudadController(numV);
//        gend = cc.getGrafoEND();
//        
//        jPanel2.setVisible(true);
//        jPanel4.setVisible(true);
//        jPanel5.setVisible(true);
//        String[] aux = {""};
//        ListCaminoMinimo.setListData(aux);
//        ListDijkstra.setListData(aux);
//        btnModificar.setEnabled(false);
//
//         mtc.setGrafo(gend);
//        mtc.fireTableStructureChanged();
//        mtc.fireTableDataChanged();
//        tblCiudad.setModel(mtc);
//        tblCiudad.updateUI();
//        cargarcbxVertices();
//    }
    private void crear() {
        Integer numV = Integer.parseInt(jspinnerNro.getValue().toString());
        cc = new CiudadController(numV);
//        Integer nro = Integer.parseInt(cbxnro.getSelectedItem().toString());
        
        if (gend.numVertices()==0) {
            

            gend = cc.getGrafoED();

            System.out.println(cc.getGrafoED().toString());
            GrafoED nuevo = gend;
            gend = new GrafoEND(nuevo.numVertices() + (numV - nuevo.numVertices()), Ciudad.class);
            try {
                for (int i = 1; i <= nuevo.numVertices(); i++) {

                    gend.etiquetarVertice(i, nuevo.obtenerEtiqueta(i));

                }
                for (int i = 1; i <= nuevo.numVertices(); i++) {
                    for (int j = 0; j < nuevo.adyacentes(i).getSize(); j++) {
                        gend.insertarArista(nuevo.obtenerEtiqueta(i), nuevo.obtenerEtiqueta(nuevo.adyacentes(i).obtenerDato(j).getDestino()), (Double) nuevo.existeArista(nuevo.obtenerEtiqueta(i), nuevo.obtenerEtiqueta(nuevo.adyacentes(i).obtenerDato(j).getDestino()))[1]);
                    }
                }
                gend.etiquetarVertice(gend.numVertices(), txtNombre.getText());
                Limpiar();
            } catch (Exception e) {
            }
        } else if(gend.numVertices() != 0) {
            
            gend = cc.getGrafoED();

            System.out.println(cc.getGrafoED().toString());
            GrafoED nuevo = gend;
            gend = new GrafoEND(nuevo.numVertices() + (numV - nuevo.numVertices()), Ciudad.class);
            try {
                for (int i = 1; i <= nuevo.numVertices(); i++) {

                    gend.etiquetarVertice(i, nuevo.obtenerEtiqueta(i));

                }
                for (int i = 1; i <= nuevo.numVertices(); i++) {
                    for (int j = 0; j < nuevo.adyacentes(i).getSize(); j++) {
                        gend.insertarArista(nuevo.obtenerEtiqueta(i), nuevo.obtenerEtiqueta(nuevo.adyacentes(i).obtenerDato(j).getDestino()), (Double) nuevo.existeArista(nuevo.obtenerEtiqueta(i), nuevo.obtenerEtiqueta(nuevo.adyacentes(i).obtenerDato(j).getDestino()))[1]);
                    }
                }
                gend.etiquetarVertice(gend.numVertices(), txtNombre.getText());
                Limpiar();
            } catch (Exception e) {
            }

        }
        String[] aux = {""};
        ListCaminoMinimo.setListData(aux);
        ListDijkstra.setListData(aux);
        btnModificar.setEnabled(false);

        mtc.setGrafo(gend);
        mtc.fireTableStructureChanged();
        mtc.fireTableDataChanged();
        tblCiudad.setModel(mtc);
        tblCiudad.updateUI();

        cbxdestino.removeAllItems();
        cbxorigen.removeAllItems();
        try {
            for (int i = 1; i <= gend.numVertices(); i++) {
                cbxorigen.addItem(gend.obtenerEtiqueta(i).toString());
                cbxdestino.addItem(gend.obtenerEtiqueta(i).toString());
            }
        } catch (Exception e) {
        }
    }
//    private void guardar(){
//        if (camposValidos()) {
//            cc.getCiudad().setNomCiudad(txtNombre.getText().trim());
//            cc.getCiudad().setDescripcion(txtDescripcion.getText().trim());
//            cc.getCiudad().getUbicacion().setLatitud(Double.parseDouble(txtLatitud.getText().trim()));
//            cc.getCiudad().getUbicacion().setLongitud(Double.parseDouble(txtLongitud.getText().trim()));
//            if (cc.guardar()) {
//                JOptionPane.showMessageDialog(null, "Datos Guardados", "Exito", JOptionPane.INFORMATION_MESSAGE);
//            }else{
//            JOptionPane.showMessageDialog(null, "No se pudo Guardar", "Advertencia", JOptionPane.ERROR_MESSAGE);
//            }
//        }else{
//        JOptionPane.showMessageDialog(null, "Llene los campos vacios", "Advertencia", JOptionPane.ERROR_MESSAGE);
//        }      
//    }

    private void modificar() throws Exception {
        if (!camposValidos()) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "Advertencia", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                Integer pos = cc.getGrafoED().obtenerCodigo(cc.getCiudad());
                cc.getCiudad().setNomCiudad(txtNombre.getText());
                cc.getCiudad().setDescripcion(txtDescripcion.getText().trim());
                cc.getCiudad().getUbicacion().setLatitud(Double.parseDouble(txtLatitud.getText()));
                cc.getCiudad().getUbicacion().setLongitud(Double.parseDouble(txtLongitud.getText()));
                if (cc.modificar(pos) && cc.getGrafoED().modificar(cc.getGrafoED().obtenerEtiqueta(pos), cc.getCiudad())) {
                    System.out.println(cc.getGrafoED().toString());
                    cargarcbxVertices();
                    Limpiar();
                    JOptionPane.showMessageDialog(null, "Datos Modificados", "Exito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo Modificar", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void cargarcbxVertices() {
        cbxdestino.removeAllItems();
        cbxorigen.removeAllItems();
        try {
            for (int i = 1; i <= cc.getGrafoED().numVertices(); i++) {
                cbxorigen.addItem(cc.getGrafoED().obtenerEtiqueta(i).toString());
                cbxdestino.addItem(cc.getGrafoED().obtenerEtiqueta(i).toString());
            }
        } catch (Exception e) {
        }

    }

    private boolean camposValidos() {
        if (txtDescripcion.getText().trim().isEmpty()
                || txtNombre.getText().trim().isEmpty()
                || txtLatitud.getText().trim().isEmpty()
                || txtLongitud.getText().trim().isEmpty()) {
            return false;

        }

        return true;

    }

    private void cargarDatos() {
        btnModificar.setEnabled(true);
        Integer fila = -1;
        fila = tblCiudad.getSelectedRow();
        try {

            if (fila >= 0) {
                cc.setCiudad(cc.getGrafoED().obtenerEtiqueta(fila + 1));
                txtDescripcion.setText(String.valueOf(cc.getCiudad().getDescripcion().trim()));
                txtLatitud.setText(String.valueOf(cc.getCiudad().getUbicacion().getLatitud()));
                txtLongitud.setText(String.valueOf(cc.getCiudad().getUbicacion().getLongitud()));

//               btnGenerar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Elija una fila", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }

    }

    private void adyacencia() {
        Integer origen = (cbxorigen.getSelectedIndex() + 1);
        Integer destino = (cbxdestino.getSelectedIndex() + 1);
        if (origen == destino) {
            JOptionPane.showMessageDialog(null, "Escoja una ciudad diferente", "Advertencia", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Double distancia = cc.calcularDistancia(cc.getGrafoED().obtenerEtiqueta(origen), cc.getGrafoED().obtenerEtiqueta(destino));
                cc.getGrafoED().insertarArista(cc.getGrafoED().obtenerEtiqueta(origen), cc.getGrafoED().obtenerEtiqueta(destino), distancia);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Advertencia", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    private void camino() {
        String[] aux = {""};
        ListCaminoMinimo.setListData(aux);
        Integer origen = (cbxorigen.getSelectedIndex() + 1);
        Integer destino = (cbxdestino.getSelectedIndex() + 1);
        if (origen == destino) {
            JOptionPane.showMessageDialog(null, "Escoja una ciudad diferente", "Advertencia", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ListaEnlazada<Integer> lista = cc.getGrafoED().caminoMinimo(origen, destino);
                aux = new String[lista.getSize()];
                for (int i = 0; i < lista.getSize(); i++) {
                    aux[i] = cc.getGrafoED().obtenerEtiqueta(lista.obtenerDato(i)).toString();
                }
                ListCaminoMinimo.setListData(aux);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }

    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtLongitud = new javax.swing.JTextField();
        txtLatitud = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        cbxnro = new javax.swing.JComboBox<>();
        btnGenerar = new javax.swing.JButton();
        jspinnerNro = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCiudad = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbxorigen = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbxdestino = new javax.swing.JComboBox<>();
        btnVincular = new javax.swing.JButton();
        btnPresentar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListCaminoMinimo = new javax.swing.JList<>();
        btnCaminoMinimo = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ListDijkstra = new javax.swing.JList<>();
        btnDijkstra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Ciudades");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 10, 70, 29);

        jLabel2.setText("Nombre:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 50, 60, 30);
        jPanel2.add(txtNombre);
        txtNombre.setBounds(90, 50, 160, 30);

        jLabel3.setText("Descripcion:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 100, 70, 30);

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(90, 90, 240, 90);

        jLabel4.setText("Latitud:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(370, 40, 60, 30);

        jLabel5.setText("Longitud:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(370, 90, 60, 30);
        jPanel2.add(txtLongitud);
        txtLongitud.setBounds(440, 90, 140, 30);
        jPanel2.add(txtLatitud);
        txtLatitud.setBounds(440, 40, 140, 30);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificar);
        btnModificar.setBounds(510, 140, 100, 30);

        cbxnro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4", "5", "6", "7", "8", "9", "10" }));
        jPanel2.add(cbxnro);
        cbxnro.setBounds(90, 10, 70, 30);

        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGenerar);
        btnGenerar.setBounds(180, 10, 100, 30);

        jspinnerNro.setModel(new javax.swing.SpinnerNumberModel(4, 4, null, 1));
        jPanel2.add(jspinnerNro);
        jspinnerNro.setBounds(290, 10, 70, 30);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 10, 680, 190);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        jPanel3.setLayout(null);

        tblCiudad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCiudad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCiudadMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCiudad);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(10, 10, 320, 280);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(10, 210, 340, 300);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)), "Adyacencias", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel4.setLayout(null);

        jLabel6.setText("Origen");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(20, 30, 60, 30);

        cbxorigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(cbxorigen);
        cbxorigen.setBounds(90, 30, 110, 30);

        jLabel7.setText("Destino");
        jPanel4.add(jLabel7);
        jLabel7.setBounds(220, 30, 70, 30);

        cbxdestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(cbxdestino);
        cbxdestino.setBounds(300, 30, 110, 30);

        btnVincular.setText("VINCULAR");
        btnVincular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVincularActionPerformed(evt);
            }
        });
        jPanel4.add(btnVincular);
        btnVincular.setBounds(430, 30, 110, 30);

        btnPresentar.setText("Presentar");
        btnPresentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPresentarActionPerformed(evt);
            }
        });
        jPanel4.add(btnPresentar);
        btnPresentar.setBounds(560, 30, 90, 30);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(10, 520, 680, 80);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 153)), "Caminos Minimos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        ListCaminoMinimo.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(ListCaminoMinimo);

        btnCaminoMinimo.setText("Camino");
        btnCaminoMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaminoMinimoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCaminoMinimo, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnCaminoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(360, 210, 330, 140);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 204)), "Dijkstra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        ListDijkstra.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(ListDijkstra);

        btnDijkstra.setText("Camino");
        btnDijkstra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDijkstraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDijkstra, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnDijkstra, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6);
        jPanel6.setBounds(360, 360, 330, 150);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 700, 610);

        setSize(new java.awt.Dimension(715, 659));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVincularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVincularActionPerformed
        // TODO add your handling code here:
        try {
            adyacencia();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnVincularActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        try {
            modificar();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCaminoMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaminoMinimoActionPerformed
        // TODO add your handling code here:
        try {
            camino();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCaminoMinimoActionPerformed

    private void tblCiudadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCiudadMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            //Datos a mostrar en pantalla Modificar
            cargarDatos();

        }
    }//GEN-LAST:event_tblCiudadMouseClicked

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        // TODO add your handling code here:
        try {
            crear();
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnPresentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPresentarActionPerformed
        // TODO add your handling code here:
        FmrVistaGrafo frm = new FmrVistaGrafo(gend);
        frm.setSize(400, 400);
        frm.setVisible(true);
    }//GEN-LAST:event_btnPresentarActionPerformed

    private void btnDijkstraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDijkstraActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnDijkstraActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frm_Ciudad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Ciudad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Ciudad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Ciudad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frm_Ciudad dialog = new Frm_Ciudad(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListCaminoMinimo;
    private javax.swing.JList<String> ListDijkstra;
    private javax.swing.JButton btnCaminoMinimo;
    private javax.swing.JButton btnDijkstra;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnPresentar;
    private javax.swing.JButton btnVincular;
    private javax.swing.JComboBox<String> cbxdestino;
    private javax.swing.JComboBox<String> cbxnro;
    private javax.swing.JComboBox<String> cbxorigen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jspinnerNro;
    private javax.swing.JTable tblCiudad;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtLatitud;
    private javax.swing.JTextField txtLongitud;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
