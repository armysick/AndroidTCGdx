package com.mygdx.gamelogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by OwnSick on 16-05-2016.
 */
public class Minion extends Card {


    public Minion(String name, CardEffect eff, Texture img){
        super(name, eff, img);
    }

    public Minion(String name, CardEffect eff){
        super(name, eff);
    }

}
