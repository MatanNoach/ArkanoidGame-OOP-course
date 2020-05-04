//ID:316441534

/**
 * The class holds the game settings
 * Variables:
 * WINDOW_HEIGHT - The window's height.
 * WINDOW_WIDTH - The window's width.
 * BLOCK_EDGE_SIZE - The size of the block's edges
 * speed - The ball's and paddle's speed.
 */
public class GameSettings {
    static final int WINDOW_HEIGHT = 600;
    static final int WINDOW_WIDTH = 800;
    static final int BLOCK_EDGE_SIZE = 10;
    private double speed;

    /**
     * Constructor. set default speed as 5.
     */
    public GameSettings() {
        this.speed = 5;
    }

    /**
     * @return The ball's and paddle's speed
     */
    public double getSpeed() {
        return speed;
    }
}
