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
abstract public class MetaSensor extends CognitiveFunction{
    private Object percepObject;
    public void percive(Object object){
        this.percepObject   =object;
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
    //</editor-fold>
}