/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    
    public static void getHoja(Arbol arbol) {
        Nodo tmp = arbol.root;
        insertarHojaSiguientes(arbol, tmp);
    }

    public static void insertarHojaSiguientes(Arbol arbol, Nodo actual) {
        Nodo tmp = actual;
        if (tmp != null) {
            insertarHojaSiguientes(arbol, actual.izquierdo);
            if (actual.identificador != null) {
                arbol.siguientes.add(actual.simbolo + "|" + actual.identificador + "|");
            }
            insertarHojaSiguientes(arbol, actual.derecho);
        }
    }
    
    public static void imprimirArbol(Arbol arbol) throws IOException {
        Nodo tmp = null;
        tmp = arbol.root;

        String nombreDot = arbol.nombreRegex + ".dot";
        String nombrePng = arbol.nombreRegex + ".png";

        File archivo = new File(nombreDot);
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("digraph afd {\n");
        bw.write("rankdir=TD;\n");

        bw.close();

        escribir_rec(tmp, nombreDot);

        FileWriter fw2 = new FileWriter(archivo, true);
        BufferedWriter bw2 = new BufferedWriter(fw2);

        bw2.write("}");
        bw2.close();

        Runtime.getRuntime().exec("dot -Tpng " + nombreDot + " -o " + nombrePng);
    }

    public static void escribir_rec(Nodo raiz, String nombreDot) throws IOException {
        if (raiz != null) {
            File archivo = new File(nombreDot);
            FileWriter fw = new FileWriter(archivo, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String identificadorR = " ";
            if (raiz.identificador != null) {
                identificadorR = raiz.identificador;
            }

            bw.write("\"" + raiz.ID + "\"" + "[shape=none, margin=0, label=<");
            bw.write("<TABLE BORDER=" + "\"" + "0" + "\"" + " CELLBORDER=" + "\"" + "0" + "\"" + " CELLSPACING=" + "\"" + "0" + "\"" + " CELLPADDING=" + "\"" + "1" + "\"" + " weight='bold'>\n");
            bw.write("<TR><TD ROWSPAN=" + "\"" + "3" + "\"" + "><FONT COLOR=" + "\"" + "#3498DB" + "\"" + ">" + raiz.primeros + "</FONT></TD>\n");
            bw.write("<TD CELLPADDING=" + "\"" + "0" + "\"" + "><FONT COLOR=" + "\"" + "red" + "\"" + ">" + raiz.anulable + "</FONT></TD>\n");
            bw.write("<TD ROWSPAN=" + "\"" + "3" + "\"" + "><FONT COLOR=" + "\"" + "#F5B041" + "\"" + ">" + raiz.ultimos + "</FONT></TD></TR>\n");
            bw.write("<TR><TD style=" + "\"" + "rounded" + "\"" + " BORDER=" + "\"" + "1" + "\"" + ">" + raiz.simbolo + "</TD></TR>\n");
            bw.write("<TR><TD CELLPADDING=" + "\"" + "0" + "\"" + "><FONT COLOR=" + "\"" + "#239B56" + "\"" + ">" + identificadorR + "</FONT></TD></TR>\n");
            bw.write("</TABLE>>];\n");

            if (raiz.izquierdo != null) {

                String identificadorI = " ";
                if (raiz.izquierdo.identificador != null) {
                    identificadorI = raiz.izquierdo.identificador;
                }

                bw.write("\"" + raiz.izquierdo.ID + "\"" + "[shape=none, margin=0, label=<");
                bw.write("<TABLE BORDER=" + "\"" + "0" + "\"" + " CELLBORDER=" + "\"" + "0" + "\"" + " CELLSPACING=" + "\"" + "0" + "\"" + " CELLPADDING=" + "\"" + "1" + "\"" + ">\n");
                bw.write("<TR><TD ROWSPAN=" + "\"" + "3" + "\"" + "><FONT COLOR=" + "\"" + "#3498DB" + "\"" + ">" + raiz.izquierdo.primeros + "</FONT></TD>\n");
                bw.write("<TD CELLPADDING=" + "\"" + "0" + "\"" + "><FONT COLOR=" + "\"" + "red" + "\"" + ">" + raiz.izquierdo.anulable + "</FONT></TD>\n");
                bw.write("<TD ROWSPAN=" + "\"" + "3" + "\"" + "><FONT COLOR=" + "\"" + "#F5B041" + "\"" + ">" + raiz.izquierdo.ultimos + "</FONT></TD></TR>\n");
                bw.write("<TR><TD style=" + "\"" + "rounded" + "\"" + " BORDER=" + "\"" + "1" + "\"" + ">" + raiz.izquierdo.simbolo + "</TD></TR>\n");
                bw.write("<TR><TD CELLPADDING=" + "\"" + "0" + "\"" + "><FONT COLOR=" + "\"" + "#239B56" + "\"" + ">" + identificadorI + "</FONT></TD></TR>\n");
                bw.write("</TABLE>>];\n");
                bw.write("\"" + raiz.ID + "\"" + "->" + "\"" + raiz.izquierdo.ID + "\"" + "\n");
            }
            if (raiz.derecho != null) {

                String identificadorD = "";
                if (raiz.derecho.identificador != null) {
                    identificadorD = raiz.derecho.identificador;
                }

                bw.write("\"" + raiz.derecho.ID + "\"" + "[shape=none, margin=0, label=<");
                bw.write("<TABLE BORDER=" + "\"" + "0" + "\"" + " CELLBORDER=" + "\"" + "0" + "\"" + " CELLSPACING=" + "\"" + "0" + "\"" + " CELLPADDING=" + "\"" + "1" + "\"" + ">\n");
                bw.write("<TR><TD ROWSPAN=" + "\"" + "3" + "\"" + "><FONT COLOR=" + "\"" + "#3498DB" + "\"" + ">" + raiz.derecho.primeros + "</FONT></TD>\n");
                bw.write("<TD CELLPADDING=" + "\"" + "0" + "\"" + "><FONT COLOR=" + "\"" + "red" + "\"" + ">" + raiz.derecho.anulable + "</FONT></TD>\n");
                bw.write("<TD ROWSPAN=" + "\"" + "3" + "\"" + "><FONT COLOR=" + "\"" + "#F5B041" + "\"" + ">" + raiz.derecho.ultimos + "</FONT></TD></TR>\n");
                bw.write("<TR><TD style=" + "\"" + "rounded" + "\"" + " BORDER=" + "\"" + "1" + "\"" + ">" + raiz.derecho.simbolo + "</TD></TR>\n");
                bw.write("<TR><TD CELLPADDING=" + "\"" + "0" + "\"" + "><FONT COLOR=" + "\"" + "#239B56" + "\"" + ">" + identificadorD + "</FONT></TD></TR>\n");
                bw.write("</TABLE>>];\n");
                bw.write("\"" + raiz.ID + "\"" + "->" + "\"" + raiz.derecho.ID + "\"" + "\n");
            }
            bw.close();

            escribir_rec(raiz.izquierdo, nombreDot);
            escribir_rec(raiz.derecho, nombreDot);
        }
    }
}
