/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1_proyecto1_201900629;

import Errores.Excepcion;
import analizadores.Arbol;
import analizadores.Lexema;
import olc1_proyecto1_201900629.JSONValues;
import java.awt.event.ItemEvent;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario Josue Solis Solorzano
 */
public class InterfazGrafica extends javax.swing.JFrame {

    JFileChooser elegirArchivo = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;
    boolean archivoNuevo = true;
    String archivoUsado;
    String rutaArchivo;
    File[] listaImagenes = new File[0];
    int imagenActual = 0;

    /**
     * Creates new form InterfazGrafica
     */
    public InterfazGrafica() {
        initComponents();
    }

    public String AbrirArchivo(File archivo) {
        String textoEntrada = "";
        try {
            entrada = new FileInputStream(archivo);
            int ascii;
            while ((ascii = entrada.read()) != -1) {
                char caracter = (char) ascii;
                textoEntrada += caracter;
            }
            entrada.close();
        } catch (Exception e) {
        }
        return textoEntrada;
    }

    public String GuardarArchivo(File archivo, String documento) {
        String mensaje = null;
        try {
            salida = new FileOutputStream(archivo);
            byte[] txtbytes = documento.getBytes();
            salida.write(txtbytes);
            mensaje = "Archivo Guardado";
            salida.close();
        } catch (Exception e) {
        }
        return mensaje;
    }

    public void guardar() throws IOException {
        if (archivoNuevo) {
            if (elegirArchivo.showDialog(null, "Guardar Como") == JFileChooser.APPROVE_OPTION) {
                archivo = elegirArchivo.getSelectedFile();
                if (archivo.getName().endsWith("olc")) {
                    String documentoSalida = txtEntrada.getText();
                    String mensaje = GuardarArchivo(archivo, documentoSalida);
                    if (mensaje != null) {
                        JOptionPane.showMessageDialog(null, mensaje);
                        archivoNuevo = false;
                        archivoUsado = archivo.getName();
                        rutaArchivo = archivo.getPath();
                    } else {
                        JOptionPane.showMessageDialog(null, "El archivo no es compatible");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo Guardado");
                }
            }
        } else {
            File archivo = new File(rutaArchivo);
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(txtEntrada.getText());
            bw.close();
        }
    }

    public void traerImagenes(String tipoImagenes) {
        if (tipoImagenes.equals("Arboles")) {
            listaImagenes = OLC1_Proyecto1_201900629.arboles.listFiles();
        } else if (tipoImagenes.equals("Siguientes")) {
            listaImagenes = OLC1_Proyecto1_201900629.siguientes.listFiles();
        } else if (tipoImagenes.equals("Transiciones")) {
            listaImagenes = OLC1_Proyecto1_201900629.transiciones.listFiles();
        } else if (tipoImagenes.equals("Automatas Deterministas")) {
            listaImagenes = OLC1_Proyecto1_201900629.afds.listFiles();
        } else if (tipoImagenes.equals("Automatas no Deterministas")) {
            listaImagenes = OLC1_Proyecto1_201900629.afnds.listFiles();
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEntrada = new javax.swing.JTextArea();
        btnGenerarAutomatas = new javax.swing.JButton();
        btnAnalizarCadenas = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSalida = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        lblImagen = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnImgAnterior = new javax.swing.JButton();
        btnImgSiguiente = new javax.swing.JButton();
        lblNombreImagen = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmArchivo = new javax.swing.JMenu();
        mnNuevo = new javax.swing.JMenuItem();
        mnAbrir = new javax.swing.JMenuItem();
        mnGuardar = new javax.swing.JMenuItem();
        mnGuardarComo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Archivo de entrada");

        txtEntrada.setColumns(20);
        txtEntrada.setRows(5);
        jScrollPane1.setViewportView(txtEntrada);

        btnGenerarAutomatas.setText("Generar Autómatas");
        btnGenerarAutomatas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarAutomatasActionPerformed(evt);
            }
        });

