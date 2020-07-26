package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.severgames.dino.Person.Person;
import com.severgames.dino.enemies.EnemyManager;
import com.severgames.lib.Button;
import com.severgames.lib.ClickListener;

import static com.severgames.dino.MyGdxGame.dj;

public class Frame extends ScreenAdapter implements ClickListener{

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Person person;
    private EnemyManager enemy;
    private boolean active=true;
    private Sprite count;
    private Button over;
    private Button menu;
    private BitmapFont font;
    private BackgroundManager manager;
    private ShapeRenderer shapeRenderer;
    private MoneyManager money;
    private BitmapFont ft;
    private boolean pause;
    private Button resume;
    private Button quit;
    private Button retry;
    private Button settings;
    private Sprite filter;


    Frame(BackgroundManager manager){




        this.manager = manager;
        shapeRenderer=new ShapeRenderer();
        money=new MoneyManager();
        try {
            FreeTypeFontGenerator f = new FreeTypeFontGenerator(new FileHandle("font.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter= new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size=20;
            f.generateData(parameter);
            ft = f.generateFont(parameter);
            ft.setColor(Color.CORAL);
            f.dispose();
        }catch (Exception e){
            new Data().log("font not found");
        }
        filter=SpriteLoad.getUI(2);
        batch = new SpriteBatch();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        person= new Person(dj,money,this);
        resume=new Button(SpriteLoad.getUI(6));
        resume.setSizeH(8);
        resume.setPosition(ClickListener.POSITION_HORIZONTAL.Center, POSITION_VERTICAL.UpCenter);
        resume.addClickListener(this,camera);
        retry = new Button(SpriteLoad.getUI("Retry"));
        retry.setSizeH(8);
        retry.setX(POSITION_HORIZONTAL.Center);
        retry.setY(resume.getY()-resume.getHeight()-retry.getHeight()-Gdx.graphics.getHeight()/11f);
        retry.addClickListener(this,camera);
        settings=new Button(SpriteLoad.getUI("Setting"));
        settings.setSizeH(8);
        settings.setX(POSITION_HORIZONTAL.Center);
        settings.setY(retry.getY()-retry.getHeight()-Gdx.graphics.getHeight()/11f);
        settings.addClickListener(this,camera);
        quit=new Button(SpriteLoad.getUI(5));
        quit.setSizeH(8);
        quit.setX(POSITION_HORIZONTAL.Center);
        quit.setY(settings.getY()-settings.getHeight()-Gdx.graphics.getHeight()/11f);
        quit.addClickListener(this,camera);
        enemy= new EnemyManager();
        count = SpriteLoad.getSprite(37);
        menu=new Button(SpriteLoad.getUI(4));
        menu.setSizeW(4);
        menu.setPosition(ClickListener.POSITION_HORIZONTAL.LeftBottom, ClickListener.POSITION_VERTICAL.DownBottom);
        menu.addClickListener(id -> MyGdxGame.myGdxGame.setMenu(),camera);
        over= new Button(SpriteLoad.getUI(3));
        over.setSizeW(4);
        over.setPosition(ClickListener.POSITION_HORIZONTAL.Center, ClickListener.POSITION_VERTICAL.Center);
        over.addClickListener(id -> {
            if(over.id(id)){
                person.spawn();
                enemy.spawn();
                enemy.respawn();
                active=true;
                manager.reSpawn();
                money.respawn();
                dj.playFon();
            }
        },camera);
        dj.load();


        batch.setProjectionMatrix(camera.combined);
        font = new BitmapFont();
        font.setColor(Color.GREEN);

    }


    @Override
    public void show() {
        pause=false;
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        dj.playFon();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.updateMatrices();
        batch.setProjectionMatrix(camera.combined);
        person.spawn();
        enemy.spawn();
        active=true;
        manager.reSpawn();
        money.respawn();
        dj.playFon();
    }



    @Override
    public void pause() {
        pause=true;
    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            return;

        }
        camera.update();
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
        if(active) {
            if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
                pause=!pause;
            }
            if(!pause) {
                update(delta);
                draw();
                batch.end();
            }else{
                draw();
                filter.draw(batch);
                resume.draw(batch);
                retry.draw(batch);
                settings.draw(batch);
                quit.draw(batch);
                batch.end();

            }
        }else {
            batch.begin();
            over.draw(batch);
            menu.draw(batch);
            batch.end();
        }

    }

