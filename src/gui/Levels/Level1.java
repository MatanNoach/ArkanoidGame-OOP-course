//ID:316441534
package gui.Levels;

import gui.Backgrounds.Background1;
import gui.gamedata.GameSettings;
import gui.gameobjects.Block;
import gui.gameobjects.Sprite;
import gui.shapes.Point;
import gui.shapes.Rectangle;
import gui.shapes.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represent the first level of the game.
 */
public class Level1 implements LevelInformation {
    private int ballsNum;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private int blocksToRemove;

    /**
     * Constructor.
     */
    public Level1() {
        this.ballsNum = 1;
        this.velocities = initializeVelocities();
        this.background = new Background1();
        this.paddleSpeed = (int) GameSettings.SPEED;
        this.paddleWidth = 100;
        this.levelName = "Direct hit";
        this.blocksToRemove = 1;
    }

    @Override
    public List<Velocity> initializeVelocities() {
        List<Velocity> startVelocities = new ArrayList<>();
        startVelocities.add(new Velocity(0, GameSettings.SPEED));
        return startVelocities;
    }

    @Override
    public int numberOfBalls() {
        return this.ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        int x = GameSettings.WINDOW_WIDTH / 2;
        int y = GameSettings.WINDOW_HEIGHT / 2;
        int squareSize = 50;
        Block b = new Block(new Rectangle(new Point((x - squareSize / 2), y - squareSize / 2),
                squareSize, squareSize, Color.RED));
        List<Block> blockList = new ArrayList<>();
        blockList.add(b);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksToRemove;
    }
}
