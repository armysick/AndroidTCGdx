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

    public Vehicle(String name, CardEffect eff, Texture img, int mat_cost, int trip_cost){
        super(name, eff, img);
        this.mat_cost = mat_cost;
        this.trip_cost = trip_cost;
    }

    public AbstractMap.SimpleEntry<ArrayList<Integer>, int[]> playvehicle(Card[] board, int[] mats){
        int minion_counter = 0;
        for(int i = 0 ; i < 4 ; i++)
            if(board[i] instanceof Minion)
                minion_counter++;
        System.out.println("MINION COUNTER: " + minion_counter);
        int sum = 0;
        for(int m : mats)
            sum+=m;

        ArrayList<Integer> indexes_to_remove = new ArrayList<Integer>();
        if(minion_counter >=trip_cost && sum >= mat_cost){  // if board can pay summon cost
            int cost_left_to_pay = trip_cost;
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
}
