//ID:316441534
package gui.levels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animation.Animation;
import gui.animation.AnimationRunner;
import gui.animation.KeyPressStoppableAnimation;
import gui.animation.PauseScreen;
import gui.animation.CountdownAnimation;
import gui.gamedata.GameSettings;
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
import gui.shapes.Velocity;

import java.awt.Color;

/**
 * The class represents the game.
 * Variables:
 * sprites - A collection of sprites in the game
 * environment - A collection of collidable objects in the level
 * remainingBlocks - The counter of the remaining blocks in the level
 * remainingBalls - The counter of the remaining balls in the level
 * scoreCounter - The counter of the  player's score
 * keyboardSensor - The keyboard sensor of the level
 * runner - Runs the animation of the level
 * running - true if the game is running and false if it is paused
 * isLost - true if the player lost and false otherwise
 * isWin - true if the player won and false otherwise
 * information - Contains the level's information
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter scoreCounter;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner runner;
    private boolean running;
    private boolean isLost;
    private LevelInformation information;
    private boolean isWin;

    /**
     * Constructor.
     *
     * @param levelInformation The level's information
     * @param keyboardSensor   The keyboardSensor
     * @param score            The score counted before the level
     * @param ar               The animation runner of the level
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor, AnimationRunner ar, Counter score) {
        this.information = levelInformation;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(levelInformation.numberOfBalls());
        this.scoreCounter = score;
        this.keyboardSensor = keyboardSensor;
        this.runner = ar;
        this.running = true;
        this.isLost = false;
        this.isWin = false;
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
     * Removes the sprite from the collection.
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
        //adds the background to the level
        this.addSprite(information.getBackground());
        initializePaddleAndBalls();
        //Create the blocks in a special sequence
        createBlocks();
        //Create the block walls
        creteEdges();
        //add the score indicator to the game
        addScoreIndicator();
    }

    /**
     * The function initializes the paddle and the balls on top of it.
     */
    public void initializePaddleAndBalls() {
        double paddleWidth = information.paddleWidth(), paddleHeight = 20;
        double xPaddle = (double) (GameSettings.WINDOW_WIDTH / 2) - paddleWidth / 2;
        double yPaddle = GameSettings.WINDOW_HEIGHT - 50;
        Rectangle rectangle = new Rectangle(new Point(xPaddle, yPaddle), paddleWidth, paddleHeight, Color.RED);
        Paddle paddle = new Paddle(rectangle, this.keyboardSensor, this.information.paddleSpeed());
        paddle.addToGame(this);
        //create all the balls
        for (int i = 0; i < information.numberOfBalls(); i++) {
            createBall(new Point(xPaddle + paddleWidth / 2, yPaddle - 5), information.initialBallVelocities().get(i));
        }
    }

    /**
     * The function create a ball and adds it to the game.
     *
     * @param start The starting point
     * @param v     The ball's velocity
     */
    private void createBall(Point start, Velocity v) {
        //Create the ball
        Ball b = new Ball(start, 5, Color.WHITE, this.environment);
        b.setVelocity(v);
        b.addToGame(this);
    }

    /**
     * The function creates the blocks, adds them to the game, and adds their listeners.
     */
    private void createBlocks() {
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        for (Block b : this.information.blocks()) {
            b.addToGame(this);
            this.addListenersToBlock(b, scoreTrackingListener, blockRemover);
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
    }

    /**
     * The function prints the level's name.
     *
     * @param d The drawSurface to print on
     */
    private void printLevelName(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(200, 20, "Level: " + this.information.levelName(), GameSettings.FONT_SIZE);
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
                size, height - size * 2, Color.GRAY));
        Block left = new Block(new Rectangle(new Point(0, size * 2), size, height - size * 2, Color.GRAY));
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

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        printLevelName(d);
        //if the player killed all the blocks, end the game
        if (this.remainingBlocks.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;
            this.isWin = true;
        }
        //if all the balls are lost, end the game
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
            this.isLost = true;
        }
        if (this.keyboardSensor.isPressed("p")) {
            PauseScreen pauseScreen = new PauseScreen();
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, pauseScreen));
        }
        //notify everybody that time passed and they should act
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * The function runs the game infinitely.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(1, 3, this.sprites, this.information.levelName()));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * @return True if the player won the game and false otherwise.
     */
    public boolean isWin() {
        return this.isWin;
    }

    /**
     * @return True if the player lost the game and false otherwise.
     */
    public boolean isLost() {
        return this.isLost;
    }
}
