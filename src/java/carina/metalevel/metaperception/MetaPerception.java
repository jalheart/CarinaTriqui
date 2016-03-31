/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.metalevel.metaperception;

import carina.metacore.CognitiveFunction;

/**
 *
 * @author jalheart
 */
abstract public class MetaPerception extends CognitiveFunction{
    private Object percepObject;
    private Object sensors;
    abstract public Object perceive(Object object);

    @Override
    public Object processInformation(Object value) {
        return value;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Gets y Sets.">
    /**
     * @return the percepObject
     */
    public Object getPercepObject() {
        return percepObject;
    }
    /**
     * @param percepObject the percepObject to set
     */
    public void setPercepObject(Object percepObject) {
        this.percepObject = percepObject;
    }
    /**
     * @return the sensors
     */
    public Object getSensors() {
        return sensors;
    }
    /**
     * @param sensors the sensors to set
     */
    public void setSensors(Object sensors) {
        this.sensors = sensors;
    }
    //</editor-fold>
}