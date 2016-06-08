/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.views;

import carina.metacore.Event;
import carina.objectlevel.Actuator;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author jalheart
 */
public class ViewBoard extends Actuator{    
    public ViewBoard(PrintWriter out) {
        this.setOutput(out);
    }
    
    public void showBoard(String[][] data,List<Event> events){
        PrintWriter out =(PrintWriter)this.getOutput();
        out.print(this.header());
        out.print(this.body(data, events));
        out.print(this.footer());
    }
    private String header(){
        String output = 	"<html><head><title>Tic Tac Toe</title>";
        output +=                   "<link rel='stylesheet' href='css/triqui.css'>";
        output +=                   "<link rel='stylesheet' href='css/bootstrap.min.css'>";
        output +=                   "</head><body>";
        return output;
    }
    public String body(String[][] data,List<Event> events){
        int i,j;        
        String  output  = "<div class='navbar navbar-fixed-top navbar-inverse'>"+
                                "<div class='container'>"+
                                    "<div class='navbar-header'>"+
                                       "<a class='navbar-brand' href='#'>Carina Triqui</a>"+
                                       "<img src='imgs/triqui.png' class='loguito'/>"+
                                    "</div>"+
                                "</div>"+
                            "</div>";
         output         += "<br/><br/><br/><br/><div class='row'>"+
                            "<div class='col-sm-3'>"+
                                "<div class='panel panel-primary events'>"+
                                    "<div class='panel-heading'>Events</div>"+
                                    "<div class='panel-body scroll_y'>"+
                                        "<table class='table table-hover'>";
        if(events!=null){
            for (i=0; i < events.size(); i++) { 
                output += "<tr><td>"+events.get(i).getName() +"</td></tr>";
            }            
        }
        output +=                       "</table>"+
                                    "</div>"+
                                "</div>"+
                            "</div>"+                            
                            "<div class='col-sm-6'>";
        
        output          +="<form action='' method='POST'><table class='tictactoe'><tbody>";
        for (i=0; i < 3 ; i++) { 
            output += "<tr>";
            for (j=0; j < 3; j++) { 
                if(data[i][j]==null){
                    output += "<td><input type='submit' name='player_move' value='"+i+"_"+j+"' ></td>";
                }else{
                    output += "<td><input type='submit' name='player_move' value='"+data[i][j]+"' disabled></td>";
                }                
            }
            output += "</tr>";
        }
        output += "<tr><td colspan='3'><a class='btn btn-primary bn-lg' href='?reset=reset'><span class='glyphicon glyphicon-refresh'></span>&nbsp;REINICIAR</a></td></tr>";
        output += "</tbody></table></form>";
        
        output +=           "</div>"+
                            "<div class='col-sm-3'>"+                            
                                "<div class='panel panel-primary events'>"+
                                    "<div class='panel-heading'>Mental states</div>"+
                                        "<div class='panel-body scroll_y'>"+
                                            "<table class='table table-hover'>";
            if(events!=null){
                for (i=0; i < events.size(); i++) { 
                    output += "<tr><td>"+events.get(i).getName() +"</td></tr>";
                }            
            }
            output +=                       "</table>"+
                                        "</div>"+
                                    "</div>"+
                                "</div>"+
                            "</div>"+
                        "</div>";        
        
        return output;                      
        //TODO Mostrar tambien estados mentales y el historial de los eventos
    }
    private String footer(){
        String salida;
        salida  ="<a href='./' class='btn btn-primary'>&laquo; Back</a>";
        salida  +="</body></html>";
        return salida;
    }    
}