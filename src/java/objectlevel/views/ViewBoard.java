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
import objectlevel.models.ModelOfTheWorld;

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
        output +=                   "<link rel='stylesheet' href='css/style.css'>";
        output +=                   "</head><body>";
        return output;
    }
    public String body(String[][] data,List<Event> events){
        String  output  = "<form action='' method='POST'><table class='tictactoe'><tbody>";
        int i,j;            
        for (i=0; i < 3 ; i++) { 
            output += "<tr>";
            for (j=0; j < 3; j++) { 
                if(data[i][j]==null){
                    output += "<td><input type='submit' name='player_move' value='"+i+"_"+j+"' ></td>";
                }else{
                    output += "<td><input type='submit' name='player_move' value='"+data[i][j]+"' disabled></td>";
                }
                /*if($data[$i][$j] != '') 
                        $output .= "disabled";*/
//                output += "></td>";
            }
            output += "</tr>";
        }
        output += "<tr><td colspan='3'><a href='?reset=reset'>REINICIAR</a></td></tr>";
        output += "</tbody></table></form>";

        if(events==null)return output;
        output += "<div class='container'><table class='events'><thead><tr><th>Eventos</th></tr></thead><tbody>";
        for (j=0; j < events.size(); j++) { 
            output += "<tr><td>"+events.get(j).getName() +"</td></tr>";
        }
        output += "</tbody></table></div>";
        return output;
    }
    private String footer(){
        return "</body></html>";
    }    
}