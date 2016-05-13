/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.controllers;

import carina.memory.WorkingMemory;
import carina.metacore.Plan;
import carina.metacore.State;
import carina.objectlevel.BasicCognitiveProcessingUnit;
import carina.objectlevel.Categorization;
import carina.objectlevel.Category;
import carina.objectlevel.Input;
import carina.objectlevel.Perception;
import carina.objectlevel.Planning;
import carina.objectlevel.Recognition;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import objectlevel.models.Board;
import objectlevel.models.CategorizationAlgorithmStrategy;
import objectlevel.models.ChangeTurn;
import objectlevel.models.MachinePlays;
import objectlevel.models.ModelOfTheWorld;
import objectlevel.models.ModifyBoard;
import objectlevel.models.PlanningAlgorithmStrategy;
import objectlevel.models.PlayerMovement;
import objectlevel.models.RecognizeAlgorithmStrategy;
import objectlevel.models.ResetBoard;
import objectlevel.models.ShowWorld;
import objectlevel.models.VerifyWinner;
import objectlevel.views.ViewBoard;

/**
 *
 * @author jalheart
 */
public class Reasoner {
    private WorkingMemory   _workingMemory;
    private PlayerMovement  _playerMovement;
    private Perception      _perception;
    private Recognition     _recognition;
    private Categorization  _categorization;
    private Planning        _planning;
    private Map<String,Plan>  plans;
    
    private Map<String,String[]> _inputs;   
    private PrintWriter _out;

    public Reasoner(Map<String,String[]> inputs,PrintWriter out) {
        this._inputs        =inputs;
        this._out           =out;
        this._workingMemory =WorkingMemory.getInstance();
        if(this._workingMemory.getModel_of_the_world()==null){
            ModelOfTheWorld modelOfTheWorld    =new ModelOfTheWorld();  
            modelOfTheWorld.addMission("win_game");
            modelOfTheWorld.addTokens("O", "X");
            this._workingMemory.setModel_of_the_world(modelOfTheWorld);
        }
        
        this._playerMovement    =new PlayerMovement();
        this._recognition       =new Recognition();
        this._categorization    =new Categorization();
        this._perception        =new Perception();
        this._planning          =new Planning();
        this.plans  =new HashMap<>();
    }
    public Boolean sensing(){
        if(this._inputs.get("player_move")!=null){
            this._playerMovement.setMovement(this._inputs.get("player_move")[0]);
            return true;
        }
        if(this._inputs.get("reset")!=null){
            this._playerMovement.setMovement(this._inputs.get("reset")[0]);
            return true;
        }
        return false;
    }
    public Boolean perception(){
        if(!this.sensing()) return false;        
        _perception.processInformation(new HashMap<String, Object>(){{
            put("information", _playerMovement.getMovement());
            put("type_sensor", "player_movement");
        }});
        return true;
    }
    public Boolean recognition(){
        Boolean recognized  =(Boolean)this._recognition.processInformation(RecognizeAlgorithmStrategy.class);
        _workingMemory.updateMentalState("is_recognized", recognized);
        return recognized;
    }
    public List<Object> categorization(){
        return  (List<Object>)this._categorization.processInformation(CategorizationAlgorithmStrategy.class);
    }
    public void planing(){
        _planning.processInformation(PlanningAlgorithmStrategy.class);        
    }
    public void run(){
        _planning.executePlans();
//        BasicCognitiveProcessingUnit bcpu   =this._workingMemory.getBcpu();
//        List<Category> categories           =bcpu.getCategorys();
//        for (Category category : categories) {
//            bcpu.getPlans().get((String)category.getCategory()).executePlan();
//        }
    }
    public void showBoard(){
        ViewBoard   vb  =new ViewBoard(this._out);        
        vb.showBoard(this._workingMemory.getModel_of_the_world().getBoard().getCells(),null);
    }
}