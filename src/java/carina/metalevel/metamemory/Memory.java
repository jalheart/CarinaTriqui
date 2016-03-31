/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.metalevel.metamemory;

/**
 *
 * @author jalheart
 */
abstract public class Memory{
    abstract public void memorize(String key,Object information);
    abstract public Object remember(String key);
    abstract public void forgetAll();
}