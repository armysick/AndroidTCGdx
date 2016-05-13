package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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

/**
 * Created by OwnSick on 13-05-2016.
 */
public class Hand implements Clickable {
    TextButton textButton;
    Stage stage;
    Skin skin;
    final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();

    public Hand(){


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        Pixmap pixmap = new Pixmap(150, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        BitmapFont bfont = new BitmapFont();
        skin.add("default", bfont);
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
        textButton = new TextButton("PLAY",textButtonStyle);
        textButton.setPosition(165, 0);
        stage.addActor(textButton);
    }

    //GETTERS

    public Stage getStage(){
        return stage;
    }
    @Override
    public void setupClickListener() {
       textButton.addListener(new ChangeListener() {
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
