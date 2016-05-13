/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.metacore.ComputationalStrategy;
import carina.metacore.Plan;
import carina.objectlevel.Category;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jalheart
 */
public class PlanningAlgorithmStrategy extends ComputationalStrategy{
    private List<Category>_categories;
    public PlanningAlgorithmStrategy(List<Category>categories) {
        _categories =categories;
    }    
    @Override
    public Object run() {
        Map<String,Plan> plans  =new HashMap<>();
        if("playable".equals(_categories.get(0).getCategory())){
            ModifyBoard modifyBoard     =new ModifyBoard();
            VerifyWinner verifyWinner   =new VerifyWinner();
            ChangeTurn  changeTurn      =new ChangeTurn();
            MachinePlays machinePlays   =new MachinePlays();
            ShowWorld showWorld         =new ShowWorld(this);

            Plan planTmp    =new Plan();
            planTmp.setAction(modifyBoard);
            planTmp.setAction(verifyWinner);
            planTmp.setAction(changeTurn);
            planTmp.setAction(machinePlays);
            planTmp.setAction(verifyWinner);
            planTmp.setAction(changeTurn);
            plans.put("playable", planTmp);
        }else if("reset".equals(_categories.get(0).getCategory())){
            ResetBoard resetBoard   =new ResetBoard();
            Plan planTmp    =new Plan();
            planTmp.setAction(resetBoard);
            plans.put("reset", planTmp);
        }
        return plans;
    }    
}