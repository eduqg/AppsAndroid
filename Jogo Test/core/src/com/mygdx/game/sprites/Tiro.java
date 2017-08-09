package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.util.Constantes;

/**
 * Created by Wesley on 07/04/2017.
 */

public class Tiro {

    private Vector3 posicao;
    private Vector3 velocidade;
    private Texture textura;
    private Rectangle limite;
    private Sound sound;

    public Tiro(float x, float y){
        textura = new Texture("tiro.png");
        posicao = new Vector3(x, y+10, 0);
        velocidade = new Vector3(0,Constantes.VELOCIDADE_TIRO_INICIAL,0);
        limite = new Rectangle(posicao.x, posicao.y, textura.getWidth(), textura.getHeight());
        sound = Gdx.audio.newSound(Gdx.files.internal("tiro2.wav"));
    }

    public void update(float dt){
        velocidade.add(0, Constantes.NAVE_VELOCIDADE,0);
        velocidade.scl(dt);
        posicao.add(0,Constantes.NAVE_VELOCIDADE,0);
        posicao.add(velocidade);
        velocidade.scl(1/dt);
        limite.setPosition(posicao.x, posicao.y);
    }

    public Texture getTextura() {
        return textura;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }

    public Vector3 getPosicao() {
        return posicao;
    }

    public void dispose() {
        sound.dispose();
        textura.dispose();
        posicao = null;
        velocidade = null;
    }

    public Rectangle getLimite() {
        return limite;
    }

    public Sound getSound() {
        return sound;
    }
}
