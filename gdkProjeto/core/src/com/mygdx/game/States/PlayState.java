package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Aplicacao;
import com.mygdx.game.sprites.Cactus;
import com.mygdx.game.sprites.Dino;

/**
 * Created by eduar on 04/04/2017.
 */

public class PlayState extends State{
    private static final int CACTUS_SPACING = 125;
    private static  final int CACTUS_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -20;//change here ground position

    private Dino dino;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Cactus> cactusArray;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        dino = new Dino(50, 300);
        cam.setToOrtho(false, Aplicacao.WIDTH / 2, Aplicacao.HEIGTH / 2);
        bg = new Texture("bg3.png");
        ground = new Texture("ground.png");

        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);



        cactusArray = new Array<Cactus>();

        for (int i = 1; i <= CACTUS_COUNT; i++){
            cactusArray.add(new Cactus(i * (CACTUS_SPACING + Cactus.CACTUS_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            dino.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        dino.update(dt);
        cam.position.x = dino.getPosition().x+80;

        for(int i = 0; i < cactusArray.size; i++){
            Cactus cactus = cactusArray.get(i);
            if(cam.position.x - (cam.viewportHeight/2) > cactus.getPosTopCactus().x + cactus.getTopCactus().getWidth()){
                cactus.reposition(cactus.getPosTopCactus().x + (Cactus.CACTUS_WIDTH + CACTUS_SPACING) * CACTUS_COUNT);
            }
            if(cactus.collides(dino.getBounds())){
                gsm.set(new MenuState(gsm));
            }
        }

        if(dino.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gsm.set(new MenuState(gsm));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportHeight/2), 0);
        sb.draw(dino.getTexture(), dino.getPosition().x, dino.getPosition().y);

        for(Cactus cactus : cactusArray){
            sb.draw(cactus.getTopCactus(), cactus.getPosTopCactus().x, cactus.getPosTopCactus().y);
            sb.draw(cactus.getBottomCactus(), cactus.getPosBotCactus().x, cactus.getPosBotCactus().y);
            //System.out.println("y: " + cactus.getPosBotCactus().y + "x: " + cactus.getPosBotCactus().x);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();;
        dino.dispose();
        ground.dispose();
        for(Cactus cactus : cactusArray){
            cactus.dispose();
        }
        System.out.println("Play State mostrado");
    }
    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth/2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x - (cam.viewportWidth/2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
