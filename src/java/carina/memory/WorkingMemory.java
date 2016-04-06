/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.memory;

import carina.metacore.Profile;
import carina.metacore.State;
import carina.objectlevel.BasicCognitiveProcessingUnit;
import java.util.List;
import objectlevel.old.models.ModelOfTheWorld;


/**
 *
 * @author jalheart
 */
public class WorkingMemory extends Memory{        
    private BasicCognitiveProcessingUnit bcpu;
    private ModelOfTheWorld model_of_the_world;
    private List<Profile>   profiles;
    private List<State>     mental_state;
    public WorkingMemory(MemoryDriver driver) {
        super(driver);
        MemoryInformation memoryTmp  =this.getDriver().retrieveInformation("bcpu");
        if(memoryTmp!=null){
            this.setBcpu((BasicCognitiveProcessingUnit)memoryTmp.information);
        }
        
        memoryTmp  =this.getDriver().retrieveInformation("model_of_the_world");
        if(memoryTmp!=null){
            this.setModel_of_the_world((ModelOfTheWorld) memoryTmp.information);
        }
        
        memoryTmp  =this.getDriver().retrieveInformation("profiles");
        if(memoryTmp!=null){
            for (Profile profile : (List<Profile>) memoryTmp.information) {
                this.setProfiles(profile,true);                
            }
        }
        
        memoryTmp  =this.getDriver().retrieveInformation("mental_state");
        if(memoryTmp!=null){
            for (State state : (List<State>) memoryTmp.information) {
                this.setMental_state(state);
            }
        }
    }    
    
    // <editor-fold defaultstate="collapsed" desc="GETs y SETs">
    /**
     * @return the bcpu
     */
    public Object getBcpu() {
        return bcpu;
    }

    /**
     * @param bcpu the bcpu to set
     */
    public void setBcpu(BasicCognitiveProcessingUnit bcpu) {
        this.bcpu = bcpu;
        this.syncBCPU(bcpu);
    }

    /**
     * @return the model_of_the_world
     */
    public ModelOfTheWorld getModel_of_the_world() {
        return model_of_the_world;
    }

    /**
     * @param model_of_the_world the model_of_the_world to set
     */
    public void setModel_of_the_world(ModelOfTheWorld model_of_the_world) {        
        this.model_of_the_world = model_of_the_world;
        this.syncModelOfTheWorld(this.model_of_the_world);
    }

    /**
     * @return the profiles
     */
    public Profile getProfiles(int id) {        
        return this.profiles.get(id);
    }

    /**
     * @param profiles the profiles to set
     */
    public void setProfiles(Profile profiles) {
        this.setProfiles(profiles, Boolean.FALSE);
    }
    public void setProfiles(Profile profile,Boolean s) {
        this.profiles.add(profile);
        if(!s){
            this.getDriver().storeInformation(new MemoryInformation("profiles", this.profiles));
        }
    }    

    /**
     * @return the mental_state
     */
    public List<State> getMental_state() {
        return mental_state;
    }

    /**
     * @param mental_state the mental_state to set
     */
    public void setMental_state(State mental_state) {
        this.setMental_state(mental_state, Boolean.FALSE);
    }
    public void setMental_state(State mental_state,Boolean s) {
        this.mental_state.add(mental_state);
        if(!s){
            this.getDriver().storeInformation(new MemoryInformation("mental_state", this.mental_state));
        }
    }
    // </editor-fold>
    
    public void syncBCPU(BasicCognitiveProcessingUnit value){
        this.getDriver().storeInformation(new MemoryInformation("bcpu",value));
    }
    public void syncModelOfTheWorld(ModelOfTheWorld value){
        this.getDriver().storeInformation(new MemoryInformation("model_of_the_world",value));
    }
}