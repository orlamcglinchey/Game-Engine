package com.moteurDeJeu.game.entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public abstract class Entity {
	
	protected float x,y;
	protected TextureRegion region;
	
	public Entity (float x, float y, TextureRegion region) {
		this.x=x;
		this.y=y; 
		this.region = region;
	}
	
	public Entity(float x, float y) {
		this.y=y;
		this.x=x;
	}
	

	public void setX(float x) {
		this.x=x;
	}
	public float getX() {
		return x;
	}
	
	public void setY(float y) {
		this.y=y;
	}
	public float getY() {
		return y;
	}

	public void setRegion(TextureRegion region) {
		this.region = region;
	}
	public TextureRegion getRegion() {
		return this.region;
	}
	
	public abstract void move(int dx, int dy);
	public abstract void setPosition(float x, float y);
	public abstract Rectangle getBounds(float nextX,float nextY);
	
	public void render(SpriteBatch batch) {

		
	}

	
	
	//ajouter des methodes pour the extended classes

}