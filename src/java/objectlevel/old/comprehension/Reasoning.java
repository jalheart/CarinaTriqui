/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.old.comprehension;

import carina.metalevel.old.metacomprehesion.MetaReasoning;
import carina.metalevel.old.metamemory.LongTermMemory;
import carina.memory.WorkingMemory;
import com.sun.glass.events.ViewEvent;
import objectlevel.old.comprehension.minimax.MinimaxTTT;
import objectlevel.old.models.Board;
import objectlevel.old.models.ModelOfTheWorld;

/**
 *
 * @author jalheart
 */
public class Reasoning extends MetaReasoning{
    public Reasoning(WorkingMemory wm,LongTermMemory ltm, ModelOfTheWorld motw) {
        this.setWorkingMemory(wm);
        this.setLongTermMemory(ltm);
        this.setModelOfTheWorld(motw);
    }
    public Move selectMove(){
//        return randomMove();
        return minimax();
    }
    private Move minimax(){
        String[][] cells    =((Board)this.getModelOfTheWorld().getModelOfTheWorld()).getCells();
        MinimaxTTT  mm=new MinimaxTTT(cells, "O");
        return mm.getMove();
    }
    public Move randomMove(){
        Move move   =new Move(0,0);        
        do {            
            move.fila       =(int)Math.floor(Math.random()*3);
            move.columna    =(int)Math.floor(Math.random()*3);
        } while (!this.canPlay(move));
        return move;
    }
    public boolean canPlay(){
        if(this.getWorkingMemory().remember("moves_made")==null){
            return true;
        }
        return ((int)this.getWorkingMemory().remember("moves_made"))<9;
    }
    public boolean canPlay(Move move){        
        Board   board   =(Board)this.getModelOfTheWorld().getModelOfTheWorld();
        return board.getData(move.fila, move.columna)=="";
    }
    public boolean win(String player,Move move){        
        Board   board   =(Board)this.getModelOfTheWorld().getModelOfTheWorld();
        String[][] cells    =board.getCells();        
        return checkVertival(player, move, cells) || checkHorizontal(player, move, cells) || checkDiagonal(player, cells);
    }
    private boolean checkVertival(String player,Move move,String[][] cells){
        return cells[0][move.columna]==player && cells[1][move.columna]==player && cells[2][move.columna]==player;
    }
    private boolean checkHorizontal(String player,Move move,String[][] cells){
        return cells[move.fila][0]==player && cells[move.fila][1]==player && cells[move.fila][2]==player;
    }
    private boolean checkDiagonal(String player,String[][] cells){
        boolean winTmp  =cells[0][0]==player && cells[1][1]==player && cells[2][2]==player;
        winTmp  =winTmp || (cells[0][2]==player && cells[1][1]==player && cells[2][0]==player);
        return winTmp;
    }
    @Override
    public Object processInformation(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}