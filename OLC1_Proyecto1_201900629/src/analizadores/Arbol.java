/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores;

import java.util.LinkedList;

/**
 *
 * @author Mario Josue Solis Solorzano
 */
public class Arbol {
    Nodo root = null;
    String nombreRegex = null;
    LinkedList<String> siguientes = new LinkedList<>();
    LinkedList<String> alfabeto = new LinkedList<>();
    LinkedList<String> transiciones = new LinkedList<>();
    LinkedList<String> estadosAceptacion = new LinkedList<>();
    LinkedList<Conjunto> conjuntos = new LinkedList<>();

    public Arbol(String nombreRegex, Nodo root) {
        this.nombreRegex = nombreRegex;
        this.root = root;
    }
}
