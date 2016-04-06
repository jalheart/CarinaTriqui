/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.old.models;

import objectlevel.old.perception.Perception;

/**
 *
 * @author jalheart
 */
public class GetPlayerMove extends Perception{
    public GetPlayerMove(int[] value){
        this.processInformation(value);
    }
}