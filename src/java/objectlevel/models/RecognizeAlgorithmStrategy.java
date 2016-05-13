/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.metacore.ComputationalStrategy;

/**
 *
 * @author jalheart
 */
public class RecognizeAlgorithmStrategy extends ComputationalStrategy{
    private Object _value;
    public RecognizeAlgorithmStrategy(Object value) {
        this._value =value;
    }
    
    @Override
    public Object run() {
        //TODO esto [0-2]_[0-2] deberia estar en la LongTermMemory
        return java.util.regex.Pattern.matches("[0-2]_[0-2]", (String)this.getValue()) || "1".equals(this.getValue());
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