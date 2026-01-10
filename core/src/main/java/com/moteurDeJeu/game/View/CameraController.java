package com.moteurDeJeu.game.View;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class CameraController{
	private OrthographicCamera camera;
	
	public CameraController (float viewWidth, float viewHeight){
		camera = new OrthographicCamera();
		camera.setToOrtho(false,viewWidth,viewHeight);
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public void centre(float x, float y) {
		camera.position.set(x,y,0);
		camera.update();
	}
	
	public void fitMap(float mapWidth, float mapHeight) {
		float zoomX = mapWidth/camera.viewportWidth;
		float zoomY = mapHeight/camera.viewportHeight;
		camera.zoom = Math.max(zoomX, zoomY);
		
		camera.position.set(mapWidth/2f,mapHeight/2f,0);
		camera.update();
	}
	
}