/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.memory.MemoryInformation;
import carina.memory.WorkingMemory;
import carina.metacore.Goal;
import java.io.Serializable;

/**
 *
 * @author jalheart
 */
public class ModelOfTheWorld implements Serializable{
    private Board  board;
    private Boolean is_created      = false;
    private Goal  mission;
    private String  machine_token   = "O";
    private String  player_token    = "X";
    private Boolean is_me_turn      = false;

    public ModelOfTheWorld() {
        this.board  =new Board();
        this.board.create();
        this.setMission(new Goal());
        if(!board.equals(getStateIs_created())){
            this.setStateIs_created(true);
            this.updateModelOfTheWorld();
        }else{
            this.getBoard().setCells((String[][])WorkingMemory.getInstance().retrieveInformation("cells").information);
        }
    }
    public void changeTurn(){
        this.setStateIs_me_turn(!this.getStateIs_me_turn());
    }
    public String currentToken(){
        return this.getStateIs_me_turn()?this.getMachine_token():this.getPlayer_token();
    }
    /**
     * Agrega los TOKENs usados para cada jugador
     * <p>
     * @param String machine_token
     * @param String player_token </p>
     */
    public void addTokens(String machine_token, String player_token){
        this.setMachine_token(machine_token);
        this.setPlayer_token(player_token);
    }
    public void addMission(String value){
        this.getMission().getCurrentState().setName(value);
        this.getMission().getCurrentState().setValue(false);
        this.getMission().getTargetState().setName(value);
        this.getMission().getTargetState().setValue(false);
    }
// <editor-fold defaultstate="collapsed" desc="GETs y SETs">
    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * @return the mission
     */
    public Goal getMission() {
        return mission;
    }

    /**
     * @param mission the mission to set
     */
    public void setMission(Goal mission) {
        this.mission = mission;
    }
    /**
     * @return the is_created
     */
    public Boolean getStateIs_created() {
        MemoryInformation isCreated =WorkingMemory.getInstance().retrieveInformation("is_created");
        return isCreated!=null?(Boolean)isCreated.information:this.is_created;        
    }
    /**
     * @param is_created the is_created to set
     */
    public void setStateIs_created(Boolean is_created) {
        this.is_created = is_created;
    }
    /**
     * @return the is_me_turn
     */
    public Boolean getStateIs_me_turn() {
        return is_me_turn;
    }

    /**
     * @param is_me_turn the is_me_turn to set
     */
    public void setStateIs_me_turn(Boolean is_me_turn) {
        this.is_me_turn = is_me_turn;
    }
    /**
     * @return the machine_token
     */
    public String getMachine_token() {
        return machine_token;
    }

    /**
     * @param machine_token the machine_token to set
     */
    public void setMachine_token(String machine_token) {
        this.machine_token = machine_token;
    }

    /**
     * @return the player_token
     */
    public String getPlayer_token() {
        return player_token;
    }

    /**
     * @param player_token the player_token to set
     */
    public void setPlayer_token(String player_token) {
        this.player_token = player_token;
    }
// </editor-fold>
    public void updateModelOfTheWorld(){
        WorkingMemory.getInstance().storeInformation(new MemoryInformation("cells", this.getBoard().getCells()));
        WorkingMemory.getInstance().storeInformation(new MemoryInformation("is_created", this.getStateIs_created()));
    }
}