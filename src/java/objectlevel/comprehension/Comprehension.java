/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.comprehension;

import carina.metalevel.metacomprehesion.MetaComprehension;
import carina.metalevel.metamemory.LongTermMemory;
import carina.metalevel.metamemory.working.WorkingMemory;
import objectlevel.models.Board;
import objectlevel.models.ModelOfTheWorld;

/**
 *
 * @author jalheart
 */
public class Comprehension extends MetaComprehension{
    public Comprehension(WorkingMemory wm,LongTermMemory ltm, ModelOfTheWorld motw) {
        this.setWorkingMemory(wm);
        this.setLongTermMemory(ltm);
        this.setModelOfTheWorld(motw);
    }    
    public Move processInformation(String data) {
        Move   move     =new Move(Integer.parseInt(data.split("_")[0]),Integer.parseInt(data.split("_")[1]));        
        return move;
    }
    @Override
    public Object processInformation(Object value) {
        return value;
    }
}