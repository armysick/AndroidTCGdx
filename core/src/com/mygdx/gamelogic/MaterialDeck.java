package com.mygdx.gamelogic;

import com.badlogic.gdx.graphics.Texture;

import java.util.AbstractMap;
import java.util.ArrayList;

/**
 * Created by OwnSick on 08-06-2016.
 */
public class MaterialDeck {

    private ArrayList<Mats> mats = new ArrayList<Mats>();
    public MaterialDeck(){

    }

    public void fill_deck_1(){
        for(int i = 0 ; i<5 ;i++) {
            mats.add(new Mats("metal", new CardEffect(), new Texture("metal.jpg")));  // dá 100
            mats.add(new Mats("wood", new CardEffect(), new Texture("wood.jpg")));  // retira 100
            mats.add(new Mats("glass", new CardEffect(), new Texture("glass.jpg")));  // Draw
            mats.add(new Mats("rubber", new CardEffect(), new Texture("rubber.jpg"))); // mill
        }
    }

    public ArrayList<Card> draw(int qty){
        ArrayList<Card> drawn = new ArrayList<Card>();
        int range = mats.size();


        for(int i = 0; i < qty ; i++) {
            if(mats.size() != 0) {
                int random = (int) (Math.random() * range);
                drawn.add(mats.get(random));
                mats.remove(random);
                range -=1;
            }
            else
                break;
        }

        return drawn;
    }


    public ArrayList<Integer> mill(int qty){
        ArrayList<Mats> milled = new ArrayList<Mats>();
        ArrayList<Integer> finalmilled = new ArrayList<Integer>();
        int range = this.mats.size();
        for(int i = 0 ; i < qty ; i ++){
            if(mats.size() > 0) {
                int random = (int) (Math.random() * range);
                milled.add(mats.get(random));
                mats.remove(random);
                range -= 1;
            }
            else
                break;
        }

        int mc = 0, wc = 0, gc = 0 , rc = 0;  // Each material counter

        for(int x = 0; x < milled.size(); x++){
            if(milled.get(x).getName().equals("metal"))
                mc++;
            else if(milled.get(x).getName().equals("wood"))
                wc++;
            else if(milled.get(x).getName().equals("glass"))
                gc++;
            else if(milled.get(x).getName().equals("rubber"))
                rc++;

        }

        finalmilled.add(mc);
        finalmilled.add(wc);
        finalmilled.add(gc);
        finalmilled.add(rc);

        return finalmilled;

    }
}
