package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.severgames.lib.Button;
import com.severgames.lib.ClickListener;

public class Achievements extends ScreenAdapter {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Button fon;
    private BitmapFont ft;
    private boolean isMove;
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

    }

    public void updateBoard(){
        if(isShow){
            //TODO
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public Achievements(){
        camera=new OrthographicCamera();
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
            parameter.size=18;
            f.generateData(parameter);
            ft = f.generateFont(parameter);
            ft.setColor(Color.WHITE);
            f.dispose();
        }catch (Exception e){
            new Data().log("font not found");
        }
        isMove=false;
        isShow=false;
        timeShow=0;
    }

    public void respawn(){
        isMove=false;
        isShow=false;
        timeShow=0;
    }







}
