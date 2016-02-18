package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;



public class Paddle extends GameObject {

    public static final String TAG = Paddle.class.getName();

    Vector2 velocity;
    Viewport viewport;

    public Paddle(Viewport viewportIn){
        viewport = viewportIn;
        init();
    }

    public void init(){
        position = new Vector2(viewport.getWorldWidth()/2 - Constants.PADDLE_LEN/2, viewport.getWorldHeight()/8);
        velocity = new Vector2();
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            velocity.x -= delta * Constants.PADDLE_ACCEL;

        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            velocity.x += delta * Constants.PADDLE_ACCEL;
        }

        velocity.clamp(0, Constants.PADDLE_SPEED);

        if (position.x < 0) {
            velocity.x = 0;
            position.x = 0;
        }

        if (position.x + Constants.PADDLE_LEN > viewport.getWorldWidth()) {
            velocity.x = 0;
            position.x = viewport.getWorldWidth() - Constants.PADDLE_LEN;
        }

        velocity.x -= delta * Constants.PADDLE_DRAG * velocity.x;

        position.x += delta * velocity.x;

        float accelInput = -Gdx.input.getAccelerometerY() / (9.8f * 0.5f);

        position.x += -delta * accelInput * Constants.PADDLE_SPEED;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.RED);
        renderer.set(ShapeType.Filled);
        renderer.rect(position.x, position.y, Constants.PADDLE_LEN, Constants.PADDLE_LEN / 4);
        renderer.setColor(Color.WHITE);
        renderer.set(ShapeType.Line);
        renderer.rect(position.x, position.y, Constants.PADDLE_LEN, Constants.PADDLE_LEN / 4);

    }

}
