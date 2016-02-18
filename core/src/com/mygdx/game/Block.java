package com.mygdx.game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;


import com.badlogic.gdx.math.Vector2;

public class Block extends GameObject {

    boolean collide = false;

    public Block(Vector2 pos) {
        position = pos;
    }

    public void render(ShapeRenderer renderer) {
        if (collide == false) {
            renderer.setColor(Color.BLACK);
            renderer.set(ShapeType.Filled);
            renderer.rect(position.x, position.y, Constants.BLOCK_W, Constants.BLOCK_H);
            renderer.setColor(Color.WHITE);
            renderer.rect(position.x + 4, position.y + 4, Constants.BLOCK_W - 8, Constants.BLOCK_H - 8);
        }
    }

    public void setCollide(boolean hit) {
        collide = hit;

    }
    public boolean getCollide() {
        return collide;
    }
}
