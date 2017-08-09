package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.util.Constantes;

/**
 * Created by Wesley on 09/04/2017.
 */

public class Meteoro {

    private Vector3 posicao;
    private Vector3 velocidade;
    private Texture textura;
    private Rectangle limite;

    public Meteoro(float x, float y){
        textura = new Texture("meteoro_p_1.png");
        posicao = new Vector3(x,y,0);
        velocidade = new Vector3(0,0,0);
        limite = new Rectangle(posicao.x, posicao.y, textura.getWidth(), textura.getHeight());
    }

    public void update(float dt){
        limite.setPosition(posicao.x, posicao.y);
    }

    public boolean isColisao(Rectangle obj){
        if(obj.overlaps(limite)){
            return true;
        }
        return false;
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

    public void dispose() {
        posicao =null;
        velocidade = null;
        textura = null;
    }
}
