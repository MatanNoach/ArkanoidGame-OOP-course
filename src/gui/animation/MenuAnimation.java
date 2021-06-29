//ID:316441534
package gui.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.backgrounds.Background;
import gui.backgrounds.BackgroundFactory;
import gui.backgrounds.MenuBackground;
import gui.gamedata.GameSettings;
import gui.gameobjects.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represents the menu of the game.
 * Variables:
 * keys - The list of keys
 * messages - The list of messages for each key
 * tasks - The list of task objects
 * background - The menu's background
 * stop - true if the animation needs to stop and false otherwise
 * ks - The keyboard sensor
 * chosenStatus - The chosen task's index
 *
 * @param <T> The menu type
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<String> keys;
    private List<String> messages;
    private List<T> tasks;
    private Background background;
    private boolean stop;
    private KeyboardSensor ks;
    private int chosenStatus;

    /**
     * Constructor.
     *
     * @param ks The keyboardSensor
     */
    public MenuAnimation(KeyboardSensor ks) {
        this.keys = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.stop = false;
        this.ks = ks;
        this.background = BackgroundFactory.createBackgroundForLevel("image(spaceMenu.gif)");
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.tasks.add(returnVal);
    }

    @Override
    public T getStatus() {
        this.stop = false;
        return this.tasks.get(chosenStatus);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.background != null) {
            this.background.drawOn(d);
        }
        //print the user's options
        this.printOptions(d);
        //checks for every option if it was pressed. if it did, stop the screen.
        for (int i = 0; i < keys.size(); i++) {
            if (ks.isPressed(keys.get(i))) {
                this.chosenStatus = i;
                this.stop = true;
                break;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * The function prints the instructions of how to get to each menu option.
     *
     * @param d The drawSurface to prints the text on
     */
    public void printOptions(DrawSurface d) {
        background.drawOn(d);
        d.setColor(Color.cyan);
        int headStartX = 250;
        int startingX = 150;
        int startingY = 190;
        int fontSize = 32;
        //prints the headline
        d.drawText(headStartX, GameSettings.HEADLINE_Y, "Arkanoid", fontSize * 2);
        d.drawText(headStartX, GameSettings.HEADLINE_Y, "_______", fontSize * 2);
        //prints the options
        for (int i = 0; i < keys.size(); i++) {
            d.drawText(startingX, startingY + fontSize * i,
                    "For " + this.messages.get(i) + ", press " + this.keys.get(i), fontSize);
        }
    }
}
