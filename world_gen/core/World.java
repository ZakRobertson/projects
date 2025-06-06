package core;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.*;

import static utils.RandomUtils.uniform;

public class World {
    TETile[][] world;
    int worldSize;
    int worldWidth;
    int worldHeight;
    Random r;
    int numberOfRooms;
    Partition root;
    private final int maxPartitionSize;
    Avatar avatar1;
    Avatar avatar2;
    Movement movement1;
    Movement movement2;
    private long prevFrameTimestamp;
    int score;
    boolean readyToQuit;
    String inputString;
    boolean isGameOver;
    boolean lightsOn;
    TETile[][] lightsOff;


    public World(long seed) {
        this.r = new Random(seed);
        worldWidth = 80;
        this.worldHeight = 48;
        this.worldSize = worldWidth * worldHeight;

        this.numberOfRooms = 0;
        this.world = new TETile[worldWidth][worldHeight];
        this.maxPartitionSize = uniform(r, 200, 400);
        score = 0;

        for (int x = 0; x < worldWidth; x++) {
            for (int y = 0; y < worldHeight; y++) {
                world[x][y] = Tileset.GRASS;
            }
        }

        root = new Partition(0, 0, worldHeight, worldWidth, r, world);
        createPartitions(root);
        createHallways(root);
        avatar1 = createAvatar();
        avatar2 = createAvatar();
        this.movement1 = new Movement(this, avatar1);
        this.movement2 = new Movement(this, avatar2);
        inputString = "";
        isGameOver = false;
        lightsOff = new TETile[worldWidth][worldHeight];
        lightsOn = true;
    }

    public TETile[][] getTiles() {
        return world;
    }

    private void createPartitions(Partition p) {
        if (p.size > maxPartitionSize) {
            p.splitPartition();
            createPartitions(p.child1);
            createPartitions(p.child2);
        } else {
            p.addRoom();
        }
    }

    private void createHallways(Partition p) {
        if (p.child1 == null) { //binary so only need to check if one child is null
            return;
        }
        createHallways(p.child1); //DFS
        createHallways(p.child2);
        new Hallway(p.child1, p.child2, world, r);
    }



    public Avatar createAvatar() {
        Room start = root.findRoom();
        int startingX = uniform(r, start.xPos + 1, start.xPos + start.width - 1);
        int startingY = uniform(r, start.yPos + 1, start.yPos + start.height - 1);

        world[startingX][startingY] = Tileset.AVATAR;
        return new Avatar(startingX, startingY);
    }

    public void updateWorld() {
        if (StdDraw.hasNextKeyTyped()) {
            char currInput = StdDraw.nextKeyTyped();
            currInput = Character.toLowerCase(currInput);
            inputString += currInput;
            if (readyToQuit) {
                if (currInput == 'q') {
                    isGameOver = true;
                } else {
                    readyToQuit = false;
                }
            }
            if (currInput == 'a') {
                movement1.tryMove(-1, 0);
            } else if (currInput == 's') {
                movement1.tryMove(0, -1);
            } else if (currInput == 'd') {
                movement1.tryMove(1, 0);
            } else if (currInput == 'w') {
                movement1.tryMove(0, 1);
            } else if (currInput == ':') {
                readyToQuit = true;
            } else if (currInput == 'j') {
                movement2.tryMove(-1, 0);
            } else if (currInput == 'k') {
                movement2.tryMove(0, -1);
            } else if (currInput == 'l') {
                movement2.tryMove(1, 0);
            } else if (currInput == 'i') {
                movement2.tryMove(0, 1);
            } else if (currInput == 'f') {
                lightsOn = !lightsOn;
            }
        }
    }

    public void runGame(TERenderer ter) {
        resetFrameTimer();
        while (!isGameOver) {
            renderScore();
            if (shouldRenderNewFrame()) {
                updateWorld();
                if (!lightsOn) {
                    turnOffLights();
                    ter.renderFrame(lightsOff);
                } else {
                    ter.renderFrame(world);
                }
            }
        }
    }

    private long frameDeltaTime() {
        return System.currentTimeMillis() - prevFrameTimestamp;
    }

    private void resetFrameTimer() {
        prevFrameTimestamp = System.currentTimeMillis();
    }

    public boolean shouldRenderNewFrame() {
        if (frameDeltaTime() > 16) {
            resetFrameTimer();
            return true;
        }
        return false;
    }
    private void renderScore() {
        StdDraw.setPenColor(255, 255, 255);
        StdDraw.text(worldWidth - 3,  worldHeight + 1, "Score: " + score);

        double mouseX = StdDraw.mouseX();
        double mouseY = StdDraw.mouseY();
        String currentTile = null;
        if (mouseX < worldWidth && mouseY < worldHeight) {
            currentTile = world[(int) mouseX][(int) mouseY].description();
        }
        StdDraw.text(5,  worldHeight + 1, "Current Tile: " + currentTile);
        StdDraw.show();
        StdDraw.pause(20);
    }


    public void loadWorld(Iterator<Character> iterator) {
        while (iterator.hasNext()) {
            char currChar = iterator.next();
            if (currChar == 'a') {
                movement1.tryMove(-1, 0);
            } else if (currChar == 's') {
                movement1.tryMove(0, -1);
            } else if (currChar == 'd') {
                movement1.tryMove(1, 0);
            } else if (currChar == 'w') {
                movement1.tryMove(0, 1);
            } else if (currChar == 'j') {
                movement2.tryMove(-1, 0);
            } else if (currChar == 'k') {
                movement2.tryMove(0, -1);
            } else if (currChar == 'l') {
                movement2.tryMove(1, 0);
            } else if (currChar == 'i') {
                movement2.tryMove(0, 1);
            } else if (currChar == 'f') {
                lightsOn = !lightsOn;
            }
        }
    }

    public void turnOffLights() {
        for (int x = 0; x < worldWidth; x++) {
            for (int y = 0; y < worldHeight; y++) {
                    lightsOff[x][y] = Tileset.NOTHING;
                    if (Math.abs(avatar1.xPos - x) < 5 && Math.abs(avatar1.yPos - y) < 5) {
                        lightsOff[x][y] = world[x][y];
                    } else if (Math.abs(avatar2.xPos - x) < 5 && Math.abs(avatar2.yPos - y) < 5) {
                        lightsOff[x][y] = world[x][y];
                    }
            }
        }
    }
}

