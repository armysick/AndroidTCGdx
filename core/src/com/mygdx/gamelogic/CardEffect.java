package com.mygdx.gamelogic;

/**
 * Created by OwnSick on 16-05-2016.
 */
public class CardEffect {

    private int id;
    public CardEffect(int id){
        this.id = id;
    }

    public BoardState activate(){

        switch(id) {
            case 2:  // pickaxesoldier
                return pickaxesoldiereff();
            case 3:
                return handysoldiereff();
        }
        return new BoardState();
    }

    public BoardState pickaxesoldiereff(){
        BoardState bs = new BoardState();
        bs.mill(1);
        return bs;
    }

    public BoardState handysoldiereff(){
        BoardState bs= new BoardState();
        bs.draw(1);
        return bs;
    }
}
