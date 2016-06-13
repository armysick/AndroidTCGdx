package com.mygdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.gamelogic.BoardState;
import com.mygdx.gamelogic.Card;
import com.mygdx.gamelogic.CardEffect;
import com.mygdx.gamelogic.Combat;
import com.mygdx.gamelogic.Deck;
import com.mygdx.gamelogic.Minion;
import com.mygdx.gamelogic.Vehicle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.ArrayList;

/**
 * Created by OwnSick on 13-06-2016.
 */
public class TestClass{

    @Test
    public void testcombat() {
        Combat comb = new Combat();
        Vehicle attacker = new Vehicle("attacker", new CardEffect(0), 0, 0, 1000);
        Vehicle defender = new Vehicle("defender", new CardEffect(0), 0, 0, 2000);
        comb.setAttacker(attacker, 1);
        comb.setDefender(defender, 2);
        assertEquals(comb.fight(), -1);
        comb.reset();

        comb.setAttacker(defender, 2);
        comb.setDefender(attacker, 1);
        assertEquals(comb.fight(), 1);
    }

    @Test
    public void testOverDraw(){
        Deck md = new Deck();
        md.fill_deck_3();
        ArrayList<Card> drawn;
        drawn = md.draw(3); // only leave 1 left on deck
        assertEquals(drawn.size(), 3);
        drawn = md.draw(2); // Try to draw 2 with only 1 in deck
        assertEquals(drawn.size(), 1);
    }

    @Test
    public void playVehicle(){
        Card[] board = {new Minion("testminion", new CardEffect(0)), null, null, null};
        int[] matcounters = {1, 0, 0, 0};

        Vehicle v = new Vehicle("testvehicle", new CardEffect(0), 2, 2, 1000);

        AbstractMap.SimpleEntry<ArrayList<Integer>, int[]> result = v.playvehicle(board, matcounters);

        assertEquals(result, null); // Not enough resources to pay vehicle cost;



        Vehicle v2 = new Vehicle("testvehicle2", new CardEffect(0), 1, 1, 1000);
        AbstractMap.SimpleEntry<ArrayList<Integer>, int[]> result2 = v2.playvehicle(board, matcounters);

        assertEquals(result2.getKey().size(), 1); // index of tripulation to remove
        assertEquals(result2.getValue()[0], 0);  // Decremented/Used up the only material to summon the vehicle

    }

    @Test
    public void activateEffect(){
        Minion m1 = new Minion("pickaxesoldier", new CardEffect(2));
        // m1 card effect is "On summon: mill a card"

        BoardState b1 = m1.activateEffect();

        assertEquals(b1.getMilled(), 1);
        assertEquals(b1.getDrawn(), 0);

        Minion m2 = new Minion("handysoldier", new CardEffect(3));
        // m2 card effect is "On summon: draw a card"

        BoardState b2 = m2.activateEffect();
        assertEquals(b2.getDrawn(), 1);
        assertEquals(b2.getMilled(), 0);
    }
}
