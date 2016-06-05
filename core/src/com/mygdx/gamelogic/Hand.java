package com.mygdx.gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Clickable;
import com.mygdx.game.GameScreen;

import java.util.ArrayList;

/**
 * Created by OwnSick on 13-05-2016.
 */
public class Hand implements Clickable {
    TextButton normalModeButton;
    Skin skin;
    Stage stage;
    final TextButton.TextButtonStyle normalGameStyle = new TextButton.TextButtonStyle();
    ArrayList<Card> cards = new ArrayList<Card>();

    public Hand(){

    }



    public void addCardsToHand(ArrayList<Card> toadd){
        cards.addAll(toadd);
    }


    public void remove(int index){
        this.cards.remove(index);
    }
    //GETTERS

    public ArrayList<Card> getCards(){
        return cards;
    }
    @Override
    public void setupClickListener() {
       normalModeButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
    }

    @Override
    public void setupHoverListener() {

    }

    @Override
    public void setupDragListener() {

    }
}
