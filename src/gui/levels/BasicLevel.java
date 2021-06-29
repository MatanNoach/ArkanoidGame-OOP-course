//ID:316441534
package gui.levels;

import gui.gameobjects.Block;
import gui.gameobjects.Sprite;
import gui.shapes.Velocity;

import java.util.List;

/**
 * The class represents a basic level.
 * variables:
 * ballsNUm - The number of balls in the level
 * velocities - A list of velocities for each ball
 * paddleSpeed - The paddle's speed
 * paddleWidth - The paddle's width
 * levelName - The level's name
 * background - The background for the level
 * blockToRemove - The number of blocks need to be removed to pass the level
 * blocks - A list of the blocks in the level
 */
public class BasicLevel implements LevelInformation {
    private int ballsNum;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private int blocksToRemove;
    private List<Block> blocks;

    /**
     * Basic constructor.
     */
    public BasicLevel() {

    }

    @Override
    public List<Velocity> initializeVelocities() {
        return this.velocities;
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
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksToRemove;
    }

    /**
     * @param newVelocities The new velocities to set
     */
    public void setVelocities(List<Velocity> newVelocities) {
        this.velocities = newVelocities;
        this.ballsNum = newVelocities.size();
    }

    /**
     * @param b The new background to set
     */
    public void setBackground(gui.backgrounds.Background b) {
        this.background = b;
    }

    /**
     * @param name The level's name
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }

    /**
     * @param speed The paddle's speed.
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * @param width The paddle's width
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**
     * @param num The new blocks number
     */
    public void setBlocksNum(int num) {
        this.blocksToRemove = num;
    }

    /**
     * @param blockList The blocks list
     */
    public void setBlocks(List<Block> blockList) {
        this.blocks = blockList;
    }

    /**
     * The function checks if there are missing vital values for in the object.
     * @return true if there are missing values and false otherwise.
     */
    public boolean isMissingValue() {
        return (ballsNum == 0 || velocities == null || background == null
                || paddleSpeed == 0 || paddleWidth == 0 || levelName == null);
    }
}
