package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class OutbreakScreen implements Screen  {

    public static final String TAG = OutbreakScreen.class.getName();

    public static final Color BACKGROUND_COLOR = Color.BLACK;

    OutbreakGame game;

    FitViewport outbreakViewport;

    ShapeRenderer renderer;

    Paddle paddle;

    Ball ball;

    Blocks blocks;

    public OutbreakScreen(OutbreakGame game){
        this.game = game;
    }

    @Override
    public void show() {
        outbreakViewport = new FitViewport(Constants.WORLD_W, Constants.WORLD_H);

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        paddle = new Paddle(outbreakViewport);

        ball = new Ball(paddle);

        blocks = new Blocks();

        Gdx.input.setInputProcessor(ball);
    }

    @Override
    public void render(float delta) {
        paddle.update(delta);
        ball.update(delta, outbreakViewport);

        outbreakViewport.apply();

        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(outbreakViewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);
        paddle.render(renderer);
        ball.render(renderer);
        blocks.render(renderer);
        renderer.end();
        ball.collideWithBlock(blocks);
    }

    @Override
    public void resize(int width, int height) {
        outbreakViewport.update(width, height, true);

        paddle.init();
        ball.init();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        renderer.dispose();

    }

    @Override
    public void dispose() {

    }
}
