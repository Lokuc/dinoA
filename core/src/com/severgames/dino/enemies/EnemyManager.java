package com.severgames.dino.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class EnemyManager implements Mother {

    private static float speed = 600f;
    private static float speedAdd=0f;
    private Wormix wormix;
    private Wormix2 wormix2;
    private Wall wall;
    private int type;
    private boolean isSpawn;
    private float time;
    private float timeA;
    private Random r;

    public EnemyManager(){
        isSpawn=false;
        time=0f;
        r=new Random();
        wall=new Wall(this);
        wormix = new Wormix(this);
        wormix2=new Wormix2(this);
        timeA=0.6f;
    }

    public static float getSpeed(){
        return speed+speedAdd;
    }

    public void update(float delta){
        timeA-=delta/40;
        speedAdd+=delta;
        if(isSpawn) {
            if (type == 0) {
                wormix.update(delta);
            }else if(type==1){
                wall.update(delta);
            }else if(type==2){
                wormix2.update(delta);
            }
        }else{
            time+=delta;
            if(time>=timeA){
                spaw();
            }
        }
    }
    public void draw(SpriteBatch batch){
        if(isSpawn) {
            if (type == 0) {
                wormix.draw(batch);
            } else if (type == 1) {
                wall.draw(batch);
            }else if(type==2){
                wormix2.draw(batch);
            }
        }
    }
    public void draw1(SpriteBatch batch){
        if(isSpawn) {
            if (type == 1) {
                wall.draw1(batch);
            }
            if(type==0){
                wormix.draw1(batch);
            }
        }
    }
    public Rectangle getRect(){
        if(type==0){
            return wormix.getRect();
        }else if(type==1){
            return wall.getRect();
        }else if(type==2){
            return wormix2.getRect();
        }
        return null;
    }
    public void spawn() {
        timeA=4f;
        spaw();
    }
    private void spaw(){
        int count = 3;
        type=r.nextInt(count);
        isSpawn=true;
        if(type==0){
            wormix.spawn();
        }else if(type==1){
            wall.spawn();
        }else if(type==2){
            wormix2.spawn();
        }
    }
    public void resize(float H,float h){
        speed= Gdx.graphics.getWidth()/2f;
        wormix.resize(H);
        wall.resize(H,h);
        wormix2.resize(H);
    }
    @Override
    public void imDead(String type) {
        isSpawn=false;
        time=0f;
    }

    public Rectangle getGround() {
        if(isSpawn&&type==0){
            return wormix.getGround();
        }else if(isSpawn&&type==2){
            return wormix2.getGround();
        }
        return null;
    }

    public Color getGroundColor() {
        return Color.BLUE;
    }

    public Color getColor() {
        return Color.RED;
    }

    public void respawn() {
        speedAdd=0f;
    }

    public void dispose() {
        wormix2.dispose();
        wormix.dispose();
        wall.dispose();

    }
}
