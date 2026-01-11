package com.moteurDeJeu.game.View;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

public class UIAssets {
	private TextureRegion heart;
	
	public UIAssets(TiledMap map) {
		int count = 0;
		for (TiledMapTileSet set : map.getTileSets()) {
			System.out.println(count);
			count++;			
			for(TiledMapTile tile : set) {
				if(tile==null)System.out.println("null tile");
		        String name = tile.getProperties().get("name", String.class);
		        String type = tile.getProperties().get("type", String.class);
				if("heart".equals(type)) {
					heart = tile.getTextureRegion();
				}
				else {
				//	System.out.println(tile.getProperties().get("Name")+" is "+ tile.getProperties().get("type",String.class));
				}
			}
		}
	}
	
	public TextureRegion getHeart() {
		return heart;
	}


}
