/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.actuators;

import carina.metalevel.MetaActuator.MetaActuator;
import carina.metalevel.metamemory.MetaModelOfTheWorld;
import carina.metalevel.metamemory.LongTermMemory;
import carina.metalevel.metamemory.working.WorkingMemory;
import objectlevel.comprehension.Move;
import objectlevel.models.Board;
import objectlevel.models.ModelOfTheWorld;

/**
 *
 * @author jalheart
 */
public class ModelOfTheWorldActuator extends MetaActuator{    
    public ModelOfTheWorldActuator(WorkingMemory wm,LongTermMemory ltm,Object environment) {
        super(environment);//Para este caso el environment es el modelo del mundo        
        this.setModelOfTheWorld((MetaModelOfTheWorld) environment);
        this.setWorkingMemory(wm);
        this.setLongTermMemory(ltm);
    }
    public void play(Move positions,String player){
        Board   board   =(Board)this.getModelOfTheWorld().getModelOfTheWorld();
        board.setData(positions.fila, positions.columna, player);
        ((ModelOfTheWorld)this.getModelOfTheWorld()).updateModelOfTheWorld(board);
        int movesMade   = this.getWorkingMemory().remember("moves_made")==null?0:(int)this.getWorkingMemory().remember("moves_made");
        movesMade++;
        this.getWorkingMemory().memorize("moves_made", movesMade);
    }
}