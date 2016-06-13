package com.mygdx.gamelogic;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by OwnSick on 05-06-2016.
 */
public class Boss extends Card {

    private int start_hand;
    private int draws;
    private int mills;

    /**
     * Constructor
     * @param name - Boss name
     * @param eff - Card Effect
     * @param img - texture
     * @param start_hand - starting hand size
     * @param draws - draws per turn
     * @param mills - mills per turn
     */
    public Boss(String name, CardEffect eff, Texture img, int start_hand, int draws, int mills){
        super(name, eff, img);
        this.start_hand = start_hand;
        this.draws = draws;
        this.mills = mills;
    }

    public int getStartHand(){
        return this.start_hand;
    }

    public int getDraws(){
        return this.draws;
    }

    public int getMills(){
        return this.mills;
    }
}