        btnAnalizarCadenas.setText("Analizar Cadenas");
        btnAnalizarCadenas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarCadenasActionPerformed(evt);
            }
        });

        txtSalida.setColumns(20);
        txtSalida.setRows(5);
        jScrollPane3.setViewportView(txtSalida);

        lblImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScrollPane4.setViewportView(lblImagen);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ver imagenes", "Arboles", "Siguientes", "Transiciones", "Automatas Deterministas", "Automatas no Deterministas" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        btnImgAnterior.setText("Anterior");
        btnImgAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImgAnteriorActionPerformed(evt);
            }
        });

        btnImgSiguiente.setText("Siguiente");
        btnImgSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImgSiguienteActionPerformed(evt);
            }
        });

        lblNombreImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreImagen.setToolTipText("");

        jmArchivo.setText("File");

        mnNuevo.setText("Nuevo Archivo");
        mnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnNuevoActionPerformed(evt);
            }
        });
        jmArchivo.add(mnNuevo);

        mnAbrir.setText("Abrir Archivo");
        mnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAbrirActionPerformed(evt);
            }
        });
        jmArchivo.add(mnAbrir);

        mnGuardar.setText("Guardar");
        mnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGuardarActionPerformed(evt);
            }
        });
        jmArchivo.add(mnGuardar);

        mnGuardarComo.setText("Guardar Como");
        mnGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGuardarComoActionPerformed(evt);
            }
        });
        jmArchivo.add(mnGuardarComo);

        jMenuBar1.add(jmArchivo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnGenerarAutomatas)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAnalizarCadenas))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnImgAnterior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNombreImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnImgSiguiente))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane4)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnImgAnterior)
                        .addComponent(btnGenerarAutomatas)
                        .addComponent(btnAnalizarCadenas))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnImgSiguiente)
                        .addComponent(lblNombreImagen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAbrirActionPerformed
        // TODO add your handling code here:
        if (elegirArchivo.showDialog(null, "Abrir Archivo") == JFileChooser.APPROVE_OPTION) {
            archivo = elegirArchivo.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("olc")) {
                    String entrada = AbrirArchivo(archivo);
                    txtEntrada.setText(entrada);
                    archivoUsado = archivo.getName();
                    rutaArchivo = archivo.getPath();
                    archivoNuevo = false;
                } else {
                    JOptionPane.showMessageDialog(null, "El archivo seleccionado no es compatible.");
                }
            }
        }
    }//GEN-LAST:event_mnAbrirActionPerformed

    private void btnGenerarAutomatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarAutomatasActionPerformed
        // TODO add your handling code here:
        String entrada = txtEntrada.getText();
        if (!entrada.equals("")) {
            Limpiar();
            ArrayList<Excepcion> errores = new ArrayList();
            InputStream intstream = new ByteArrayInputStream(entrada.getBytes());
            analizadores.Sintactico pars;
            analizadores.Lexico lex;
            lex = new analizadores.Lexico(intstream);
            pars = new analizadores.Sintactico(lex);
            try {
                pars.parse();
                errores.addAll(lex.errores);
                errores.addAll(pars.errores);
                for (int i = 0; i < errores.size(); i++) {
                    ArchivosSalida salida = new ArchivosSalida();
                    salida.escribirHTML(archivo.getName().replace(".olc", ""), errores);
                }
                txtSalida.append("Autómatas generados...\n");
            } catch (Exception ex) {
                Logger.getLogger(InterfazGrafica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGenerarAutomatasActionPerformed

    public void Limpiar() {
        OLC1_Proyecto1_201900629.listaNombresArboles.clear();
        OLC1_Proyecto1_201900629.listaArboles.clear();
        OLC1_Proyecto1_201900629.listaConjuntos.clear();
        OLC1_Proyecto1_201900629.listaLexemas.clear();
        txtSalida.setText("");
        traerImagenes(jComboBox1.getSelectedItem().toString());
        if (listaImagenes.length > 0) {
            ImageIcon image = new ImageIcon(listaImagenes[0].getAbsolutePath());
            Icon fondo = image;
            lblImagen.setIcon(fondo);
            lblNombreImagen.setText(listaImagenes[0].getName().replace(".png", ""));
        }
    }

    private void mnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnNuevoActionPerformed
        // TODO add your handling code here:
        archivoUsado = null;
        archivoNuevo = true;
        txtEntrada.setText("");
    }//GEN-LAST:event_mnNuevoActionPerformed

    private void mnGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGuardarComoActionPerformed
        // TODO add your handling code here:
        if (elegirArchivo.showDialog(null, "Guardar Como") == JFileChooser.APPROVE_OPTION) {
            archivo = elegirArchivo.getSelectedFile();
            if (archivo.getName().endsWith("olc")) {
                String documentoSalida = txtEntrada.getText();
                String mensaje = GuardarArchivo(archivo, documentoSalida);
                if (mensaje != null) {
                    JOptionPane.showMessageDialog(null, mensaje);
                    archivoNuevo = false;
                    archivoUsado = archivo.getName();
                    rutaArchivo = archivo.getPath();
                } else {
                    JOptionPane.showMessageDialog(null, "El archivo no es compatible");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Archivo Guardado");
            }
        }
    }//GEN-LAST:event_mnGuardarComoActionPerformed

    private void mnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGuardarActionPerformed
        try {
            // TODO add your handling code here:
            guardar();
        } catch (IOException ex) {
            Logger.getLogger(InterfazGrafica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnGuardarActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (listaImagenes.length > 0) {
                traerImagenes(jComboBox1.getSelectedItem().toString());
                ImageIcon image = new ImageIcon(listaImagenes[0].getAbsolutePath());
                Icon fondo = image;
                lblImagen.setIcon(fondo);
                lblNombreImagen.setText(listaImagenes[0].getName().replace(".png", ""));
            }
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void btnImgSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImgSiguienteActionPerformed
        // TODO add your handling code here:
        if (listaImagenes.length > 0) {
            if (imagenActual < listaImagenes.length - 1) {
                imagenActual++;
                ImageIcon image = new ImageIcon(listaImagenes[imagenActual].getAbsolutePath());
                Icon fondo = image;
                lblImagen.setIcon(fondo);
                lblNombreImagen.setText(listaImagenes[imagenActual].getName().replace(".png", ""));
            } else {
                imagenActual = 0;
                ImageIcon image = new ImageIcon(listaImagenes[imagenActual].getAbsolutePath());
                Icon fondo = image;
                lblImagen.setIcon(fondo);
                lblNombreImagen.setText(listaImagenes[imagenActual].getName().replace(".png", ""));
            }
        }
    }//GEN-LAST:event_btnImgSiguienteActionPerformed

    private void btnImgAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImgAnteriorActionPerformed
        // TODO add your handling code here:
        if (listaImagenes.length > 0) {
            if (imagenActual > 0) {
                imagenActual--;
                ImageIcon image = new ImageIcon(listaImagenes[imagenActual].getAbsolutePath());
                Icon fondo = image;
                lblImagen.setIcon(fondo);
                lblNombreImagen.setText(listaImagenes[imagenActual].getName().replace(".png", ""));
            } else {
                imagenActual = listaImagenes.length - 1;
                ImageIcon image = new ImageIcon(listaImagenes[imagenActual].getAbsolutePath());
                Icon fondo = image;
                lblImagen.setIcon(fondo);
                lblNombreImagen.setText(listaImagenes[imagenActual].getName().replace(".png", ""));
            }
        }
    }//GEN-LAST:event_btnImgAnteriorActionPerformed

    private void btnAnalizarCadenasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarCadenasActionPerformed
        // TODO add your handling code here:
        if (OLC1_Proyecto1_201900629.listaLexemas.size() > 0) {
            ArrayList<JSONValues> jsons = new ArrayList<>();
            for (int i = 0; i < OLC1_Proyecto1_201900629.listaLexemas.size(); i++) {
                String strvalida = new String();
                Lexema lex = OLC1_Proyecto1_201900629.listaLexemas.get(i);
                String regex = lex.regex;
                int indiceArbol = OLC1_Proyecto1_201900629.listaNombresArboles.indexOf(regex);
                if(indiceArbol >= 0){
                Arbol arbolito = OLC1_Proyecto1_201900629.listaArboles.get(indiceArbol);
                boolean validacion = Arbol.validarCadena(arbolito, lex.lexema, OLC1_Proyecto1_201900629.listaConjuntos);
                if (validacion) {
                    txtSalida.append("La expresión: \"" + lex.lexema + "\" es válida con la expresion regular " + lex.regex + ".\n");
                    strvalida = "Cadena válida";
                } else {
                    txtSalida.append("La expresión: \"" + lex.lexema + "\" no es válida con la expresion regular " + lex.regex + ".\n");
                    strvalida = "Cadena no válida";
                }
                jsons.add(new JSONValues(lex.lexema, lex.regex, strvalida));
                }
                else{
                    txtSalida.append("La expresión regular: " + lex.regex + " no existe.\n");
                }
            }
            ArchivosSalida resultados = new ArchivosSalida();
            if (archivo != null) {
                resultados.escribirJSON(jsons, archivo.getName().replace(".olc", ""));
            } else {
                resultados.escribirJSON(jsons, "defaultName");
            }
            txtSalida.append("Archivo JSON generado...\n");
        }
    }//GEN-LAST:event_btnAnalizarCadenasActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazGrafica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizarCadenas;
    private javax.swing.JButton btnGenerarAutomatas;
    private javax.swing.JButton btnImgAnterior;
    private javax.swing.JButton btnImgSiguiente;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JMenu jmArchivo;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNombreImagen;
    private javax.swing.JMenuItem mnAbrir;
    private javax.swing.JMenuItem mnGuardar;
    private javax.swing.JMenuItem mnGuardarComo;
    private javax.swing.JMenuItem mnNuevo;
    private javax.swing.JTextArea txtEntrada;
    private javax.swing.JTextArea txtSalida;
    // End of variables declaration//GEN-END:variables
}
