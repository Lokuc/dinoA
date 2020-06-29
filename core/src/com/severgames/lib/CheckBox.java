package com.severgames.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.severgames.dino.SpriteLoad;

public class CheckBox {

    private boolean check;
    private Sprite sprite2;
    private Sprite sprite;
    private Vector3 vector3;
    private OrthographicCamera camera;

    public CheckBox(OrthographicCamera c){
        camera=c;
        vector3=new Vector3();
        sprite2 = new Sprite(SpriteLoad.getUI(1));
        sprite = new Sprite(SpriteLoad.getUI(0));
        float h= Gdx.graphics.getHeight()/20;
        float w = (h / sprite.getHeight()) * sprite.getWidth();
        sprite.setSize(w,h);
        sprite2.setSize(w,h);
        h = Gdx.graphics.getHeight() / 2f - h / 2f;
        w = Gdx.graphics.getWidth() / 2f - 2 / 2f;
        sprite.setPosition(w,h);
        sprite2.setPosition(w,h);
    }

    public void addY(){
        sprite2.setY(sprite2.getY()-sprite2.getHeight()*2);
        sprite.setY(sprite.getY()-sprite.getHeight()*2);
    }

    public void draw(SpriteBatch batch){
        if(Gdx.input.justTouched()){
            vector3.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(vector3);
            if (sprite.getBoundingRectangle().contains(new Vector2(vector3.x, vector3.y))) {
                check = !check;
            }
        }
        if(check){
            sprite2.draw(batch);
        }else {
            sprite.draw(batch);
        }
    }


    public boolean getCheck() {
        return check;
    }
}
