/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.metalevel.metamemory.working;

import javax.servlet.http.HttpSession;


/**
 *
 * @author jalheart
 */
public class WorkingMemory {
    private Object memory=null;
    private String type="sesion";
    public void init(String type,Object memory) {
        this.type   =type;
        this.memory =memory;
    }
    public void memorize(String key,Object information){
        if("sesion".equals(type)){
            memorizeSesion(key, information);
        }
    }
    public Object remember(String key){
        if("sesion".equals(type)){
            return rememberSesion(key);
        }
        return null;
    }
    public void forgetAll() {
        if("sesion".equals(type)){
            forgetAllSesion();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Métodos que trabajan con sesion">
    private void memorizeSesion(String key,Object information){
        ((HttpSession)memory).setAttribute(key, information);
    }
    private Object rememberSesion(String key){
        return ((HttpSession)memory).getAttribute(key);
    }    
    private void forgetAllSesion(){
        ((HttpSession)memory).invalidate();
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Implementación Singleton">
    private static WorkingMemory instance  =null;
    protected WorkingMemory(){}
    public static WorkingMemory getInstance() {
        if(instance==null){
            instance    =new WorkingMemory();
        }
        return instance;
    }
    //</editor-fold>    
}