package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Utils.Constants;

public class PlayScreen extends ScreenAdapter {

    public static final String TAG = PlayScreen.class.getName();

    GameTest gameTest;
    Viewport gameViewport;
    OrthographicCamera gameCam;

    TmxMapLoader mapLoader;
    TiledMap map;
    OrthogonalTiledMapRenderer mapRenderer;

    public PlayScreen(GameTest game) {
        gameTest = game;
        gameCam = new OrthographicCamera();

        gameViewport = new FitViewport(Constants.WOLRD_WIDTH, Constants.WOLRD_HEIGTH, gameCam);

        //PER CARICARE LA MAPPA
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tiledMapTest2.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gameViewport.getWorldWidth() / 2, gameViewport.getWorldHeight() / 2, 0);

    }

    @Override
    public void show() {
        super.show();
    }

    public void update(float delta){
        gameCam.update();
        mapRenderer.setView(gameCam);

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            gameCam.position.x -= 300 * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            gameCam.position.x += 300 * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            gameCam.position.y += 300 * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            gameCam.position.y -= 300 * delta;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(Color.LIGHT_GRAY.r, Color.LIGHT_GRAY.g, Color.LIGHT_GRAY.b, Color.LIGHT_GRAY.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
    }
}
