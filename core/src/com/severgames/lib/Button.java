package com.severgames.lib;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Button extends Objects{


    public Button(Sprite sprite) {
        super();
        load(sprite);
    }

    public void setX(ClickListener.POSITION_HORIZONTAL position_horizontal){
        switch (position_horizontal) {
            case Center:
                sprite.setX((float) Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2);
                break;
            case LeftBottom:
                sprite.setX(0);
                break;
            case RightBottom:
                sprite.setX(Gdx.graphics.getWidth() - sprite.getWidth());
                break;
            case LeftCenter:
                int temp = (int) (Gdx.graphics.getWidth() / 4f - sprite.getWidth() / 2f);
                sprite.setX(temp < 0 ? 0 : temp);
                break;
            case RightCenter:
                int temp2 = (int) ((Gdx.graphics.getWidth() / 4f) * 3f - sprite.getWidth() / 2f);
                sprite.setX(temp2 > Gdx.graphics.getWidth() - sprite.getWidth() ? Gdx.graphics.getWidth() - sprite.getWidth() : temp2);
                break;
        }

    }
    public void setX(float X){
        sprite.setX(X);
    }
    public void setY(ClickListener.POSITION_VERTICAL position_vertical){
        switch (position_vertical) {
            case Center:
                sprite.setY((float) Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);
                break;
            case UpBottom:
                sprite.setY(Gdx.graphics.getHeight() - sprite.getHeight());
                break;
            case DownBottom:
                sprite.setY(0);
                break;
            case UpCenter:
                int temp = (int) ((Gdx.graphics.getHeight() / 4) * 3 - sprite.getHeight() / 2);
                sprite.setY(temp > Gdx.graphics.getHeight() - sprite.getHeight() ? Gdx.graphics.getHeight() - sprite.getHeight() : temp);
                break;
            case DownCenter:
                int temp2 = (int) (Gdx.graphics.getHeight() / 4 - sprite.getHeight() / 2);
                sprite.setY(temp2 > 0 ? temp2 : 0);
                break;

        }
    }
    public void setY(float Y){
        sprite.setY(Y);
    }
    public float getX(){
        return sprite.getX();
    }
    public float getY(){
        return sprite.getY();
    }
    public float getHeight(){
        return sprite.getHeight();
    }
    public float getWidth(){
        return sprite.getWidth();
    }

    public Rectangle getRect(){
        return sprite.getBoundingRectangle();
    }

    public Button(String path) {
        super();
        load(path);
    }




}
