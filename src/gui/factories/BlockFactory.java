//ID:316441534
package gui.factories;

import gui.backgrounds.Background;
import gui.gameobjects.Block;

/**
 * The class is responsible for creating blocks.
 * variable:
 * block - The block's type to create
 */
public class BlockFactory implements BlockCreator {
    private Block block;

    /**
     * Constructor.
     *
     * @param background The block's background
     */
    public BlockFactory(Background background) {
        this.block = new Block(background.getRectangle());
    }

    @Override
    public Block create(int xPos, int yPos) {
        this.block.getCollisionRectangle().setUpperLeft(xPos, yPos);
        return this.block;
    }
}
