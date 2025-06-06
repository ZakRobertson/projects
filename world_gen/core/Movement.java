package core;

import tileengine.TETile;
import tileengine.Tileset;

public class Movement {

    World world;
    TETile[][] tiles;
    Avatar avatar;

    public Movement(World world, Avatar avatar) {
        this.world = world;
        this.tiles = world.getTiles();
        this.avatar = avatar;
    }

    public void tryMove(int xChange, int yChange) {
        int currentX = avatar.xPos;
        int currentY = avatar.yPos;
        int updatedX = currentX + xChange;
        int updatedY = currentY + yChange;
        if (tiles[updatedX][updatedY] == Tileset.FLOOR) {
            tiles[currentX][currentY] = Tileset.FLOOR;
            tiles[updatedX][updatedY] = Tileset.AVATAR;
            avatar.xPos = updatedX;
            avatar.yPos = updatedY;
        }
        if (tiles[updatedX][updatedY] == Tileset.COIN) {
            tiles[currentX][currentY] = Tileset.FLOOR;
            tiles[updatedX][updatedY] = Tileset.AVATAR;
            avatar.xPos = updatedX;
            avatar.yPos = updatedY;
            world.score += 1;
        }
    }

}
