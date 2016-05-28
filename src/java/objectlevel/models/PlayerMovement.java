/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.memory.BasicMemoryUnity;
import carina.memory.MemoryDriverFile;
import carina.memory.SensorMemory;
import carina.objectlevel.MouseSensor;

/**
 *
 * @author jalheart
 */
public class PlayerMovement extends MouseSensor{    
    public PlayerMovement() {
    }
    public void setMovement(Object value){
        this.getSensorMemory().storeInformation(new BasicMemoryUnity(this.getType(), value));
    }
    public Object getMovement(){
        return this.getSensorMemory().retrieveInformation(this.getType());
    }
}