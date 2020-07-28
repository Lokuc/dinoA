package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

public class MoneyManager {
    private Money[] money;
    private Random r;
    private int tmp;
    private float time=0;
    private Rectangle[] rect;
    private Sprite coin;


    MoneyManager(){
        money=new Money[8];
        for (int i = 0; i < money.length; i++) {
            money[i]=new Money();
        }
        coin=SpriteLoad.getSprite("coin");
        r= new Random();
    }

    void respawn(){
        time=0;
        for(Money m:money){
            m.respawn();
        }
    }

    Color getColor(){
        return Color.YELLOW;
    }

    private void spawn(){
        if(time>=3.0f){
            tmp = 3 + r.nextInt(5);
            for (int i = 0; i < tmp; i++) {
                money[i].spawn(i);
            }
            time=0;
        }

    }

    void draw(SpriteBatch batch){
        for(Money m:money){
            m.draw(batch);
        }
    }

    void update(float delta){
        time+=delta;
        spawn();
        for(Money m:money){
            m.update(delta);
        }
    }

    public Rectangle[] getRect(){
        rect = new Rectangle[tmp];
        for (int i = 0; i < tmp; i++) {
            rect[i]=money[i].getRect();
        }
        return rect;
    }

    public void delete(int i){
        money[i].delete();
    }

    void resize(float he){
        float h = Gdx.graphics.getHeight()/16;
        float w=h/coin.getHeight()*coin.getWidth();
        coin.setSize(w,h);
        coin.setPosition(w,Gdx.graphics.getHeight()-h*2);
        for(Money m:money){
            m.resize(he);
        }
    }

    public void dispose() {
        for(Money m:money){
            m.dispose();
        }
    }

    void drawCoin(SpriteBatch batch) {
        coin.draw(batch);
        Frame.ft.setColor(Color.BLUE);
        Frame.ft.draw(batch,new Data().getMoney()+"",coin.getWidth()+coin.getX()*1.4f,coin.getY()+coin.getHeight()/1.5f);
    }
}
