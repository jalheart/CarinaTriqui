/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.old.actuators;

import carina.metalevel.old.MetaActuator.MetaActuator;
import java.io.PrintWriter;

/**
 *
 * @author jalheart
 */
public class ViewBoardActuator extends MetaActuator{    
    public ViewBoardActuator(Object environment) {
        super(environment);
    }    
    public void showBoard(String[][] data,String winner){
        String salida   =this.header();        
        salida  +=winner==""?this.body(data):this.body(data,winner);
        salida  +=this.footer();        
        this.getEnvironment().println(salida);
    }
    private String header(){
        String  headStr;			
        headStr = "<html><head><title>Tic Tac Toe</title>";
        headStr +=  "<link rel='stylesheet' href='css/style.css'>";
        headStr += 	"</head><body>";
        return headStr;
    }    
    private String body(String[][] data,String winner){
        String salida   ="<form action='' method='POST'><table><tbody>";
        for (int i=0; i < data.length ; i++) {
            salida += "<tr>";
            for (int j=0; j < data[i].length; j++) {                    
                    salida += "<td><input type='submit' value='"+data[i][j]+"' name='posicion' ";
                    salida += "disabled";                    
                    salida += "></td>";
            }
            salida += "</tr>";
        }
        salida += "<tr><td colspan='"+data.length+"' class='reset'><a href='?reset=1'>REINICIAR</a></td></tr>";
        salida += "<tr><td colspan='"+data.length+"' class='winrow'>LAS "+winner+" GANAN</td></tr>";        
        salida += "</tbody></table></form>";        
        return salida;
    }
    private String body(String[][] data){
        String salida   ="<form action='' method='POST'><table><tbody>";
        for (int i=0; i < data.length ; i++) {
            salida += "<tr>";
            for (int j=0; j < data[i].length; j++) {
                    if(data[i][j]!=""){
                        salida += "<td><input type='submit' value='"+data[i][j]+"' name='posicion' ";
                        salida += "disabled";
                    }else{
                        salida += "<td><input type='submit' value='"+i+"_"+j+"' name='posicion' ";
                    }
                    salida += "></td>";
            }
            salida += "</tr>";
        }
        salida += "<tr><td colspan='"+data.length+"' class='reset'><a href='?reset=1'>REINICIAR</a></td></tr>";
        salida += "</tbody></table></form>";        
        return salida;
    }
    private String footer(){        
        return "</body></html>";
    }    

    @Override
    public PrintWriter getEnvironment() {
        return (PrintWriter)super.getEnvironment(); //To change body of generated methods, choose Tools | Templates.
    }
}
