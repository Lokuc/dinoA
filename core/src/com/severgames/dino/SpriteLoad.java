package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class SpriteLoad  {

    private static Sprite[] sprites;
    private static  String[] path = new String[]{
            "texture/enemy/0.png",
            "texture/enemy/3.png",
            "texture/enemy/fonEnemy.png",
            "texture/enemy/fonEnemy2.png",  //3
            "texture/fon/back.png",
            "texture/fon/filter.png",
            "texture/fon/fon.png",
            "texture/fon/line.png",
            "texture/fon/plane.png",
            "texture/fon/statuya.png",      //9
            "texture/person/animation/anim0.png",
            "texture/person/animation/anim1.png",
            "texture/person/animation/anim2.png",
            "texture/person/animation/anim3.png",
            "texture/person/animation/anim4.png",
            "texture/person/animation/anim5.png",
            "texture/person/animation/anim6.png",
            "texture/person/animGopnik/gopnik0.png", //17
            "texture/person/animGopnik/gopnik1.png",
            "texture/person/fly/down.png",
            "texture/person/fly/up.png",             //20
            "texture/money/KEY0.png",
            "texture/money/KEY1.png",
            "texture/money/KEY2.png",
            "texture/money/KEY3.png",
            "texture/money/KEY4.png",
            "texture/money/KEY5.png",            //26
            "texture/money/hide/hide0.png",
            "texture/money/hide/hide1.png",
            "texture/money/hide/hide2.png",
            "texture/money/hide/hide3.png",
            "texture/money/hide/hide4.png",
            "texture/money/hide/hide5.png",
            "texture/money/hide/hide6.png",      //33
            "texture/enemy/2.png"

    };
    private static  String[] paths = new String[]{
            "0",
            "3",
            "fonEnemy",
            "fonEnemy2",  //3
            "back",
            "filter",
            "fon",
            "line",
            "plane",
            "statuya",      //9
            "anim0",
            "anim1",
            "anim2",
            "anim3",
            "anim4",
            "anim5",
            "anim6",
            "gopnik0", //17
            "gopnik1",
            "down",
            "up",             //20
            "KEY0",
            "KEY1",
            "KEY2",
            "KEY3",
            "KEY4",
            "KEY5",            //26
            "hide0",
            "hide1",
            "hide2",
            "hide3",
            "hide4",
            "hide5",
            "hide6",      //33
            "2",
            "1"             //35

    };
    private loadScreen ld;
    private static TextureAtlas atlas;
    private int iter=4;

    public SpriteLoad(loadScreen loadScreen){
        try {
            TexturePacker.Settings settings = new TexturePacker.Settings();
            settings.fast = true;
            settings.maxHeight = 16384;
            settings.maxWidth = 16384;
            TexturePacker.process(settings, "./texture/input",  "./texture/output", "res2");
            atlas = new TextureAtlas("./texture/output/res2.atlas");
        }catch (Exception e){
            FileHandle log = Gdx.files.absolute("log.txt");
            log.writeString(Gdx.files.absolute("./texture/output/res2.atlas").path(),true);
            log.writeString(e.getMessage(),true);

            Gdx.app.log("g",e.getMessage());
        }
        ld = loadScreen;
        sprites=new Sprite[path.length];

    }

    public static Sprite getSprite(int i){

        return atlas.createSprite(paths[i]);
    }

    public SpriteLoad(){

    }

//    public void load(){
//        sprites=new Sprite[path.length];
//        for(int i=0;i<path.length;i++){
//            sprites[i]=new Sprite(new Texture(path[i]));
//            ld.count();
//        }
//    }

    public boolean getLoad(){
        return true;
    }

    public void loadi(){
        sprites[iter]=new Sprite(new Texture(path[iter]));
        sprites[iter].getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        ld.count();
        iter++;
    }

    float getCount() {
        return path.length;
    }
}
