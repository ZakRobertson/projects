package core;

import tileengine.TETile;


import java.util.Random;

import static utils.RandomUtils.uniform;

public class Partition {
    int height;
    int width;
    int size;
    int xLocation;
    int yLocation;
    Partition child1;
    Partition child2;
    Room room;
    Random r;
    TETile[][] world;



    public Partition(int x, int y, int h, int w, Random r, TETile[][] world) {
        this.xLocation = x;
        this.yLocation = y;
        this.height = h;
        this.width = w;
        this.size = h * w;
        this.r = r;
        this.world = world;
    }

    public void splitPartition() {
        //chooses to split partition length- or width-wise randomly
        if (this.height < this.width) { //split vertically
            int splitWidth = uniform(r, 6, width - 5);
            this.child1 = new Partition(xLocation, yLocation, this.height, splitWidth, r, world);
            this.child2 = new Partition(xLocation + splitWidth, yLocation, this.height, width - splitWidth, r, world);
        } else {
            int splitHeight = uniform(r, 6, this.height - 5);
            this.child1 = new Partition(xLocation, yLocation, splitHeight, this.width, r, world);
            this.child2 = new Partition(xLocation, yLocation + splitHeight,
                            this.height - splitHeight, this.width, r, world);
        }
    }

    //adds a room to the partition
    public void addRoom() {
        room = new Room(this, r, world);
    }

    public boolean hasRoom() {
        return this.room != null;
    }

    public Room findRoom() {
        if (this.hasRoom()) {
            return room;
        }
        if (uniform(r, 1) < 0.5) {
            return this.child1.findRoom();
        } else {
            return this.child2.findRoom();
        }
    }
}

