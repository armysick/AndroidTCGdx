package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuScreen implements Screen {
    private final TCG game;
    SpriteBatch batch;
    //Texture img;
    TextButton playButton;
    Stage stage;
    Skin skin;
    Skin normalSkin;
    private boolean playMode = false;
    final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
    final TextButton.TextButtonStyle normalGameStyle = new TextButton.TextButtonStyle();
    TextButton textButton;
    TextButton normalModeButton;
    private Screen currScreen;
    private boolean readytoplay = false;


    public MenuScreen(TCG game){
        this.game = game;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/3, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        BitmapFont bfont = new BitmapFont();
        skin.add("default", bfont);

        setPlayButton();
        setNormalGameButton();
    }


    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        skin.dispose();
        //img.dispose();
    }

    @Override
    public void show() {
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(135/255f, 135/255f, 135/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        if(readytoplay){
            game.setScreen(new GameScreen(game, this));
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


    public void setPlayButton(){
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
        textButton = new TextButton("PLAY",textButtonStyle);
        textButton.setPosition(Gdx.graphics.getWidth()/3, 0);
        textButton.getLabel().setFontScale(Gdx.graphics.getWidth()/480, Gdx.graphics.getWidth()/320);
        stage.addActor(textButton);
        stage.addActor(textButton);
        stage.addActor(textButton);
        textButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if(!playMode)
                    textButton.setText("Please select game mode!");
                else {
                    textButton.setText("Starting game...");
                    readytoplay = true;
                }
            }
        });
    }

    public void setNormalGameButton(){
        normalGameStyle.up = skin.newDrawable("white", Color.BLUE);
        normalGameStyle.down = skin.newDrawable("white", Color.BLUE);
        normalGameStyle.checked = skin.newDrawable("white", Color.RED);
        normalGameStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        normalGameStyle.font = skin.getFont("default");
        TextButton normalModeButton;
        normalModeButton = new TextButton("Normal Game", normalGameStyle);
        normalModeButton.setPosition(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight()/4));
        normalModeButton.getLabel().setFontScale(Gdx.graphics.getWidth()/480, Gdx.graphics.getWidth()/320);
        stage.addActor(normalModeButton);
        stage.addActor(normalModeButton);
        stage.addActor(normalModeButton);
        normalModeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                playMode = !playMode;
            }
        });
    }





}
