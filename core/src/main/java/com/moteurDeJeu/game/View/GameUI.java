package com.moteurDeJeu.game.View;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.moteurDeJeu.game.Model.entity.Player;

/**
 * Handles all in-game UI rendering 
 * Includes health display and win/loss messages 
 */
public class GameUI {
    private BitmapFont font;
    private TextureRegion heart;

    public GameUI(UIAssets assets) {
        font = new BitmapFont();
        font.getData().setScale(3f);
        
        this.heart=assets.getHeart();        
    }

    public void renderWin(SpriteBatch batch) {
    		font.draw(batch, "LEVEL COMPLETE", 250, 400);
    }
    public void renderLose(SpriteBatch batch) {
		font.draw(batch, "YOU LOST", 250, 400);

    }
    /**
     * Render's the player's health as heart icons 
     * @param batch
     * @param player
     */
    public void renderHearts(SpriteBatch batch, Player player) {
    	int health = player.getHealth();
    	
    	//draw each heart at a different position
    	int startX=20;
    	int startY=600;
    	int spacing = heart.getRegionWidth()+4;   	
    	
    	for(int i=0;i<health;i++) {
    		batch.draw(heart, startX+i*spacing,startY); 		
    	}
    }

    public void dispose() {
        font.dispose();
    }
}
