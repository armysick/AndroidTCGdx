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

    /**
     * Automatically fills with default deck
     */
    public void fill_deck_1(){
        for(int i = 0 ; i<5 ;i++) {
            mats.add(new Mats("metal", new CardEffect(0), new Texture("metal.jpg")));  // dá 100
            mats.add(new Mats("wood", new CardEffect(0), new Texture("wood.jpg")));  // retira 100
            mats.add(new Mats("glass", new CardEffect(0), new Texture("glass.jpg")));  // Draw
            mats.add(new Mats("rubber", new CardEffect(0), new Texture("rubber.jpg"))); // mill
        }
    }

    /**
     *
     * @param qty - quantity to be drawn [milled]
     * @return ArrayList of cards - Cards [materials] milled
     */
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

    /**
     * readds materials to deck based on counter differences / changes
     * @param prev - previous counter for each material type
     * @param curr - current counter for each material type
     */
    public void readd(int[] prev, int[] curr){

        System.out.println("MATS SIZE BEFORE: " + mats.size());
        for(int i = 0 ; i < 4 ; i++){
            System.out.println("mat counter: " + prev[i]);
            System.out.println("new counter: " + curr[i]);
            for(int x = 0; x <(prev[i] - curr[i]); x++){

                if(i == 0)
                    mats.add(new Mats("metal", new CardEffect(0), new Texture("metal.jpg")));  // dá 100
                else if(i == 1)
                    mats.add(new Mats("wood", new CardEffect(0), new Texture("wood.jpg")));  // retira 100
                else if(i == 2)
                   mats.add(new Mats("glass", new CardEffect(0), new Texture("glass.jpg")));  // Draw
                else if(i == 3)
                    mats.add(new Mats("rubber", new CardEffect(0), new Texture("rubber.jpg"))); // mill
            }
        }
        System.out.println("MATS SIZE AFTER: " + mats.size());
    }
}
