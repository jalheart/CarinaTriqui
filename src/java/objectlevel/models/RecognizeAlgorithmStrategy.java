/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.memory.BasicMemoryUnity;
import carina.memory.LongTermMemory;
import carina.metacore.ComputationalStrategy;
import carina.objectlevel.Pattern;
import java.util.List;

/**
 *
 * @author jalheart
 */
public class RecognizeAlgorithmStrategy extends ComputationalStrategy{
    private Object _value;
    public RecognizeAlgorithmStrategy(Object value) {
        this._value =value;
    }
    /**
     * Reconoce los patrones con base en aquellos que tiene almacenados en la memoria a largo plazo
     * @return 
     */
    @Override
    public Object run() {
        //TODO Mover a Recognition.java
        //Se cargan todos lo patrones en la memoria de largo plazo
        BasicMemoryUnity    bmu =LongTermMemory.getInstance().retrieveInformation("patterns");
        List<Pattern> patterns   =(List<Pattern>)bmu.information;
        //Se verifica que el valor ingresado corresponda con algun patron
        for (Pattern pattern : patterns) {
            if(java.util.regex.Pattern.matches((String)pattern.getPattern(), (String)this.getValue()))
                return true;
        }
        return false;
        //TODO Aquí se debe escoger que tipo de reconocimiento se hce depeniento del tipo de sensor
    }

    /**
     * @return the _value
     */
    public Object getValue() {
        return _value;
    }
    /**
     * @param _value the _value to set
     */
    public void setValue(Object _value) {
        this._value = _value;
    }
}