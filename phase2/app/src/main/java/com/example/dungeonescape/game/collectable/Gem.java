package com.example.dungeonescape.game.collectable;

import com.example.dungeonescape.game.GameData;
import com.example.dungeonescape.game.GameObject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Path;
import com.example.dungeonescape.game.Drawable;
import com.example.dungeonescape.game.RetrieveData;
import com.example.dungeonescape.player.Player;

/**
 * Creates a gem that can be collected by the player to increase their score.
 */
public class Gem extends GameObject implements Collectable, Drawable {

    /**
     * available - whether the gem can be collected by the player.
     * gemShape - Rect representation of the Gem.
     */
    private Boolean available;
    private Rect gemShape;

    /**
     * Initializes a gem item.
     * @param x x coordinate of top left corner of the gem.
     * @param y y coordinate of top left corner of the gem
     * @param size size of the gem.
     */
    public Gem(int x, int y, int size) {

        super(x, y);
        available = true;
        setPaintColour(Color.BLUE);
        gemShape = new Rect(x, y, x + size, y + size);
    }

    @Override
    public void draw(Canvas canvas) {
        /*
         * Math required to draw the gem shape is taken from:
         * https://stackoverflow.com/questions/51370566/diamond-shape-in-android-studio/51372564
         */
        int width = gemShape.width();
        int x = getX();
        int y = getY();

        Path path = new Path();

        path.moveTo(x, y + width); // Top
        path.lineTo(x - width/2, y); // Left
        path.lineTo(x, y - width); // Bottom
        path.lineTo(x + width/2, y); // Right
        path.lineTo(x, y + width); // Back to Top
        path.close();

        canvas.drawPath(path, getPaint());
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

        return gemShape;
    }

    @Override
    public void collect(Player player) {
        player.addToSatchel(this);
    }
}
