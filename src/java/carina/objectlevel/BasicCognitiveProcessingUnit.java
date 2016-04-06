/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.objectlevel;

import carina.metacore.RootElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jalheart
 */
public class BasicCognitiveProcessingUnit extends RootElement{
    private Input input;
    private Pattern pattern;
    private List<Category> categorys;
    public void addInput(Object information,String typeSensor){
        Input newInput  =new Input();
        newInput.setInformation(information);
        newInput.setType(typeSensor);
        this.setInput(newInput);
    }
    public void addPattern(Object pattern){
        Pattern newPattern = new Pattern();
        newPattern.setPattern(pattern);
        this.setPattern(newPattern);
    }
    public void addCategories(List<Object>categories){
            List<Category> categorysTemp = new ArrayList<>();
            Category newCategory;
            for (Object category : categories) {
                newCategory =new Category();
                newCategory.setCategory(category);
                categorysTemp.add(newCategory);
            }
            this.setCategorys(categorysTemp);            
    }
    // <editor-fold defaultstate="collapsed" desc="GETs y SETs">
    /**
     * @return the input
     */
    public Input getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(Input input) {
        this.input = input;
    }

    /**
     * @return the pattern
     */
    public Pattern getPattern() {
        return pattern;
    }

    /**
     * @param pattern the pattern to set
     */
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    /**
     * @return the categorys
     */
    public List<Category> getCategorys() {
        return categorys;
    }

    /**
     * @param categorys the categorys to set
     */
    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }
    // </editor-fold>
}