package com.moteurDeJeu.game;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.moteurDeJeu.game.MapManager.MapLoader.CollisionManager;
import com.moteurDeJeu.game.MapManager.MapLoader.MapLoader;
import com.moteurDeJeu.game.entity.*;

public class GameScreen implements Screen {
	private MyGame game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private CollisionManager collisionmanager;
	private int speed = 5;
	
	private MapLoader mapLoader;
	private OrthogonalTiledMapRenderer mapRenderer; 
	
	private List<Enemy> enemies;
	private List<Player> players;

	
	public GameScreen(MyGame game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
	}
	
	@Override
	public void show() {
		camera.position.set(400,240,0);
		camera.update();
		mapLoader = new MapLoader("maps/main.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(mapLoader.getMap());
		collisionmanager = new CollisionManager(mapLoader.getMap(), "collision");
		mapLoader.loadEntities();
		enemies = mapLoader.getEnemies();
		players = mapLoader.getPlayers();
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0,0,0,1);
		
		/**
		 * player input and update positions
		 */
		
	    for (Player player : players) {
	    	
	    	float nextX = player.getX();
	    	float nextY = player.getY();
	    	
	        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  nextX -= speed;
	        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) nextX += speed;
	        if (Gdx.input.isKeyPressed(Input.Keys.UP))    nextY += speed;
	        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))  nextY -= speed;

	        // ask collision manager BEFORE moving
	        if (!collisionmanager.collides(player.getBounds(nextX, nextY))) {
	            player.setPosition(nextX, nextY);
	        }
	    	
	    	/*
	        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  player.move(-speed, 0);
	        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.move(speed, 0);
	        if (Gdx.input.isKeyPressed(Input.Keys.UP))    player.move(0, speed);
	        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))  player.move(0, -speed);
	        
	        */
	    	
	    }
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		mapRenderer.setView(camera);
		mapRenderer.render();
		
		batch.begin();
		//draw things
		for(Enemy e:enemies) {
			e.render(batch);
		}
		for (Player p:players) {
			p.render(batch);
		}
		batch.end();
		
		
		
	}
	
	@Override 
	public void resize(int width,int height) {
		
	}
	
	
	
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void dispose() {
    	batch.dispose();
    	
}
}
