package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.gamelogic.*;

import java.util.ArrayList;

/**
 * Created by OwnSick on 13-05-2016.
 */

public class GameScreen implements Screen {
    private final TCG game;
    private final Screen parentscr;
    Texture bgndTex;
    Image bgndImage;
    Image miniHand;
    Container<Image> bigHand;
    SpriteBatch batch = new SpriteBatch();
    Skin skin;
    Stage stage;
    OrthographicCamera camera;
    FillViewport viewport;
    ArrayList<Clickable> objects = new ArrayList<Clickable>();
    Deck MainDeck;
    Hand hand;
    boolean handExpandedFlag;

    public GameScreen(TCG game, Screen parent){
        this.game=game;
        this.parentscr=parent;

        //
        camera = new OrthographicCamera();
        camera.viewportHeight = 1024;
        camera.viewportWidth = 1024;

        camera.position.set(camera.viewportWidth * .5f,
                camera.viewportHeight * .5f, 0f);
        camera.update();
        viewport = new FillViewport(1024, 1024, camera);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        loadTextures();
        stage.addActor(bgndImage);  // Add Background to stage
        loadStartingDeck();
        loadStartingHand();
        stage.addActor(miniHand);

        handExpandedFlag = false;
        /* Draw hand with cards
        ArrayList<Card> handcards = hand.getCards();
        int coord_x=Gdx.graphics.getWidth(), coord_y = 60;
        Image temp;
        for(int x = 0; x<handcards.size() ; x++){
            temp = handcards.get(x).getImage();
            temp.setSize(40, 40);
            coord_x -= (temp.getWidth() + 10); // separate cards
            temp.setPosition(coord_x, coord_y);
            stage.addActor(temp);
            //coord_x += 20;
        }*/


        System.out.println(stage.getActors());

    }

    // LOADING
    public void loadTextures(){
        bgndTex = new Texture("boardbackground.jpg");
        bgndImage = new Image(bgndTex);
        bgndImage.setOrigin(0, 0);
        bgndImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    public void loadStartingDeck(){
        MainDeck = new Deck();
        MainDeck.fill_deck_1();
    }
    public void loadStartingHand(){
        //graphical
        miniHand = new Image(new Texture("miniHand.jpg"));
        miniHand.setSize(40, 40);
        miniHand.setPosition(Gdx.graphics.getWidth()-(miniHand.getWidth() + 10), 78);

        miniHand.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button){
                expandHand();
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y,int pointer, int button){

            }
        });
        hand = new Hand();
        objects.add(hand);

        ArrayList<Card> drawn = new ArrayList<Card>();
        drawn = MainDeck.draw(2);
        hand.addCardsToHand(drawn);
    }

    // END LOADING

    // EDITORS

    public void expandHand(){
        handExpandedFlag = true;
        System.out.println("Hand expand");

        //bigHand
        //TODO
    }
    public void addActorToStage(Actor act){
        stage.addActor(act);
    }
    @Override
    public void show() {

    }

    public void update(float delta){

    }
    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(135/255f, 135/255f, 135/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        batch.begin();
        ArrayList<Card> handCards = hand.getCards();

        System.out.println("size: " + handCards.size());
        System.out.println("texture do zero: " + handCards.get(0).getImage());

        stage.draw();
        int x = 0, y = Gdx.graphics.getHeight();
        for(int i = 0; i<handCards.size();i++){
            if(i < 4 && handExpandedFlag) {
                System.out.println("entrou!!!!!");
                batch.draw(handCards.get(i).getImage(), x + 95, y - 95);
                x+=45;
                y -= 45;
            }
        }
        batch.end();
        /*batch.begin();
            renderBackground();
        batch.end();*/
    }

    public void renderBackground(){
        //bgndSprite.draw(batch);
    }
    @Override
    public void resize(int i, int i1) {
       /* camera.viewportWidth = i;
        camera.viewportHeight = i1;
        camera.update();*/
        camera.position.set(512, 512, 0);
        viewport.update(i, i1);
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

    @Override
    public void dispose() {
        stage.dispose();
    }
}
