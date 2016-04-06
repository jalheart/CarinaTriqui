/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.old.models;

import carina.metalevel.old.metamemory.MetaModelOfTheWorld;
import carina.memory.WorkingMemory;
/**
 *
 * @author jalheart
 */
public class ModelOfTheWorld extends MetaModelOfTheWorld{
    //private Board board;//No se ncesita, ya que hereda de MetaModelOfTheWorld
    private boolean isCreated   =false;

    public ModelOfTheWorld() {                
        //this.board  =new Board();
        this.modelOfTheWorld    =new Board();
        //this.board.create();
        ((Board)this.modelOfTheWorld).create();
        if(!this.getIsCreated()){
            this.setIsCreated(true);
            this.updateModelOfTheWorld(this.modelOfTheWorld);
        }else{            
            this.modelOfTheWorld  =(Board) WorkingMemory.getInstance().remember("model_of_the_world");            
        }        
    }
    public  void updateModelOfTheWorld(Object value){
        WorkingMemory.getInstance().memorize("model_of_the_world",  value);
    }
    @Override
    public Board getModelOfTheWorld(){
        return (Board)this.modelOfTheWorld;
    }
    /**
     * @return the isCreated
     */
    public boolean getIsCreated() {
        if(WorkingMemory.getInstance().remember("is_created") != null){
            return (boolean)WorkingMemory.getInstance().remember("is_created");
        }else{
            return isCreated;
        }
    }
    /**
     * @param isCreated the isCreated to set
     */
    public void setIsCreated(boolean isCreated) {
        this.isCreated = isCreated;        
        WorkingMemory.getInstance().memorize("is_created",isCreated);
    }
}