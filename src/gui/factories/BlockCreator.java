//ID:316441534
package gui.factories;

import gui.gameobjects.Block;

/**
 * The interface represents an object that can create a block.
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     *
     * @param xPos The x position of the block
     * @param yPos The y position of the block
     * @return The created block
     */
    Block create(int xPos, int yPos);
}