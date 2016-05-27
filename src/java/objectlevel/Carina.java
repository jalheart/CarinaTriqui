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
import carina.memory.WorkingMemory;
import carina.metacore.Event;
import carina.metacore.State;
import carina.objectlevel.BasicCognitiveProcessingUnit;
import carina.objectlevel.Category;
import carina.objectlevel.Pattern;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import objectlevel.controllers.Reasoner;

/**
 *
 * @author jalheart
 */
public class Carina {
    public Carina(HttpSession sess,PrintWriter out,Map<String,String[]> inputs) {
        Map<String,String> mysqlMemoriDriveConfig   =new HashMap<String, String>(){{
                                                                                    put("server", "localhost");
                                                                                    put("db", "karina");
                                                                                    put("user", "root");
                                                                                    put("pass", "");
//                                                                                    put("table", "perception1");
                                                                                }};
        mysqlMemoriDriveConfig.put("table","perceptual_memory");
        PerceptualMemory.init(new MemoryDriverMySQL(mysqlMemoriDriveConfig));
        LongTermMemory.init(new MemoryDriverMySQL(new HashMap<String, String>(){{
                                                                                put("server", "localhost");
                                                                                put("db", "karina");
                                                                                put("user", "root");
                                                                                put("pass", "");
                                                                                put("table", "longterm_memory");
                                                                            }}));
        List<Category> initialCategories    =new ArrayList<>();
        initialCategories.add(new Category("playable"));
        initialCategories.add(new Category("reset"));
        BasicMemoryUnity memoryInformation =new BasicMemoryUnity("categories", initialCategories);
        LongTermMemory.getInstance().storeInformation(memoryInformation);
        List<Pattern>   initialPatterns =new ArrayList<>();
        initialPatterns.add(new Pattern("[0-2]_[0-2]"));
        initialPatterns.add(new Pattern("reset"));
        LongTermMemory.getInstance().storeInformation(new BasicMemoryUnity("patterns",initialPatterns));
        
//       BasicMemoryUnity bmu= LongTermMemory.getInstance().retrieveInformation("cat%");
//       out.print(((List<Category>)bmu.information).get(0).getCategory());
//        WorkingMemory.init(new MemoryDriverFile("working_memory"));
        WorkingMemory.init(new MemoryDriverMySQL(new HashMap<String, String>(){{
                                                                                put("server", "localhost");
                                                                                put("db", "karina");
                                                                                put("user", "root");
                                                                                put("pass", "");
                                                                                put("table", "working_memory");
                                                                            }}));
        WorkingMemory wm    =WorkingMemory.getInstance();
        
        wm.setMental_state( new State("is_system_started", true));
        wm.setMental_state( new State("is_perceived", false));
        wm.setMental_state( new State("is_recognized", false));
        wm.setMental_state( new State("is_categorized", false));
        wm.setMental_state( new State("is_planned", false));
        wm.setMental_state( new State("is_board_modified", false));
        wm.setMental_state( new State("is_player_winner_verified", false));
        wm.setMental_state( new State("is_player_turn_changed", false));    
        wm.setMental_state( new State("is_machine_played", false));    
        wm.setMental_state( new State("is_machine_winner_verified", false));    
        wm.setMental_state( new State("is_machine_turn_changed", false));    
        wm.setMental_state( new State("is_world_shown", false));
        
        wm.setBcpu(new BasicCognitiveProcessingUnit());
        
        List<Event> eventos =new ArrayList<>();
        wm.storeInformation(new BasicMemoryUnity("events", eventos));
       
//        PerceptualMemory pmTmp  =new PerceptualMemory(new MemoryDriverMySQL(new HashMap<String, String>(){{
//                                                                                                            put("server", "localhost");
//                                                                                                            put("db", "karina");
//                                                                                                            put("user", "root");
//                                                                                                            put("pass", "");
//                                                                                                            put("table", "perception1");
//                                                                                                        }}));
//        pmTmp.storeInformation(new BasicMemoryUnity("algo", new Input("Entrada", "esta")));
//        BasicMemoryUnity mi= pmTmp.retrieveInformation("algo");
//        System.out.println(((Input)mi.information).getInformation());
        
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
}