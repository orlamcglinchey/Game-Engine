package com.moteurDeJeu.game.Model.entity;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Entity{
	
	private float speed = 3;
	private float dx=0;
	private float dy = 0;
	private float directiontimer=0;
	private final float directionchangeinterval=2f;
	
	/**
	 * Represents Enemy entity
	 * @param x starting x position in world coordinates
	 * @param y starting y position in world coordinates
	 * @param region texture region used for rendering
	 */
	public Enemy(float x, float y,TextureRegion region) {
		super(x,y,region);
		
	}
	public Enemy(float x, float y) {
		super(x,y);
	}
	public float getSpeed() {
		return speed;
	}
	public float getdx() {
		return dx;
	}
	public float getdy() {
		return dy;
	}
	public void setDirection(float dx,float dy) {
		this.dx=dx;
		this.dy=dy;
	}
	
	/**
	 * Updates direction automatically and randomlly
	 * @param delta
	 * @param random
	 */
	public void updateDirection(float delta, Random random) {
	    directiontimer += delta;
	    if (directiontimer >= directionchangeinterval) {
	        directiontimer = 0;
	        int dir = random.nextInt(4);
			switch(dir) {
			case 0 :
				setDirection(1, 0);//right
				break;
			 case 1 :
				 setDirection(-1, 0);  // left
				 break;
	         case 2 :
	        	 setDirection(0, 1);   // up
	        	 break;
	         case 3 :
	        	 setDirection(0, -1);  // down
	        	 break;
			}
	    }
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(region,x,y);
	}
	
	@Override
	public void setPosition(float x,float y) {
		this.x=x;
		this.y=y;
	}
	@Override 
	public Rectangle getBounds(float nextX, float nextY) {
        return new Rectangle(nextX,nextY, region.getRegionWidth(),region.getRegionHeight());
	}
	
	@Override
	public void move(int dx, int dy) {
		x+=dx;
		y+=dy;

	}
}
