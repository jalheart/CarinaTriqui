/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel;

import carina.memory.LongTermMemory;
import carina.memory.MemoryDriver;
import carina.memory.MemoryDriverFile;
import carina.memory.MemoryDriverSQLite;
import carina.memory.MemoryInformation;
import carina.memory.PerceptualMemory;
import carina.memory.WorkingMemory;
import carina.metacore.State;
import carina.objectlevel.BasicCognitiveProcessingUnit;
import carina.objectlevel.Category;
import carina.objectlevel.Input;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import objectlevel.controllers.Reasoner;

/**
 *
 * @author jalheart
 */
public class Karina {
    public Karina(HttpSession sess,PrintWriter out,Map<String,String[]> inputs) {
//        WorkingMemory.init(new MemoryDriverSQLite("working_memory"));
//        WorkingMemory.init(new MemoryDriverSession(sess,"working_memory"));
        
        PerceptualMemory.init(new MemoryDriverFile("perceptual_memory"));
        
        LongTermMemory.init(new MemoryDriverFile("longterm_memory"));
//        List<Category> initialCategories    =new ArrayList<>();
//        initialCategories.add(new Category("playable"));
//        initialCategories.add(new Category("reset"));
//        MemoryInformation memoryInformation =new MemoryInformation("categories", initialCategories);
//        LongTermMemory.getInstance().storeInformation(memoryInformation);
        
        WorkingMemory.init(new MemoryDriverFile("working_memory"));
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
        
        
//        PerceptualMemory pmTmp  =new PerceptualMemory(new MemoryDriverSQLite("memoria_de_prueba"));
//        pmTmp.storeInformation(new MemoryInformation("algo", new Input("Entrada", "esta")));
//        pmTmp.retrieveInformation("algo");
        //TODO La memoria se manejará usando la unidad básica de memoria
        Reasoner reasoner   =new Reasoner(inputs,out);        
        out.print("Perception...</br>");
        if(reasoner.perception()){
            out.print("Recognition...</br>");
            if(reasoner.recognition()){
                out.print("Categorization...</br>");
                reasoner.categorization();
//                System.out.println("is_categorized: "+wm.getMental_state("is_categorized").getValue());
                if(wm.getMental_state("is_categorized").getValue()){//Es una categoria conocida
                    out.print("Planning...</br>");
                    reasoner.planing();
                    out.print("Executing plan...</br>");
                    reasoner.run();
                }
            }
        }        
        out.print("Acting...</br>");        
        reasoner.showBoard();
    }
}