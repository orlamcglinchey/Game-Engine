package com.moteurDeJeu.game.Controller;
import java.util.List;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.moteurDeJeu.game.Controller.GameController.Gamestate;
import com.moteurDeJeu.game.Model.MapManager.MapLoader.CollisionManager;
import com.moteurDeJeu.game.Model.MapManager.MapLoader.MapLoader;
import com.moteurDeJeu.game.Model.config.ConfigManager;
import com.moteurDeJeu.game.Model.entity.Enemy;
import com.moteurDeJeu.game.Model.entity.Exit;
import com.moteurDeJeu.game.Model.entity.Player;
import com.moteurDeJeu.game.Model.entity.PowerUp;
import com.moteurDeJeu.game.View.CameraController;
import com.moteurDeJeu.game.View.GameUI;
import com.moteurDeJeu.game.View.UIAssets;

public class GameScreen implements Screen {
	private MyGame game;
	private SpriteBatch batch;
	private CollisionManager collisionmanager;
	
	private MapLoader mapLoader;
	private OrthogonalTiledMapRenderer mapRenderer; 
	private CameraController camera;
	private GameController gameController;
	private GameUI levelText;
	private GameUI ui;
	private EnemyController enemyController;
	private float endTimer = 0f;	//reset timer
	private final float resetDelay = 2.5f;
	
	private ConfigManager config;
	
	private List<Enemy> enemies;
	private List<Player> players;
	private List<Exit> exits;
	private List<PowerUp> powerUps;

	
	public GameScreen(MyGame game) {
		this.game = game;
		batch = new SpriteBatch();
	}
	
	@Override
	public void show() {

		mapLoader = new MapLoader("maps/main.tmx");
		mapLoader.loadEntities();
		
		ConfigManager config = new ConfigManager("config/game.json");

		camera = new CameraController(800,480);
		camera.fitMap(mapLoader.getMapPixelWidth(),mapLoader.getMapPixelHeight());

		mapRenderer = new OrthogonalTiledMapRenderer(mapLoader.getMap());
		collisionmanager = new CollisionManager(mapLoader.getMap(), "collision");
		gameController = new GameController(collisionmanager);
		UIAssets uiAssets = new UIAssets(mapLoader.getMap());
		ui = new GameUI(uiAssets);
		levelText = new GameUI(uiAssets); //doesn't feel great
		enemyController = new EnemyController(collisionmanager);
		enemies = mapLoader.getEnemies();
		players = mapLoader.getPlayers();
		exits = mapLoader.getExits();
		powerUps = mapLoader.getPowerUps();
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0,0,0,1);
		Gamestate state = gameController.getGamestate();
		
		if(state == Gamestate.RUNNING) {
			//update game logic
			gameController.updatePlayers(players, exits,enemies,powerUps,delta);
			
			//update enemy logic
			enemyController.update(enemies,delta);			
		}
		else {
			endTimer+=delta;
		}
		

		//render maps
		mapRenderer.setView(camera.getCamera());
		mapRenderer.render();
		
		//render camera
		batch.setProjectionMatrix(camera.getCamera().combined);
	
		/*batch.begin();
		
		//if exit reached --> level complete
		if(gameController.getLevelComplete()) {
			levelText.render(batch);
		}
		batch.end();*/

		
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
		for(PowerUp pu: powerUps) {
			pu.render(batch);
		}
		if(!players.isEmpty()) {
			ui.renderHearts(batch, players.get(0));
		}
		//game won
		if(state == Gamestate.WON) {
			levelText.renderWin(batch);
		}
		//game lost
		if(state == Gamestate.LOST) {
			levelText.renderLose(batch);
		}
		
		batch.end();
		
		//reset game 
		if(state != Gamestate.RUNNING) {
			endTimer +=delta;
			if(endTimer >=resetDelay) {
				game.setScreen(new GameScreen(game));
			}
		}
		
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
