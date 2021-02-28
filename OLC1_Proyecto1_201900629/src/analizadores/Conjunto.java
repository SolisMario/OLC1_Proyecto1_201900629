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
public class Conjunto {
    String nombre = null;
    LinkedList<String> componentes = new LinkedList<>();

    public Conjunto(String nombre, LinkedList<String> componentes) {
        this.nombre = nombre;
        this.componentes = componentes;
    }
}
