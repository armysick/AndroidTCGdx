package com.mygdx.gamelogic;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by OwnSick on 05-06-2016.
 */
public class Boss extends Card {

    private int start_hand;
    private int draws;
    private int mills;

    public Boss(String name, CardEffect eff, Texture img, int start_hand, int draws, int mills){
        super(name, eff, img);
        this.start_hand = start_hand;
        this.draws = draws;
        this.mills = mills;
    }

    public int getStartHand(){
        return this.start_hand;
    }
}
