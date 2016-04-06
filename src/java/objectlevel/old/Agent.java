/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.old;

import carina.metalevel.old.MetaAgent;
import carina.metalevel.old.metamemory.LongTermMemory;
import carina.metalevel.old.metamemory.LongTermMemoryFile;
import carina.metalevel.old.metamemory.LongTermMemorySQLite;
import carina.memory.WorkingMemory;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpSession;
import objectlevel.old.actuators.ModelOfTheWorldActuator;
import objectlevel.old.comprehension.Comprehension;
import objectlevel.old.models.ModelOfTheWorld;
import objectlevel.old.perception.Perception;
import objectlevel.old.actuators.ViewBoardActuator;
import objectlevel.old.comprehension.Move;
import objectlevel.old.comprehension.Reasoning;

/**
 *
 * @author jalheart
 */
public final class Agent extends MetaAgent{    
    private Map<String,String[]> sensors;
    private ModelOfTheWorldActuator modelOfTheWorldActuator;
    public Agent(Map<String,String[]> sensors, HttpSession sesion,PrintWriter salida) {
        this.initialize(sensors,sesion,salida);        
    }
    public void showBoard(String winner){
        ModelOfTheWorld cmotw   =this.getCurrentModelOfTheWorld();
        ((ViewBoardActuator)this.getActuator()).showBoard(cmotw.getModelOfTheWorld().getCells(),winner);
    }
        
    public void initialize(Map<String,String[]> sensors,HttpSession sesion,PrintWriter salida) {
        WorkingMemory.getInstance().init("sesion", sesion);
        this.setActuator(new ViewBoardActuator(salida));
        this.sensors        =sensors;
        
        this.setCurrentPerception(new Perception());
        this.getCurrentPerception().setSensors(sensors);
        
        this.setCurrentModelOfTheWorld(new ModelOfTheWorld());
        this.setComprehension(new Comprehension(WorkingMemory.getInstance(), null, this.getCurrentModelOfTheWorld()));
        this.setReasoning(new Reasoning(WorkingMemory.getInstance(), null, this.getCurrentModelOfTheWorld()));        
        this.modelOfTheWorldActuator    =new ModelOfTheWorldActuator(WorkingMemory.getInstance(), null,this.getCurrentModelOfTheWorld());
        
//        this.setLongTermMemory(new LongTermMemorySQLite());
//        Move    m=new Move(8, 4);
        this.setLongTermMemory(new LongTermMemoryFile());
//        this.getLongTermMemory().memorize("juan", m);        
//        Move m2 = (Move)this.getLongTermMemory().remember("juan");
//        System.err.println(m2.fila);
    }
    public void restart() {
        WorkingMemory.getInstance().forgetAll();
    }
    @Override
    public void run() {
        String winner   ="";
        //La perscepción, solo es obtener los datos de los sensores
        String perceivedData    =(String)this.getCurrentPerception().perceive("posicion");
        if(perceivedData!=null){
            //Se hace el procesamiento de los datos percibidos; esto genera información
            Move processedInformation  =this.getComprehension().processInformation(perceivedData);
            //Con la información se toma la decisión de jugar o no en la casilla
            if(((Reasoning)this.getReasoning()).canPlay(processedInformation)){
                //Con base en la decision tomada, se realiza una o varias acciones
                this.modelOfTheWorldActuator.play(processedInformation, "X");
                if(!((Reasoning)this.getReasoning()).win("X", processedInformation)){
                    if(((Reasoning)this.getReasoning()).canPlay()){
                        Move    move    =((Reasoning)this.getReasoning()).selectMove();
                        this.modelOfTheWorldActuator.play(move, "O");
                        if(((Reasoning)this.getReasoning()).win("O", move)){//Ganan las O
                            winner  ="O";
                        }
                    }
                }else{//Gana las X
                    winner  ="X";
                }
            }
        }else{
            this.restart();
        }
        this.showBoard(winner);
    }
    // <editor-fold defaultstate="collapsed" desc="Sobreescritura de métodos para dar compatibilidad con los tipos de dato">
    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public ModelOfTheWorld getCurrentModelOfTheWorld() {
        return (ModelOfTheWorld)super.getCurrentModelOfTheWorld();
    }
    @Override
    public Comprehension getComprehension() {
        return (Comprehension)super.getComprehension(); //To change body of generated methods, choose Tools | Templates.
    }    
    // </editor-fold>
}