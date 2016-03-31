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
        cells   = new String[3][3];
        for(int i =0;i<cells.length;i++)
            for(int j =0;j<cells[i].length;j++)
                cells[i][j]="";
    }
    /**
     * @return the cells
     */
    public String[][] getCells() {
        return cells;
    }
    public String getData(int x,int y){
        return cells[x][y];
    }
    public void setData(int x,int y,String value){
        cells[x][y] =value;
    }    
}