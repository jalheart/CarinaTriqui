/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.memory.BasicMemoryUnity;
import carina.memory.WorkingMemory;
import carina.metacore.ComputationalStrategy;
import carina.objectlevel.BasicCognitiveProcessingUnit;
import carina.objectlevel.Category;
import carina.objectlevel.ModelOfTheWorld;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jalheart
 */
public class CategorizationAlgorithmStrategy extends ComputationalStrategy{
    private ModelOfTheWorld modelOfTheWorld;
    private List<Category> categories;
    private String value;

    public CategorizationAlgorithmStrategy(List<Category> categories) {
        this.setCategories(categories);        
    }
    
    @Override
    public Object run() {
        List<Object> categoriesTmp  =new ArrayList<>();
        WorkingMemory workingMemory         =WorkingMemory.getInstance();
        BasicCognitiveProcessingUnit bcpu   =workingMemory.getBcpu();
        BasicMemoryUnity memoryInformation =(BasicMemoryUnity)bcpu.getInput("player_movement").getInformation();
        this.setModelOfTheWorld(workingMemory.getModel_of_the_world());
        this.setValue((String)memoryInformation.information);
        //TODO cargar los patrones desde la bcpu
        if(java.util.regex.Pattern.matches("[0-2]_[0-2]", (String)this.getValue())){
            String[]    information =this.getValue().split("_");
            String[][]  cells   =((TriquiModelOfTheWorld)this.getModelOfTheWorld()).getBoard().getCells();
            String cellData =cells[Integer.parseInt(information[0])][Integer.parseInt(information[1])];        
            if(cellData==null || "".equals(cellData)){
                for (Category category : this.getCategories()) {
                    //TODO Ya las categorias stan almacenadas en la memoria
                    if("playable".equals(category.getCategory())){
                        if(!categoriesTmp.contains(category.getCategory()))
                            categoriesTmp.add(category.getCategory());
                    }
                }
            }
        }else{
            for (Category category : this.getCategories()) {                
                if("reset".equals(category.getCategory())){
                    if(!categoriesTmp.contains(category.getCategory()))
                        categoriesTmp.add(category.getCategory());
                }
            }            
        }
        return categoriesTmp;
    }
    // <editor-fold defaultstate="collapsed" desc="GETs y SETs">
    /**
     * @return the modelOfTheWorld
     */
    public ModelOfTheWorld getModelOfTheWorld() {
        return modelOfTheWorld;
    }

    /**
     * @param modelOfTheWorld the modelOfTheWorld to set
     */
    public void setModelOfTheWorld(ModelOfTheWorld modelOfTheWorld) {
        this.modelOfTheWorld = modelOfTheWorld;
    }
    /**
     * @return the categories
     */
    public List<Category> getCategories() {
        return this.categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    // </editor-fold>
}