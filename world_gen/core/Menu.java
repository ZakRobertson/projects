package core;

import edu.princeton.cs.algs4.StdDraw;
import org.apache.commons.lang3.StringUtils;

import tileengine.TERenderer;


import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Menu {
    String seed;
    Font defaultFont = new Font("Monaco", Font.BOLD, 14);
    Font big = new Font("Arial", Font.BOLD, 60);
    Font small = new Font("Arial", Font.BOLD, 40);
    StringBuilder inputString;
    TERenderer ter;

    public Menu(TERenderer ter) {

        drawMenu();
        inputString = new StringBuilder();
        this.ter = ter;
    }

    public void pressedN() {
        clearSaveFile();
        inputString = new StringBuilder();
        StdDraw.clear(Color.black);
        drawSeedScreen();
        seed = "";
    }
    public void clearSeed() {
        seed = "";
    }

    public void inputDigit(char d) {
        StdDraw.clear(Color.black);
        drawSeedScreen();
        seed = seed + d;
        StdDraw.setFont(small);
        StdDraw.text(40, 20, seed);
        StdDraw.show();
    }

    public void startGame() {
        long seedLong = Long.parseLong(seed);
        StdDraw.setFont(defaultFont);
        World world = new World(seedLong);
        world.runGame(ter);
        quitGame(world);
    }

    public void loadGame(World loadWorld) {
        StdDraw.setFont(defaultFont);
        loadWorld.runGame(ter);
        quitGame(loadWorld);
    }

    public void quitGame(World w) {
        inputString.append(w.inputString);
        writeToSaveFile();
        backToMenu();
    }

    public void backspace() {
        seed = StringUtils.substring(seed, 0, seed.length() - 1);
        StdDraw.clear(Color.black);
        drawSeedScreen();
        StdDraw.setFont(small);
        StdDraw.text(40, 20, seed);
        StdDraw.show();
    }

    public void backToMenu() {
        StdDraw.clear(Color.black);
        drawMenu();
    }

    public void drawMenu() {
        StdDraw.setPenColor(255, 255, 255);
        StdDraw.setFont(big);
        StdDraw.text(40, 35, "Gamey McGameface");
        StdDraw.setFont(small);
        StdDraw.text(40, 25, "New Game: Press [N]");
        StdDraw.text(40, 20, "Load Game: Press [L]");
        StdDraw.text(40, 15, "Quit: Press [Q]");
        StdDraw.show();
    }

    public void drawSeedScreen() {
        StdDraw.setPenColor(255, 255, 255);
        Font smaller = new Font("Arial", Font.BOLD, 20);
        StdDraw.setFont(big);
        StdDraw.text(40, 35, "Enter Seed:");
        StdDraw.setFont(smaller);
        StdDraw.textLeft(65, 8, "[B] : Backspace");
        StdDraw.textLeft(65, 5, "[E] : Escape to Menu");
        StdDraw.textLeft(65, 11, "[S] : Start Game");
        StdDraw.show();
    }

    public void writeToSaveFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("SavedInputString.txt", true));
            bw.write(String.valueOf(inputString));
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearSaveFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("SavedInputString.txt", false));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
