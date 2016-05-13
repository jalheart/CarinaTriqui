/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.metalevel.old.metamemory;

import carina.memory.Memory;
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
public class LongTermMemoryFile extends Memory{
    private static final String URL="./logtermmemory/longtermmemory.ltm";
    public LongTermMemoryFile() {
    }
    @Override
    public void memorize(String key, Object information) {
        FileOutputStream file;
        ObjectOutputStream objectOut;        
        try {
            File f=new File(URL);
            if(!f.exists()){
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            Map<String,Object> data =this.getFileData();
            if(data==null){
                data    =new HashMap<String, Object>();
            }
            data.put(key, information);
            
            file        =new FileOutputStream(URL);
            objectOut   =new ObjectOutputStream(file);
            objectOut.writeObject(data);
            objectOut.flush();
            objectOut.close();
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LongTermMemoryFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LongTermMemoryFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object remember(String key) {
        Map<String,Object> data =(Map<String,Object>)this.getFileData();
        return data==null?null:(data.get(key));
    }

    @Override
    public void forgetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private Map<String,Object> getFileData(){
        FileInputStream file;
        ObjectInputStream objectIn;
        Map<String,Object> obj  =null;
        try {            
            file        =new FileInputStream(URL);
            objectIn    =new ObjectInputStream(file);
            obj=(Map<String,Object>)objectIn.readObject();
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