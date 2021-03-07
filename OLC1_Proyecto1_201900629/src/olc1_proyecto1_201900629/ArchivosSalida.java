/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1_proyecto1_201900629;

import olc1_proyecto1_201900629.JSONValues;
import Errores.Excepcion;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Mario Josue Solis Solorzano
 */
public class ArchivosSalida {

    String path = OLC1_Proyecto1_201900629.errores.getAbsolutePath();
    File css = new File(path + "\\" + "CSS");

    public void escribirHTML(String name, ArrayList<Excepcion> errores) throws IOException {
        File archivo = new File(OLC1_Proyecto1_201900629.errores.getAbsoluteFile() + "\\" + name + "_error.html");
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("<!doctype html>\n <html lang=\"en\">\n");
        bw.write("<head>\n \t<meta charset=\"utf-8\">\n \t<title>" + name + " Errores</title>\n \t<link rel=\"stylesheet\" type=\"text/css\" href=\"" + css + "\\" + "estilo.css\">\n \t</head>\n");
        bw.write("<body>\n \t<table>\n \t\t<thead>\n");
        bw.write("\t\t\t<tr>\n \t\t\t\t<th colspan=\"5\" style=\"text-align:center;\">Reporte de Errores</th>\n \t\t\t</tr>\n");
        bw.write("\t\t\t<tr>\n \t\t\t\t<th>#</th>\n \t\t\t\t<th>Tipo de Error</th>\n \t\t\t\t<th>Descripción</th>\n \t\t\t\t<th>Línea</th>\n \t\t\t\t<th>Columna</th>\n \t\t\t</tr>\n \t\t</thead>\n");
        bw.write("\t\t\t<tbody>\n");
        for (int i = 0; i < errores.size(); i++) {
            Excepcion error = errores.get(i);
            bw.write("\t\t\t<tr>\n \t\t\t\t<td>" + String.valueOf(i + 1) + "</td>\n \t\t\t\t<td>" + error.tipo + "</td>\n \t\t\t\t<td>" + error.descripcion + "</td>\n \t\t\t\t<td>" + error.linea + "</td>\n \t\t\t\t<td>" + error.columna + "</td>\n \t\t\t</td>\n");

        }
        bw.write("\t\t\t</tbody>\n \t\t</table>\n </body> </html>");

        bw.close();
    }

    public void escribirCSS() throws IOException {
        if (!css.exists()) {
            if (css.mkdirs()) {
            }
        }

        File archivo = new File(css.getAbsolutePath() + "\\" + "estilo.css");
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("*{\n \tmargin:0;\n \tpadding:0;\n \tbox-sizing:0;\n }\n");
        bw.write("body {\n \tfont-family: -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\", Arial, sans-serif;\n \tcolor: #333;\n }\n");
        bw.write("table {\n \ttext-align: left;\n \tline-height: 40px;\n  \tborder-collapse: separate;\n \tborder-spacing: 0;\n \tborder: 2px solid #ed1c40;\n  \twidth: 700px;\n \tmargin: 50px auto;\n \tborder-radius: .25rem;\n }\n");
        bw.write("thead tr:first-child {\n \tbackground: #ed1c40;\n \tcolor: #fff;\n \tborder: none;\n }\n");
        bw.write("th:first-child,\n td:first-child {\n \tpadding: 0 15px 0 20px;\n }\n");
        bw.write("th {\n \tfont-weight: 500;\n }\n");
        bw.write("thead tr:last-child th {\n \tborder-bottom: 3px solid #ddd;\n }\n");
        bw.write("tbody tr:last-child td {\n \tborder: none;\n }\n");
        bw.write("tbody td {\n \tborder-bottom: 1px solid #ddd;\n }\n");
        bw.write("td:last-child {\n \ttext-align: center;\n \tpadding-right: 10px;\n }\n");
        bw.close();
    }

    public void escribirJSON(ArrayList<JSONValues> listaJsons, String name) {
        JSONArray jsarr = new JSONArray();
        for (int i = 0; i < listaJsons.size(); i++) {
            JSONValues j = listaJsons.get(i);
            JSONObject innerObj = new JSONObject();
            innerObj.put("ExpresionRegular", j.expresionRegular);
            innerObj.put("valor", j.valor);
            innerObj.put("Resultado", j.resultado);
            jsarr.add(innerObj);
        }
        try {
            FileWriter file = new FileWriter("SALIDAS_201900629/" + name + ".json");
            file.write(jsarr.toJSONString());
            file.close();
        } catch (IOException e) {
        }
    }
}
