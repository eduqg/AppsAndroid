package com.mygdx.game.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.sprites.Explosao;
import com.mygdx.game.sprites.Meteoro;
import com.mygdx.game.sprites.Tiro;
import com.mygdx.game.util.Constantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MeteorHandle {

    private static final Random RANDOM = new Random();

    private List<Meteoro> listaMeteoros;
    public Long deltaTime;

    public MeteorHandle(){
        listaMeteoros = new ArrayList<Meteoro>();
        deltaTime = 0l;
    }

    public void gerarMeteoro(OrthographicCamera camera){
        if(RANDOM.nextBoolean()){
            add(new Meteoro(RANDOM.nextInt(235),
                            (Constantes.GAME_HEIGHT/2) + camera.position.y),
                            System.currentTimeMillis());        }
    }

    public void add(Meteoro meteoro, Long timeMiliSeconds){
        if(deltaTime.equals(0l)){
            //Constantes.printarRelatorio("meteoro gerado em x: " + meteoro.getPosicao().x + " y: " + meteoro.getPosicao().y );
            deltaTime = timeMiliSeconds;
            listaMeteoros.add(meteoro);
        }else if (timeMiliSeconds - deltaTime >= Constantes.METEOROS_TIME_GAP){
            //Constantes.printarRelatorio("meteoro gerado em x: " + meteoro.getPosicao().x + " y: " + meteoro.getPosicao().y );
            deltaTime = timeMiliSeconds;
            listaMeteoros.add(meteoro);
        }
    }

    public boolean verificarColisao(Rectangle obj){
        for (Meteoro meteoro:listaMeteoros){
            if (meteoro.isColisao(obj)){
                return true;
            }
        }
        return false;
    }

    public Explosao handleColisionTiro(List<Tiro> tiros){
        for (int t = 0; t < tiros.size(); t++){
            Tiro tiro = tiros.get(t);
            for (int i=0; i<listaMeteoros.size(); i++){
                Meteoro meteoro = listaMeteoros.get(i);
                if (meteoro.isColisao(tiro.getLimite())){
                    listaMeteoros.remove(meteoro);
                    tiros.remove(tiro);
                    tiro.dispose();
                    Explosao x= new Explosao(meteoro.getPosicao().x, meteoro.getPosicao().y);
                    meteoro.dispose();
                    return x;
                }
            }
        }
        return null;
    }

    public void atualizarMeteoros(float dt){
        for (Meteoro meteoro:listaMeteoros){
            meteoro.update(dt);
        }
    }

    public void desenharMeteoro(SpriteBatch sb){
        for (Meteoro meteoro:listaMeteoros){
            sb.draw(meteoro.getTextura(), meteoro.getPosicao().x, meteoro.getPosicao().y);
        }
    }

    public void removerObjOffScreen(OrthographicCamera camera){
        for (int i=0; i < listaMeteoros.size() ; i++){
            Meteoro meteoro = listaMeteoros.get(i);
            if (meteoro.getPosicao().y < camera.position.y - (camera.viewportHeight/2 + meteoro.getTextura().getHeight())){
                listaMeteoros.remove(meteoro);
                meteoro.dispose();
                Constantes.printarRelatorio("removido meteoro");
            }
        }
    }

    public void disposeAll(){
        for (Meteoro meteoro:listaMeteoros){
            meteoro.dispose();
        }
    }

    public List<Meteoro> getLista(){
        return listaMeteoros;
    }
}
