package com.mygdx.game;
//String path = ((FileTextureData)texture.getTextureData()).getFileHandle().path();
//Texture texture = ...;
//Image image = new Image(texture);

//// switch to a new texture
//        Texture newTexture = ...;
//        image.setDrawable(new SpriteDrawable(new Sprite(newTexture)));

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.Utils.Utilidades;
import com.mygdx.gamelogic.*;

import java.util.AbstractMap;
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
    Image enemyBosserinoImg;
    Image expandBosserino;
    Image enemyexpandBosserino;
    SpriteBatch batch = new SpriteBatch();
    Skin skin;
    Stage stage;
    OrthographicCamera camera;
    FillViewport viewport;
    ArrayList<Clickable> objects = new ArrayList<Clickable>();
    Deck MainDeck;
    MaterialDeck MatDeck;
    ExtraDeck extraDeck;
    Hand hand;
    Boss bosserino;
    Boss enemybosserino;
    boolean handExpandedFlag;
    boolean extraExpandedFlag;
    boolean okay_to_select = false;
    Sprite str;
    ArrayList<Sprite> expandSpriteList = new ArrayList<Sprite>();
    Card[] boardCards = new Card[4];
    ArrayList<Image> minionvehiczone = new ArrayList<Image>();
    ArrayList<Image> enemyminionvehiczone = new ArrayList<Image>();
    ArrayList<Image> expandedZones = new ArrayList<Image>();
    ArrayList<Image> expandedEnemyZones = new ArrayList<Image>();
    ArrayList<Image> materialzone = new ArrayList<Image>();
    ArrayList<Image> enemymaterialzone = new ArrayList<Image>();
    ArrayList<Label> numberslbls = new ArrayList<Label>();  // Order: Metal // Wood // Glass // Rubber
    int[] matcounters = {0,0,0,0};

    public GameScreen(TCG game, Screen parent){
        this.game=game;
        this.parentscr=parent;

        //
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        stage = new Stage();

        loadTextures();
        bgndImage.toBack();
        stage.addActor(bgndImage);  // Add Background to stage
        addZones();
        loadStartingBoard();
        loadStartingDecks();
        loadStartingHand();
        stage.addActor(bosserinoImg);
        stage.addActor(miniHand);
        stage.addActor(enemyBosserinoImg);

        handExpandedFlag = false;
        extraExpandedFlag = false;


        Gdx.input.setInputProcessor(stage);

        //stageRotate();
        System.out.println(stage.getActors());

    }

    // LOADING

    public void addZones(){
        for(int h = 0 ; h < minionvehiczone.size() ; h++)
            stage.addActor(minionvehiczone.get(h));

        for(int he = 0; he < enemyminionvehiczone.size(); he++)
            stage.addActor(enemyminionvehiczone.get(he));

        for(int m = 0 ; m < materialzone.size(); m++)
            stage.addActor(materialzone.get(m));

        for(int me = 0 ; me < enemymaterialzone.size(); me++)
            stage.addActor(enemymaterialzone.get(me));

        for(int l = 0 ; l < numberslbls.size(); l++)
            stage.addActor(numberslbls.get(l));
    }
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



        // ENEMY BOSS

        enemybosserino = new Boss("highgeneral", new CardEffect(), new Texture("highgeneralboss.jpg"), 4, 2, 2);
        enemyBosserinoImg = new Image(enemybosserino.getImage());
        enemyBosserinoImg.setSize((int) (Gdx.graphics.getWidth() / 6.4),(int) (Gdx.graphics.getHeight()/4.3));
        enemyBosserinoImg.setPosition(wid + (enemyBosserinoImg.getWidth()/2) , hei + enemyBosserinoImg.getHeight() + Gdx.graphics.getHeight()/20);
        enemyBosserinoImg.rotateBy(180);

        //expanded version
        enemyexpandBosserino = new Image(enemybosserino.getImage());
        enemyexpandBosserino.setSize((int) (enemyBosserinoImg.getWidth()*2.5), (int) (enemyBosserinoImg.getHeight()*2.5));
        enemyexpandBosserino.setPosition(wid - (bosserinoImg.getWidth()/2) , hei - (bosserinoImg.getHeight()/2) - hei/5);

        //

        enemyBosserinoImg.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button){

                stage.addActor(enemyexpandBosserino);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y,int pointer, int button){
                enemyexpandBosserino.remove();
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
                if((handExpandedFlag || extraExpandedFlag) && !col) {
                    handExpandedFlag = false;
                    extraExpandedFlag = false;
                    okay_to_select = false;
                }
                for(int u = 0 ; u < expandedZones.size();u++){
                    expandedZones.get(u).remove();
                    expandedEnemyZones.get(u).remove();
                }
                return true;
            }
        });


        // Monster ZONES
        int hei = Gdx.graphics.getHeight()/2;

        int x_coord = Gdx.graphics.getWidth()/10;
        int exp_x_coord = x_coord;
        for(int mvz = 0 ; mvz < 4 ; mvz++){
            Image new_img2 = new Image(new Texture("cardzone.jpg"));
            Image new_img = new Image(new Texture("cardzone.jpg"));
            new_img.setSize(Gdx.graphics.getWidth()/8, (int) (Gdx.graphics.getHeight()/5.3));
            new_img2.setSize(Gdx.graphics.getWidth()/8, (int) (Gdx.graphics.getHeight()/5.3));
            if(mvz == 3)
                x_coord = (int) (Gdx.graphics.getWidth()/10 + 3*(new_img.getWidth() + Gdx.graphics.getWidth()/11));
            else if(mvz == 2)
                x_coord += (int) (Gdx.graphics.getWidth() / 6.4) ;  //first part = boss card width
            new_img.setPosition(x_coord, hei - (new_img.getHeight()/2) - hei/5);
            new_img2.setPosition(x_coord, Gdx.graphics.getHeight() - (float) (1.35*(hei - (new_img.getHeight()/2) - hei/5)));


            //expanded version
            Image new_img2_expand = new Image(new Texture("cardzone.jpg"));
            Image new_img_expand = new Image(new Texture("cardzone.jpg"));
            new_img_expand.setSize((int) (new_img.getWidth()*2.5), (int) (new_img.getHeight()*2.5));
            new_img2_expand.setSize((int) (new_img.getWidth()*2.5), (int) (new_img.getHeight()*2.5));
            new_img_expand.setPosition(exp_x_coord , hei - (new_img.getHeight()/2) - hei/5);
            new_img2_expand.setPosition(Gdx.graphics.getWidth() - (exp_x_coord + new_img2.getWidth()), Gdx.graphics.getHeight() - (float)(0.7*new_img_expand.getY()));
            new_img2_expand.rotateBy(180);
            expandedEnemyZones.add(new_img2_expand);
            expandedZones.add(new_img_expand);
            //

            x_coord += new_img.getWidth() + (int) (Gdx.graphics.getWidth()/22);
            minionvehiczone.add(new_img);
            enemyminionvehiczone.add(new_img2);

        }
        // End Monster Zones
        // Material Zones

        x_coord = Gdx.graphics.getWidth()/6;
        exp_x_coord = x_coord;
        for(int mvz = 0 ; mvz < 4 ; mvz++){
            Image new_img = null;
            Image new_img2 = null;
            if(mvz == 0) {
                new_img = new Image(new Texture("metal.jpg"));
                new_img2 = new Image(new Texture("metal.jpg"));
            }
            else if(mvz == 1) {
                new_img = new Image(new Texture("wood.jpg"));
                new_img2 = new Image(new Texture("wood.jpg"));
            }
            else if(mvz == 2) {
                new_img = new Image(new Texture("glass.jpg"));
                new_img2 = new Image(new Texture("glass.jpg"));
            }
            else if(mvz == 3) {
                new_img = new Image(new Texture("rubber.jpg"));
                new_img2 = new Image(new Texture("rubber.jpg"));
            }
            new_img.setSize(Gdx.graphics.getWidth()/8, (int) (Gdx.graphics.getHeight()/5.3));
            new_img2.setSize(new_img.getWidth(), new_img.getHeight());
            new_img.setPosition(x_coord, hei / 12);
            new_img2.setPosition(x_coord + (Gdx.graphics.getWidth()/10), Gdx.graphics.getHeight() - new_img.getY());
            new_img2.rotateBy(180);


            x_coord += new_img.getWidth() + (int) (Gdx.graphics.getWidth()/22);
            materialzone.add(new_img);
            enemymaterialzone.add(new_img2);

        }

        //MATERIAL LABELS
        skin = new Skin();
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/3, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        BitmapFont bfont = new BitmapFont();
        skin.add("default", bfont);
        Label.LabelStyle lblst = new Label.LabelStyle();
        lblst.font = skin.getFont("default");
        skin.add("default", lblst);

        x_coord = (Gdx.graphics.getWidth()/6) + Gdx.graphics.getWidth()/16;  // add half of material card size
        exp_x_coord = x_coord;
        for(int l = 0; l < 4 ; l++) {

            Label numberslbl = new Label("0", skin);
            numberslbl.setPosition(x_coord, (hei / 12) + (int) (Gdx.graphics.getHeight()/5.5)); // Mat position + Mat height + a bit
            numberslbls.add(numberslbl);
            x_coord += (Gdx.graphics.getWidth()/8) + (int) (Gdx.graphics.getWidth()/22);
        }


    }
    public void loadStartingDecks(){
        MainDeck = new Deck();
        MainDeck.fill_deck_1();
        MainDeck.startgui(this);

        MatDeck = new MaterialDeck();
        MatDeck.fill_deck_1();

        extraDeck = new ExtraDeck();
        extraDeck.fill_extra_deck_1();
        Image deckimg = new Image(new Texture("extradeck.jpg"));
        deckimg.setSize(Gdx.graphics.getWidth()/8, (int) (Gdx.graphics.getHeight()/5.3));
        deckimg.setPosition(0, Gdx.graphics.getHeight()/15);

        deckimg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button){
                expand(1);  // ExtraDeckExpand = 1 || HandExpand = 0
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y,int pointer, int button){
                okay_to_select = true;
            }
        });
        stage.addActor(deckimg);
    }
    public void loadStartingHand(){
        //graphical
        miniHand = new Image(new Texture("miniHand.jpg"));
        miniHand.setSize(40, 40);
        miniHand.setPosition(Gdx.graphics.getWidth()-(miniHand.getWidth() + 10), 78);

        miniHand.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button){
                expand(0); // ExtraDeckExpand = 1 || HandExpand = 0
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


    // PROCESSING
    public void handleMill(int mc, int wc, int gc, int rc){

        matcounters[0] += mc;
        matcounters[1] += wc;
        matcounters[2] += gc;
        matcounters[3] += rc;
        // @ GUI  Update label counters
        numberslbls.get(0).setText(Integer.toString(matcounters[0]));
        numberslbls.get(1).setText(Integer.toString(matcounters[1]));
        numberslbls.get(2).setText(Integer.toString(matcounters[2]));
        numberslbls.get(3).setText(Integer.toString(matcounters[3]));


    }

    public boolean fullboard(){
        for(Card c: boardCards)
            if(c == null)
                return false;
        return true;
    }

    public void expandStatusAndCardPlaying(){
        if(Gdx.input.isTouched()){
            if((handExpandedFlag || extraExpandedFlag) && okay_to_select){
                int index = -1;
                if((index = expandHandCollisionDetected(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) > -1){  // Gdx.input.getY() has zero on top
                    //boolean fullboard = true;
                    AbstractMap.SimpleEntry<ArrayList<Integer>, int[]> state_after_play = null;
                    ArrayList<Integer> indexes_to_remove = new ArrayList<Integer>();
                    if(extraExpandedFlag) {
                        int[] matcopy =matcounters.clone();
                        state_after_play = extraDeck.getVehics().get(index).playvehicle(boardCards, matcounters);
                        if(state_after_play != null) {
                            indexes_to_remove.clear();
                            indexes_to_remove = state_after_play.getKey();
                            for(int r = 0; r < indexes_to_remove.size(); r++) {
                                boardCards[r] = null;
                                minionvehiczone.get(indexes_to_remove.get(r)).remove();
                                expandedZones.get(indexes_to_remove.get(r)).remove();
                                minionvehiczone.get(indexes_to_remove.get(r)).setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("cardzone.jpg"))));
                                expandedZones.get(indexes_to_remove.get(r)).setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("cardzone.jpg"))));
                                stage.addActor(minionvehiczone.get(indexes_to_remove.get(r)));
                            }
                            MatDeck.readd(matcopy, state_after_play.getValue());
                            //matcounters = state_after_play.getValue();
                            handleMill(0,0,0,0); // update label values
                        }
                    }
                    for(int i = 0; i < minionvehiczone.size(); i++) {
                        if(extraExpandedFlag && state_after_play == null)
                            break;
                        if(handExpandedFlag && fullboard())
                            break;
                        TextureRegionDrawable texdraw = (TextureRegionDrawable) minionvehiczone.get(i).getDrawable();
                        TextureRegion texreg = texdraw.getRegion();
                        Texture texture = texreg.getTexture();
                        String path = ((FileTextureData) texture.getTextureData()).getFileHandle().path();
                        if(path.equals("cardzone.jpg")){
                            // Set zone texture to played card texture
                            minionvehiczone.get(i).remove();
                            expandedZones.get(i).remove();
                            Texture texturino;

                            if(handExpandedFlag) {
                                System.out.println("ao menos passou 1");
                                texturino = hand.getCards().get(index).getImage();
                            }
                            else if(extraExpandedFlag) {
                                System.out.println("ao menos passou 2");
                                texturino = extraDeck.getVehics().get(index).getImage();
                                System.out.println("index: " + index);
                            }
                            else {
                                System.out.println("ao menos passou 3");
                                texturino = new Texture("cardzone.jpg");
                            }
                            minionvehiczone.get(i).setDrawable(new TextureRegionDrawable(new TextureRegion(texturino)));
                            expandedZones.get(i).setDrawable(new TextureRegionDrawable(new TextureRegion(texturino)));
                            stage.addActor(minionvehiczone.get(i));
                            if(handExpandedFlag) {
                                boardCards[i] = hand.getCards().get(index);
                                hand.remove(index);
                            }
                            if(extraExpandedFlag){
                                boardCards[i] = extraDeck.getVehics().get(index);
                                extraDeck.remove(index);
                            }
                            break;
                        }
                    }


                    handExpandedFlag = false;
                    extraExpandedFlag = false;
                }

            }

            // Zoom cards
            else if (!handExpandedFlag && !extraExpandedFlag){
                int index = getZoneClickedFromCoords((int)Gdx.input.getX(), (int) (Gdx.graphics.getHeight() - Gdx.input.getY()));

                if(index > -1) {  // Player side
                    //System.out.println("GOT EM");
                    stage.addActor(expandedZones.get(index - 1));
                }
                else if(index <= -1 && index != -9){
                    stage.addActor(expandedEnemyZones.get(Math.abs(index) - 1));
                }
            }
            // End Zoom cards
        }
    }

    public void printBatch(){
        int scrwidth = Gdx.graphics.getWidth();
        int scrheight = Gdx.graphics.getHeight();
        int cardwidth = scrwidth/5;
        int cardheight = scrheight/2 - 30;
        if(handExpandedFlag || extraExpandedFlag) {
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
    }


    /*
    public void stageRotate(){
        for (Actor a: stage.getActors()){
            Actor a2 = a;
            float y_coord = a.getY();
            a2.setPosition(a.getX(), Gdx.graphics.getHeight() - y_coord);
            a2.rotateBy(180);
            stage2.addActor(a2);
            a2.rotateBy(180);
            a2.setPosition(a.getX(), y_coord);
        }
    }
    */
    //
    public void expand(int hore){ // ExtraDeckExpand = 1 || HandExpand = 0
        expandSpriteList.clear();
        if(hore == 0) {
            ArrayList<Card> handCards = hand.getCards();
            for (int i = 0; i < handCards.size(); i++) {
                Texture mH = handCards.get(i).getImage();
                TextureRegion tr = new TextureRegion(mH, mH.getWidth(), mH.getHeight());
                str = new Sprite(tr);
                str.setSize(20, 20);
                expandSpriteList.add(str);
            }
            okay_to_select = false;
            extraExpandedFlag = false;
            handExpandedFlag = true;
            System.out.println("Hand expand");
        }
        else if(hore == 1){
            ArrayList<Vehicle> vehiclist = extraDeck.getVehics();
            for(int i = 0; i < vehiclist.size(); i++){
                Texture mH = vehiclist.get(i).getImage();
                TextureRegion tr = new TextureRegion(mH, mH.getWidth(), mH.getHeight());
                str = new Sprite(tr);
                str.setSize(20, 20);
                expandSpriteList.add(str);
            }
            okay_to_select = false;
            handExpandedFlag = false;
            extraExpandedFlag = true;
            System.out.println("Extra expand");
        }

        //TODO Check here for mill
        ArrayList<Integer> milled = MatDeck.mill(4);
        handleMill(milled.get(0), milled.get(1), milled.get(2), milled.get(3));


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


        // Hand + Extra deck expanding and playing
        expandStatusAndCardPlaying();
        // End Hand + Extra Deck Expanding and Playing


        stage.draw();
        //stage2.draw();

        batch.begin();
       // System.out.println("width: " + Gdx.graphics.getWidth());  // Width = 480
       // System.out.println("height: " + Gdx.graphics.getHeight()); // Height = 320
        printBatch();
        batch.end();
    }


    public int expandHandCollisionDetected(int x, int y){

        int col = -1;
        int scrwidth = Gdx.graphics.getWidth();
        int scrheight = Gdx.graphics.getHeight();
        int cardwidth = scrwidth/5;
        int cardheight = scrheight/2 - 30;
        int x_expand = scrwidth/9 - cardwidth/3;
        int y_expand = scrheight - cardheight - 10;
        int size = 0;
        if(handExpandedFlag)
            size = hand.getCards().size();
        else if(extraExpandedFlag)
            size = extraDeck.getVehics().size();
        for (int i = 0; i < size; i++) {
            if (x >= x_expand && x <= x_expand + cardwidth && y >= y_expand && y <= y_expand + cardheight) {
                col = i;
            }
            x_expand += cardwidth + 15;
            if (i == 3) {
                y_expand -= scrheight / 2 + 15;
                x_expand = scrwidth / 9 - cardwidth / 3;
            }
        }

        if(handExpandedFlag && col > -1) {
            //handExpandedFlag = false;
            okay_to_select = false;
        }
        else if(extraExpandedFlag && col > -1) {
            //extraExpandedFlag = false;
            okay_to_select = false;
        }
        return col;
    }

    public int getZoneClickedFromCoords(int x, int y){
        int answer = -9;
        int hei = Gdx.graphics.getHeight()/2;

        int zonewid = Gdx.graphics.getWidth() / 8;
        int zonehei = (int) (Gdx.graphics.getHeight() / 5.3);
        int x_coord = Gdx.graphics.getWidth()/10;
        int y_coord = hei - (zonehei / 2) - hei / 5;
        int enemy_y_coord = Gdx.graphics.getHeight() - (int) (1.35*(hei - ((Gdx.graphics.getHeight()/5.3)/2) - hei/5));
        for(int mvz = 0 ; mvz < 4 ; mvz++) {


            if(mvz == 3) {
                x_coord = (int) (Gdx.graphics.getWidth() / 10 + 3 * (zonewid + Gdx.graphics.getWidth() / 11));
            }
            else if(mvz == 2)
                x_coord += (int) (Gdx.graphics.getWidth() / 6.4) ;  //first part = boss card width

            if(x >= x_coord && x <= (x_coord + zonewid) && y >= y_coord && y <= y_coord + zonehei) {
                System.out.println(" x +- y " + x + " -- "  + y);
                System.out.println(" index: " + mvz);
                answer = mvz +1;
            }
            else if(x >= x_coord && x <= (x_coord + zonewid) && y >= enemy_y_coord && y <= enemy_y_coord + zonehei){
                System.out.println(" x +- y " + x + " -- "  + y);
                System.out.println(" index: " + (-mvz));
                answer = -(mvz + 1);
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
