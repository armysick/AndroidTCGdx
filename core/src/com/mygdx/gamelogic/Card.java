package com.mygdx.gamelogic;

import com.mygdx.game.Clickable;

/**
 * Created by OwnSick on 13-05-2016.
 */
public class Card implements Clickable{
    private String name;
    private CardEffect eff;

    public Card(){
    }
    public Card(String name, CardEffect eff){
        this.name = name;
        this.eff=eff;
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
