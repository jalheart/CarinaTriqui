/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.metalevel.old.metamemory;

import carina.memory.Memory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jalheart
 */
public class LongTermMemory extends Memory{
    private Memory memory=null;//Una instancia segun el tipo de almacenamiento que se use    

    public void init(Class type){
        try {
            this.memory  =(Memory)type.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(LongTermMemory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    @Override
    public void memorize(String key,Object information){
        
    }
    @Override
    public Object remember(String key){
        
        return null;
    }
    @Override
    public void forgetAll() {
        
    }
}