/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.metacore;

import java.util.Optional;

/**
 *
 * @author jalheart
 */
abstract public class Task extends FuntionalElement{
    private Goal goal;
    protected Boolean _executed     =false;
    protected Boolean _successful   =false;
    protected Boolean _stopPlan     =false;
    public Task() {
        this.setGoal(new Goal());
    }    
    abstract public Object run();
    public void buildProfile(){}
    protected void updateTaskState(Boolean executed, Boolean successful, Boolean stopPlan){
        _executed   =executed;
        _successful =successful;
        _stopPlan   =stopPlan;
    }
    // <editor-fold defaultstate="collapsed" desc="GETs y SETs">
    /**
     * @return the goal
     */
    public Goal getGoal() {
        return goal;
    }

    /**
     * @param goal the goal to set
     */
    public void setGoal(Goal goal) {
        this.goal = goal;
    }
    /**
     * @return the _executed
     */
    public Boolean getExecuted() {
        return _executed;
    }

    /**
     * @return the _successful
     */
    public Boolean getSuccessful() {
        return _successful;
    }
    /**
     * @return the _stopPlan
     */
    public Boolean getStopPlan() {
        return _stopPlan;
    }
    // </editor-fold>
}