package com.mygdx.gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameScreen;

import java.util.ArrayList;

/**
 * Created by OwnSick on 11-06-2016.
 */
public class ExtraDeck{
    private ArrayList<Vehicle> vehics = new ArrayList<Vehicle>();


    public ExtraDeck(){

    }

    public void fill_extra_deck_1(){
        vehics.add(new Vehicle("vehic1", new CardEffect(0), new Texture("boardbackground.jpg"), 1, 1));
        vehics.add(new Vehicle("vehic2", new CardEffect(0), new Texture("twinsoldier.jpg"), 2, 2));
        vehics.add(new Vehicle("vehic3", new CardEffect(0), new Texture("demominion.jpg"), 3, 3));
        vehics.add(new Vehicle("vehic4", new CardEffect(0), new Texture("demominion2.jpg"), 4, 4));
    }


    public ArrayList<Vehicle> getVehics(){
        return this.vehics;
    }

    public void remove(int index){
        vehics.remove(index);
    }


}
