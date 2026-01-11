package com.moteurDeJeu.game.View;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
/**
 * this class loads in UI Assets 
 */
public class UIAssets {
	private TextureRegion heart;
	
	public UIAssets(TiledMap map) {
		for (TiledMapTileSet set : map.getTileSets()) {
			for(TiledMapTile tile : set) {
				if(tile==null)System.out.println("null tile");
		        String type = tile.getProperties().get("type", String.class);
				if("heart".equals(type)) {
					heart = tile.getTextureRegion();
				}
				else {
				}
			}
		}
	}
	
	public TextureRegion getHeart() {
		return heart;
	}


}