    private void draw() {
        batch.begin();
        batch.disableBlending();
        manager.drawFon(batch);
        batch.enableBlending();
        manager.drawBack(batch);
        manager.drawPlane(batch);
        manager.drawLine(batch);
        enemy.draw(batch);
        person.draw(batch);
        enemy.draw1(batch);
        money.draw(batch);
        manager.drawGrass(batch);
        manager.drawDownGrass(batch);
        manager.drawFilter(batch);
        count.draw(batch);
        ft.draw(batch, person.getLong(), count.getX() + 10, count.getY() + 25);
        ft.draw(batch, Gdx.graphics.getFramesPerSecond() + " fps", 100, 500);
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            tmp(person.getRect(), person.getColor());
            tmp(enemy.getRect(), enemy.getColor());
            tmp(enemy.getGround(), enemy.getGroundColor());
            for (Rectangle re : money.getRect()) {
                tmp(re, money.getColor());
            }
            shapeRenderer.end();
        }
    }

    private void tmp(Rectangle r,Color c){
        if(r!=null) {
            shapeRenderer.setColor(c);
            shapeRenderer.rect(r.x, r.y, r.width, r.height);
        }
    }


    private void update(float delta){
        manager.update(delta);
        money.update(delta);
        enemy.update(delta);
        person.update(delta,enemy.getGround());
        if(!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            person.checkCol(enemy.getRect());
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.updateMatrices();
        camera.update();
        resume.setSizeH(8);
        resume.setPosition(POSITION_HORIZONTAL.Center,POSITION_VERTICAL.UpCenter);
        retry.setSizeH(8);
        retry.setX(POSITION_HORIZONTAL.Center);
        retry.setY(resume.getY()-resume.getHeight()-Gdx.graphics.getHeight()/16f);
        settings.setSizeH(8);
        settings.setX(POSITION_HORIZONTAL.Center);
        settings.setY(retry.getY()-retry.getHeight()-Gdx.graphics.getHeight()/16f);
        quit.setSizeH(8);
        quit.setX(POSITION_HORIZONTAL.Center);
        quit.setY(settings.getY()-settings.getHeight()-Gdx.graphics.getHeight()/16f);
        filter.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        float tmp = manager.resize();
        money.resize(tmp);

        count.setSize(50/count.getHeight()*count.getWidth(),50);
        count.setPosition(Gdx.graphics.getWidth()-count.getWidth()*1.5f,Gdx.graphics.getWidth()/40);

        over.setSizeW(4);
        over.setPosition(ClickListener.POSITION_HORIZONTAL.Center, ClickListener.POSITION_VERTICAL.Center);

        float tm = person.resize(tmp);
        enemy.resize(tmp,tm);

    }



    public void dead() {
        active=false;
        dj.stopFon();
        dj.playEnd();
    }

    @Override
    public void click(String id) {
        if(resume.id(id)){
            pause=false;
        }
        if(quit.id(id)){
            dj.stopFon();
            MyGdxGame.myGdxGame.setMenu();
        }
        if(retry.id(id)){
            dj.stopFon();
            person.spawn();
            enemy.spawn();
            enemy.respawn();
            manager.reSpawn();
            money.respawn();
            dj.playFon();
            pause=false;
        }
        if(settings.id(id)){
            dj.stopFon();
            MyGdxGame.myGdxGame.setSetting();
        }
    }

    @Override
    public void dispose() {
        count.getTexture().dispose();
        ft.dispose();
        font.dispose();
        filter.getTexture().dispose();
        batch.dispose();
        person.dispose();
        enemy.dispose();
        over.dispose();
        menu.dispose();
        dj.dispose();
        manager.dispose();
        shapeRenderer.dispose();
        money.dispose();
        resume.dispose();
        quit.dispose();
        retry.dispose();
        settings.dispose();
    }

    void setChose(int chose) {
        person.setChose(chose);
    }
}
