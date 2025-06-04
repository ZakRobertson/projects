package core;

import tileengine.TETile;
import tileengine.Tileset;

import static utils.RandomUtils.uniform;
import java.util.Random;

public class Room {
    int width;
    int height;
    int size;
    Partition partition;
    int xPos;
    int yPos;
    Random r;
    TETile[][] world;

    public Room(Partition p, Random r, TETile[][] w) {
        this.partition = p;
        this.r = r;
        this.world = w;

        //randomizing size of room inside partition
        this.height = uniform(r, 4, p.height - 2);
        this.width = uniform(r, 4, p.width - 2);
        this.size = height * width;

        //randomizing location inside the partition
        this.xPos = uniform(r, p.width - this.width) + p.xLocation;
        this.yPos = uniform(r, p.height - this.height) + p.yLocation;

        //filling the whole floor of the room with floor tiles
        for (int x = xPos; x < xPos + this.width; x++) {
            for (int y = yPos; y < yPos + this.height; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }
        //replacing outer edge with wall tiles
        for (int i = xPos; i <= xPos + this.width; i++) {
            world[i][yPos] = Tileset.WALL;
            world[i][yPos + this.height] = Tileset.WALL;
        }
        for (int i = yPos; i <= yPos + this.height; i++) {
            world[xPos][i] = Tileset.WALL;
            world[xPos + this.width][i] = Tileset.WALL;
        }

        if (uniform(r, 100) < 60) {
            this.addCoin();
        }
    }

    public void addCoin() {
        int x = uniform(r, xPos + 1, xPos + width - 1);
        int y = uniform(r, yPos + 1, yPos + height - 1);
        world[x][y] = Tileset.COIN;
    }
}
