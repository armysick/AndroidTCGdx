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
        vehics.add(new Vehicle("warmotocycle", new CardEffect(0), new Texture("warmotocycle.jpg"), 1, 1, 1000));
        vehics.add(new Vehicle("armoredcar", new CardEffect(0), new Texture("armoredcar.jpg"), 2, 2, 2000));
        vehics.add(new Vehicle("lizardtank", new CardEffect(0), new Texture("lizardtank.jpg"), 3, 3, 3000));
        //vehics.add(new Vehicle("vehic4", new CardEffect(0), new Texture("demominion2.jpg"), 4, 4, 4000));
    }

    public void fill_extra_deck_2(){
        vehics.add(new Vehicle("humanoid", new CardEffect(0), new Texture("humanoid.jpg"), 1, 0, 6000));
        vehics.add(new Vehicle("humanoid", new CardEffect(0), new Texture("humanoid.jpg"), 1, 0, 6000));
        vehics.add(new Vehicle("humanoid", new CardEffect(0), new Texture("humanoid.jpg"), 1, 0, 6000));
    }

    public ArrayList<Vehicle> getVehics(){
        return this.vehics;
    }

    public void remove(int index){
        vehics.remove(index);
    }


}
