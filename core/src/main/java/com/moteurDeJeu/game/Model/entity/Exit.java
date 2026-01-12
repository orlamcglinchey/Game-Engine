package com.moteurDeJeu.game.Model.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Represents the game exit
 */
public class Exit extends Entity{
	public Exit(float x, float y, TextureRegion region) {
		super(x,y,region);
	}
	
	@Override
	public void move(int x, int y) {
		
	}
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(region,x,y);		
	}
	
	@Override
	public void setPosition(float x, float y) {
		this.x=x;
		this.y=y;
	}
	@Override
    public Rectangle getBounds(float x, float y) {
        return new Rectangle(x,y,region.getRegionWidth(),region.getRegionHeight());
    }
}
