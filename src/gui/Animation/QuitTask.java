//ID:316441534
package gui.Animation;

/**
 * The class activates the Quit task.
 */
public class QuitTask implements Task<Void> {
    @Override
    public Void run() {
        System.exit(0);
        return null;
    }
}
