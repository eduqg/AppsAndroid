package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Contact;
import com.mygdx.game.animations.Animation;
import com.mygdx.game.animations.NaveAnimation;
import com.mygdx.game.util.Constantes;

public class Nave {

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle limite;

    Texture naveReta;
    NaveAnimation nave = new NaveAnimation();

    public Nave(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, Constantes.NAVE_VELOCIDADE, 0);
        naveReta = new Texture("nave\\reto.png");
        limite = new Rectangle(position.x, position.y, naveReta.getWidth() /2 , naveReta.getHeight());
    }

    private boolean isLimiteScreen(OrthographicCamera camera){
        if (isLimiteEsquerdo(camera)){
            return true;
        }
        if (isLimiteDireito(camera)){
            return true;
        }
        Constantes.printarRelatorio("false");
        return false;
    }

    private boolean isLimiteDireito(OrthographicCamera camera){
        if (position.x >= camera.position.x + (camera.viewportWidth /2) - naveReta.getWidth()/2){
            return true;
        }
        return false;
    }

    private boolean isLimiteEsquerdo(OrthographicCamera camera){
        if (position.x <= camera.position.x - camera.viewportWidth /2){
            Constantes.printarRelatorio("true");
            return true;
        }
        return false;
    }

    public void update(float dt, OrthographicCamera camera){
        //nave_reta.update(dt);
        if (isLimiteDireito(camera)){
            if (velocity.x >0){
                position.add(0, velocity.y, 0);
            }else{
                position.add(velocity.x, velocity.y, 0);
            }
        }else if (isLimiteEsquerdo(camera)){
            if (velocity.x < 0){
                position.add(0,velocity.y,0);
            }else{
                position.add(velocity.x,velocity.y,0);
            }
        }else {
            position.add(velocity.x,velocity.y,0);
        }


        nave.updateAll(dt);
        //position.add(velocity.x, velocity.y, 0);
        reduzEscalaVelocidadeLateral();
        limite.setPosition(position.x, position.y);
    }

    private void reduzEscalaVelocidadeLateral(){
        if (velocity.x > 0 ){
            velocity.x -= 0.5f;
        }if (velocity.x < 0){
            velocity.x += 0.5f;
        }
    }

    public Tiro atirar() {
        return new Tiro(position.x+(naveReta.getWidth()/4)-3, position.y);
    }

    public void praEsquerda(){
        velocity.x -=1;
        //position.x -= 4;
    }

    public void praDireita(){
        velocity.x +=1;
        //position.x += 4;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public TextureRegion getNave() {
        return nave.getByPosition(velocity).getFrame();
    }

    public void dispose(){
        //nave_reta.dispose();
        position = null;
        velocity = null;
    }

    public Rectangle getLimite() {
        return limite;
    }
}
