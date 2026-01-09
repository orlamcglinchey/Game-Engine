package com.moteurDeJeu.game.entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends Entity{
	

	public Enemy(float x, float y,TextureRegion region) {
		super(x,y,region);
		
	}
	public Enemy(float x, float y) {
		super(x,y);
	}
	

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(region,x,y);
	}
	
	
	// to be changed
	@Override
	public void move(int dx, int dy) {
		x+=dx;
		y+=dy;

	}
	//@Override
	public void draw() {
	
	}
}
