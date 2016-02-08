package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class OutbreakScreen implements Screen  {

    public static final String TAG = OutbreakScreen.class.getName();

    public static final Color BACKGROUND_COLOR = Color.BLACK;

    OutbreakGame game;

    ExtendViewport outbreakViewport;

    ShapeRenderer renderer;

    Paddle paddle;

    public OutbreakScreen(OutbreakGame game){
        this.game = game;
    }

    @Override
    public void show() {
        outbreakViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        paddle = new Paddle(outbreakViewport);
    }

    @Override
    public void render(float delta) {
        paddle.update(delta);

        outbreakViewport.apply();

        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(outbreakViewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);
        paddle.render(renderer);
        renderer.end();

    }

    @Override
    public void resize(int width, int height) {
        outbreakViewport.update(width, height, true);

        paddle.init();

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
