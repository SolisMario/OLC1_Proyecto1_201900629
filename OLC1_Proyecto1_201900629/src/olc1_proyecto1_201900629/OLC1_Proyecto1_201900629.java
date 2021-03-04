/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1_proyecto1_201900629;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author Mario Josue Solis Solorzano
 */
public class OLC1_Proyecto1_201900629 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {
        // TODO code application logic here
        File arboles = new File("ARBOLES_201900629");
        File afnds = new File("AFND_201900629");
        File siguientes = new File("SIGUIENTES_201900629");
        File transiciones = new File("TRANSICIONES_201900629");
        File afds = new File("AFD_201900629");
        File errores = new File("ERRORES_201900629");
        File salidas = new File("SALIDAS_201900629");
        File dot = new File("Archivos DOT");
        
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
        
        interpretar("entrada.txt");
        InterfazGrafica inter = new InterfazGrafica();
        inter.setVisible(true);
    }

    /**
     * Método que interpreta el contenido del archivo que se encuentra en el
     * path que recibe como parámentro
     *
     * @param path ruta del archivo a interpretar
     */
    private static void interpretar(String path) throws FileNotFoundException, Exception {
        analizadores.Sintactico pars;
            pars = new analizadores.Sintactico(new analizadores.Lexico(new FileInputStream(path)));
            pars.parse();
    }
}
