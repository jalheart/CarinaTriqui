/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.memory.WorkingMemory;
import carina.objectlevel.ReasoningTask;
import java.util.Optional;

/**
 *
 * @author jalheart
 */
public class VerifyWinner extends ReasoningTask{
    private WorkingMemory   workingMemory;
    /**
     * @return Boolean Devuelve true si la tarea se ejecuta exitosamente
     */
    public Object run() {
        this.workingMemory  =WorkingMemory.getInstance();
        TriquiModelOfTheWorld motw  =(TriquiModelOfTheWorld)workingMemory.getModel_of_the_world();
        String stateName    =motw.currentToken().equals(motw.getPlayer_token())?"is_player_winner_verified":"is_machine_winner_verified";        
        String[][]  columns =this.transposition(motw.getBoard().getCells());
        String[][]  temp    =this.diagonal(motw.getBoard().getCells());
        String[]    diagonal=temp[0];
        String[]    cross   =temp[1];
        int i,countToken;
        for (i = 0; i < 3; i++) {
            String[]    row = motw.getBoard().getCells()[i];
            countToken  =this.tell(row, motw.currentToken());
            if(countToken==3){
                workingMemory.updateMentalState(stateName,true);
                updateTaskState(true, true, true);
                return true;
            }
        }
        for (i = 0; i < 3; i++) {
            String[] column =columns[i];
            countToken      =this.tell(column, motw.currentToken());
            if(countToken==3){
                workingMemory.updateMentalState(stateName,true);
                updateTaskState(true, true, true);
                return true;
            }
        }
        int count_d =this.tell(diagonal, motw.currentToken());
        int count_t =this.tell(cross, motw.currentToken());
        if(count_d==3 || count_t==3){
            workingMemory.updateMentalState(stateName,true);
            updateTaskState(true, true, true);
            return true;
        }
        workingMemory.updateMentalState(stateName,false);
        updateTaskState(true, true, false);
        return true;
    }    
    private String[][] transposition(String[][] cells){
        String[][] temp = new String[3][3];
        int i,j;
        for (i=0; i < 3; i++) { 
            for (j=0; j < 3; j++) { 
                temp[j][i] = cells[i][j];
            }
        }
        return temp;
    }
    private String[][] diagonal(String[][] cells){
        String[][] temps = new String[2][3];        
        int i;
        for (i=0; i < 3; i++) { 
            temps[0][i]=cells[i][i];
            temps[1][i]=cells[i][2-i];
        }
        return temps;
    }
    private int tell(String[] cells, String token){
        int temp = 0;
        int i;
        for (i=0; i < 3; i++) { 
            if(cells[i]!=null && cells[i].equals(token))
                temp++;
        }
        return temp;
    }
}