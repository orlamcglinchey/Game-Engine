package com.moteurDeJeu.game.Model.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
/**
 * Represents a collectible power-up providing a health benefit
 */
public class PowerUp extends Entity{
	private boolean collected = false;
	
	public PowerUp(float x, float y, TextureRegion region) {
		super(x,y,region);
	}
	@Override
	public void render(SpriteBatch batch) {
		if(!collected) { //disappears when collected
			batch.draw(region, x, y);
		}
	}
	
	@Override
	public void setPosition(float x, float y) {
		this.x=x;
		this.y=y;
	}
	@Override
	public Rectangle getBounds(float nextX, float nextY){
		return new Rectangle(nextX,nextY,region.getRegionWidth(),region.getRegionHeight());
	}
	
	public void collect() {
		collected = true;
	}
	public boolean isCollected() {
		return collected;
	}
	
	
	@Override
	public void move(int dx, int dy) {
		
	}
}
