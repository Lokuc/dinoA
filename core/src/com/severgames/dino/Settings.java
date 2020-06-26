package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.severgames.lib.CheckBox;
import com.sun.org.apache.xpath.internal.operations.Or;

class Settings extends ScreenAdapter {

    private CheckBox music;
    private CheckBox sound;
    private OrthographicCamera camera;
    private SpriteBatch batch;


    Settings(){
        camera=new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        music=new CheckBox(camera);
        sound=new CheckBox(camera);
        sound.addY();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f,0.4f,0.3f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();
        sound.draw(batch);
        music.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void show() {

    }
}
