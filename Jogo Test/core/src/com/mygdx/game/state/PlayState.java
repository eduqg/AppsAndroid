package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.handlers.BackgroundHandler;
import com.mygdx.game.handlers.ExplosaoHandler;
import com.mygdx.game.handlers.MeteorHandle;
import com.mygdx.game.sprites.Nave;
import com.mygdx.game.handlers.TirosHandler;
import com.mygdx.game.util.Constantes;

public class PlayState extends State{

    private BackgroundHandler bg;
    private Nave nave;
    private TirosHandler tirosHandler;
    private MeteorHandle meteoroHandle;
    private ExplosaoHandler explosaoHandler;

    private float indicadorBg;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        tirosHandler = new TirosHandler();
        meteoroHandle = new MeteorHandle();
        nave = new Nave(100, 200);
        camera.setToOrtho(false,Constantes.GAME_WHIDTH/2, Constantes.GAME_HEIGHT/2 );
        bg = new BackgroundHandler();
        explosaoHandler = new ExplosaoHandler();
    }

    @Override
    protected void handleRequest() {
        reconheceControleTouchpad();
        reconheceControleTeclado();
    }

    private void reconheceControleTouchpad(){
        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos);

            if (touchPos.y < nave.getPosition().y+50){
                if (touchPos.x < 100){
                    nave.praEsquerda();
                }
                if (touchPos.x > 100){
                    nave.praDireita();
                }
            }else{
                tirosHandler.add(nave.atirar(), System.currentTimeMillis());
            }
        }
    }

    private void reconheceControleTeclado(){
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            nave.praDireita();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            nave.praEsquerda();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            tirosHandler.add(nave.atirar(), System.currentTimeMillis());
        }
    }

    @Override
    public void update(float dt) {
        handleRequest();
        meteoroHandle.gerarMeteoro(camera);
        nave.update(dt,camera);
        tirosHandler.atualizaTiros(dt);
        meteoroHandle.atualizarMeteoros(dt);

        camera.position.y = nave.getPosition().y + Constantes.NAVE_OFFSET_CAMERA;
        camera.update();
        tirosHandler.removerTirosOffScreen(camera);
        meteoroHandle.removerObjOffScreen(camera);

        if(meteoroHandle.verificarColisao(nave.getLimite())){
            gsm.set(new MenuState(gsm));
        }
        explosaoHandler.add(meteoroHandle.handleColisionTiro(tirosHandler.getLista()));
        explosaoHandler.updateList(dt);
    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.setProjectionMatrix(camera.combined);
        sprite.begin();
        bg.renderBackGround(sprite, camera);
        tirosHandler.desenhaTiros(sprite);
        meteoroHandle.desenharMeteoro(sprite);
        explosaoHandler.desenhaExplosoes(sprite);
        Constantes.printarRelatorio(String.valueOf(nave.getVelocity().x));
        sprite.draw(nave.getNave(), nave.getPosition().x, nave.getPosition().y);

        sprite.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        nave.dispose();
        meteoroHandle.disposeAll();
        tirosHandler.disposeAll();
    }


}
