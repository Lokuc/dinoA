package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.severgames.lib.Button;
import com.severgames.lib.ClickListener;

import static com.severgames.dino.MyGdxGame.dj;

public class Menu extends ScreenAdapter implements ClickListener {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Button start;
    private Button settings;
    private Button shop;

    Menu(){
        batch = new SpriteBatch();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        start= new Button(SpriteLoad.getUI(8));
        start.setSizeW(3);
        start.addClickListener(this,camera);
        start.setPosition(ClickListener.POSITION_HORIZONTAL.Center, ClickListener.POSITION_VERTICAL.Center);
        shop = new Button(SpriteLoad.getUI(10));
        shop.setSizeH(5);
        settings=new Button(SpriteLoad.getUI(7));
        settings.setSizeH(5);
        settings.setPosition(POSITION_HORIZONTAL.RightBottom,POSITION_VERTICAL.UpBottom);
        settings.addClickListener(this,camera);
        shop.addClickListener(this,camera);
        shop.setPosition(POSITION_HORIZONTAL.RightCenter,POSITION_VERTICAL.UpBottom);
        shop.setPosition(Gdx.graphics.getWidth()-settings.getRect().width*2,shop.getRect().y);
    }

    @Override
    public void hide() {
        dj.stopFonM();
        super.hide();
    }

    @Override
    public void show() {
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        start.setSizeW(3);
        batch.setProjectionMatrix(camera.combined);
        start.setPosition(ClickListener.POSITION_HORIZONTAL.Center, ClickListener.POSITION_VERTICAL.Center);
        dj.playFonM();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
        settings.setSizeH(5);
        settings.setPosition(POSITION_HORIZONTAL.RightBottom,POSITION_VERTICAL.UpBottom);
        shop.setSizeH(5);
        shop.setPosition(POSITION_HORIZONTAL.RightCenter,POSITION_VERTICAL.UpBottom);
        shop.setPosition(Gdx.graphics.getWidth()-settings.getRect().width*2,shop.getRect().y);
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(0.1f,0.1f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        start.draw(batch);
        settings.draw(batch);
        shop.draw(batch);

        batch.end();


    }


    @Override
    public void click(String id) {
        if(start.id(id)){
            MyGdxGame.myGdxGame.setFrame(false);
        }
        if(settings.id(id)){
            MyGdxGame.myGdxGame.setSetting(true);
        }
        if(shop.id(id)){
            MyGdxGame.myGdxGame.setShop();
        }
    }
}
