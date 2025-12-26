package com.moteurDeJeu.game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	private MyGame game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private MapLoader mapLoader;
	private OrthogonalTiledMapRenderer mapRenderer; 
	
	public GameScreen(MyGame game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
	}
	
	@Override
	public void show() {
		mapLoader = new MapLoader("maps/level25.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(mapLoader.getMap());
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0,0,0,1);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		mapRenderer.setView(camera);
		mapRenderer.render();
		batch.begin();
		//draw things
		batch.end();
		
	}
	
	@Override 
	public void resize(int width,int height) {
		
	}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void dispose() {
}
}
