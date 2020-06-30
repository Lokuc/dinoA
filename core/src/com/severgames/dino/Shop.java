package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.severgames.lib.Button;
import com.severgames.lib.ClickListener;

public class Shop extends ScreenAdapter implements ClickListener {

        private Button[] skin;
        private OrthographicCamera camera;
        private SpriteBatch batch;
        private int chose;
        private Button back;

    public Shop() {
        batch=new SpriteBatch();
        camera=new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
        skin=new Button[3];
        skin[0]=new Button(SpriteLoad.getUI(11));
        skin[1]=new Button(SpriteLoad.getUI(12));
        skin[2]=new Button(SpriteLoad.getUI(13));
        skin[0].setSizeH(3);
        skin[1].setSizeH(3);
        skin[2].setSizeH(3);
        skin[0].setPosition(POSITION_HORIZONTAL.LeftCenter, POSITION_VERTICAL.Center);
        skin[1].setPosition(POSITION_HORIZONTAL.RightCenter, POSITION_VERTICAL.Center);
        skin[2].setPosition(POSITION_HORIZONTAL.Center,POSITION_VERTICAL.Center);
        skin[0].addClickListener(this,camera);
        skin[1].addClickListener(this,camera);
        skin[2].addClickListener(this,camera);
        chose=0;
        back = new Button(new Sprite(SpriteLoad.getUI(9)));
        back.setSizeH(12);
        back.setPosition(ClickListener.POSITION_HORIZONTAL.LeftBottom, POSITION_VERTICAL.UpCenter);
        back.addClickListener(this,camera);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.4f,0.2f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        skin[0].draw(batch);
        skin[1].draw(batch);
        skin[2].draw(batch);
        back.draw(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
        skin[0].setSizeH(3);
        skin[1].setSizeH(3);
        skin[2].setSizeH(3);
        skin[0].setPosition(ClickListener.POSITION_HORIZONTAL.LeftCenter, ClickListener.POSITION_VERTICAL.Center);
        skin[1].setPosition(ClickListener.POSITION_HORIZONTAL.RightCenter, ClickListener.POSITION_VERTICAL.Center);
        skin[2].setPosition(POSITION_HORIZONTAL.Center,POSITION_VERTICAL.Center);
    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void click(String id) {
        if(skin[0].id(id)){
            skin[0].setSizeH(2);
            skin[0].setPosition(ClickListener.POSITION_HORIZONTAL.LeftCenter, ClickListener.POSITION_VERTICAL.Center);
            skin[1].setSizeH(3);
            skin[1].setPosition(POSITION_HORIZONTAL.RightCenter, ClickListener.POSITION_VERTICAL.Center);
            skin[2].setSizeH(3);
            skin[2].setPosition(POSITION_HORIZONTAL.Center,POSITION_VERTICAL.Center);
            chose=0;
        }
        if(skin[1].id(id)){
            skin[1].setSizeH(2);
            skin[1].setPosition(POSITION_HORIZONTAL.RightCenter, ClickListener.POSITION_VERTICAL.Center);
            skin[0].setSizeH(3);
            skin[0].setPosition(ClickListener.POSITION_HORIZONTAL.LeftCenter, ClickListener.POSITION_VERTICAL.Center);
            skin[2].setSizeH(3);
            skin[2].setPosition(POSITION_HORIZONTAL.Center,POSITION_VERTICAL.Center);
            chose=1;
        }
        if(skin[2].id(id)){
            skin[2].setSizeH(2);
            skin[2].setPosition(POSITION_HORIZONTAL.Center,POSITION_VERTICAL.Center);
            skin[1].setSizeH(3);
            skin[1].setPosition(POSITION_HORIZONTAL.RightCenter, ClickListener.POSITION_VERTICAL.Center);
            skin[0].setSizeH(3);
            skin[0].setPosition(ClickListener.POSITION_HORIZONTAL.LeftCenter, ClickListener.POSITION_VERTICAL.Center);
            chose=2;
        }
        if(back.id(id)){
            MyGdxGame.myGdxGame.setMenu(chose);
        }
    }
}
