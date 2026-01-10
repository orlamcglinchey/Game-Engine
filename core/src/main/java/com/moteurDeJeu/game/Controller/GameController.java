package com.moteurDeJeu.game.Controller;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.moteurDeJeu.game.Model.MapManager.MapLoader.CollisionManager;
import com.moteurDeJeu.game.Model.entity.*;

// New class instead of being in GameScreen

public class GameController {
    
    private CollisionManager collisionManager;
    private int speed = 2;
    private boolean levelComplete;

    public GameController(CollisionManager collisionManager) {
    	this.collisionManager=collisionManager;
    }
    
    
    public void updatePlayers(List<Player> players, List<Exit> exits,List<Enemy> enemies, float delta) {
        for (Player player : players) {
        	
        	//update damage cooldown period
        	player.updateCooldown(delta);

            float nextX = player.getX();
            float nextY = player.getY();

            // Input
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  nextX -= speed;
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) nextX += speed;
            if (Gdx.input.isKeyPressed(Input.Keys.UP))    nextY += speed;
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))  nextY -= speed;

            // Collision with map
            if (!collisionManager.collides(player.getBounds(nextX, nextY))) {
                player.setPosition(nextX, nextY);
            }

            // Collision with exits
            for (Exit exit : exits) {
                if (player.getBounds(nextX, nextY)
                        .overlaps(exit.getBounds(exit.getX(), exit.getY()))) {
                	levelComplete = true;
                    System.out.println("player has reached exit");
                }
            }
            
            for(Enemy enemy: enemies) {
            	if(player.getBounds(player.getX(),player.getY()).overlaps(enemy.getBounds(enemy.getX(),enemy.getY()))){
            		player.takeDamage(1);
            	}
            }
        }
    }
    public boolean getLevelComplete() {
    	return levelComplete;
    }
}
