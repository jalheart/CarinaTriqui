/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.objectlevel;

import carina.memory.LongTermMemory;
import carina.memory.MemoryInformation;
import carina.metacore.CognitiveFunction;
import carina.metacore.ComputationalStrategy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectlevel.old.models.ModelOfTheWorld;

/**
 *
 * @author jalheart
 */
public class Categorization extends CognitiveFunction{

    @Override
    public Object processInformation(Object value) {
        return this.processInformation((Map<String,Object>)value);
    }
    public BasicCognitiveProcessingUnit processInformation(Map<String,Object> value) {
        Object information  =((BasicCognitiveProcessingUnit)value.get("bcpu")).getInput().getInformation();
        List<Category> categories   =this.getCategories();
        
        try {
            Constructor<?> constructor  =((Class)value.get("algorithmStrategy")).getConstructor(List.class,Object.class,ModelOfTheWorld.class);            
            ComputationalStrategy   algorithmStrategy   =(ComputationalStrategy)constructor.newInstance(categories,information,value.get("modelOfTheWorld"));
            List<Object> categorization   =(List<Object>)algorithmStrategy.run();
            ((BasicCognitiveProcessingUnit)value.get("bcpu")).addCategories(categorization);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Categorization.class.getName()).log(Level.SEVERE, null, ex);        
        }        
        return ((BasicCognitiveProcessingUnit)value.get("bcpu"));
    }
    public List<Category>getCategories(){
        MemoryInformation   mem= LongTermMemory.getInstance().retrieveInformation("categories");
        return mem==null?null:(List<Category>)mem.information;
    }
}