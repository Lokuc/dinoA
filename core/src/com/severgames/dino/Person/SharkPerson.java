package com.severgames.dino.Person;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.severgames.dino.MoneyManager;
import com.severgames.dino.SpriteLoad;

class SharkPerson {

    private boolean inFly = false;
    private float Y = 0;
    private float speedFly = 0;
    private boolean onWorm = false;
    private float y = 0;
    private Sprite[] run;
    private Sprite[] fly;
    private float timeAnim = 0f;
    private int skinNum;
    private Rectangle temp = new Rectangle();
    private Person person;
    private MoneyManager money;

    SharkPerson(Person person, MoneyManager money) {
        this.person = person;
        this.money = money;
        run = new Sprite[7];
        fly = new Sprite[2];
        run[0] = SpriteLoad.getSprite("anim10");
        run[1] = SpriteLoad.getSprite("anim11");
        run[2] = SpriteLoad.getSprite("anim12");
        run[3] = SpriteLoad.getSprite("anim13");
        run[4] = SpriteLoad.getSprite("anim14");
        run[5] = SpriteLoad.getSprite("anim15");
        run[6] = SpriteLoad.getSprite("anim16");
        fly[0] = SpriteLoad.getSprite("down1");
        fly[1] = SpriteLoad.getSprite("up1");
    }

    void spawn() {
        float x = Gdx.graphics.getWidth() / 11f;
        for (Sprite s : run) {
            s.setPosition(x, y);
        }
        fly[0].setPosition(x, y);
        fly[1].setPosition(x, y);
        Y = y;
        speedFly = 0;
        inFly = false;
        onWorm = false;
    }

    float resize(float y) {
        float h = Gdx.graphics.getHeight() / 6.5f;
        float w = (h / run[0].getHeight()) * run[0].getWidth();
        for (Sprite sprite : run) {
            sprite.setSize(w, h);
        }
        w = run[0].getWidth();
        h = (w / fly[0].getWidth()) * fly[0].getHeight();
        fly[0].setSize(w, h);
        fly[1].setSize(w, h);
        this.y = y;
        spawn();
        return run[0].getHeight();
    }

    void draw(SpriteBatch batch) {
        if (inFly) {
            if (onWorm) {
                run[skinNum].draw(batch);
            } else {
                if (speedFly > 0) {
                    fly[0].draw(batch);
                } else {
                    fly[1].draw(batch);
                }
            }
        } else {
            run[skinNum].draw(batch);
        }
    }

    void update(float delta, Rectangle rect) {
        timeAnim += delta;
        if (onWorm) {
            checkGround(rect);
        }
        if (!inFly && (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W))) {
            inFly = true;
            speedFly = Gdx.graphics.getHeight() / 35f;
        }
        if (inFly) {
            speedFly -= delta * Gdx.graphics.getHeight() / 16f;
            Y += speedFly;
            checkGround(rect);
        }
        if (timeAnim >= 0.07f) {
            skinNum++;
            if (skinNum == run.length) {
                skinNum = 0;
            }
            timeAnim = 0;
        }
        run[0].setY(Y);
        run[skinNum].setY(Y);
        fly[0].setY(Y);
        fly[1].setY(Y);
    }



    Rectangle getReckt(){
        temp=run[0].getBoundingRectangle();
        temp.x+=temp.width/6;
        temp.width-=temp.width/3;
        return temp;
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

    private void checkGround(Rectangle rect) {
        if (Y < y) {
            Y = (int) y;
            inFly = false;
        } else if (rect != null) {
            if (getReckt().y > rect.y && getReckt().y < rect.y + rect.height * 2) {
                if (getReckt().x + getReckt().width > rect.x && rect.x + rect.width > getReckt().x) {
                    if (getReckt().y > rect.y && getReckt().y < rect.y + rect.height) {
                        Y = (int) (rect.y + rect.height);
                        onWorm = true;
                        speedFly = 0;
                    } else {
                        onWorm = false;
                    }
                } else {
                    onWorm = false;
                }
            } else {
                onWorm = false;
            }
        } else {
            onWorm = false;
        }
    }
    void dispose() {
        for(Sprite s:run){
            s.getTexture().dispose();
        }
        for(Sprite s:fly){
            s.getTexture().dispose();
        }
    }




}
