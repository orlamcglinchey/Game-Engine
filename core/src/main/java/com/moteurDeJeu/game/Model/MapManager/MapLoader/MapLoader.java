package com.moteurDeJeu.game.Model.MapManager.MapLoader;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;

//import jdk.javadoc.internal.doclets.formats.html.markup.Entity;

import java.util.ArrayList;
import java.util.List;

import com.moteurDeJeu.game.Model.entity.Enemy;
import com.moteurDeJeu.game.Model.entity.Exit;
import com.moteurDeJeu.game.Model.entity.Player;

public class MapLoader {
	private TiledMap map;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private List<Exit> exits = new ArrayList<>();

	
	
	public MapLoader(String mapPath) {		
		map = new TmxMapLoader().load(mapPath); //load .tmx fileS
	}
	
	public TiledMap getMap() {
		return map;
		
	}
	
	public int getMapPixelWidth() {
	    return getWidth() * getTileWidth();
	}

	public int getMapPixelHeight() {
	    return getHeight() * getTileHeight();
	}

	
	public int getWidth() {
		MapProperties p = map.getProperties();
		return p.get("width",Integer.class);
		
	}
	
	public int getHeight() {
		MapProperties p = map.getProperties();
		return p.get("height",Integer.class);
	}
	
 	//get tilewidth/height for camera
 	public Integer getTileWidth() {
		MapProperties p = map.getProperties();
 		return p.get("tilewidth",Integer.class);
 	}
 	
 	public Integer getTileHeight() {
		MapProperties p = map.getProperties();
 		return p.get("tileheight",Integer.class);
 	}
 	
 	
	public void loadEntities() {
		MapLayer entitylayer = map.getLayers().get("entities");
			
	    for (MapObject object : entitylayer.getObjects()) {
	    	
	        // Only accept tile objects
	        if (!(object instanceof TiledMapTileMapObject)) {
	            System.out.print("skipping non-tile object: "+ object.getName());
	        	continue;
	        }

	        TiledMapTileMapObject tileObject = (TiledMapTileMapObject) object;
	        TextureRegion region = tileObject.getTile().getTextureRegion();
	        if(region == null) {
	        	System.out.println("Skipping object with null texture: "+ object.getName());
	        	continue;
	        }
	    	
	        /*
	         * * Get type
	         */
	    	MapProperties props = object.getProperties();
	    	
	     	String type = object.getProperties().get("type", String.class);
	     	
	     	
	    	if (type == null && object instanceof TiledMapTileMapObject) {
	    	    type = ((TiledMapTileMapObject) object)
	    	            .getTile()
	    	            .getProperties()
	    	            .get("type", String.class);
	    	}

	    	/*
	    	 * Get x and y coordinates
	    	 */
	        float x = tileObject.getX();
	        float y = tileObject.getY()- region.getRegionHeight(); //fix tiled coordinates 

	        
	        if("player".equals(type)) {
	        	Player player = new Player(x,y,region);
	        	
	        	//read health if exists 
	        	Integer health = props.get("health",Integer.class);
	        	if(health!=null) player.setHealth(health);
	        	
	        	players.add(player);
                System.out.println("Player spawned at X: " + x + " Y: " + y+" health: "+ health);

	        }
	        else if("enemy".equals(type)){
	        	Enemy enemy = new Enemy(x,y,region);
		        enemies.add(enemy);
                System.out.println("Enemy spawned at X: " + x + " Y: " + y);
	        }
	        else {
	        	Exit exit = new Exit(100,100,region);
	        	exits.add(exit);

	        }
	        //debug 
	        System.out.println("Name: "+ tileObject.getName()+" Class: "+type +" at X: " + tileObject.getX() +" Y: "+ tileObject.getY());
			
		}
	    
			
	}

    public List<Enemy> getEnemies() {
        return enemies;
    }
    public List<Player> getPlayers(){
    	return players;
    }
    public List<Exit> getExits(){
    	return exits;
    }
}
