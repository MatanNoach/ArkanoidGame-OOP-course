//ID:316441534
package gui.Levels;

import gui.Backgrounds.Background4;
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
 * The class represents the second level of the game.
 */
public class Level4 implements LevelInformation {
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
    public Level4() {
        this.ballsNum = 3;
        this.velocities = initializeVelocities();
        this.background = new Background4();
        this.paddleSpeed = (int) GameSettings.SPEED * 2;
        this.paddleWidth = 100;
        this.levelName = "Final four";
        this.blocksToRemove = 91;
    }

    @Override
    public List<Velocity> initializeVelocities() {
        int angle = 45;
        int speed = (int) GameSettings.SPEED - 1;
        List<Velocity> startVelocities = new ArrayList<>();
        Velocity v1 = new Velocity(0, 0).fromAngleAndSpeed(-angle, speed);
        Velocity v2 = new Velocity(0, 0).fromAngleAndSpeed(0, speed);
        Velocity v3 = new Velocity(0, 0).fromAngleAndSpeed(angle, speed);
        startVelocities.add(v1);
        startVelocities.add(v2);
        startVelocities.add(v3);
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
        List<Block> blockList = new ArrayList<>();
        int width = GameSettings.WINDOW_WIDTH;
        int blockWidth = 60;
        int lineNumber = 0;
        Color[] colors = {Color.cyan, Color.pink, Color.white, Color.green, Color.yellow, Color.RED, Color.gray};
        for (int i = 230; i >= 100; i -= 20) {
            for (int j = width - blockWidth - 10; j >= GameSettings.BLOCK_EDGE_SIZE;
                 j -= blockWidth) {
                Block block = new Block(new Rectangle(new Point(j, i), blockWidth, 20, colors[lineNumber]));
                blockList.add(block);
            }
            lineNumber++;
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksToRemove;
    }
}
