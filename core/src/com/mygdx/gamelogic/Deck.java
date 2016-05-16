package com.mygdx.gamelogic;

import java.util.ArrayList;

/**
 * Created by OwnSick on 16-05-2016.
 */
public class Deck {
    ArrayList<Card> cards = new ArrayList<Card>();
    public Deck(){

    }

    public void fill_deck_1(){
        cards.add(new Minion("demo minion", new CardEffect()));
        cards.add(new Minion("demo minion2", new CardEffect()));
        cards.add(new Minion("demo minion3", new CardEffect()));
        cards.add(new Minion("demo minion4", new CardEffect()));
    }

    public ArrayList<Card> draw(int qty){
        ArrayList<Card> drawn = new ArrayList<Card>();
        int range = cards.size();

        for(int i = 0; i < qty ; i++) {
            if(cards.size() != 0) {
                int random = (int) (Math.random() * range);
                drawn.add(cards.get(random));
                cards.remove(random);
                range -=1;
            }
            else
                break;
        }

        return drawn;
    }
}
