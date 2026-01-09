package com.moteurDeJeu.game.entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity{
	private int speed = 3;
	public Player(float x,float y,TextureRegion region) {
		super(x,y,region);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(region, x,y);
		
	}
	
	@Override
	public void move(int dx, int dy) {
		x+=dx;
		y+=dy;
	}
}
