package com.severgames.dino.enemies;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.severgames.dino.SpriteLoad;

class Wormix {
        private static Sprite sprite;
        private static Sprite wet;
        private float X;
        private Rectangle temp=new Rectangle();
        private Mother mother;

        Wormix(Mother mother){
            this.mother=mother;
            sprite= SpriteLoad.getSprite(0);
            wet=SpriteLoad.getSprite(35);
        }

        void spawn(){
            X= Gdx.graphics.getWidth()+sprite.getWidth()/2;
            sprite.setX(X);
            wet.setX(X+sprite.getWidth()/2f);
        }

        void update(float delta){
            X-= EnemyManager.getSpeed()*delta;
            if(X<-wet.getWidth()){
                mother.imDead(getClass()+"");
            }
            sprite.setX(X);
            wet.setX(X+sprite.getWidth()/2f);
        }

        void draw(SpriteBatch batch){
            sprite.draw(batch);
        }

        void draw1(SpriteBatch batch){
            wet.draw(batch);
        }

        void resize(float H){
            float h = Gdx.graphics.getHeight() / 5;
            float w = (h / sprite.getHeight() * sprite.getWidth());
            sprite.setSize(w, h);
            sprite.setPosition(Gdx.graphics.getWidth()+sprite.getWidth()/2,H);
            w *=1.3f;
            h = w /wet.getWidth()*wet.getHeight();
            wet.setSize(w, h);
            wet.setPosition(Gdx.graphics.getWidth()+sprite.getWidth()/2-wet.getWidth()/3,H+wet.getHeight()/40);



        }

        Rectangle getRect(){
            temp=sprite.getBoundingRectangle(); //TODO edit temp
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

     void dispose() {
            sprite.getTexture().dispose();
    }
}
