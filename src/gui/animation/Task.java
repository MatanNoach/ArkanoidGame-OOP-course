//ID:316441534
package gui.animation;

/**
 * The interface represents a task for the menu.
 *
 * @param <T> The type of the menu
 */
public interface Task<T> {
    /**
     * Runs a certain function for the menu option.
     * @return If needed, returns an object by task type.
     */
    T run();
}
