/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.metalevel;

import carina.metalevel.MetaActuator.MetaActuator;
import carina.metalevel.metacomprehesion.MetaComprehension;
import carina.metalevel.metacomprehesion.MetaReasoning;
import carina.metalevel.metamemory.LongTermMemory;
import carina.metalevel.metamemory.Memory;
import carina.metalevel.metamemory.MetaModelOfTheWorld;
import carina.metalevel.metaperception.MetaPerception;

/**
 *
 * @author jalheart
 */
abstract public class MetaAgent {
    private MetaPerception currentPerception;
    private MetaModelOfTheWorld currentModelOfTheWorld;
    private MetaComprehension comprehension;
    private MetaReasoning reasoning;
    private MetaActuator actuator;
    private Memory longTermMemory;
    /**
     * Inicializa y configura los elementos relacionados con el agente
     */
    abstract public void initialize();
    /**
     * Pone en ejecución al agente
     */
    abstract public void run();
    
    // <editor-fold defaultstate="collapsed" desc="GETs y SETs">
    /**
     * @return the currentPerception
     */
    public MetaPerception getCurrentPerception() {
        return currentPerception;
    }
    /**
     * @param currentPerception the currentPerception to set
     */
    public void setCurrentPerception(MetaPerception currentPerception) {
        this.currentPerception = currentPerception;
    }
    /**
     * @return the currentModelOfTheWorld
     */
    public MetaModelOfTheWorld getCurrentModelOfTheWorld() {
        return currentModelOfTheWorld;
    }
    /**
     * @param currentModelOfTheWorld the currentModelOfTheWorld to set
     */
    public void setCurrentModelOfTheWorld(MetaModelOfTheWorld currentModelOfTheWorld) {
        this.currentModelOfTheWorld = currentModelOfTheWorld;
    }
    /**
     * @return the comprehension
     */
    public MetaComprehension getComprehension() {
        return comprehension;
    }

    /**
     * @param comprehension the comprehension to set
     */
    public void setComprehension(MetaComprehension comprehension) {
        this.comprehension = comprehension;
    }
    /**
     * @return the actuator
     */
    public MetaActuator getActuator() {
        return actuator;
    }

    /**
     * @param actuator the actuator to set
     */
    public void setActuator(MetaActuator actuator) {
        this.actuator = actuator;
    }
    /**
     * @return the reasoning
     */
    public MetaReasoning getReasoning() {
        return reasoning;
    }

    /**
     * @param reasoning the reasoning to set
     */
    public void setReasoning(MetaReasoning reasoning) {
        this.reasoning = reasoning;
    }
    /**
     * @return the longTermMemory
     */
    public Memory getLongTermMemory() {
        return longTermMemory;
    }

    /**
     * @param longTermMemory the longTermMemory to set
     */
    public void setLongTermMemory(Memory longTermMemory) {
        this.longTermMemory = longTermMemory;
    }
    // </editor-fold >
}