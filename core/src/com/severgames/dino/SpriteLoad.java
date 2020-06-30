package com.severgames.dino;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class SpriteLoad {

    private static Sprite[] sprites;
    private static  String[] path = new String[]{
            "texture/fon/back.png",
            "texture/fon/filter.png",
            "texture/fon/fon.png",
            "texture/fon/line.png",
            "texture/fon/plane.png",
            "texture/fon/statuya.png",
            "texture/fon/down.png",
            "texture/fon/grass1.png",
            "texture/fon/grass2.png"

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
            "1",             //35
            "grass",
            "count"


    };
    private static String[] pathUI=new String[]{
            "checkF",
            "checkT",
            "Filter",
            "gameover",
            "menu",
            "Quit",
            "Resume",
            "settings",
            "start",
            "back",
            "shop",
            "skin1",
            "skin2",
            "skin3"
    };
    private loadScreen ld;
    private static TextureAtlas atlas;
    private static TextureAtlas atlasUI;
    private int iter=0;

    public SpriteLoad(loadScreen loadScreen){
        try {
            TexturePacker.Settings settings = new TexturePacker.Settings();
            settings.fast = true;
            settings.maxHeight = 16384;
            settings.maxWidth = 16384;
            settings.filterMin= Texture.TextureFilter.Linear;
            settings.filterMag= Texture.TextureFilter.Linear;
            TexturePacker.process(settings, "./texture/input",  "./texture/output", "res2");
            atlas = new TextureAtlas("./texture/output/res2.atlas");
        }catch (Exception ignore){
        }
        try {
            TexturePacker.Settings settings = new TexturePacker.Settings();
            settings.fast = true;
            settings.maxHeight = 16384;
            settings.maxWidth = 16384;
            settings.filterMin= Texture.TextureFilter.Linear;
            settings.filterMag= Texture.TextureFilter.Linear;
            TexturePacker.process(settings, "./texture/UI",  "./texture/output", "UI");
            atlasUI = new TextureAtlas("./texture/output/UI.atlas");
        }catch (Exception ignore){
        }
        ld = loadScreen;
        sprites=new Sprite[path.length];

    }

    public static Sprite getSprite(int i){

        return atlas.createSprite(paths[i]);
    }
    public static Sprite getSprite(String name){
        return atlas.createSprite(name);
    }
    public static Sprite getUI(int i){
        return atlasUI.createSprite(pathUI[i]);
    }
    public static Sprite getFon(int i){
        return sprites[i];
    }



    public boolean getLoad(){
        return iter>=path.length;
    }

    public void load(){
        sprites[iter]=new Sprite(new Texture(path[iter]));
        sprites[iter].getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        ld.count();
        iter++;
    }

    float getCounts() {
        return path.length;
    }


}
