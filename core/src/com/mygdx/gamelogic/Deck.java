package com.mygdx.gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameScreen;

import java.util.ArrayList;

/**
 * Created by OwnSick on 16-05-2016.
 */
public class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();
    public Deck(){

    }

    public void fill_deck_1(){
        cards.add(new Minion("twinsoldier", new CardEffect(1), new Texture("twinsoldier.jpg")));
        cards.add(new Minion("twinsoldier", new CardEffect(1), new Texture("twinsoldier.jpg")));
        cards.add(new Minion("pickaxesoldier", new CardEffect(2), new Texture("pickaxesoldier.jpg")));
        cards.add(new Minion("pickaxesoldier", new CardEffect(2), new Texture("pickaxesoldier.jpg")));
        cards.add(new Minion("vanillasoldier", new CardEffect(0), new Texture("vanillasoldier.jpg")));
        cards.add(new Minion("vanillasoldier", new CardEffect(0), new Texture("vanillasoldier.jpg")));
        cards.add(new Minion("handysoldier", new CardEffect(3), new Texture("handysoldier.jpg")));
        cards.add(new Minion("handysoldier", new CardEffect(3), new Texture("handysoldier.jpg")));
    }

    public void fill_deck_2(){
        cards.add(new Minion("vanillasoldier", new CardEffect(0), new Texture("vanillasoldier.jpg")));
        cards.add(new Minion("vanillasoldier", new CardEffect(0), new Texture("vanillasoldier.jpg")));
        cards.add(new Minion("vanillasoldier", new CardEffect(0), new Texture("vanillasoldier.jpg")));
        cards.add(new Minion("vanillasoldier", new CardEffect(0), new Texture("vanillasoldier.jpg")));
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

    public void startgui(GameScreen gs){
        Image deckimg = new Image(new Texture("extradeck.jpg"));
        deckimg.setSize(Gdx.graphics.getWidth()/8, (int) (Gdx.graphics.getHeight()/5.3));
        deckimg.setPosition(Gdx.graphics.getWidth() - deckimg.getWidth(), Gdx.graphics.getHeight()/15);
        gs.addActorToStage(deckimg);

    }
}
