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
public class ChangeTurn extends ReasoningTask{
    private WorkingMemory workingMemory;

    @Override
    public Object run() {
        this.workingMemory  =WorkingMemory.getInstance();
        ((TriquiModelOfTheWorld)this.workingMemory.getModel_of_the_world()).changeTurn();
        this.workingMemory.syncModelOfTheWorld(this.workingMemory.getModel_of_the_world());
        return null;
    }
}