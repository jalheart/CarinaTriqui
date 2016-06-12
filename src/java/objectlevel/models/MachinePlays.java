/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.memory.WorkingMemory;
import carina.objectlevel.ModelOfTheWorld;
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
        TriquiModelOfTheWorld modelOfTheWorld =(TriquiModelOfTheWorld)workingMemory.getModel_of_the_world();
        //TODO Vamos a incluir metacognición
        RandomAlgorithmStrategy   strategy    =new RandomAlgorithmStrategy(modelOfTheWorld.getBoard().getCells());
        MiniMaxAlgorithmStrategy mmStrategy =new MiniMaxAlgorithmStrategy(modelOfTheWorld.getBoard().getCells(), modelOfTheWorld.currentToken());
//        int[]   position    =(int[])strategy.run();
        int[]   position    =(int[])mmStrategy.run();
        modelOfTheWorld.getBoard().setData(position[0], position[1], modelOfTheWorld.currentToken());
        this.workingMemory.setModel_of_the_world(modelOfTheWorld);
        WorkingMemory.getInstance().updateMentalState("is_machine_played", Boolean.TRUE);
        return null;
    }    
}