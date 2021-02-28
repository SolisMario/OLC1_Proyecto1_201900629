/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores;

/**
 *
 * @author Mario Josue Solis Solorzano
 */
public class Nodo {
    String simbolo = null;
    Nodo izquierdo = null;
    Nodo derecho = null;
    String ID = null;
    String identificador = null;
    String anulable = new String();
    String primeros = "";
    String ultimos = null;

    public Nodo(String simbolo, Nodo izquierdo, Nodo derecho, String ID, String identificador) {
        this.simbolo = simbolo;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.ID = ID;
        this.identificador = identificador;
    }
}
