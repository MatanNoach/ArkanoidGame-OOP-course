//ID:316441534
package gui.Levels;

import gui.Backgrounds.Background3;
import gui.gamedata.GameSettings;
import gui.gameobjects.Block;
import gui.gameobjects.Sprite;
import gui.shapes.Point;
import gui.shapes.Rectangle;
import gui.shapes.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class Level3 implements LevelInformation {
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
    public Level3() {
        this.ballsNum = 2;
        this.velocities = initializeVelocities();
        this.background = new Background3();
        this.paddleSpeed = (int) GameSettings.SPEED;
        this.paddleWidth = 70;
        this.levelName = "Green 3";
        this.blocksToRemove = 40;
    }

    @Override
    public List<Velocity> initializeVelocities() {
        List<Velocity> startVelocities = new ArrayList<>();
        Velocity v1 = new Velocity(0, 0).fromAngleAndSpeed(-45, 5);
        Velocity v2 = new Velocity(0, 0).fromAngleAndSpeed(45, 5);
        startVelocities.add(v1);
        startVelocities.add(v2);
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
        int blockWidth = 50;
        int lineNumber = 0;
        int minBlockNumber = 6;
        Color[] colors = {Color.white, Color.BLUE, Color.yellow, Color.red, Color.gray};
        for (int i = 300; i > 200; i -= 20) {
            for (int j = width - blockWidth - 10; j >= width - 10 - (blockWidth * minBlockNumber + blockWidth * lineNumber);
                 j -= blockWidth) {
                Block block = new Block(new Rectangle(new Point(j, i), colors[lineNumber]));
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
