package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.severgames.dino.Person.Person;
import com.severgames.lib.Button;
import com.severgames.lib.ClickListener;

public class Achievements extends ScreenAdapter {

    private Person person;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Button fon;
    private BitmapFont ft;
    private boolean isMove;
    private boolean isNotMove;
    private boolean isShow;
    private float timeShow;
    private float X;




    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        fon.setSizeH(5);
        fon.setPosition(ClickListener.POSITION_HORIZONTAL.RightBottom, ClickListener.POSITION_VERTICAL.UpBottom);
    }

    @Override
    public void show() {
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    public void updateAndCheck(){
        check();
        updateBoard();
    }

    private void check(){
        if(person.jump()){
            isShow=true;
            isMove=true;
        }
    }

    private void updateBoard(){
        if(isShow){
            if(isMove){
                fon.setX(fon.getY()-600*Gdx.graphics.getDeltaTime());
                if(fon.getX()<Gdx.graphics.getWidth()-fon.getWidth()){
                    fon.setX(Gdx.graphics.getWidth()-fon.getWidth());
                    isMove=false;
                }
            }else {
                timeShow += Gdx.graphics.getDeltaTime();
                if (timeShow >= 2f) {
                    isNotMove = true;
                }
                if (isNotMove) {
                    fon.setX(fon.getY() + 600 * Gdx.graphics.getDeltaTime());
                    if (fon.getX() > Gdx.graphics.getWidth()) {
                        fon.setX(Gdx.graphics.getWidth());
                        isNotMove = false;
                        isShow=false;
                    }
                }
            }
        }
    }

    public void draw(SpriteBatch batch){
        fon.draw(batch);
        ft.draw(batch,"Test",fon.getX(),fon.getY());
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public Achievements(Person person){
        camera=new OrthographicCamera();
        this.person = person;
        batch=new SpriteBatch();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        fon = new Button(SpriteLoad.getSprite("atchi"));
        fon.setSizeH(5);
        fon.setPosition(ClickListener.POSITION_HORIZONTAL.RightBottom, ClickListener.POSITION_VERTICAL.UpBottom);
        try {
            FreeTypeFontGenerator f = new FreeTypeFontGenerator(new FileHandle("font.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter= new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size=(Gdx.graphics.getHeight()/5)/5;
            f.generateData(parameter);
            ft = f.generateFont(parameter);
            ft.setColor(Color.WHITE);
            f.dispose();
        }catch (Exception e){
            new Data().log("font not found");
        }
        isMove=false;
        isShow=false;
        isNotMove=false;
        timeShow=0f;
    }

    public void respawn(){
        isMove=false;
        isShow=false;
        timeShow=0;
    }







}
