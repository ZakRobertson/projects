package core;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Main {
    public static void main(String[] args) {


        TERenderer ter = new TERenderer();
        ter.initialize(80, 50);
        Menu menu = new Menu(ter);


        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char input = StdDraw.nextKeyTyped();
                input = Character.toLowerCase(input);
                if (input == 'n') {
                    menu.pressedN();
                    menu.inputString.append(input);
                    boolean stillEnteringSeed = true;
                    while (stillEnteringSeed) {
                        if (StdDraw.hasNextKeyTyped()) {
                            char seedInput = StdDraw.nextKeyTyped();
                            seedInput = Character.toLowerCase(seedInput);
                            if (Character.isDigit(seedInput) && menu.seed.length() < 19) {
                                menu.inputString.append(seedInput);
                                menu.inputDigit(seedInput);
                            }

                            if (seedInput == 's' && !menu.seed.isEmpty()) {
                                menu.inputString.append(seedInput);
                                stillEnteringSeed = false;
                                menu.startGame();
                            }
                            if (seedInput == 'b') {
                                menu.backspace();
                            }
                            if (seedInput == 'e') {
                                stillEnteringSeed = false;
                                menu.clearSeed();
                                menu.backToMenu();
                            }
                        }
                    }
                }
                if (input == 'l') {
                    menu.inputString.append(input);
                    In in = new In("SavedInputString.txt");
                    String nextLine = in.readLine();
                    menu.loadGame(getLoadWorld(nextLine));
                }
            }
        }

    }
    public static World getLoadWorld(String s) {

        World loadWorld = null;
        StringBuilder loadSeed = new StringBuilder();
        char[] cArray = s.toCharArray();
        char firstInput = cArray[0];

        if (firstInput == 'n') { //input is for new game, overwrite save
            AutograderBuddy.overwriteSaveFile(s);
        } else if (firstInput == 'l') { //input is to load, add to saave
            AutograderBuddy.addToSaveFile(s);
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

        return loadWorld;
    }
}


