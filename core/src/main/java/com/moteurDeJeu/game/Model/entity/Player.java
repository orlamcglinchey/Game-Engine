package com.moteurDeJeu.game.Model.entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Entity{
	private int health = 3;
	private float cooldown = 1f;
	private float timer = 0f;
	/**
	 * Represents player entity
	 * Player is controlled by input and has health 
	 * Texture and other properties are provided by Tiled map
	 * @param x starting x position in world coordinates
	 * @param y starting y position in world coordinates
	 * @param region texture region used to render player
	 */
	public Player(float x,float y,TextureRegion region) {
		super(x,y,region);
	}
	
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health=health;
	}
	
	public void increaseHealth(int increase) {
		health+=increase;
	}
	
	public void updateCooldown(float delta) {
		if(timer<cooldown) {
			timer+=delta;
		}
	}
	public void takeDamage(int amount) {
		if(timer>=cooldown) {
			health-=amount;
			timer = 0; //reset timer
			System.out.println("player health: "+ health);			
		}	
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
