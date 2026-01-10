package com.moteurDeJeu.game.Model.entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Entity{
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
	
	@Override
	public void setPosition(float x, float y ) {
		this.x=x;
		this.y=y;
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
	public float getWidth() {
	    return region.getRegionWidth();
	}

	public float getHeight() {
	    return region.getRegionHeight();
	}

}
