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
    
    public static void setAnulables(Nodo raiz) {
        Nodo tmp = raiz;
        if (tmp != null) {
            setAnulables(tmp.izquierdo);
            if (tmp.identificador != null) {
                tmp.anulable = "N";
            } else {
                reglaAnulable(tmp);
            }
            setAnulables(tmp.derecho);
        }
    }

    public static void reglaAnulable(Nodo actual) {
        switch (actual.simbolo) {
            case "*":
                actual.anulable = "A";
                break;
            case "+":
                if (actual.derecho.anulable.equals("A")) {
                    actual.anulable = "A";
                } else {
                    actual.anulable = "N";
                }
                break;
            case "?":
                actual.anulable = "A";
                break;
            case "|":
                if (actual.izquierdo.anulable.equals("A") || actual.derecho.anulable.equals("A")) {
                    actual.anulable = "A";
                } else {
                    actual.anulable = "N";
                }
                break;
            case ".":
                if (actual.izquierdo.anulable.equals("A") && actual.derecho.anulable.equals("A")) {
                    actual.anulable = "A";
                } else {
                    actual.anulable = "N";
                }
                break;
        }
    }

    public static void setPrimeros(Nodo raiz) {
        Nodo tmp = raiz;
        if (tmp != null) {
            setPrimeros(tmp.izquierdo);
            setPrimeros(tmp.derecho);
            if (tmp.identificador != null) {
                tmp.primeros = tmp.identificador;
            } else {
                reglaPrimeros(tmp);
            }
        }
    }

    public static void reglaPrimeros(Nodo actual) {
        switch (actual.simbolo) {
            case "*":
                actual.primeros = actual.derecho.primeros;
                break;
            case "+":
                actual.primeros = actual.derecho.primeros;
                break;
            case "?":
                actual.primeros = actual.derecho.primeros;
                break;
            case "|":
                actual.primeros = actual.izquierdo.primeros + "," + actual.derecho.primeros;
                break;
            case ".":
                if (actual.izquierdo.anulable.equals("A")) {
                    actual.primeros = actual.izquierdo.primeros + "," + actual.derecho.primeros;
                } else {
                    actual.primeros = actual.izquierdo.primeros;
                }
                break;
        }
    }

    public static void setUltimos(Nodo raiz) {
        Nodo tmp = raiz;
        if (tmp != null) {
            setUltimos(tmp.izquierdo);
            setUltimos(tmp.derecho);
            if (tmp.identificador != null) {
                tmp.ultimos = tmp.identificador;
            } else {
                reglaUltimos(tmp);
            }
        }
    }

    public static void reglaUltimos(Nodo actual) {
        switch (actual.simbolo) {
            case "*":
                actual.ultimos = actual.derecho.ultimos;
                break;
            case "+":
                actual.ultimos = actual.derecho.ultimos;
                break;
            case "?":
                actual.ultimos = actual.derecho.ultimos;
                break;
            case "|":
                actual.ultimos = actual.izquierdo.ultimos + "," + actual.derecho.ultimos;
                break;
            case ".":
                if (actual.derecho.anulable.equals("A")) {
                    actual.ultimos = actual.izquierdo.ultimos + "," + actual.derecho.ultimos;
                } else {
                    actual.ultimos = actual.derecho.ultimos;
                }
                break;
        }
    }

    public static void getSiguientes(Arbol arbol, Nodo raiz) {
        Nodo tmp = raiz;
        if (tmp != null) {
            getSiguientes(arbol, tmp.izquierdo);
            if (tmp.simbolo.equals(".") || tmp.simbolo.equals("*") || tmp.simbolo.equals("+")) {
                reglaSiguientes(arbol, tmp);
            }
            getSiguientes(arbol, tmp.derecho);
        }
    }

    public static void reglaSiguientes(Arbol arbol, Nodo actual) {
        if (actual.simbolo.equals(".")) {
            String[] ultimosC1 = actual.izquierdo.ultimos.split(",");
            String[] primerosC2 = actual.derecho.primeros.split(",");

            for (int i = 0; i < ultimosC1.length; i++) {
                int ultimo = Integer.parseInt(ultimosC1[i]);
                int indexUltimo = ultimo - 1;
                for (int j = 0; j < primerosC2.length; j++) {
                    arbol.siguientes.set(indexUltimo, arbol.siguientes.get(indexUltimo) + primerosC2[j] + ",");
                }
            }
        } else {
            String[] ultimosC1 = actual.derecho.ultimos.split(",");
            String[] primerosC1 = actual.derecho.primeros.split(",");

            for (int i = 0; i < ultimosC1.length; i++) {
                int ultimo = Integer.parseInt(ultimosC1[i]);
                int indexUltimo = ultimo - 1;
                for (int j = 0; j < primerosC1.length; j++) {
                    arbol.siguientes.set(indexUltimo, arbol.siguientes.get(indexUltimo) + primerosC1[j] + ",");
                }
            }
        }
    }

    public static void esteticaSiguientes(Arbol arbol) {
        for (int i = 0; i < arbol.siguientes.size() - 1; i++) {
            String siguientes = arbol.siguientes.get(i);
            siguientes = siguientes.replaceFirst(".$", "");
            arbol.siguientes.set(i, siguientes);
        }
    }

    public static void esteticaTransiciones(Arbol arbol) {
        for (int i = 0; i < arbol.transiciones.size(); i++) {
            String transicion = arbol.transiciones.get(i);
            transicion = transicion.replaceFirst(".$", "");
            arbol.transiciones.set(i, transicion);
        }
    }

    public static void tablaTransiciones(Arbol arbol) {
        int identificadorEstados = 1;
        String estadoInicial = "S0|" + arbol.root.primeros + "|";
        arbol.transiciones.add(estadoInicial);
        for (int i = 0; i < arbol.transiciones.size(); i++) {
            String[] fila = arbol.transiciones.get(i).split("\\|");


            String[] identificadores = fila[1].split(",");

            for (int j = 0; j < arbol.alfabeto.size() - 1; j++) {
                String transicionAlfabeto = new String();
                for (int k = 0; k < identificadores.length; k++) {
                    int identificadorIndex = Integer.parseInt(identificadores[k]) - 1;
                    String simboloIdentificador = arbol.siguientes.get(identificadorIndex).split("\\|")[0];
                    if (simboloIdentificador.replaceAll("\"", "").equals(arbol.alfabeto.get(j).replaceAll("\"", ""))) {
                        transicionAlfabeto += arbol.siguientes.get(identificadorIndex).split("\\|")[2] + ",";
                    }
                }

                String subconjunto = crearSubconjuntos(transicionAlfabeto);
                if (!subconjunto.equals("")) {
                    subconjunto = ordenarSubconjunto(subconjunto);
                    String verifSubconjunto = verificarSubconjunto(subconjunto, arbol.transiciones);
                    if (verifSubconjunto.equals("")) {
                        String estado = "S" + String.valueOf(identificadorEstados);
                        arbol.transiciones.add(estado + "|" + subconjunto + "|");
                        String transActual = arbol.transiciones.get(i);
                        arbol.transiciones.set(i, transActual + estado + "~" + arbol.alfabeto.get(j) + ",");
                        identificadorEstados += 1;
                    } else {
                        String transActual = arbol.transiciones.get(i);
                        arbol.transiciones.set(i, transActual + verifSubconjunto + "~" + arbol.alfabeto.get(j) + ",");
                    }
                }
            }
        }
        esteticaTransiciones(arbol);
        getAceptacion(arbol);
    }

    public static String crearSubconjuntos(String todos) {
        LinkedList<String> estadoLimpio = new LinkedList<>();
        String[] todosSiguientes = todos.split(",");
        for (int i = 0; i < todosSiguientes.length; i++) {
            if (!estadoLimpio.contains(todosSiguientes[i]) && !todosSiguientes[i].equals("")) {
                estadoLimpio.add(todosSiguientes[i]);
            }
        }
        String subconjunto = new String();
        for (int i = 0; i < estadoLimpio.size(); i++) {
            if (i != estadoLimpio.size() - 1) {
                subconjunto += estadoLimpio.get(i) + ",";
            } else {
                subconjunto += estadoLimpio.get(i);
            }
        }
        return subconjunto;
    }

    public static String ordenarSubconjunto(String subconjunto) {
        String subOrdenado = "";
        String[] siguientesStr = subconjunto.split(",");
        int[] siguientesInt = new int[siguientesStr.length];
        for (int i = 0; i < siguientesStr.length; i++) {
            if (!siguientesStr[i].equals("")) {
                siguientesInt[i] = Integer.parseInt(siguientesStr[i]);
            }
        }
        int size = siguientesInt.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (siguientesInt[j] > siguientesInt[j + 1]) {
                    int temp = siguientesInt[j];
                    siguientesInt[j] = siguientesInt[j + 1];
                    siguientesInt[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                subOrdenado += String.valueOf(siguientesInt[i]) + ",";
            } else {
                subOrdenado += String.valueOf(siguientesInt[i]);
            }
        }
        return subOrdenado;
    }

    public static String verificarSubconjunto(String subconjunto, LinkedList<String> transiciones) {
        String estadoHallado = "";
        for (int i = 0; i < transiciones.size(); i++) {
            String conjunto = transiciones.get(i).split("\\|")[1];
            if (subconjunto.equals(conjunto)) {
                estadoHallado = transiciones.get(i).split("\\|")[0];
                break;
            }
        }
        return estadoHallado;
    }

    public static void getAlfabeto(Arbol arbol, Nodo raiz) {
        Nodo tmp = raiz;
        if (tmp != null) {
            getAlfabeto(arbol, tmp.izquierdo);
            if (tmp.identificador != null) {
                if(!arbol.alfabeto.contains(tmp.simbolo) && !tmp.simbolo.equals("#")){
                    arbol.alfabeto.add(tmp.simbolo);
                }
            }
            getAlfabeto(arbol, tmp.derecho);
        }
    }

    public static void getAceptacion(Arbol arbol) {
        LinkedList<String> transiciones = arbol.transiciones;
        String idAceptacion = arbol.root.derecho.identificador;
        for (int i = 0; i < transiciones.size(); i++) {
            String[] transicion = transiciones.get(i).split("\\|");
            String estadoActual = transicion[0];
            String composestado = transicion[1];
            if (composestado.endsWith(idAceptacion)) {
                arbol.estadosAceptacion.add(estadoActual);
            }
        }
    }

    public static void graficarAFD(Arbol arbol) throws IOException {
        LinkedList<String> tablaTrancisiones = arbol.transiciones;
        LinkedList<String> estadosAceptacion = arbol.estadosAceptacion;

        String nombreDot = arbol.nombreRegex + "AFD.dot";
        String nombrePng = arbol.nombreRegex + "AFD.png";

        File archivo = new File(nombreDot);
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("digraph afd {\n");
        bw.write("rankdir=LR;\n");

        for (int i = 0; i < tablaTrancisiones.size(); i++) {
            String estado = tablaTrancisiones.get(i).split("\\|")[0];
            bw.write("\"" + estado + "\"" + "[shape=circle,label=\"" + estado + "\"]\n");
        }

        for (int i = 0; i < estadosAceptacion.size(); i++) {
            String estado = estadosAceptacion.get(i).split("\\|")[0];
            bw.write("\"" + estado + "\"" + "[shape=doublecircle,label=\"" + estado + "\"]\n");
        }

        for (int i = 0; i < tablaTrancisiones.size(); i++) {
            System.out.println(tablaTrancisiones.get(i));
            String[] transicion = tablaTrancisiones.get(i).split("\\|");
            String estadoActual = transicion[0];
            String[] estadosSiguientes;
            if (transicion.length > 2) {
                estadosSiguientes = transicion[2].split(",");
                for (int j = 0; j < estadosSiguientes.length; j++) {
                    String[] composTransicion = estadosSiguientes[j].split("~");
                    bw.write("\"" + estadoActual + "\"" + "->" + "\"" + composTransicion[0] + "\"" + "[label="  + composTransicion[1] + "]\n");
                }
            }
        }

        bw.write("vacio[shape=none, style=invisible]\n");
        bw.write("vacio -> S0[label=" + "\"" + "Inicio" + "\"" + "]\n");

        bw.write("}");
        bw.close();

        Runtime.getRuntime().exec("dot -Tpng " + nombreDot + " -o " + nombrePng);

    }

    public static void reporteSiguienes(Arbol arbol) throws IOException {
        LinkedList<String> tablaSiguientes = arbol.siguientes;

        String nombreDot = arbol.nombreRegex + "Sig.dot";
        String nombrePng = arbol.nombreRegex + "sig.png";

        File archivo = new File(nombreDot);
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("digraph tablaSiguientes {\n");
        bw.write("abc [shape=none, margin=0, label=<\n");
        bw.write("<TABLE BORDER=" + "\"" + 0 + "\"" + " CELLBORDER=" + "\"" + 1 + "\"" + " CELLSPACING=" + "\"" + 0 + "\"" + " CELLPADDING=" + "\"" + 4 + "\"" + ">\n");
        bw.write("<TR>\n");
        bw.write("<TD COLSPAN=" + "\"" + 2 + "\"" + ">Hojas</TD>\n");
        bw.write("<TD>Siguientes</TD>\n");
        bw.write("</TR>\n");
        for (int i = 0; i < tablaSiguientes.size(); i++) {
            if (i < tablaSiguientes.size() - 1) {
                String[] filaTabla = tablaSiguientes.get(i).split("\\|");
                bw.write("<TR>\n");
                bw.write("<TD>" + filaTabla[0] + "</TD>\n");
                bw.write("<TD>" + filaTabla[1] + "</TD>\n");
                bw.write("<TD>" + filaTabla[2] + "</TD>\n");
                bw.write("</TR>\n");
            } else {
                String[] filaTabla = tablaSiguientes.get(i).split("\\|");
                bw.write("<TR>\n");
                bw.write("<TD>" + filaTabla[0] + "</TD>\n");
                bw.write("<TD>" + filaTabla[1] + "</TD>\n");
                bw.write("<TD>--</TD>\n");
                bw.write("</TR>\n");
            }
        }
        bw.write("</TABLE>>];\n");
        bw.write("}");
        bw.close();

        Runtime.getRuntime().exec("dot -Tpng " + nombreDot + " -o " + nombrePng);
    }
    
    public static void reporteTransiciones(Arbol arbol) throws IOException {
        LinkedList<String> tabTransicion = arbol.transiciones;

        String nombreDot = arbol.nombreRegex + "Trans.dot";
        String nombrePng = arbol.nombreRegex + "Trans.png";

        File archivo = new File(nombreDot);
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("digraph tablatransiciones {\n");
        bw.write("abc [shape=none, margin=0, label=<\n");
        bw.write("<TABLE BORDER=" + "\"" + 0 + "\"" + " CELLBORDER=" + "\"" + 1 + "\"" + " CELLSPACING=" + "\"" + 0 + "\"" + " CELLPADDING=" + "\"" + 4 + "\"" + ">\n");
        bw.write("<TR>\n");
        bw.write("<TD ROWSPAN=" + "\"" + 2 + "\"" + ">Estado</TD>\n");
        bw.write("<TD COLSPAN=" + "\"" + arbol.alfabeto.size() + "\"" + ">Terminales</TD>\n");
        bw.write("</TR>\n");
        bw.write("<TR>\n");
        for (int i = 0; i < arbol.alfabeto.size(); i++) {
            bw.write("<TD>" + arbol.alfabeto.get(i) + "</TD>");
        }
        bw.write("</TR>\n");

        for (int i = 0; i < tabTransicion.size(); i++) {
            bw.write("<TR>\n");
            bw.write("<TD>" + tabTransicion.get(i).split("\\|")[0] + "</TD>");
            for (int j = 0; j < arbol.alfabeto.size(); j++) {
                bw.write("<TD>" + getTransicionSimbolo(tabTransicion.get(i), arbol.alfabeto.get(j)) + "</TD>");
            }
            bw.write("</TR>\n");
        }

        bw.write("</TABLE>>];\n");
        bw.write("}");
        bw.close();

        Runtime.getRuntime().exec("dot -Tpng " + nombreDot + " -o " + nombrePng);

    }

    public static String getTransicionSimbolo(String transicion, String simboloAlfabeto) {
        String estado = "--";
        if (transicion.split("\\|").length > 2) {
            String[] estadosTrans = transicion.split("\\|")[2].split(",");
            for (int i = 0; i < estadosTrans.length; i++) {
                String[] estadoSimbolo = estadosTrans[i].split("~");
                if (estadoSimbolo[1].equals(simboloAlfabeto)) {
                    return estadoSimbolo[0];
                }
            }
        }
        return estado;
    }
}
