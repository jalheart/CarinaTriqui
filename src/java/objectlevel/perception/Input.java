/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.perception;

import carina.metacore.Element;

/**
 *
 * @author jalheart
 */
public class Input extends Element{
    private int[] information;
    // <editor-fold defaultstate="collapsed" desc="Gets y Sets.">
    /**
     * @return the information
     */
    public int[] getInformation() {
        return information;
    }

    /**
     * @param information the information to set
     */
    public void setInformation(int[] information) {
        this.information = information;
    }
    //</editor-fold>
}
