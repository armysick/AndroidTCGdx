package com.mygdx.gamelogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Clickable;

/**
 * Created by OwnSick on 13-05-2016.
 */
public class Card implements Clickable{
    private String name;
    private CardEffect eff;
    private Texture img;

    public Card(){
    }
    public Card(String name, CardEffect eff){
        this.name = name;
        this.eff = eff;
    }
    public Card(String name, CardEffect eff, Texture img){
        this.name = name;
        this.eff=eff;
        this.img=img;
    }


    //GETTERS
    public String getName(){
        return name;
    }

    public Texture getImage(){
        return this.img;
    }
    public CardEffect getEff(){return this.eff;};

    public BoardState activateEffect(){
        return this.getEff().activate();
    }
    @Override
    public void setupClickListener() {
    }

    @Override
    public void setupHoverListener() {
    }

    @Override
    public void setupDragListener() {
    }
}
