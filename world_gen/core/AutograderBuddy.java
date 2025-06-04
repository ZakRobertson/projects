package core;

import edu.princeton.cs.algs4.In;
import tileengine.TETile;
import tileengine.Tileset;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AutograderBuddy {

    /**
     * Simulates a game, but doesn't render anything or call any StdDraw
     * methods. Instead, returns the world that would result if the input string
     * had been typed on the keyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quit and
     * save. To "quit" in this method, save the game to a file, then just return
     * the TETile[][]. Do not call System.exit(0) in this method.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public static TETile[][] getWorldFromInput(String input) {

        World loadWorld = null;
        StringBuilder loadSeed = new StringBuilder();
        char[] cArray = input.toCharArray();
        char firstInput = cArray[0];

        if (firstInput == 'n') { //input is for new game, overwrite save
            overwriteSaveFile(input);
        } else if (firstInput == 'l') { //input is to load, add to saave
            addToSaveFile(input);
        }

        In in = new In("SavedInputString.txt");
        String savedInputs = in.readLine();
        cArray = savedInputs.toCharArray();
        List<Character> inputList = new LinkedList<>();
        for (char c : cArray) {
            inputList.add(c);
        }
        Iterator<Character> inputIterator = inputList.listIterator();

        while (inputIterator.hasNext()) {
            char currentInput = inputIterator.next();
            if (currentInput == 'n') {
                currentInput = inputIterator.next();
                while (Character.isDigit(currentInput)) {
                    loadSeed.append(currentInput);
                    currentInput = inputIterator.next();
                }
            }
            if (currentInput == 's') {
                String loadSeedString = String.valueOf(loadSeed);
                loadWorld = new World(Long.parseLong(loadSeedString));
            }
            assert loadWorld != null;
            loadWorld.loadWorld(inputIterator);
        }

        return loadWorld.getTiles();
    }

    /**
     * Used to tell the autograder which tiles are the floor/ground (including
     * any lights/items resting on the ground). Change this
     * method if you add additional tiles.
     */
    public static boolean isGroundTile(TETile t) {
        return t.character() == Tileset.FLOOR.character()
                || t.character() == Tileset.AVATAR.character()
                || t.character() == Tileset.FLOWER.character();
    }

    /**
     * Used to tell the autograder while tiles are the walls/boundaries. Change
     * this method if you add additional tiles.
     */
    public static boolean isBoundaryTile(TETile t) {
        return t.character() == Tileset.WALL.character()
                || t.character() == Tileset.LOCKED_DOOR.character()
                || t.character() == Tileset.UNLOCKED_DOOR.character();
    }


    public static void addToSaveFile(String inputString) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("SavedInputString.txt", true));
            bw.write(String.valueOf(inputString));
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void overwriteSaveFile(String inputString) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("SavedInputString.txt", false));
            bw.write(String.valueOf(inputString));
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
