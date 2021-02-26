/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1_proyecto1_201900629;

import java.io.FileInputStream;

/**
 *
 * @author Mario Josue Solis Solorzano
 */
public class OLC1_Proyecto1_201900629 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        interpretar("entrada.txt");
    }

    /**
     * Método que interpreta el contenido del archivo que se encuentra en el
     * path que recibe como parámentro
     *
     * @param path ruta del archivo a interpretar
     */
    private static void interpretar(String path) {
        analizadores.Sintactico pars;
        try {
            pars = new analizadores.Sintactico(new analizadores.Lexico(new FileInputStream(path)));
            pars.parse();
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: " + ex.getCause());
        }
    }
}
