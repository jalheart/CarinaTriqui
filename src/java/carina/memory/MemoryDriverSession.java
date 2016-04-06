/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.memory;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jalheart
 */
public class MemoryDriverSession extends MemoryDriver{
    private String table;
    public MemoryDriverSession(HttpSession session,String table) {
        super(session);
        this.table  =table;
    }
    @Override
    public void storeInformation(MemoryInformation information) {
        Map<String,MemoryInformation> tableData =(Map<String, MemoryInformation>)((HttpSession)this.getConfig()).getAttribute(getTable());
        if(tableData ==null){
            tableData   =new HashMap<>();
        }
        tableData.put(information.cue, information);
        ((HttpSession)this.getConfig()).setAttribute(getTable(), tableData);
    }
    @Override
    public MemoryInformation retrieveInformation(String cue) {
        Map<String,MemoryInformation> tableData =(Map<String, MemoryInformation>)((HttpSession)this.getConfig()).getAttribute(getTable());        
        return tableData==null?null:tableData.get(cue);
    }
    @Override
    public void forgetInformation(String cue) {
        Map<String,MemoryInformation> tableData =(Map<String, MemoryInformation>)((HttpSession)this.getConfig()).getAttribute(getTable());
        tableData.remove(cue);
        ((HttpSession)this.getConfig()).setAttribute(getTable(), tableData);
    }
    // <editor-fold defaultstate="collapsed" desc="GETs y SETs">
    /**
     * @return the table
     */
    public String getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(String table) {
        this.table = table;
    }
    // </editor-fold>
}