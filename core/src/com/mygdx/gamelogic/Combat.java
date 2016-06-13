package com.mygdx.gamelogic;

/**
 * Created by OwnSick on 13-06-2016.
 */
public class Combat {
    private Vehicle attacker;
    private Vehicle defender;

    public Vehicle getAttacker(){
        return attacker;
    }

    public Vehicle getDefender(){
        return defender;
    }

    public void setAttacker(Vehicle attacker){
        this.attacker = attacker;
    }
    public void setDefender(Vehicle defender){
        this.defender = defender;
    }

}
