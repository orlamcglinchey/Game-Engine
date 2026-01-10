package com.moteurDeJeu.game.View;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameUI {
    private BitmapFont font;

    public GameUI() {
        font = new BitmapFont();
        font.getData().setScale(3f);
    }

    public void render(SpriteBatch batch) {
    		font.draw(batch, "LEVEL COMPLETE", 200, 300);
    }

    public void dispose() {
        font.dispose();
    }
}
