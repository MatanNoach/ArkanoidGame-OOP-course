//ID:316441534
package gui.factories;

import gui.gameobjects.Block;

import java.util.Map;
import java.util.TreeMap;

/**
 * The class holds data from a block's definition file, and responsible for creating it's objects.
 * variables:
 * spacerWidth - a map of spaces symbol's and their width's value
 * blockCreators - a map of block types and their symbols.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constructor.
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new TreeMap<>();
        this.blockCreators = new TreeMap<>();
    }


    /**
     * The function checks if 's' is a valid symbol.
     *
     * @param s The symbol to check
     * @return true if 's' is a valid space symbol and false otherwise.
     */
    public boolean isSpaceSymbol(String s) {
        return (spacerWidths.get(s) != null);
    }

    /**
     * The function checks if 's' is a valid block symbol.
     *
     * @param s The symbol to check
     * @return true if 's' is a valid block symbol and false otherwise.
     */
    public boolean isBlockSymbol(String s) {
        return (blockCreators.get(s) != null);
    }

    /**
     * The function adds a spacer to the map.
     *
     * @param s     The space's key symbol.
     * @param value The space width value.
     */
    public void addSpacer(String s, int value) {
        spacerWidths.put(s, value);
    }

    /**
     * The function adds a block to the map.
     *
     * @param s The block's key symbol.
     * @param b The block type.
     */
    public void addBlockCreator(String s, BlockCreator b) {
        blockCreators.put(s, b);
    }

    /**
     * The function returns a space in the map by a symbol.
     *
     * @param s The symbol to find the space by
     * @return The spacer width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * The function returns a block in the map by a symbol.
     *
     * @param s The symbol to find the block by
     * @return The block
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }
}
