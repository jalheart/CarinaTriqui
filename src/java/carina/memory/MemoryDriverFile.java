/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.memory;

import carina.metalevel.old.metamemory.LongTermMemoryFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jalheart
 */
public class MemoryDriverFile extends MemoryDriver{
    private String URL;
    public MemoryDriverFile(String type){
        super(type);
        this.URL  ="./memory/"+type+".mem";
        this.createFile(type);
    }
    @Override
    public void storeInformation(MemoryInformation information) {
        Map<String,MemoryInformation> data =this.getFileData();
        if(data==null){
            data    =new HashMap<>();
        }
        data.put(information.cue, information);
        this.saveFileData(data);
    }
    @Override
    public MemoryInformation retrieveInformation(String cue) {
        Map<String,MemoryInformation> data =(Map<String,MemoryInformation>)this.getFileData();
        return data==null?null:(data.get(cue));
    }
    @Override
    public void forgetInformation(String cue) {
        Map<String,MemoryInformation> data =this.getFileData();
        if(data==null){
            data    =new HashMap<>();
        }
        data.remove(cue);
        this.saveFileData(data);
    }
    private void createFile(String type){        
        try {
            File f=new File(this.URL);
            if(!f.exists()){
                f.getParentFile().mkdirs();
                f.createNewFile();
            }            
        } catch (Exception e) {
        }        
    }
    private void saveFileData(Map<String,MemoryInformation> data){
        try {
            FileOutputStream file;
            ObjectOutputStream objectOut;                        
            file        =new FileOutputStream(URL);
            objectOut   =new ObjectOutputStream(file);
            objectOut.writeObject(data);
            objectOut.flush();
            objectOut.close();
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MemoryDriverFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MemoryDriverFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private Map<String,MemoryInformation> getFileData(){
        FileInputStream file;
        ObjectInputStream objectIn;
        Map<String,MemoryInformation> obj  =null;
        try {            
            file        =new FileInputStream(this.URL);
            objectIn    =new ObjectInputStream(file);
            obj=(Map<String,MemoryInformation>)objectIn.readObject();
            objectIn.close();
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LongTermMemoryFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LongTermMemoryFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
}