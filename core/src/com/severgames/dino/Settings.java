package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.severgames.lib.Button;
import com.severgames.lib.CheckBox;
import com.severgames.lib.ClickListener;

class Settings extends ScreenAdapter implements ClickListener {

    private CheckBox music;
    private CheckBox sound;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Button back;
    private boolean isMenu;


    Settings(){
        camera=new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        music=new CheckBox(camera);
        sound=new CheckBox(camera);
        sound.addY();
        sound.setCheck(new Data().getSettings()[0]);
        music.setCheck(new Data().getSettings()[1]);
        back  = new Button(new Sprite(SpriteLoad.getUI(9)));
        back.setSizeH(12);
        back.setPosition(ClickListener.POSITION_HORIZONTAL.LeftBottom, POSITION_VERTICAL.UpCenter);
        back.addClickListener(this,camera);
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
        back.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
        back.setSizeH(12);
        back.setPosition(ClickListener.POSITION_HORIZONTAL.LeftBottom, POSITION_VERTICAL.UpCenter);

    }

    @Override
    public void show() {
        MyGdxGame.dj.playSetting();

    }

    @Override
    public void click(String id) {
        if(back.id(id)){
            MyGdxGame.dj.stopSetting();
            new Data().saveSettings(sound.getCheck(),music.getCheck());
            MyGdxGame.dj.setSettings(music.getCheck(),sound.getCheck());
            if(isMenu) {
                MyGdxGame.myGdxGame.setMenu();
            }else{
                MyGdxGame.myGdxGame.setFrame(true);
            }
        }
    }

    void setParent(boolean isMenu) {
        this.isMenu=isMenu;
    }
}
