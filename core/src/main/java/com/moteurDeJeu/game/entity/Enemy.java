package com.moteurDeJeu.game.entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

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
	
	@Override
	public void setPosition(float x,float y) {
		
	}
	@Override 
	public Rectangle getBounds(float nextX, float nextY) {
        return new Rectangle(
                nextX,
                nextY,
                region.getRegionWidth(),
                region.getRegionHeight()
        );
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
