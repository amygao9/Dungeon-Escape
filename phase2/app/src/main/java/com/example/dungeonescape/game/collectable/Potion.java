package com.example.dungeonescape.game.collectable;

import com.example.dungeonescape.game.GameObject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Path;
import com.example.dungeonescape.game.Drawable;

import java.io.Serializable;
import java.util.Random;


public class Potion extends GameObject implements Collectable, Drawable, Serializable {

    private Boolean available;
    private Rect potionShape;
    /** The size of the potion. */
    private int size;

    public Potion(int x, int y, int size) {
        super(x, y);
        available = true;
        this.size = size;
        setPaintColour(Color.GREEN);
        potionShape = new Rect(x, y, x + size, y + size);
    }

    @Override
    public void draw(Canvas canvas) {
        int width = potionShape.width();
        int x = getX();
        int y = getY();

        Path path = new Path();

        path.moveTo(x + width/3, y + width/3);
        path.lineTo(x + width/3, y);
        path.lineTo(x + (width * 2/3), y);
        path.lineTo(x + (width * 2/3), y + width/3);
        path.lineTo(x + width, y + width/3);
        path.lineTo(x + (width * 2/3), y + width);
        path.lineTo(x + width/3, y + width);
        path.lineTo(x, y + width/3);
        path.lineTo(x + width/3, y + width/3);
        path.close();

        canvas.drawPath(path, getPaint());
    }

    /** Moves the Gem down when the Character jumps up. */
    public void update(int down, int height) {

        if (getY() + down > height) {
            /* Moves coin up if the Character moves down without collection the PlatformerCoin. */
            int diff = Math.abs(getY() + down - height);
            if (diff > 400) {
                setY(0);
            } else if (diff > 200) {
                setY(-200);
            } else {
                setY(-diff);
            }
            Random r = new Random();
            int a = r.nextInt(height - 150);
            this.setX(a);
        } else {
            incY(down);
        }
        updatePotionLocation();
    }
    private void updatePotionLocation() {
        this.potionShape.top = getY();
        this.potionShape.right = getX() + size;
        this.potionShape.bottom = getY() + size;
        this.potionShape.left = getX();
    }
    public void gotCollectable() {
        setY(0);
        Random r = new Random();
        setX(r.nextInt(1080 - 150));
        updatePotionLocation();
    }
    @Override
    public Boolean getAvailableStatus() {
        return this.available;
    }

    @Override
    public void gotCollected() {
        this.available = false;
    }

    @Override
    public Rect getItemShape(){
        return potionShape;
    }
}
