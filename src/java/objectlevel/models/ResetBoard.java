/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.memory.WorkingMemory;
import carina.objectlevel.ReasoningTask;

/**
 *
 * @author jalheart
 */
public class ResetBoard extends ReasoningTask{
    @Override
    public Object run() {
        WorkingMemory workingMemory =WorkingMemory.getInstance();
        TriquiModelOfTheWorld modelOfTheorld  =(TriquiModelOfTheWorld)workingMemory.getModel_of_the_world();
        modelOfTheorld.setBoard(new Board());
        modelOfTheorld.getBoard().create();
        workingMemory.setModel_of_the_world(modelOfTheorld);
        WorkingMemory.getInstance().updateMentalState("is_board_modified", Boolean.TRUE);
        return null;
    }    
}