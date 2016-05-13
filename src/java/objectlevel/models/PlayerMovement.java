/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.memory.MemoryDriverFile;
import carina.memory.MemoryInformation;
import carina.memory.SensorMemory;
import carina.objectlevel.MouseSensor;

/**
 *
 * @author jalheart
 */
public class PlayerMovement extends MouseSensor{
    private SensorMemory sensorMemory;
    public PlayerMovement() {
        this.sensorMemory   =new SensorMemory(new MemoryDriverFile("sensor_memory_player_move"));
    }
    public void setMovement(Object value){
        this.sensorMemory.storeInformation(new MemoryInformation(this.getType(), value));
    }
    public Object getMovement(){
        return this.sensorMemory.retrieveInformation(this.getType());
    }
}