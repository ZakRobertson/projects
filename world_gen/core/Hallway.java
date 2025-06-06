package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.Random;

import static utils.RandomUtils.uniform;

public class Hallway {
    Room start;
    Room end;
    TETile[][] world;
    Random r;
    boolean vertical;
    boolean pBelowQ;
    boolean pLeftOfQ;

    public Hallway(Partition p, Partition q, TETile[][] world, Random r) {
        this.world = world;
        this.r = r;
        vertical = (p.xLocation == q.xLocation);
        pBelowQ = (p.yLocation < q.yLocation);
        pLeftOfQ = (p.xLocation < q.xLocation);
        Room pRoom = p.findRoom();
        Room qRoom = q.findRoom();


        if (vertical) {
            if (!pBelowQ) {
                start = qRoom;
                end = pRoom;
            } else {
                start = pRoom;
                end = qRoom;
            }
            createVerticalHallways();
        } else {
            if (!pLeftOfQ) {
                start = qRoom;
                end = pRoom;
            } else {
                start = pRoom;
                end = qRoom;
            }
            createHorizontalHallways();
        }
    }

    public void createVerticalHallways() {

        int maxLeftVal = Math.max(start.xPos, end.xPos);
        int minRightVal = Math.min(start.xPos + start.width, end.xPos + end.width);
        if (minRightVal - maxLeftVal > 1) {
            int x = uniform(r, maxLeftVal + 1, minRightVal - 1);

            for (int y = start.yPos + start.height; y <= end.yPos; y++) {
                world[x][y] = Tileset.FLOOR;
                if (world[x - 1][y] != Tileset.FLOOR && world[x - 1][y] != Tileset.COIN) {
                    world[x - 1][y] = Tileset.WALL;
                }
                if (world[x + 1][y] != Tileset.FLOOR && world[x + 1][y] != Tileset.COIN) {
                    world[x + 1][y] = Tileset.WALL;
                }
            }
        } else {
            int startingX = uniform(r, start.xPos + 1, start.xPos + start.width - 1);
            int endingX = uniform(r, end.xPos + 1, end.xPos + end.width - 1);
            int midpointY = uniform(r, start.yPos + start.height + 1, end.yPos);

            for (int y = start.yPos + start.height; y <= midpointY; y++) {
                world[startingX][y] = Tileset.FLOOR;
                if (world[startingX - 1][y] != Tileset.FLOOR && world[startingX - 1][y] != Tileset.COIN) {
                    world[startingX - 1][y] = Tileset.WALL;
                }
                if (world[startingX + 1][y] != Tileset.FLOOR && world[startingX + 1][y] != Tileset.COIN) {
                    world[startingX + 1][y] = Tileset.WALL;
                }
            }
            for (int y = midpointY; y <= end.yPos; y++) {
                world[endingX][y] = Tileset.FLOOR;
                if (world[endingX - 1][y] != Tileset.FLOOR && world[endingX - 1][y] != Tileset.COIN) {
                    world[endingX - 1][y] = Tileset.WALL;
                }
                if (world[endingX + 1][y] != Tileset.FLOOR && world[endingX + 1][y] != Tileset.COIN) {
                    world[endingX + 1][y] = Tileset.WALL;
                }
            }
            if (startingX < endingX) {
                for (int i = startingX; i <= endingX; i++) {
                    world[i][midpointY] = Tileset.FLOOR;
                    if (world[i][midpointY + 1] != Tileset.FLOOR && world[i][midpointY + 1] != Tileset.COIN) {
                        world[i][midpointY + 1] = Tileset.WALL;
                    }
                    if (world[i][midpointY - 1] != Tileset.FLOOR && world[i][midpointY - 1] != Tileset.COIN) {
                        world[i][midpointY - 1] = Tileset.WALL;
                    }
                }
            } else {
                for (int i = endingX; i <= startingX; i++) {
                    world[i][midpointY] = Tileset.FLOOR;
                    if (world[i][midpointY + 1] != Tileset.FLOOR && world[i][midpointY + 1] != Tileset.COIN) {
                        world[i][midpointY + 1] = Tileset.WALL;
                    }
                    if (world[i][midpointY - 1] != Tileset.FLOOR && world[i][midpointY - 1] != Tileset.COIN) {
                        world[i][midpointY - 1] = Tileset.WALL;
                    }
                }
            }
        }
    }

    public void createHorizontalHallways() {
        int maxBottomHeight = Math.max(start.yPos, end.yPos);
        int minTopHeight = Math.min(start.yPos + start.height, end.yPos + end.height);
        if (minTopHeight - maxBottomHeight > 1) {
            int y = uniform(r, maxBottomHeight + 1, minTopHeight - 1);
            for (int x = start.xPos + start.width; x <= end.xPos; x++) {
                world[x][y] = Tileset.FLOOR;
                if (world[x][y - 1] != Tileset.FLOOR && world[x][y - 1] != Tileset.COIN) {
                    world[x][y - 1] = Tileset.WALL;
                }
                if (world[x][y + 1] != Tileset.FLOOR && world[x][y + 1] != Tileset.COIN) {
                    world[x][y + 1] = Tileset.WALL;
                }
            }
        } else {
            int startingY = uniform(r, start.yPos + 1, start.yPos + start.height - 1);
            int endingY = uniform(r, end.yPos + 1, end.yPos + end.height - 1);
            int midpointX = uniform(r, start.xPos + start.width + 1, end.xPos);
            for (int x = start.xPos + start.width; x <= midpointX; x++) {
                world[x][startingY] = Tileset.FLOOR;
                if (world[x][startingY - 1] != Tileset.FLOOR && world[x][startingY - 1] != Tileset.COIN) {
                    world[x][startingY - 1] = Tileset.WALL;
                }
                if (world[x][startingY + 1] != Tileset.FLOOR && world[x][startingY + 1] != Tileset.COIN) {
                    world[x][startingY + 1] = Tileset.WALL;
                }
            }
            for (int x = midpointX; x <= end.xPos; x++) {
                world[x][endingY] = Tileset.FLOOR;
                if (world[x][endingY - 1] != Tileset.FLOOR && world[x][endingY - 1] != Tileset.COIN) {
                    world[x][endingY - 1] = Tileset.WALL;
                }
                if (world[x][endingY + 1] != Tileset.FLOOR && world[x][endingY + 1] != Tileset.FLOOR) {
                    world[x][endingY + 1] = Tileset.WALL;
                }
            }
            if (startingY < endingY) {
                for (int i = startingY; i <= endingY; i++) {
                    world[midpointX][i] = Tileset.FLOOR;
                    if (world[midpointX + 1][i] != Tileset.FLOOR && world[midpointX + 1][i] != Tileset.COIN) {
                        world[midpointX + 1][i] = Tileset.WALL;
                    }
                    if (world[midpointX - 1][i] != Tileset.FLOOR && world[midpointX - 1][i] != Tileset.COIN) {
                        world[midpointX - 1][i] = Tileset.WALL;
                    }
                }
            } else {
                for (int i = endingY; i <= startingY; i++) {
                    world[midpointX][i] = Tileset.FLOOR;
                    if (world[midpointX + 1][i] != Tileset.FLOOR && world[midpointX + 1][i] != Tileset.COIN) {
                        world[midpointX + 1][i] = Tileset.WALL;
                    }
                    if (world[midpointX - 1][i] != Tileset.FLOOR && world[midpointX - 1][i] != Tileset.COIN) {
                        world[midpointX - 1][i] = Tileset.WALL;
                    }
                }
            }
        }
    }
}
