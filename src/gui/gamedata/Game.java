//ID:316441534
package gui.gamedata;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import gui.gameobjects.SpriteCollection;
import gui.gameobjects.GameEnvironment;
import gui.gamelisteners.Counter;
import gui.gamelisteners.BallRemover;
import gui.gamelisteners.BlockRemover;
import gui.gameobjects.Sprite;
import gui.gameobjects.Collidable;
import gui.gameobjects.Block;
import gui.gamelisteners.ScoreTrackingListener;
import gui.gameobjects.Paddle;
import gui.gamelisteners.ScoreIndicator;
import gui.shapes.Ball;
import gui.shapes.Point;
import gui.shapes.Rectangle;

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
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter scoreCounter;

    /**
     * Constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.gameSettings = new GameSettings();
        this.environment = new GameEnvironment(this.gameSettings);
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.scoreCounter = new Counter();
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
     * Removes the collidable from the collection.
     *
     * @param c The collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
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
     * Removes the sprite from the collection
     *
     * @param s The Sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * The function initializes the game.
     * <p>
     * The function creates the ball, the blocks on the screen and the block walls.
     * </p>
     */
    public void initialize() {
        //Create 3 balls as requested in Ass5Game
        createBall(new Point(350, 400));
        createBall(new Point(340, 400));
        createBall(new Point(360, 400));
        //Create the blocks in a special sequence
        createBlockSequence();
        //Create the block walls
        this.creteEdges();
        //add the score indicator to the game
        addScoreIndicator();
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
        this.remainingBalls.increase(1);
    }

    /**
     * The function create blocks with a certain sequence and adds them to the game.
     */
    private void createBlockSequence() {
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        int width = GameSettings.WINDOW_WIDTH;
        int blockWidth = 50, blockHeight = 20;
        int lineNumber = 0, minBlockNumber = 7;
        Color[] colors = {Color.GREEN, Color.PINK, Color.BLUE, Color.YELLOW, Color.RED, Color.GRAY};
        //The loop create block rows from the right edge of the screen.
        //Each row has a rising number from 7, and each row has a different color.
        for (int i = 300; i >= 200; i -= blockHeight) {
            for (int j = width - blockWidth - 10; j >= width - 10 - (blockWidth * minBlockNumber + blockWidth * lineNumber);
                 j -= blockWidth) {
                Block block = new Block(new Rectangle(new Point(j, i), colors[lineNumber]));
                block.addToGame(this);
                this.addListenersToBlock(block, scoreTrackingListener, blockRemover);
            }
            lineNumber++;
        }
    }

    /**
     * Add hit listeners to a block.
     *
     * @param b                     The block
     * @param scoreTrackingListener The scoreTrackingListener
     * @param blockRemover          The blockRemover
     */
    private void addListenersToBlock(Block b, ScoreTrackingListener scoreTrackingListener, BlockRemover blockRemover) {
        b.addHitListener(blockRemover);
        b.addHitListener(scoreTrackingListener);
        this.remainingBlocks.increase(1);
    }

    /**
     * Create the edge blocks of the game.
     */
    private void creteEdges() {
        int width = GameSettings.WINDOW_WIDTH;
        int height = GameSettings.WINDOW_HEIGHT;
        int size = GameSettings.BLOCK_EDGE_SIZE;
        //create 3 blocks on the edges of the screen
        Block top = new Block(new Rectangle(new Point(0, size * 2), width, size, Color.GRAY));
        Block right = new Block(new Rectangle(new Point(width - size, size * 2),
                size, height - size, Color.GRAY));
        Block left = new Block(new Rectangle(new Point(0, size * 2), size, height - size, Color.GRAY));
        //adds them to the game
        top.addToGame(this);
        right.addToGame(this);
        left.addToGame(this);
        addDeathZone();
    }

    /**
     * The function adds the score indicator to the game.
     */
    private void addScoreIndicator() {
        // create a score counter at the top of the screen
        Rectangle rectangle = new Rectangle(new Point(0, 0), GameSettings.WINDOW_WIDTH,
                GameSettings.BLOCK_EDGE_SIZE * 2, Color.LIGHT_GRAY);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter, rectangle);
        scoreIndicator.addToGame(this);
    }

    /**
     * The function creates a "death zone" for the player.
     */
    private void addDeathZone() {
        int width = GameSettings.WINDOW_WIDTH;
        int height = GameSettings.WINDOW_HEIGHT;
        int size = GameSettings.BLOCK_EDGE_SIZE;
        Block bottom = new Block(new Rectangle(new Point(size, height - size),
                width - 2 * size, size, Color.gray));
        bottom.addHitListener(new BallRemover(this, this.remainingBalls));
        bottom.addToGame(this);
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
        GUI gui = new GUI("gui.gameData.Game", GameSettings.WINDOW_WIDTH, GameSettings.WINDOW_HEIGHT);
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
            //if the player killed all the blocks, end the game
            if (this.remainingBlocks.getValue() == 0) {
                System.out.println("You Won!");
                this.scoreCounter.increase(100);
                System.out.println("Your score is " + this.scoreCounter.getValue() + " points!");
                gui.close();
            }
            //if all the balls are lost, end the game
            if (this.remainingBalls.getValue() == 0) {
                System.out.println("You Lost!");
                gui.close();
            }
        }
    }
}
