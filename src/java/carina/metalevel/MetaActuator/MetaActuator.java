/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.metalevel.MetaActuator;

import carina.metalevel.metamemory.MetaModelOfTheWorld;
import carina.metalevel.metamemory.LongTermMemory;
import carina.metalevel.metamemory.working.WorkingMemory;

/**
 *
 * @author jalheart
 */
abstract public class MetaActuator {
    private MetaModelOfTheWorld modelOfTheWorld =null;
    private WorkingMemory workingMemory         =null;
    private LongTermMemory longTermMemory       =null;
    private Object environment                  =null;

    public MetaActuator(Object environment) {
        this.environment    =environment;
    }    
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
    /**
     * @return the environment
     */
    public Object getEnvironment() {
        return environment;
    }

    /**
     * @param environment the environment to set
     */
    public void setEnvironment(Object environment) {
        this.environment = environment;
    }
    //</editor-fold>
}