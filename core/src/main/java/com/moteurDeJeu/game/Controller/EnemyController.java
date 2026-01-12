package com.moteurDeJeu.game.Controller;

import java.util.List;
import java.util.Random;

import com.moteurDeJeu.game.Model.MapManager.MapLoader.CollisionManager;
import com.moteurDeJeu.game.Model.entity.Enemy;
/**
 * handles enemy-specific logic and updates
 */
public class EnemyController {
	
	private CollisionManager collisionManager;
	private Random random = new Random();
	
	
	public EnemyController(CollisionManager collisionManager) {
		this.collisionManager=collisionManager;
	}
	
	/**
	 * Updates all enemies
	 * @param enemies list of enemies
	 * @param delta time passed since last frame
	 */
	public void update(List<Enemy> enemies, float delta) {
        for (Enemy enemy : enemies) {
            // Let the enemy decide if it wants to change direction
            enemy.updateDirection(delta, random);

            // Compute next position based on current direction and speed
            float nextX = enemy.getX() + enemy.getdx() * enemy.getSpeed();
            float nextY = enemy.getY() + enemy.getdy() * enemy.getSpeed();

            // Check collision with map
            if (!collisionManager.collides(enemy.getBounds(nextX, nextY))) {
                enemy.setPosition(nextX, nextY);
            } else {
                // Hit a wall → pick a new random direction immediately
                pickRandomDirection(enemy);
            }
        }
    }
	
	
	
	
	private void pickRandomDirection(Enemy enemy) {
		int dir = random.nextInt(4);
		
		switch(dir) {
		case 0 :
			enemy.setDirection(1, 0);//right
			break;
		 case 1 :
			 enemy.setDirection(-1, 0);  // left
			 break;
         case 2 :
        	 enemy.setDirection(0, 1);   // up
        	 break;
         case 3 :
        	 enemy.setDirection(0, -1);  // down
        	 break;
		}
	}
}
