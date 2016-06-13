package com.mygdx.gamelogic;

/**
 * Created by OwnSick on 13-06-2016.
 */
public class BoardState {
    int number_to_be_milled = 0;
    int number_to_be_drawn = 0;

    public void mill(int qty){
        number_to_be_milled+=qty;
    }

    public void draw(int qty){
        number_to_be_drawn+=qty;
    }

    public int getMilled(){return this.number_to_be_milled;}
    public int getDrawn(){return this.number_to_be_drawn;}

}
