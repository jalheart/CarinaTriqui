/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.memory.WorkingMemory;
import carina.metacore.Action;

/**
 *
 * @author jalheart
 */
public class ShowWorld extends Action{
    private WorkingMemory workingMemory;
    private Object styles;
    public ShowWorld() {        
    }
    @Override
    public Object run(){
        this.workingMemory  =WorkingMemory.getInstance();
        //TODO ViewBoard::showBoard($this->getWorkingMemory()->getModelOfTheWorld()->getBoard()->getCells(), $this->getStyles());
        return true;
    }
// <editor-fold defaultstate="collapsed" desc="GETs y SETs">
    /**
     * @return the styles
     */
    public Object getStyles() {
        return styles;
    }

    /**
     * @param styles the styles to set
     */
    public void setStyles(Object styles) {
        this.styles = styles;
    }
 // </editor-fold>   
}