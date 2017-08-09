package com.mygdx.game.sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Wesley on 07/04/2017.
 */

public abstract class GameObject {

    private Vector3 posicao;
    private Vector3 velocidade;
    private Texture textura;

    public GameObject(float x, float y){

    }

    public Vector3 getPosicao() {
        return posicao;
    }

    public Vector3 getVelocidade() {
        return velocidade;
    }

    public Texture getTextura() {
        return textura;
    }
}
