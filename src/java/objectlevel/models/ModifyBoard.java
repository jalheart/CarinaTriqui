/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.memory.BasicMemoryUnity;
import carina.memory.WorkingMemory;
import carina.objectlevel.ReasoningTask;

/**
 *
 * @author jalheart
 */
public class ModifyBoard extends ReasoningTask{
    private WorkingMemory workingMemory;
    @Override
    /**
     * @return Boolean Devuelve true si la tarea se ejecuta exitosamente
     */
    public Object run(){
        this.workingMemory  =WorkingMemory.getInstance();
        String[][]  cells   =this.workingMemory.getModel_of_the_world().getBoard().getCells();
        String positionTmp  =(String)((BasicMemoryUnity)this.workingMemory.getBcpu().getInput().getInformation()).information;
        String[] position   =positionTmp.split("_");        
        this.workingMemory.getModel_of_the_world().getBoard().setData(Integer.parseInt(position[0]), Integer.parseInt(position[1]), this.workingMemory.getModel_of_the_world().currentToken());
        this.workingMemory.getModel_of_the_world().updateModelOfTheWorld();
        this.workingMemory.setModel_of_the_world(this.workingMemory.getModel_of_the_world());
        this._executed  =true;
        this._successful=true;
        this._stopPlan  =false;
        return true;
    }
}