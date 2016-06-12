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
        TriquiModelOfTheWorld   motw        =(TriquiModelOfTheWorld)this.workingMemory.getModel_of_the_world();
        String                  stateName   =motw.currentToken().equals(motw.getPlayer_token())?"is_player_turn_changed":"is_machine_turn_changed";
        motw.changeTurn();
        WorkingMemory.getInstance().updateMentalState(stateName, true);
        this.workingMemory.syncModelOfTheWorld(this.workingMemory.getModel_of_the_world());
        return null;
    }
}