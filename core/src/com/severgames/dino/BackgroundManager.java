package com.severgames.dino;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.severgames.dino.enemies.EnemyManager;

import java.util.Random;

public class BackgroundManager {


    private Sprite[] sprites;
    private float[] speeds;
    private float[] Xpos;
    private final int countObj=11;
    private int randNum;
    private int prevNum;
    private boolean isBackSpawn=false;
    private boolean isGrassSpawn=false;
    private int randG;
    private int prevG;

    private float timeGround=0.0f;
    private float timeGrass=0.0f;
    private Random r;
    private int temp;


            // fon, back, line, filter

    void load(){

        r = new Random();
        temp=7+r.nextInt(1);


        sprites= new Sprite[countObj];
        Xpos=new float[countObj];
        speeds=new float[]{
                40f,    //fon
                85f,    //filter
                600f,   //line
                600f    //back
        };

        sprites[0]=SpriteLoad.getFon(2);    //fon
        sprites[1]=SpriteLoad.getFon(1);    //filter
        sprites[2]=SpriteLoad.getFon(3);    //fon
        sprites[3]=SpriteLoad.getFon(0);    //back
        sprites[4]=SpriteLoad.getFon(5);    //statuya
        sprites[5]=SpriteLoad.getFon(4);    //plane
        sprites[6]=SpriteLoad.getSprite(36);//grass
        //sprites[7]=SpriteLoad.getFon(6);    //up
        sprites[8]=SpriteLoad.getFon(7);    //down
        sprites[9]=SpriteLoad.getFon(8);    //grass1
        sprites[10]=SpriteLoad.getFon(9);   //grass2



    }


    void drawFon(SpriteBatch batch, float delta) {
        if(Xpos[0]<-sprites[0].getWidth()){
            Xpos[0]=0;
        }
        Xpos[0]-=speeds[0]*delta;
        sprites[0].setX(Xpos[0]);
        sprites[0].draw(batch);
        sprites[0].setX(Xpos[0]+sprites[0].getWidth());
        sprites[0].draw(batch);
    }
    void drawBack(SpriteBatch batch,float delta){
        if(!isBackSpawn&&timeGround >= temp){
            prevNum=randNum;
            while(prevNum==randNum) {
                randNum = 3 + r.nextInt(3);
            }
            temp = 7 + r.nextInt(1);
            Xpos[randNum] = Gdx.graphics.getWidth();
            sprites[randNum].setX(Xpos[randNum]);
            timeGround = 0;
            isBackSpawn=true;
        }
        if(randNum==3) {
            Xpos[3] -= EnemyManager.getSpeed() * delta;
            sprites[3].setX(Xpos[3]);
            sprites[3].draw(batch);
            if(Xpos[3]<-sprites[3].getWidth()){
                isBackSpawn=false;
            }
        }else if(randNum==4){
            Xpos[4] -= EnemyManager.getSpeed() * delta;
            sprites[4].setX(Xpos[4]);
            sprites[4].draw(batch);
            if(Xpos[4]<-sprites[4].getWidth()){
                isBackSpawn=false;
            }
        }

    }
    void drawLine(SpriteBatch batch,float delta) {
        if(Xpos[2]<-sprites[2].getWidth()){
            Xpos[2]=0;
        }
        Xpos[2]-=EnemyManager.getSpeed()*delta;
        sprites[2].setX(Xpos[2]);
        sprites[2].draw(batch);
        sprites[2].setX(Xpos[2]+sprites[2].getWidth());
        sprites[2].draw(batch);
    }
    void drawPlane(SpriteBatch batch,float delta){
        if(randNum==5){
            Xpos[5] -= EnemyManager.getSpeed() * delta;
            sprites[5].setX(Xpos[5]);
            sprites[5].draw(batch);
            if(Xpos[5]<-sprites[5].getWidth()){
                isBackSpawn=false;
            }
        }
    }
    void drawFilter(SpriteBatch batch,float delta){
        if(Xpos[1]<-sprites[1].getWidth()){
            Xpos[1]=0;
        }
        Xpos[1]-=speeds[1]*delta;
        sprites[1].setX(Xpos[1]);
        sprites[1].draw(batch);
        sprites[1].setX(Xpos[1]+sprites[1].getWidth());
        sprites[1].draw(batch);
    }
    void drawGrass(SpriteBatch batch,float delta){
        if(Xpos[6]<-sprites[6].getWidth()){
            Xpos[6]=0;
        }
        Xpos[6]-=EnemyManager.getSpeed()*delta;
        sprites[6].setX(Xpos[6]);
        sprites[6].draw(batch);
        sprites[6].setX(Xpos[6]+sprites[6].getWidth());
        sprites[6].draw(batch);
    }
    void drawDownGrass(SpriteBatch batch,float delta){
        if(timeGrass>=8.5f&&!isGrassSpawn){
            prevG=randG;
            while (prevG==randG){
                randG=r.nextInt(2);
            }
            isGrassSpawn=true;
            Xpos[randG+9]=Gdx.graphics.getWidth();
        }

        if(isGrassSpawn) {
            if(randG==0) {
                if (Xpos[9] < -sprites[9].getWidth()) {
                    isGrassSpawn=false;
                    timeGrass=0f;
                }
                Xpos[9] -= EnemyManager.getSpeed() * delta * 1.4f;
                sprites[9].setX(Xpos[9]);
                sprites[9].draw(batch);
            }else if(randG==1){
                if(Xpos[10]<-sprites[10].getWidth()){
                    isGrassSpawn=false;
                    timeGrass=0f;
                }
                Xpos[10]-=EnemyManager.getSpeed()*delta*1.4f;
                sprites[10].setX(Xpos[10]);
                sprites[10].draw(batch);
            }
        }

        if(Xpos[8]<-sprites[8].getWidth()){
            Xpos[8]=0;
        }
        Xpos[8]-=EnemyManager.getSpeed()*delta*1.4f;
        sprites[8].setX(Xpos[8]);
        sprites[8].draw(batch);
        sprites[8].setX(Xpos[8]+sprites[8].getWidth());
        sprites[8].draw(batch);
    }



