package com.mygdx.gamelogic;

/**
 * Created by OwnSick on 13-06-2016.
 */
public class Combat {
    private Vehicle attacker;
    private Vehicle defender;
    private int attindex;
    private int defindex;

    public Vehicle getAttacker(){
        return attacker;
    }

    public Vehicle getDefender(){
        return defender;
    }

    public void setAttacker(Vehicle attacker, int index){
        this.attacker = attacker;
        this.attindex = index;
    }
    public void setDefender(Vehicle defender, int index){
        this.defender = defender;
        this.defindex = index;
    }

    public void reset(){
        this.attacker = null;
        this.attindex = -1;
        this.defender = null;
        this.defindex = -1;
    }

    /**
     *
     * @return defender vehicle board index, if destroyed; -1 if no success
     */
    public int fight(){
        if(attacker.getAPLeft() >= defender.getAPLeft())
            return defindex;
        return -1;
    }

}
