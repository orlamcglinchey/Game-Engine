package com.moteurDeJeu.game.Controller;
import java.util.List;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.moteurDeJeu.game.Model.MapManager.MapLoader.CollisionManager;
import com.moteurDeJeu.game.Model.MapManager.MapLoader.MapLoader;
import com.moteurDeJeu.game.Model.entity.Enemy;
import com.moteurDeJeu.game.Model.entity.Exit;
import com.moteurDeJeu.game.Model.entity.Player;
import com.moteurDeJeu.game.View.CameraController;

public class GameScreen implements Screen {
	private MyGame game;
	private SpriteBatch batch;
	private CollisionManager collisionmanager;
	
	private MapLoader mapLoader;
	private OrthogonalTiledMapRenderer mapRenderer; 
//	private int mapWidth;
	//private int mapHeight;
	private CameraController camera;
	private GameController gameController;
	
	private List<Enemy> enemies;
	private List<Player> players;
	private List<Exit> exits;

	
	public GameScreen(MyGame game) {
		this.game = game;
		batch = new SpriteBatch();
	}
	
	@Override
	public void show() {

		mapLoader = new MapLoader("maps/main.tmx");
		
		camera = new CameraController(800,480);
		camera.fitMap(mapLoader.getMapPixelWidth(),mapLoader.getMapPixelHeight());

		mapRenderer = new OrthogonalTiledMapRenderer(mapLoader.getMap());
		collisionmanager = new CollisionManager(mapLoader.getMap(), "collision");
		gameController = new GameController(collisionmanager);
		mapLoader.loadEntities();
		enemies = mapLoader.getEnemies();
		players = mapLoader.getPlayers();
		exits = mapLoader.getExits();
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0,0,0,1);
		
		gameController.updatePlayers(players, exits);

		
		/**
		 * player input and update positions
		 */
		/*
	    for (Player player : players) {
	    	for(Exit exit:exits) {
	    		
	    		
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
	        //ask if player has hit exit
	        if(player.getBounds(nextX, nextY).overlaps(exit.getBounds(exit.getX(),exit.getY()))) {
	        	//player reached exit 
	        	System.out.println("player has reached exit");
	        }
	    	}

	    }*/
		
		//camera.update();
		//batch.setProjectionMatrix(camera.combined);
		
		mapRenderer.setView(camera.getCamera());
		mapRenderer.render();
		
		batch.begin();
		//draw things
		for(Enemy e:enemies) {
			e.render(batch);
		}
		for (Player p:players) {
			p.render(batch);
		}
		//change becuase we dont want all level exits drawing at once
		for(Exit exit: exits) {
			exit.render(batch);
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
