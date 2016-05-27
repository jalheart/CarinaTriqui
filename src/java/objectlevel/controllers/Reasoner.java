/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.controllers;

import carina.memory.BasicMemoryUnity;
import carina.memory.WorkingMemory;
import carina.metacore.Event;
import carina.metacore.Plan;
import carina.objectlevel.Categorization;
import carina.objectlevel.Perception;
import carina.objectlevel.Planning;
import carina.objectlevel.Recognition;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import objectlevel.models.CategorizationAlgorithmStrategy;
import objectlevel.models.PlanningAlgorithmStrategy;
import objectlevel.models.PlayerMovement;
import objectlevel.models.RecognizeAlgorithmStrategy;
import objectlevel.models.TriquiModelOfTheWorld;
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
            TriquiModelOfTheWorld modelOfTheWorld    =new TriquiModelOfTheWorld();  
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
        this.addEvent(new Event("Sensing...."));
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
        
        addEvent(new Event("Perception..."+((BasicMemoryUnity)WorkingMemory.getInstance().getBcpu().getInput().getInformation()).information));
        
        return true;
    }
    public Boolean recognition(){
        Boolean recognized  =(Boolean)this._recognition.processInformation(RecognizeAlgorithmStrategy.class);
        _workingMemory.updateMentalState("is_recognized", recognized);
        addEvent(new Event("Recognition..."+recognized));
        return recognized;
    }
    public List<Object> categorization(){
        List<Object> categories   =(List<Object>)this._categorization.processInformation(CategorizationAlgorithmStrategy.class);
        addEvent(new Event("Categorization..."+categories.toString()));
        return  categories;
    }
    public void planing(){
        _planning.processInformation(PlanningAlgorithmStrategy.class);
        //Para mostrar el plan , hay que cargar los planes de la bcpu, cargar las categorias de la bcpu, para cada categoria hay un plan,en cada plan hay una accion
        addEvent(new Event("Planning..."));
    }
    public void run(){
        _planning.executePlans();
        addEvent(new Event("Executing plan..."));
    }
    public void showBoard(){
        addEvent(new Event("Acting..."));
        
        BasicMemoryUnity bmu    =WorkingMemory.getInstance().retrieveInformation("events");//Se obtiene el recuerdo
        List<Event> events =(List<Event>)bmu.information;//Se saca la lista de eventos
        
        TriquiModelOfTheWorld motw  =(TriquiModelOfTheWorld)WorkingMemory.getInstance().getModel_of_the_world();
        ViewBoard   vb  =new ViewBoard(this._out);
        vb.showBoard(motw.getBoard().getCells(),events);
    }
    public void addEvent(Event event){
        BasicMemoryUnity bmu    =WorkingMemory.getInstance().retrieveInformation("events");//Se obtiene el recuerdo
        List<Event> eventos =(List<Event>)bmu.information;//Se saca la lista de eventos        
        eventos.add(event);//Se agrega el nuevo evento
        bmu.information =eventos;//Se actualiza el recuerdo
        WorkingMemory.getInstance().storeInformation(bmu);//Se registra en la memoria
    }
}