//ID:316441534
package gui.Levels;


import gui.shapes.Velocity;

import java.util.List;

import gui.gameobjects.Sprite;
import gui.gameobjects.Block;

/**
 * The interface represents a level information.
 */
public interface LevelInformation {
    /**
     * @return The number of balls in the level.
     */
    int numberOfBalls();

    /**
     * @return list of initial velocities of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return The paddle's speed
     */
    int paddleSpeed();

    /**
     * @return The paddle's width
     */
    int paddleWidth();

    /**
     * @return The level's name
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return a list of Blocks that make up this level, each block contains
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed
     */
    int numberOfBlocksToRemove();
    /**
     * Initialize the ball velocities.
     *
     * @return The list of the velocities.
     */
    List<Velocity> initializeVelocities();
}
