package com.moteurDeJeu.game.Controller;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.moteurDeJeu.game.Model.MapManager.MapLoader.CollisionManager;
import com.moteurDeJeu.game.Model.entity.*;


public class GameController {
    
    private CollisionManager collisionManager;
    private int speed = 2;
    private boolean levelComplete;
    private Gamestate gameState=Gamestate.RUNNING;
    
    public GameController(CollisionManager collisionManager) {
    	this.collisionManager=collisionManager;
    }
    
    //handle winning/losing
    public enum Gamestate{
    	RUNNING,
    	WON,
    	LOST
    }
    public Gamestate getGamestate() {
    	return gameState;
    }
    
    public void updatePlayers(List<Player> players, List<Exit> exits,List<Enemy> enemies, List<PowerUp> powerUps, float delta) {
       if(gameState != Gamestate.RUNNING) return;
    	
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

            //health is zero ---> LOST
            if(player.getHealth()<=0) {
            	gameState = Gamestate.LOST;
            }
            // Collision with map
            if (!collisionManager.collides(player.getBounds(nextX, nextY))) {
                player.setPosition(nextX, nextY);
            }

            // Collision with exits
            for (Exit exit : exits) {
                if (player.getBounds(nextX, nextY).overlaps(exit.getBounds(exit.getX(), exit.getY()))) {
                	gameState = Gamestate.WON;
                	levelComplete = true;
                    System.out.println("player has reached exit");
                }
            }
            // collision with enemies
            for(Enemy enemy: enemies) {
            	if(player.getBounds(player.getX(),player.getY()).overlaps(enemy.getBounds(enemy.getX(),enemy.getY()))){
            		player.takeDamage(1);
            	}
            }
            
            //collision with powerups
            for(PowerUp pu : powerUps) {
            	if(!pu.isCollected()&& player.getBounds(nextX, nextY).overlaps(pu.getBounds(pu.getX(), pu.getY()))) {
                	pu.collect();
                	player.increaseHealth(1);
                	System.out.println("Powerup collected: "+ player.getHealth());
            	}
            	
            }
        }
    }
    public boolean getLevelComplete() {
    	return levelComplete;
    }
}
