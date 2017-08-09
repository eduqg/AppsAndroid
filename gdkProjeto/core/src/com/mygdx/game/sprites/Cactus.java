package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by eduar on 06/04/2017.
 */

public class Cactus {
    public static final int CACTUS_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int CACTUS_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Vector2 posTopCactus;
    private Rectangle boundsTop, boundsBot;
    private Vector2 posBotCactus;
    private Texture topCactus;
    private Texture bottomCactus;
    private Random rand;

    public Cactus(float x){
        topCactus = new Texture("toptube.png");
        bottomCactus = new Texture("bottomtube.png");
        rand = new Random();

        posTopCactus = new Vector2(x, rand.nextInt(FLUCTUATION) + CACTUS_GAP + LOWEST_OPENING);
        posBotCactus = new Vector2(x, posTopCactus.y - CACTUS_GAP - bottomCactus.getHeight());

        boundsTop = new Rectangle(posTopCactus.x, posTopCactus.y, topCactus.getWidth(), topCactus.getHeight());
        boundsBot = new Rectangle(posBotCactus.x, posBotCactus.y, bottomCactus.getWidth(), bottomCactus.getHeight());

    }

    public Vector2 getPosTopCactus() {
        return posTopCactus;
    }

    public Vector2 getPosBotCactus() {
        return posBotCactus;
    }

    public Texture getTopCactus() {
        return topCactus;
    }

    public Texture getBottomCactus() {
        return bottomCactus;
    }

    public void reposition(float x){
        posTopCactus.set(x, rand.nextInt(FLUCTUATION) + CACTUS_GAP + LOWEST_OPENING);
        posBotCactus.set(x, posTopCactus.y - CACTUS_GAP - bottomCactus.getHeight());
        boundsTop.setPosition(posTopCactus.x, posTopCactus.y);
        boundsBot.setPosition(posBotCactus.x, posBotCactus.y);
    }
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }
    public void dispose(){
        topCactus.dispose();
        bottomCactus.dispose();
    }
}
