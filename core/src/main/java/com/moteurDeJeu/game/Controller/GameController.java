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

    public GameController(CollisionManager collisionManager) {
    	this.collisionManager=collisionManager;
    }
    
    
    public void updatePlayers(List<Player> players, List<Exit> exits) {
        for (Player player : players) {

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
                    System.out.println("player has reached exit");
                }
            }
        }
    }
}
