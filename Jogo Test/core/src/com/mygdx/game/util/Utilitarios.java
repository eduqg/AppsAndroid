package com.mygdx.game.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.sprites.Meteoro;

public class Utilitarios {

    public static boolean isMeteorOffScreen(OrthographicCamera camera, Meteoro meteoro){
        if (meteoro.getPosicao().y < camera.position.y - (camera.viewportHeight/2)){
            return true;
        }
        return false;
    }
}
