/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.metalevel.old.metacomprehesion;

import carina.metacore.CognitiveFunction;
import carina.metalevel.old.metamemory.MetaModelOfTheWorld;
import carina.metalevel.old.metamemory.LongTermMemory;
import carina.memory.WorkingMemory;

/**
 *
 * @author jalheart
 */
abstract public class MetaReasoning extends CognitiveFunction{
    private MetaModelOfTheWorld modelOfTheWorld;
    private WorkingMemory workingMemory;
    private LongTermMemory longTermMemory;
    
    // <editor-fold defaultstate="collapsed" desc="GETs y SETs">
    /**
     * @return the modelOfTheWorld
     */
    public MetaModelOfTheWorld getModelOfTheWorld() {
        return modelOfTheWorld;
    }

    /**
     * @param modelOfTheWorld the modelOfTheWorld to set
     */
    public void setModelOfTheWorld(MetaModelOfTheWorld modelOfTheWorld) {
        this.modelOfTheWorld = modelOfTheWorld;
    }

    /**
     * @return the workingMemory
     */
    public WorkingMemory getWorkingMemory() {
        return workingMemory;
    }

    /**
     * @param workingMemory the workingMemory to set
     */
    public void setWorkingMemory(WorkingMemory workingMemory) {
        this.workingMemory = workingMemory;
    }

    /**
     * @return the longTermMemory
     */
    public LongTermMemory getLongTermMemory() {
        return longTermMemory;
    }

    /**
     * @param longTermMemory the longTermMemory to set
     */
    public void setLongTermMemory(LongTermMemory longTermMemory) {
        this.longTermMemory = longTermMemory;
    }
    //</editor-fold>
}