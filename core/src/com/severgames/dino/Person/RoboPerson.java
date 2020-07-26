package com.severgames.dino.Person;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.severgames.dino.MoneyManager;
import com.severgames.dino.SpriteLoad;
import com.badlogic.gdx.graphics.g2d.Sprite;

class RoboPerson {

    private boolean inFly=false;
    private float Y=0;
    private float speedFly=0;
    private boolean inGopnik=false;
    private boolean onWorm=false;
    private float y=0;
    private float x;
    private Sprite[] run;
    private Sprite[] gopnik;
    private Sprite[] fly;
    private float timeAnim=0f;
    private int skinNum;
    private int gSkinNum=0;
    private Rectangle temp=new Rectangle();
    private Person person;
    private MoneyManager money;


    RoboPerson(Person person, MoneyManager money){
        this.money=money;
        this.person=person;
        gopnik=new Sprite[2];
        run=new Sprite[7];
        fly=new Sprite[2];
        for(int i=0;i<run.length;i++){
            run[i]=SpriteLoad.getAnim("kaktus",i);
        }
        gopnik[0]=SpriteLoad.getPerson("kaktussit0");
        gopnik[1]=SpriteLoad.getPerson("kaktussit1");
        fly[0]=SpriteLoad.getPerson("kaktusdown");
        fly[1]=SpriteLoad.getPerson("kaktusup");

    }

    void spawn() {
        float x=Gdx.graphics.getWidth()/11f;
        for (Sprite s : run) {
            s.setPosition(x, y);
        }
        fly[0].setPosition(x, y);
        fly[1].setPosition(x, y);
        gopnik[0].setPosition(x,y);
        gopnik[1].setPosition(x,y);
        Y=y;
        speedFly=0;
        inFly=false;
        onWorm=false;
        inGopnik=false;
    }

    float resize(float y){
        float h = Gdx.graphics.getHeight()/5f;
        float w = (h/run[0].getHeight())*run[0].getWidth();
        for(int i=0;i<run.length;i++){
            run[i].setSize(w,h);
        }
        h=Gdx.graphics.getHeight()/6.5f;
        w = (h/gopnik[0].getHeight())*gopnik[0].getWidth();
        gopnik[0].setSize(w,h);
        gopnik[1].setSize(w,h);
        w = run[0].getWidth();
        h = (w/fly[0].getWidth()*fly[0].getHeight());
        fly[0].setSize(w,h);
        fly[1].setSize(w,h);
        this.y=y;
        spawn();
        return gopnik[0].getHeight();
    }

    void draw(SpriteBatch batch){
        if(inGopnik) {
            if(gSkinNum==0) {
                gopnik[0].draw(batch);
            }else{
                gopnik[1].draw(batch);
            }
        }else if(inFly){
            if(onWorm){
                run[skinNum].draw(batch);
            }else {
                if (speedFly > 0) {
                    fly[0].draw(batch);
                } else {
                    fly[1].draw(batch);
                }
            }
        }else {
            run[skinNum].draw(batch);
        }
    }

    void update(float delta, Rectangle rect){
        timeAnim+=delta;
        if(!inFly&&!onWorm) {
            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                inGopnik = true;
                if(timeAnim>=0.05f){
                    gSkinNum++;
                    if(gSkinNum>gopnik.length){
                        gSkinNum=0;
                    }
                    timeAnim=0;
                }
            } else {
                inGopnik = false;
            }
        }else if(onWorm){
            checkGround(rect);
        }
        person.jumpi=false;
        if (!inGopnik&&!inFly&&(Gdx.input.isKeyJustPressed(Input.Keys.UP)||Gdx.input.isKeyJustPressed(Input.Keys.W))){
            person.jumpi=true;
            inFly=true;
            speedFly=Gdx.graphics.getHeight()/35f;
        }
        if(inFly){
            speedFly-=delta*Gdx.graphics.getHeight()/16f;
            Y+=speedFly;
            checkGround(rect);
        }
        if(timeAnim>=0.07f){
            skinNum++;
            if(skinNum==run.length){
                skinNum=0;
            }
            timeAnim=0;
        }
        run[0].setY(Y);
        run[skinNum].setY(Y);
        fly[0].setY(Y);
        fly[1].setY(Y);
    }
    Color getColor(){
        return Color.GREEN;
    }
    private void checkGround(Rectangle rect) {
        if(Y<y){
            Y= (int) y;
            inFly=false;
        }else if(rect!=null){
            if(getReckt().y>rect.y&&getReckt().y<rect.y+rect.height*2) {
                if (getReckt().x + getReckt().width > rect.x && rect.x + rect.width > getReckt().x) {
                    if (getReckt().y > rect.y && getReckt().y < rect.y + rect.height) {
                        Y = (int) (rect.y + rect.height);
                        onWorm=true;
                        speedFly=0;
                    }else{
                        onWorm=false;
                    }
                }else{
                    onWorm=false;
                }
            }else{
                onWorm=false;
            }
        }else{
            onWorm=false;
        }
    }

    Rectangle getReckt(){
        if (inGopnik) {
            return gopnik[0].getBoundingRectangle();
        }else{
            temp=run[0].getBoundingRectangle();
            temp.x+=temp.width/6;
            temp.width-=temp.width/3;
            return temp;
        }
    }

    void checkMoney() {
        for(int i=0;i<money.getRect().length;i++){
            if(money.getRect()[i]==null){
                continue;
            }
            if(getReckt().overlaps(money.getRect()[i])){
                person.addCoin(i);
            }
        }
    }

    void checkCol(Rectangle enemy) {
        if(enemy.overlaps(getReckt())){
            person.dead();
        }

    }

    void dispose() {
        for(Sprite s:run){
            s.getTexture().dispose();
        }
        for(Sprite s:gopnik){
            s.getTexture().dispose();
        }
        for(Sprite s:fly){
            s.getTexture().dispose();
        }
    }

}
