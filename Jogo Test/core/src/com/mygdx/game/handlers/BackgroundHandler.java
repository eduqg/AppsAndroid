package com.mygdx.game.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.util.Constantes;

/**
 * Created by Wesley on 09/04/2017.
 */

public class BackgroundHandler {

    private Texture texture;
    private int indicadorNext;

    public BackgroundHandler(){
        texture = new Texture("bg.png");
    }

    public void renderBackGround(SpriteBatch sb, OrthographicCamera camera){
        sb.draw(texture,camera.position.x- (camera.viewportHeight/2),indicadorNext-texture.getHeight());
        sb.draw(texture,camera.position.x- (camera.viewportHeight/2),indicadorNext-(texture.getHeight()*2));
        verificaNextDraw(camera);
    }

    private void verificaNextDraw(OrthographicCamera camera){
        if(camera.position.y + (camera.viewportHeight /2) >= indicadorNext){
            indicadorNext += texture.getHeight();
        }
    }

    public void dispose(){
        texture.dispose();
    }
}
