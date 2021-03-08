/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1_proyecto1_201900629;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import analizadores.Arbol;
import analizadores.Conjunto;
import analizadores.Lexema;

/**
 *
 * @author Mario Josue Solis Solorzano
 */
public class OLC1_Proyecto1_201900629 {
    
    public static File dot = new File("DOT");
    public static File arboles = new File("ARBOLES_201900629");
    public static File afnds = new File("AFND_201900629");
    public static File siguientes = new File("SIGUIENTES_201900629");
    public static File transiciones = new File("TRANSICIONES_201900629");
    public static File afds = new File("AFD_201900629");
    public static File errores = new File("ERRORES_201900629");
    public static File salidas = new File("SALIDAS_201900629");

    /**
     *
     */
    public static LinkedList<String> listaNombresArboles = new LinkedList<>();
    public static LinkedList<Arbol> listaArboles = new LinkedList<Arbol>();
    public static LinkedList<Conjunto> listaConjuntos = new LinkedList<Conjunto>();
    public static LinkedList<Lexema> listaLexemas = new LinkedList<Lexema>();

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {
        // TODO code application logic here
        if (!arboles.exists()) {
            if (arboles.mkdirs()) {
            }
        }
        if (!afnds.exists()) {
            if (afnds.mkdirs()) {
            }
        }
        if (!afds.exists()) {
            if (afds.mkdirs()) {
            }
        }
        if (!siguientes.exists()) {
            if (siguientes.mkdirs()) {
            }
        }
        if (!transiciones.exists()) {
            if (transiciones.mkdirs()) {
            }
        }
        if (!errores.exists()) {
            if (errores.mkdirs()) {
            }
        }
        if (!salidas.exists()) {
            if (salidas.mkdirs()) {
            }
        }
        if (!dot.exists()) {
            if (dot.mkdirs()) {
            }
        }
        
        
        InterfazGrafica inter = new InterfazGrafica();
        inter.setLocationRelativeTo(null);
        inter.setVisible(true);
        ArchivosSalida archivos = new ArchivosSalida();
        archivos.escribirCSS();
    }
}
