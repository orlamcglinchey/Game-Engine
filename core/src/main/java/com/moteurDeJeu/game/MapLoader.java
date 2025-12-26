package com.moteurDeJeu.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class MapLoader {
	private TiledMap map;
	
	
	public MapLoader(String mapPath) {
		new TmxMapLoader();
		
		map = new TmxMapLoader().load(mapPath); //load .tmx fileS
		
	}
	
	public TiledMap getMap() {
		return map;
		
	}
	
	public int getWidth() {
		MapProperties p = map.getProperties();
		return p.get("width",Integer.class);
		
	}
	
	public int getHeight() {
		MapProperties p = map.getProperties();
		return p.get("height",Integer.class);
	}
	
}
