package com.moteurDeJeu.game.Model.MapManager.MapLoader;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {

    private final List<Rectangle> collisionRects = new ArrayList<>();

    public CollisionManager(TiledMap map, String collisionLayerName) {
        MapLayer layer = map.getLayers().get(collisionLayerName);

        if (layer == null) {
            throw new RuntimeException("Collision layer not found: " + collisionLayerName);
        }

        for (RectangleMapObject obj : layer.getObjects().getByType(RectangleMapObject.class)) {
            collisionRects.add(obj.getRectangle());
        }
    }

    public boolean collides(Rectangle bounds) {
        for (Rectangle rect : collisionRects) {
            if (bounds.overlaps(rect)) {
                return true;
            }
        }
        return false;
    }
}
