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
public class RandomAlgorithmStrategy extends ComputationalStrategy{
    private String[][] _cells;

    public RandomAlgorithmStrategy(String[][] _cells) {
        this._cells = _cells;
    }
    
    @Override
    public Object run() {
        int[] salida    =new int[2];        
        do{
            salida[0] = (int)Math.floor(Math.random()*3);
            salida[1] = (int)Math.floor(Math.random()*3);            
        } while(this._cells[salida[0]][salida[1]]!=null);        
        return salida;
    }
    // <editor-fold defaultstate="collapsed" desc="GETs y SETs">
    /**
     * @return the _cells
     */
    public String[][] getCells() {
        return _cells;
    }

    /**
     * @param _cells the _cells to set
     */
    public void setCells(String[][] _cells) {
        this._cells = _cells;
    }
    // </editor-fold>
}