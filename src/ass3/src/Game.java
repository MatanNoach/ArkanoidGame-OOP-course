//ID:316441534

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The class represents the game.
 * Variables:
 * sprites - A collection of sprites in the game
 * environment - A collection of collidable objects in the game
 * gameSettings - The game's settings
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GameSettings gameSettings;

    /**
     * Constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.gameSettings = new GameSettings();
        this.environment = new GameEnvironment(this.gameSettings);
    }

    /**
     * Adds a collidable to the collection.
     *
     * @param c The collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s The sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * The function initializes the game.
     * <p>
     * The function creates the ball, the blocks on the screen and the block walls.
     * </p>
     */
    public void initialize() {
        //Create 2 balls as requested in Ass3Game
        createBall(new Point(360, 400));
        createBall(new Point(500, 400));
        //Create the blocks in a special sequence
        createBlockSequence();
        //Create the block walls
        this.creteEdges();
    }

    /**
     * The function create a ball and adds it to the game.
     *
     * @param start The starting point
     */
    private void createBall(Point start) {
        //Create the ball
        Ball b = new Ball(start, 5, Color.WHITE, this.environment);
        b.setVelocity(b.getVelocity().fromAngleAndSpeed(0, this.gameSettings.getSpeed()));
        b.addToGame(this);
    }

    /**
     * The function create blocks with a certain sequence and adds them to the game.
     */
    private void createBlockSequence() {
        int width = GameSettings.WINDOW_WIDTH;
        int blockWidth = 50, blockHeight = 20;
        int lineNumber = 0, minBlockNumber = 7;
        Color[] colors = {Color.GREEN, Color.PINK, Color.BLUE, Color.YELLOW, Color.RED, Color.GRAY};
        //The loop create block rows from the right edge of the screen.
        //Each row has a rising number from 7, and each row has a different color.
        for (int i = 300; i >= 200; i -= blockHeight) {
            for (int j = width - 10; j >= width - 10 - (blockWidth * minBlockNumber + blockWidth * lineNumber);
                 j -= blockWidth) {
                Block block = new Block(new Rectangle(new Point(j, i), colors[lineNumber]));
                block.addToGame(this);
            }
            lineNumber++;
        }
    }

    /**
     * Create the edge blocks of the game.
     */
    private void creteEdges() {
        int width = GameSettings.WINDOW_WIDTH;
        int height = GameSettings.WINDOW_HEIGHT;
        int size = GameSettings.BLOCK_EDGE_SIZE;
        //create 4 blocks on the edges of the screen
        Block top = new Block(new Rectangle(new Point(0, 0), width, size, Color.GRAY));
        Block bottom = new Block(new Rectangle(new Point(size, height - size),
                width - 2 * size, size, Color.GRAY));
        Block right = new Block(new Rectangle(new Point(width - size, size),
                size, height - size, Color.GRAY));
        Block left = new Block(new Rectangle(new Point(0, size), size, height - size, Color.GRAY));
        //adds them to the game
        top.addToGame(this);
        bottom.addToGame(this);
        right.addToGame(this);
        left.addToGame(this);
    }

    /**
     * Draw the background for the game.
     *
     * @param d The draw surface
     */
    private void drawBackground(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, GameSettings.WINDOW_WIDTH, GameSettings.WINDOW_HEIGHT);
    }

    /**
     * The creates the paddle and adds it to the game.
     *
     * @param gui The gui to get the keyboard sensor from
     */
    public void initializePaddle(GUI gui) {
        double rectangleWidth = 100, rectangleHeight = 20;
        Rectangle rectangle = new Rectangle(new Point((double) GameSettings.WINDOW_WIDTH / 2,
                GameSettings.WINDOW_HEIGHT - 50), rectangleWidth, rectangleHeight, Color.RED);
        int maxLeft = GameSettings.BLOCK_EDGE_SIZE;
        int maxRight = GameSettings.WINDOW_WIDTH - GameSettings.BLOCK_EDGE_SIZE;
        Paddle paddle = new Paddle(rectangle, gui.getKeyboardSensor(), this.gameSettings);
        paddle.addToGame(this);
    }

    /**
     * The function runs the game infinitely.
     */
    public void run() {
        GUI gui = new GUI("Game", GameSettings.WINDOW_WIDTH, GameSettings.WINDOW_HEIGHT);
        Sleeper sleeper = new Sleeper();
        initializePaddle(gui);
        int framesPerSecond = 60;
        int millisecondPerFrame = 1000 / framesPerSecond;
        //infinite loop that runs the game.
        while (true) {
            long startTime = System.currentTimeMillis();
            //draw the background and all of the sprites in the game
            DrawSurface d = gui.getDrawSurface();
            drawBackground(d);
            this.sprites.drawAllOn(d);
            gui.show(d);
            //notify everybody that time passed and they should act
            this.sprites.notifyAllTimePassed();
            //calculates a delay time for a smooth movement on the screen
            long usedTime = System.currentTimeMillis() - startTime;
            long milleSecondLeftToSleep = millisecondPerFrame - usedTime;
            if (milleSecondLeftToSleep > 0) {
                sleeper.sleepFor(milleSecondLeftToSleep);
            }
        }
    }
}
