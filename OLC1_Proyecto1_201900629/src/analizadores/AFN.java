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
public class AFN {
    
    public static class TransicionAFN{
        public int inicio;
        public String simbolo;
        public int fin;

        public TransicionAFN(int inicio, String simbolo, int fin) {
            this.inicio = inicio;
            this.simbolo = simbolo;
            this.fin = fin;
        }
    }
    
    public static void graficarAFN(Arbol arbol) throws IOException{
        String nombreDot = "DOT/" +  arbol.nombreRegex + "AFN.dot";
        String nombrePng = arbol.nombreRegex + "AFN.png";

        File archivo = new File(nombreDot);
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("digraph afd {\n");
        bw.write("rankdir=LR;\n");
        bw.write("node [shape=circle];\n");


        LinkedList<TransicionAFN> transiciones = metodoThompson(arbol.root.izquierdo);
        transiciones.removeLast();
        
        for (int i = 0; i < transiciones.size(); i++) {
            bw.write("\"" + transiciones.get(i).inicio + "\"" + "->" + "\"" + transiciones.get(i).fin + "\"" + "[label="  + "\"" + transiciones.get(i).simbolo.replaceAll("\"", "") + "\"" + "]\n");
        }
        
        bw.write(transiciones.getLast().fin + "[shape=doublecircle,label=" + transiciones.getLast().fin + "]\n");
        bw.write("}");
        bw.close();

        Runtime.getRuntime().exec("dot -Tpng " + nombreDot + " -o AFND_201900629/" + nombrePng);
    }
    
    public static LinkedList<TransicionAFN> metodoThompson(Nodo tmp){
        LinkedList<TransicionAFN> transiciones = new LinkedList<>();
        if(tmp!=null){
            LinkedList<TransicionAFN> izquierdo = metodoThompson(tmp.izquierdo);
            LinkedList<TransicionAFN> derecho = metodoThompson(tmp.derecho);
            int fin = 0;
            switch(tmp.simbolo){
                case ".":
                    izquierdo.removeLast();
                    int join = izquierdo.getLast().fin;
                    for (int i = 0; i < derecho.size(); i++) {
                        derecho.get(i).inicio += join;
                        derecho.get(i).fin += join;
                    }
                    transiciones.addAll(izquierdo);
                    transiciones.addAll(derecho);
                    break;
                case "+":
                    transiciones.addLast(new TransicionAFN(0, "$", 1));
                    for (int i = 0; i < derecho.size(); i++) {
                        derecho.get(i).inicio++;
                        derecho.get(i).fin++;
                    }
                    fin = derecho.getLast().inicio + 1;
                    transiciones.add(new TransicionAFN(fin - 1, "$", 1));
                    derecho.getLast().fin = fin;
                    derecho.getLast().simbolo = "$";
                    transiciones.addAll(derecho);
                    transiciones.addLast(new TransicionAFN(fin, "$", -1));
                    break;
                case "|":
                    transiciones.addLast(new TransicionAFN(0, "$", 1));
                    for (int i = 0; i < izquierdo.size(); i++) {
                        izquierdo.get(i).inicio++;
                        izquierdo.get(i).fin++;
                    }
                    for (int i = 0; i < derecho.size(); i++) {
                        derecho.get(i).inicio += izquierdo.getLast().inicio + 1;
                        derecho.get(i).fin += izquierdo.getLast().inicio + 1;
                    }
                    transiciones.add(new TransicionAFN(0, "$", derecho.getFirst().inicio));
                    fin = derecho.getLast().inicio + 1;
                    izquierdo.getLast().fin = fin;
                    derecho.getLast().fin = fin;
                    izquierdo.getLast().simbolo = "$";
                    derecho.getLast().simbolo = "$";
                    transiciones.addAll(izquierdo);
                    transiciones.addAll(derecho);
                    transiciones.addLast(new TransicionAFN(fin, "", -1));
                    break;
                case "?":
                    transiciones.addLast(new TransicionAFN(0, "$", 1));
                    for (int i = 0; i < derecho.size(); i++) {
                        derecho.get(i).inicio++;
                        derecho.get(i).fin++;
                    }
                    fin = derecho.getLast().inicio + 1;
                    transiciones.add(new TransicionAFN(0, "$", fin));
                    derecho.getLast().fin = fin;
                    derecho.getLast().simbolo = "$";
                    transiciones.addAll(derecho);
                    transiciones.addLast(new TransicionAFN(fin, "$", -1));
                    break;
                case "*":
                    transiciones.addLast(new TransicionAFN(0, "$", 1));
                    for (int i = 0; i < derecho.size(); i++) {
                        derecho.get(i).inicio++;
                        derecho.get(i).fin++;
                    }
                    fin = derecho.getLast().inicio+ 1;
                    transiciones.add(new TransicionAFN(0, "$", fin));
                    transiciones.add(new TransicionAFN(fin - 1, "$", 1));
                    derecho.getLast().fin = fin;
                    derecho.getLast().simbolo = "$";
                    transiciones.addAll(derecho);
                    transiciones.addLast(new TransicionAFN(fin, "$", -1));
                    break;
                default:
                    TransicionAFN transSimbolo = new TransicionAFN(0, tmp.simbolo, 1);
                    TransicionAFN transVacia = new TransicionAFN(1, "", -1);
                    transiciones.addLast(transSimbolo);
                    transiciones.addLast(transVacia);
            }
        }
        return transiciones;
    }
}
