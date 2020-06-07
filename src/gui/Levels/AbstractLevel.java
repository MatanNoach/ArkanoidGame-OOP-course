package gui.Levels;

import gui.gamedata.GameSettings;
import gui.gameobjects.Block;
import gui.gameobjects.Sprite;
import gui.shapes.Velocity;

import java.util.List;

//ID:316441534
public class AbstractLevel implements LevelInformation {
    private int ballsNum;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private int blocksToRemove;

    @Override
    public List<Block> blocks() {
        return null;
    }

    @Override
    public List<Velocity> initializeVelocities() {
        return null;
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
    public int numberOfBlocksToRemove() {
        return this.blocksToRemove;
    }
}
