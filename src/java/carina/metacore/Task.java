/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.metacore;

/**
 *
 * @author jalheart
 */
abstract public class Task extends FuntionalElement{
    private Goal goal;

    public Task() {
        this.setGoal(new Goal());
    }    
    abstract public void run();
    public void buildProfile(){}
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
    // </editor-fold>
}