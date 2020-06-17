//ID:316441534
package gui.Animation;

/**
 * The interface represents a menu in the game.
 *
 * @param <T> The return type expected by the menu/
 */
public interface Menu<T> extends Animation {
    /**
     * The function adds a selection option to the menu.
     *
     * @param key       The key to choose this selection
     * @param message   The printed message
     * @param returnVal The return value of the selection
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return The menu's status
     */
    T getStatus();
}
