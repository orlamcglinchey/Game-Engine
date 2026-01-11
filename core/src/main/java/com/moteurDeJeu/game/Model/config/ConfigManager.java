package com.moteurDeJeu.game.Model.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class ConfigManager{
	private JsonValue root;
	private FileHandle file;
	
	public ConfigManager(String path) {
		file = Gdx.files.internal(path);
		
		if(!file.exists()) {
			System.out.println("Config file not found: "+path);
			return;
		}
		root = new JsonReader().parse(Gdx.files.internal(path));
	}
	
	public float getPlayerSpeed() {
		return root.get("player").getInt("speed");
		
	}
	public float getEnemySpeed() {
		return root.get("enemy").getFloat("speed");
	}
}
