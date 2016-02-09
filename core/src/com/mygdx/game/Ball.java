package com.mygdx.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.MathUtils;
import java.util.Random;


public class Ball {

    public static final String TAG = Ball.class.getName();

    Vector2 position, velocity;

    Paddle paddle;
    boolean stuck;


    public Ball(Paddle paddle) {
        this.paddle = paddle;

    }

    public void init() {
        position = new Vector2(paddle.position.x + Constants.PADDLE_LEN/2, paddle.position.y + Constants.PADDLE_LEN/4 + Constants.BALL_RADIUS);
        velocity = new Vector2();
        testKick();
    }

    private void testKick() {
        Random random = new Random();
        float angle = MathUtils.PI2 * random.nextFloat();
        velocity.x = 300 * MathUtils.cos(angle);
        velocity.y = 300 * MathUtils.sin(angle);
    }

    public void update(float delta, Viewport viewport) {
        position.x += delta * velocity.x;
        position.y += delta * velocity.y;


        collideWithWalls(Constants.BALL_RADIUS, viewport.getWorldWidth(), viewport.getWorldHeight());
    }

    private void collideWithWalls(float radius, float viewportWidth, float viewportHeight) {
        if (position.x - radius < 0) {
            position.x = radius;
            velocity.x = -velocity.x;
        }
        if (position.x + radius > viewportWidth) {
            position.x = viewportWidth - radius;
            velocity.x = -velocity.x;
        }

        if (position.y - radius < 0) {
            position.y = radius;
            velocity.y = -velocity.y;
        }

        if (position.y + radius > viewportHeight) {
            position.y = viewportHeight - radius;
            velocity.y = -velocity.y;
        }
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.WHITE);
        renderer.set(ShapeType.Filled);
        renderer.circle(position.x, position.y, Constants.BALL_RADIUS);


    }
}
