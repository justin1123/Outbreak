package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Input.Keys;
import java.util.Random;


public class Ball extends InputAdapter{

    public static final String TAG = Ball.class.getName();

    Vector2 position, velocity;


    Paddle paddle;
    boolean stuck;


    public Ball(Paddle paddleIn) {
        paddle = paddleIn;

    }

    public void init() {
        position = new Vector2(paddle.position.x + Constants.PADDLE_LEN/2, paddle.position.y + Constants.PADDLE_LEN/4 + Constants.BALL_RADIUS);
        velocity = new Vector2();
        stuck = true;
    }

    private void kick() {
        Random random = new Random();
        float angle = MathUtils.PI2 * random.nextFloat();
        velocity.x = Constants.BALL_SPEED * MathUtils.cos(angle);
        velocity.y = Constants.BALL_SPEED * MathUtils.sin(angle);
    }

    public void update(float delta, Viewport viewport) {
        if (stuck) {
            position.x = paddle.position.x + Constants.PADDLE_LEN/2;
            position.y = paddle.position.y + Constants.PADDLE_LEN/4 + Constants.BALL_RADIUS;
        }else {
            position.x += delta * velocity.x;
            position.y += delta * velocity.y;
        }

        collideWithWalls(Constants.BALL_RADIUS, viewport.getWorldWidth(), viewport.getWorldHeight());
    }


    public boolean keyDown(int keycode){
        if (keycode == Keys.SPACE){
            if (stuck) {
                stuck = false;
                kick();
            }
        }

        return true;
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

    public void collideWithPaddle(){
        
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.WHITE);
        renderer.set(ShapeType.Filled);
        renderer.circle(position.x, position.y, Constants.BALL_RADIUS);


    }
}
