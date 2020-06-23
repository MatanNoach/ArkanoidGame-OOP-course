//ID:316441534
package gui.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.backgrounds.MenuBackground;
import gui.gameobjects.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents the menu of the game.
 *
 * @param <T> The menu type
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<String> keys;
    private List<String> messages;
    private List<T> values;
    private Sprite background;
    private boolean stop;
    private KeyboardSensor ks;
    private int chosenStatus;

    /**
     * Default Constructor.
     */
    public MenuAnimation(KeyboardSensor ks) {
        this.keys = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.values = new ArrayList<>();
        this.stop = false;
        this.ks = ks;
        this.background = new MenuBackground();
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.values.add(returnVal);
    }

    @Override
    public T getStatus() {
        this.stop = false;
        return this.values.get(chosenStatus);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.background != null) {
            this.background.drawOn(d);
        }
        this.printOptions(d);
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
     * @param d The drawSurface to prints the text on
     */
    public void printOptions(DrawSurface d) {
        int startingX = 150;
        int startingY = 100;
        int fontSize = 32;
        for (int i = 0; i < keys.size(); i++) {
            d.drawText(startingX, startingY + fontSize * i,
                    "For " + this.messages.get(i) + ", press " + this.keys.get(i), fontSize);
        }
    }
}
