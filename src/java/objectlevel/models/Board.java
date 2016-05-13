/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.metacore.Element;

/**
 *
 * @author jalheart
 */
public class Board extends Element{
    private String[][] cells;
    public void create(){
        cells   =new String[3][3];
    }
    // <editor-fold defaultstate="collapsed" desc="Gets y SETs">
    public String getData(int fila,int columna){
        return this.cells[fila][columna];
    }
    public void setData(int fila,int columna,String dato){
        this.cells[fila][columna]   =dato;
    }
    /**
     * @return the cells
     */
    public String[][] getCells() {
        return cells;
    }

    /**
     * @param cells the cells to set
     */
    public void setCells(String[][] cells) {
        this.cells = cells;
    }
    // </editor-fold>
}