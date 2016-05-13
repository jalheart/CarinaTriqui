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
public class MachinePlays extends ReasoningTask{
    private WorkingMemory workingMemory;
    @Override
    public Object run() {
        this.workingMemory  =WorkingMemory.getInstance();
        RandomAlgorithmStrategy   strategy    =new RandomAlgorithmStrategy(this.workingMemory.getModel_of_the_world().getBoard().getCells());
        int[]   position    =(int[])strategy.run();
        ModelOfTheWorld modelOfTheWorld =workingMemory.getModel_of_the_world();
        modelOfTheWorld.getBoard().setData(position[0], position[1], modelOfTheWorld.currentToken());
        this.workingMemory.setModel_of_the_world(modelOfTheWorld);
        return null;
    }
    
}