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

    public Hand(GameScreen GS){


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();

        //TODO add number of cards received on arguments
        //GS.addActorToStage(normalModeButton);


        /*Pixmap pixmap = new Pixmap(40, 70, Pixmap.Format.RGBA8888);

        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        BitmapFont bfont = new BitmapFont();
        skin.add("default", bfont);
        normalGameStyle.up = skin.newDrawable("white", Color.BLUE);
        normalGameStyle.down = skin.newDrawable("white", Color.BLUE);
        normalGameStyle.checked = skin.newDrawable("white", Color.RED);
        normalGameStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        normalGameStyle.font = skin.getFont("default");

        normalModeButton = new TextButton("Normal Game", normalGameStyle);
        normalModeButton.setPosition(25, 25);
        stage.addActor(normalModeButton);*/


    }



    public void addCardsToHand(ArrayList<Card> toadd){
        cards.addAll(toadd);
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
