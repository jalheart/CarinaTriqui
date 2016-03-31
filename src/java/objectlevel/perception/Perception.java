/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.perception;

import carina.metalevel.metaperception.MetaPerception;
import java.util.Map;

/**
 *
 * @author jalheart
 */
public class Perception extends MetaPerception{
//    @Override
//    public void processInformation(int[] value){
//            Input new_input  = new Input();
//            new_input.setInformation(value);
//            this.setPercepObject(new_input);
//    }
    
    @Override
    public Object perceive(Object object) {        
        String[] obj    =this.getSensors().get(object);
        return obj!=null?obj[0]:null;
    }
    //Se hace para no tener que hacer casting cada vez que se use
    @Override
    public Map<String,String[]> getSensors() {
        return (Map<String,String[]>)super.getSensors();
    }
    
}