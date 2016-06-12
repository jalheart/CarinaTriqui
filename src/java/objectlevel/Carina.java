/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel;

import carina.memory.BasicMemoryUnity;
import carina.memory.LongTermMemory;
import carina.memory.MemoryDriverMySQL;
import carina.memory.PerceptualMemory;
import carina.memory.SensorMemory;
import carina.memory.WorkingMemory;
import carina.metacore.Event;
import carina.metacore.State;
import carina.objectlevel.AgentSettings;
import carina.objectlevel.BasicCognitiveProcessingUnit;
import carina.objectlevel.Category;
import carina.objectlevel.Pattern;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpSession;
import objectlevel.controllers.Reasoner;

/**
 *
 * @author jalheart
 */
public class Carina {
    public Carina(HttpSession sess,PrintWriter out,Map<String,String[]> inputs,String configPath) {        
        JsonReader  jr;
        try {
            jr = Json.createReader(new FileReader(configPath));
            AgentSettings.config    =jr.readObject();
            initMemories();   
            loadInitialData();            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Carina.class.getName()).log(Level.SEVERE, null, ex);
        }                        
        WorkingMemory wm    =WorkingMemory.getInstance();
        wm.setBcpu(new BasicCognitiveProcessingUnit());
                //Esto es usado para mostrar los eventos que sucenden en el sistema
        List<Event> eventos =new ArrayList<>();
        wm.storeInformation(new BasicMemoryUnity("events", eventos));                
        Reasoner reasoner   =new Reasoner(inputs,out);
        if(reasoner.perception()){            
            if(reasoner.recognition()){
                reasoner.categorization();
                if(wm.getMental_state("is_categorized").getValue()){//Es una categoria conocida
                    reasoner.planing();
                    reasoner.run();
                }
            }
        }
        reasoner.showBoard();
    }
    private void initActionList(){
        
    }
    private void initMemories(){
        JsonObject memoryConfig =AgentSettings.config.getJsonObject("memory_management");
        if(memoryConfig.getString("type").equals("mysql")){
            final JsonObject  base=    memoryConfig.getJsonObject("config");
            Map<String,String>  baseSettings    =new HashMap<String,String>(){{
                                                    put("server",base.getString("server"));
                                                    put("db", base.getString("db"));
                                                    put("user", base.getString("user"));
                                                    put("pass", base.getString("pass"));
                                                }};
            LongTermMemory.init(new MemoryDriverMySQL(new HashMap<String, String>(baseSettings){{
                                                                                put("table", "longterm_memory");
                                                                            }}));
            PerceptualMemory.init(new MemoryDriverMySQL(new HashMap<String, String>(baseSettings){{
                                                                                put("table", "perceptual_memory");
                                                                            }}));                        
            SensorMemory.init(new MemoryDriverMySQL(new HashMap<String, String>(baseSettings){{
                                                                                    put("table", "sensors");
                                                                                }}));
            WorkingMemory.init(new MemoryDriverMySQL(new HashMap<String, String>(baseSettings){{
                                                                                put("table", "working_memory");
                                                                            }}));
        }
    }
    private void loadInitialData(){
        JsonObject ltm =AgentSettings.config.getJsonObject("initial_data").getJsonObject("long_term_memory");
        this.loadPatterns(ltm.getJsonArray("patterns"));
        this.loadCategories(ltm.getJsonArray("categories"));
        this.loadMentalStates(AgentSettings.config.getJsonObject("initial_data").getJsonObject("mental_states"));
        /*
        for(String key:id.keySet()){
            JsonValue  value   =id.get(key);            
            if(value.getValueType().equals(JsonValue.ValueType.STRING)){
                LongTermMemory.getInstance().storeInformation(new BasicMemoryUnity(key, id.getString(key)));
            }else if(value.getValueType().equals(JsonValue.ValueType.OBJECT)){
                LongTermMemory.getInstance().storeInformation(new BasicMemoryUnity(key, id.getJsonObject(key).toString()));
            }
        }
*/
    }
    private void loadPatterns(JsonArray patterns){
        List<Pattern>   initialPatterns =new ArrayList<>();
        for(int i=0;i<patterns.size();i++){            
            initialPatterns.add(new Pattern(patterns.getString(i)));            
        }
        LongTermMemory.getInstance().storeInformation(new BasicMemoryUnity("patterns",initialPatterns));        
    }
    private void loadCategories(JsonArray categories){
        List<Category> initialCategories    =new ArrayList<>();
        for(int i=0;i<categories.size();i++){
            initialCategories.add(new Category(categories.getString(i)));
        }        
        LongTermMemory.getInstance().storeInformation(new BasicMemoryUnity("categories", initialCategories));
    }
    @SuppressWarnings("empty-statement")
    private void loadMentalStates(JsonObject states){        
        //Hay estados persistentes y volatiles, ya que algunos no se deben reiniciar cada vez que se inicie una peticion 
        JsonObject  persistents =states.getJsonObject("persistents");        
        for(String key:persistents.keySet()){
            if(WorkingMemory.getInstance().getMental_state(key)==null)
                WorkingMemory.getInstance().setMental_state(new State(key,persistents.getBoolean(key)));
        }
        JsonObject volatiles =states.getJsonObject("volatile");
        for(String key:volatiles.keySet()){
            WorkingMemory.getInstance().setMental_state(new State(key,volatiles.getBoolean(key)));
        };        
    }
}