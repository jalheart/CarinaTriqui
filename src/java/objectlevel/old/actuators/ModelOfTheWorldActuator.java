/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.old.actuators;

import carina.metalevel.old.MetaActuator.MetaActuator;
import carina.metalevel.old.metamemory.MetaModelOfTheWorld;
import carina.metalevel.old.metamemory.LongTermMemory;
import carina.memory.WorkingMemory;
import objectlevel.old.comprehension.Move;
import objectlevel.old.models.Board;
import objectlevel.old.models.ModelOfTheWorld;

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