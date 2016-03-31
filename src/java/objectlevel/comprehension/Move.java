/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.comprehension;

import java.io.Serializable;

/**
 *
 * @author jalheart
 */
public class Move implements Serializable{
    public int fila;
    public int columna;
    public Move(int fila,int columna) {
        this.fila   =fila;
        this.columna=columna;
    }    
}