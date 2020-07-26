package com.severgames.dino.Person;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.severgames.dino.Data;
import com.severgames.dino.Dj;
import com.severgames.dino.Frame;
import com.severgames.dino.MoneyManager;

public class Person {
    private final Frame frame;
    private int chose=0;
    private int coin;
    private float lon;
    private String tmp="";
    private MoneyManager money;
    private Dj dj;
    private StonePerson stone;
    private RoboPerson robo;
    private SharkPerson shark;
    boolean jumpi;



    public Person(Dj dj,MoneyManager money,Frame frame){
        this.frame=frame;
        coin=new Data().getMoney();
        this.dj=dj;
        this.money=money;
        stone=new StonePerson(this,money);
        robo=new RoboPerson(this,money);
        shark=new SharkPerson(this,money);
    }
    public void spawn(){
        lon=0f;
        if(chose==0){
            stone.spawn();
        }else if(chose==1){
            robo.spawn();
        }else if(chose==2){
            shark.spawn();
        }
    }
    public boolean jump(){
        return jumpi;
    }
    public void draw(SpriteBatch batch){
        if(chose==0){
            stone.draw(batch);
        }else if(chose==1){
            robo.draw(batch);
        }else if(chose==2){
            shark.draw(batch);
        }
    }

    public void update(float delta, Rectangle rect){
        lon+=delta*3;
        if(chose==0){
            stone.update(delta,rect);
            stone.checkMoney();
        }else if(chose==1){
            robo.update(delta,rect);
            robo.checkMoney();
        }else if(chose==2){
            shark.update(delta,rect);
            shark.checkMoney();
        }
    }
    public Color getColor(){
        if(chose==0){
            return stone.getColor();
        }else if(chose==1){
            return robo.getColor();
        }
        return Color.GREEN;
    }

    public float resize(float y){
        if(chose==0){
            return stone.resize(y);
        }else if(chose==1){
            return robo.resize(y);
        }else if(chose==2){
            return shark.resize(y);
        }
        return 0f;
    }

    public void setChose(int chose) {
        this.chose=chose;
    }
    public String getLong() {
        tmp=lon+"";
        tmp=tmp.substring(0,tmp.indexOf("."));
        for(int i=tmp.length();i<14;i++){
            tmp="0"+tmp;
        }
        return tmp;

    }
    public void dispose(){
        stone.dispose();
        robo.dispose();
        shark.dispose();
    }

    void addCoin(int i) {
        coin++;
        dj.playCoin();
        new Data().saveMoney(coin);
        money.delete(i);
    }

    void dead() {
        frame.dead();
    }

    public Rectangle getRect() {
        if(chose==0){
            return stone.getReckt();
        }else if(chose==1){
            return robo.getReckt();
        }else if(chose==2){
            return shark.getReckt();
        }
        return null;
    }

    public void checkCol(Rectangle rect) {
        if(chose==0){
            stone.checkCol(rect);
        }else if(chose==1){
            robo.checkCol(rect);
        }else if(chose==2){
            shark.checkCol(rect);
        }

    }
}
