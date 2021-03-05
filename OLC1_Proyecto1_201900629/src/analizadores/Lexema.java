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
public class Lexema {
    String regex = null;
    String lexema = null;

    public Lexema(String regex, String lexema) {
        this.lexema = lexema;
        this.regex = regex;
    }

    
}
