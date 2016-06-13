package com.mygdx.gamelogic;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.Utils.Utilidades;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Created by OwnSick on 11-06-2016.
 */
public class Vehicle extends Card {

    private int mat_cost;
    private int trip_cost; //tripulation cost
    private int attack_power;
    private int apleft; //attack power remaining each turn

    public Vehicle(String name, CardEffect eff, Texture img, int mat_cost, int trip_cost, int attack_power){
        super(name, eff, img);
        this.mat_cost = mat_cost;
        this.trip_cost = trip_cost;
        this.attack_power = attack_power;
        this.apleft = attack_power;
    }

    /**
     *
     * Checks vehicle has enough resources to be summoned and plays it if successful
     * @param board - current player board
     * @param mats - each material counter [order: metal, wood, glass, rubber]
     * @return SimpleEntry with indexes to remove from board[tripulation cost] and materials consumed [material cost]
     */
    public AbstractMap.SimpleEntry<ArrayList<Integer>, int[]> playvehicle(Card[] board, int[] mats){
        int minion_counter = 0;
        int cost_left_to_pay = trip_cost;
        for(int i = 0 ; i < 4 ; i++)
            if(board[i] instanceof Minion) {
                minion_counter++;
                if(board[i].getName().equals("twinsoldier") && (this.trip_cost > 1))
                    cost_left_to_pay--;
            }
        int sum = 0;
        for(int m : mats)
            sum+=m;

        ArrayList<Integer> indexes_to_remove = new ArrayList<Integer>();
        if(minion_counter >=trip_cost && sum >= mat_cost){  // if board can pay summon cost

            for(int t = 0; t < cost_left_to_pay ; t++){
                if(board[t] instanceof Minion) {
                    indexes_to_remove.add(t);
                    cost_left_to_pay--;
                }
                cost_left_to_pay++;
            }
            int rand;
            for(int m = 0 ; m < mat_cost ; m++){
                rand = Utilidades.randomWithRange(0, 3);
                while(mats[rand] < 1)   // Get a valid material counter to decrease
                    rand = Utilidades.randomWithRange(0, 3);
                mats[rand]--;
            }
            return (new AbstractMap.SimpleEntry<ArrayList<Integer>, int[]>(indexes_to_remove, mats));
        }
        return null;

    }


    /**
     *
     * @return remaining/Complete turn attack power
     */
    public int getAPLeft(){
        return this.apleft;
    }
}
