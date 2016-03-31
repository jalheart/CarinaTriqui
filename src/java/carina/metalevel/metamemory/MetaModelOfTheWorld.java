/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.metalevel.metamemory;

/**
 *
 * @author jalheart
 */
abstract public class MetaModelOfTheWorld {
    protected Object modelOfTheWorld;    

    // <editor-fold defaultstate="collapsed" desc="GETs y SETs">
    /**
     * @return the modelOfTheWorld
     */
    public Object getModelOfTheWorld() {
        return modelOfTheWorld;
    }

    /**
     * @param modelOfTheWorld the modelOfTheWorld to set
     */
    public void setModelOfTheWorld(Object modelOfTheWorld) {
        this.modelOfTheWorld = modelOfTheWorld;
    }
    //</editor-fold>
}