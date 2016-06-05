package com.mygdx.game;
//String path = ((FileTextureData)texture.getTextureData()).getFileHandle().path();
//Texture texture = ...;
//Image image = new Image(texture);

//// switch to a new texture
//        Texture newTexture = ...;
//        image.setDrawable(new SpriteDrawable(new Sprite(newTexture)));

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.Utils.Utilidades;
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
    Image bosserinoImg;
    Image expandBosserino;
    SpriteBatch batch = new SpriteBatch();
    Skin skin;
    Stage stage;
    OrthographicCamera camera;
    FillViewport viewport;
    ArrayList<Clickable> objects = new ArrayList<Clickable>();
    Deck MainDeck;
    Hand hand;
    Boss bosserino;
    boolean handExpandedFlag;
    boolean okay_to_select = false;
    Sprite str;
    ArrayList<Sprite> expandSpriteList = new ArrayList<Sprite>();
    ArrayList<Card> boardCards = new ArrayList<Card>();
    ArrayList<Image> minionvehiczone = new ArrayList<Image>();
    ArrayList<Image> expandedZones = new ArrayList<Image>();

    public GameScreen(TCG game, Screen parent){
        this.game=game;
        this.parentscr=parent;

        //
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        stage = new Stage();

        loadTextures();
        stage.addActor(bgndImage);  // Add Background to stage
        for(int h = 0 ; h < minionvehiczone.size() ; h++){
            stage.addActor(minionvehiczone.get(h));
        }
        loadStartingBoard();
        loadStartingDeck();
        loadStartingHand();
        stage.addActor(bosserinoImg);
        stage.addActor(miniHand);

        handExpandedFlag = false;




        Gdx.input.setInputProcessor(stage);
        System.out.println(stage.getActors());

    }

    // LOADING

    public void loadStartingBoard(){
        bosserino = new Boss("highgeneral", new CardEffect(), new Texture("highgeneralboss.jpg"), 4, 2, 2);
        bosserinoImg = new Image(bosserino.getImage());
        bosserinoImg.setSize((int) (Gdx.graphics.getWidth() / 6.4),(int) (Gdx.graphics.getHeight()/4.3));
        int wid = Gdx.graphics.getWidth()/2;
        int hei = Gdx.graphics.getHeight()/2;
        bosserinoImg.setPosition(wid - (bosserinoImg.getWidth()/2) , hei - (bosserinoImg.getHeight()/2) - hei/5);

        //expanded version
        expandBosserino = new Image(bosserino.getImage());
        expandBosserino.setSize((int) (bosserinoImg.getWidth()*2.5), (int) (bosserinoImg.getHeight()*2.5));
        expandBosserino.setPosition(wid - (bosserinoImg.getWidth()/2) , hei - (bosserinoImg.getHeight()/2) - hei/5);
        //

        bosserinoImg.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button){

                stage.addActor(expandBosserino);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y,int pointer, int button){
                expandBosserino.remove();
            }

        });

    }
    public void loadTextures(){
        bgndTex = new Texture("boardbackground.jpg");
        bgndImage = new Image(bgndTex);
        bgndImage.setOrigin(0, 0);
        bgndImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bgndImage.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button){
                boolean col = false;
                int scrwidth = Gdx.graphics.getWidth();
                int scrheight = Gdx.graphics.getHeight();
                int cardwidth = scrwidth/5;
                int cardheight = scrheight/2 - 30;
                int x_expand = scrwidth/9 - cardwidth/3;
                int y_expand = scrheight - cardheight - 10;
                for(int i=0; i<hand.getCards().size();i++) {
                    if(x >= x_expand && x <= x_expand + cardwidth && y >= y_expand && y <=y_expand + cardheight)
                        col = true;
                    x_expand += cardwidth + 15;
                    if(i == 3) {
                        y_expand -= scrheight/2 + 15;
                        x_expand = scrwidth/9 - cardwidth/3;
                    }
                }
                if(handExpandedFlag && !col) {
                    handExpandedFlag = false;
                    okay_to_select = false;
                }
                for(int u = 0 ; u < expandedZones.size();u++){
                    expandedZones.get(u).remove();
                }
                return true;
            }
        });

        int hei = Gdx.graphics.getHeight()/2;

        int x_coord = Gdx.graphics.getWidth()/10;
        int exp_x_coord = x_coord;
        for(int mvz = 0 ; mvz < 4 ; mvz++){
            Image new_img = new Image(new Texture("cardzone.jpg"));
            new_img.setSize(Gdx.graphics.getWidth()/8, (int) (Gdx.graphics.getHeight()/5.3));
            if(mvz == 3)
                x_coord = (int) (Gdx.graphics.getWidth()/10 + 3*(new_img.getWidth() + Gdx.graphics.getWidth()/11));
            else if(mvz == 2)
                x_coord += (int) (Gdx.graphics.getWidth() / 6.4) ;  //first part = boss card width
            new_img.setPosition(x_coord, hei - (new_img.getHeight()/2) - hei/5);


            //expanded version
            Image new_img_expand = new Image(new Texture("cardzone.jpg"));
            new_img_expand.setSize((int) (new_img.getWidth()*2.5), (int) (new_img.getHeight()*2.5));
            new_img_expand.setPosition(exp_x_coord , hei - (new_img.getHeight()/2) - hei/5);
            expandedZones.add(new_img_expand);
            //

            x_coord += new_img.getWidth() + (int) (Gdx.graphics.getWidth()/22);
            minionvehiczone.add(new_img);

        }


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
                okay_to_select = true;
            }
        });
        hand = new Hand();
        objects.add(hand);

        ArrayList<Card> drawn = new ArrayList<Card>();
        //drawn = MainDeck.draw(bosserino.getStartHand());
        drawn = MainDeck.draw(8);
        System.out.println("drew: " + drawn.size());
        hand.addCardsToHand(drawn);
    }

    // END LOADING

    // EDITORS

    public void expandHand(){
        expandSpriteList.clear();
        ArrayList<Card> handCards = hand.getCards();
        for(int i = 0; i < handCards.size() ; i++) {
            Texture mH = handCards.get(i).getImage();
            TextureRegion tr = new TextureRegion(mH, mH.getWidth(), mH.getHeight());
            str = new Sprite(tr);
            str.setSize(20, 20);
            expandSpriteList.add(str);
        }
        handExpandedFlag = true;
        System.out.println("Hand expand");

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

        if(Gdx.input.isTouched()){
            if(handExpandedFlag && okay_to_select){
                int index = -1;
                if((index = expandHandCollisionDetected(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) > -1){  // Gdx.input.getY() has zero on top
                    hand.remove(index);
                }
            }
            else if (!handExpandedFlag){
                int index = getZoneClickedFromCoords((int)Gdx.input.getX(), (int) (Gdx.graphics.getHeight() - Gdx.input.getY()));

                if(index > -1) {
                    System.out.println("GOT EM");
                    stage.addActor(expandedZones.get(index));
                }
            }
        }

        stage.draw();

        batch.begin();
       // System.out.println("width: " + Gdx.graphics.getWidth());  // Width = 480
       // System.out.println("height: " + Gdx.graphics.getHeight()); // Height = 320

        int scrwidth = Gdx.graphics.getWidth();
        int scrheight = Gdx.graphics.getHeight();
        int cardwidth = scrwidth/5;
        int cardheight = scrheight/2 - 30;
        if(handExpandedFlag) {
            int x_expand = scrwidth/9 - cardwidth/3;
            int y_expand = scrheight - cardheight - 10;
            int i;
            for(i = 0; i<expandSpriteList.size() && i<4; i++) {
                //System.out.println("i: " + i);
                batch.draw(expandSpriteList.get(i), x_expand, y_expand, cardwidth, cardheight);  // Coordenadas do draw; [onde origem]
                x_expand += cardwidth + 15;
                //y_expand += scrheight/5 + 15
            }
            y_expand -= scrheight/2 + 15;
            x_expand = scrwidth/9 - cardwidth/3;
            for(i = 4; i<expandSpriteList.size() && i<8;i++){
                batch.draw(expandSpriteList.get(i), x_expand, y_expand, cardwidth, cardheight);
                x_expand += cardwidth +15;
            }
        }

        batch.end();
        /*batch.begin();
            renderBackground();
        batch.end();*/
    }


    public int expandHandCollisionDetected(int x, int y){

        int col = -1;
        int scrwidth = Gdx.graphics.getWidth();
        int scrheight = Gdx.graphics.getHeight();
        int cardwidth = scrwidth/5;
        int cardheight = scrheight/2 - 30;
        int x_expand = scrwidth/9 - cardwidth/3;
        int y_expand = scrheight - cardheight - 10;
        for(int i=0; i<hand.getCards().size();i++) {
            if(x >= x_expand && x <= x_expand + cardwidth && y >= y_expand && y <=y_expand + cardheight) {
                col = i;
            }
            x_expand += cardwidth + 15;
            if(i == 3) {
                y_expand -= scrheight/2 + 15;
                x_expand = scrwidth/9 - cardwidth/3;
            }
        }
        if(handExpandedFlag && col > -1) {
            handExpandedFlag = false;
            okay_to_select = false;
        }
        return col;
    }

    public int getZoneClickedFromCoords(int x, int y){
        int answer = -1;
        int hei = Gdx.graphics.getHeight()/2;

        int zonewid = Gdx.graphics.getWidth() / 8;
        int zonehei = (int) (Gdx.graphics.getHeight() / 5.3);
        int x_coord = Gdx.graphics.getWidth()/10;
        int y_coord = hei - (zonehei / 2) - hei / 5;
        for(int mvz = 0 ; mvz < 4 ; mvz++) {


            if(mvz == 3)
                x_coord = (int) (Gdx.graphics.getWidth()/10 + 3*(zonewid + Gdx.graphics.getWidth()/11));
            else if(mvz == 2)
                x_coord += (int) (Gdx.graphics.getWidth() / 6.4) ;  //first part = boss card width

            if(x >= x_coord && x <= (x_coord + zonewid) && y >= y_coord && y <= y_coord + zonehei) {
                System.out.println(" x +- y " + x + " -- "  + y);
                answer = mvz;
            }
            x_coord += zonewid + (int) (Gdx.graphics.getWidth()/22);
        }
        return answer;

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
        //viewport.update(i, i1);
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