    float resize(){

        float w = Gdx.graphics.getWidth();
        float h = (w/sprites[0].getWidth()* sprites[0].getHeight());
        sprites[0].setSize(w,h);
        sprites[0].setPosition(0,Gdx.graphics.getHeight()-sprites[0].getHeight());
        Xpos[0]=sprites[0].getX();

        w = Gdx.graphics.getWidth();
        h = (w/sprites[2].getWidth()*sprites[2].getHeight());
        sprites[2].setSize(w,h);
        sprites[2].setPosition(0,0);
        Xpos[2]=sprites[2].getX();

        h= (w/sprites[1].getWidth()*sprites[1].getHeight());
        sprites[1].setSize(w,h);
        sprites[1].setPosition(0,Gdx.graphics.getHeight()-sprites[1].getHeight());
        Xpos[1]=sprites[1].getX();

        h=Gdx.graphics.getHeight()-sprites[2].getHeight();
        w = (h/sprites[3].getHeight()*sprites[3].getWidth());
        sprites[3].setSize(w,h);
        sprites[3].setPosition(Gdx.graphics.getWidth(),sprites[2].getHeight()-Gdx.graphics.getHeight()/60f);
        Xpos[3]=sprites[3].getX();

        h=Gdx.graphics.getHeight()/2;
        w = (h/sprites[4].getHeight()*sprites[4].getWidth());
        sprites[4].setSize(w,h);
        sprites[4].setPosition(Gdx.graphics.getWidth(),sprites[2].getHeight()-Gdx.graphics.getHeight()/60f);
        Xpos[4]=sprites[4].getX();

        h=Gdx.graphics.getHeight()/1.5f;
        w = (h/sprites[5].getHeight()*sprites[5].getWidth());
        sprites[5].setSize(w,h);
        sprites[5].setPosition(Gdx.graphics.getWidth(),sprites[2].getHeight()-Gdx.graphics.getHeight()/50f);
        Xpos[5]=sprites[5].getX();

        w=Gdx.graphics.getWidth();
        h=(w/sprites[6].getWidth())*sprites[6].getHeight();
        sprites[6].setSize(w,h);
        sprites[6].setPosition(0,sprites[2].getHeight()-sprites[6].getHeight()/1.7f);
        Xpos[6]=sprites[6].getX();

        h=(w/sprites[8].getWidth())*sprites[8].getHeight();
        sprites[8].setSize(w,h);
        sprites[8].setPosition(0,-sprites[2].getHeight()/5);
        Xpos[8]=sprites[8].getX();

        h=Gdx.graphics.getHeight()/2;
        w=(h/sprites[9].getHeight())*sprites[9].getWidth();
        sprites[9].setSize(w,h);
        sprites[9].setPosition(Gdx.graphics.getWidth(),sprites[8].getHeight()+sprites[8].getY()-sprites[2].getHeight()/4);
        Xpos[9]=sprites[9].getX();

        w=(h/sprites[10].getHeight())*sprites[10].getWidth();
        sprites[10].setSize(w,h);
        sprites[10].setPosition(Gdx.graphics.getWidth(),sprites[8].getHeight()+sprites[8].getY()-sprites[2].getHeight()/4);
        Xpos[10]=sprites[10].getX();


        return sprites[2].getHeight();
    }

    void reSpawn(){
        //TODO
    }

    void updTime(float delta){
        timeGround+=delta;
        timeGrass+=delta;
    }

}
