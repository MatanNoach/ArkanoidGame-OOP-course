//ID:316441534
package gui.Levels;

import gui.Backgrounds.Background2;
import gui.gamedata.GameSettings;
import gui.gameobjects.Block;
import gui.gameobjects.Sprite;
import gui.shapes.Point;
import gui.shapes.Rectangle;
import gui.shapes.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class Level2 implements LevelInformation {
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
    public Level2() {
        this.ballsNum = 10;
        this.velocities = initializeVelocities();
        this.background = new Background2();
        this.paddleSpeed = (int) GameSettings.SPEED;
        this.paddleWidth = 700;
        this.levelName = "Wide easy";
        this.blocksToRemove = 13;
    }

    @Override
    public List<Velocity> initializeVelocities() {
        List<Velocity> startVelocities = new ArrayList<>();
        for (int i = 0; i < this.ballsNum; i++) {
            Velocity v = new Velocity((double) ballsNum / 2 - i, -5);
            startVelocities.add(v);
        }
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
        int leftEdgeStart = GameSettings.BLOCK_EDGE_SIZE;
        int blockWidth = 60;
        Color[] colors = {Color.RED, Color.ORANGE, Color.yellow, Color.green, Color.blue, Color.pink, Color.cyan};
        for (int i = 0; i < this.blocksToRemove; i++) {
            Color c = colors[i / 2];
            Rectangle r = new Rectangle(new Point(leftEdgeStart + i * blockWidth, 200), blockWidth, 20, c);
            Block b = new Block(r);
            blockList.add(b);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksToRemove;
    }
}
