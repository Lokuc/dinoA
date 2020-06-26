package com.severgames.dino.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.severgames.dino.SpriteLoad;

class Wormix2 {

    private static Sprite sprite;
    private float X;
    private Rectangle temp = new Rectangle();
    private Mother mother;

    Wormix2(Mother mother){
        this.mother=mother;
        sprite= SpriteLoad.getSprite(34);
    }

    void spawn(){
        X= Gdx.graphics.getWidth()+sprite.getWidth()/2;
        sprite.setX(X);
    }

    void update(float delta){
        X-=EnemyManager.getSpeed()*delta;
        if(X<-sprite.getWidth()){
            mother.imDead(getClass()+"");
        }
        sprite.setX(X);
    }
    void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    void resize(float H){
        float h = Gdx.graphics.getHeight()/5;
        float w = (h/sprite.getHeight()*sprite.getWidth());
        sprite.setSize(w,h);
        sprite.setPosition(Gdx.graphics.getWidth()+sprite.getWidth()/2,H);
    }

    Rectangle getRect(){
        temp=sprite.getBoundingRectangle();
        temp.width=temp.width/6;
        temp.height-=temp.height/5;
        return temp;
    }
    Rectangle getGround(){
        temp=sprite.getBoundingRectangle();
        //temp.height-=temp.height/8;
        //temp.width-=temp.width/5;
        temp.x+=temp.width/5;
        temp.width-=temp.width/5;
        temp.y+=temp.height-temp.height/4;
        temp.height=temp.height/9;
        return temp;
    }

}
