//ID:316441534
package gui.factories;

import gui.backgrounds.Background;
import gui.backgrounds.BackgroundFactory;
import gui.shapes.Point;

import java.awt.Color;
import java.io.BufferedReader;

/**
 * The class reads and parses a block definition file.
 * variables:
 * defaultHeight - The default block's width
 * defaultWidth - The default block's height
 * defaultStroke - The Stroke's color
 * defaultFill - The blocks fill
 */
public class BlockDefinitionReader {
    private int defaultHeight;
    private int defaultWidth;
    private Color defaultStroke;
    private Background defaultFill;

    /**
     * The function reads and parses a block definition file.
     *
     * @param reader The reader to the file
     * @return A new BlockFromSymbolFactory with all of the lines' values.
     */
    public BlocksFromSymbolsFactory fromReader(BufferedReader reader, String fileName) {
        //create a new background factory
        BlocksFromSymbolsFactory factory = new BlocksFromSymbolsFactory();
        //get information from the reader and set the default values of the blocks
        this.parseDefaultValues(reader);
        try {
            String line = reader.readLine();
            //read every line. check which type it is, parse it, and enter values to the factory
            while (line != null) {
                String[] data = line.split(" ");
                if (data[0].equals("bdef")) {
                    parseBDEF(factory, data);
                } else if (data[0].equals("sdef")) {
                    parseSDEF(factory, data);
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("(BlockDefinitionReader.java) Something went wrong while reading the file " + fileName);
        }
        //return the factory
        return factory;
    }

    /**
     * The function parses the default values and sets them to the class variables.
     *
     * @param reader The file reader
     */
    private void parseDefaultValues(BufferedReader reader) {
        String line = "";
        try {
            //find the default row
            String[] info = line.split(" ");
            while (!info[0].equals("default")) {
                line = reader.readLine();
                info = line.split(" ");
            }
            //check every key and set it's value as default.
            for (int i = 1; i < info.length; i++) {
                String key = info[i].split(":")[0];
                String value = info[i].split(":")[1];
                switch (key) {
                    case "height":
                        this.defaultHeight = Integer.parseInt(value);
                        break;
                    case "width":
                        this.defaultWidth = Integer.parseInt(value);
                        break;
                    case "stroke":
                        this.defaultStroke = BackgroundFactory.colorFromString(value.split("[()]"));
                        break;
                    case "fill":
                        this.defaultFill = BackgroundFactory.createBackgroundForLevel(value);
                        break;
                    default:
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * The function parses the "bdef" lines, and adds a new block creator to the factory.
     *
     * @param factory The factory to add the spacer
     * @param data    The data of the row
     */
    private void parseBDEF(BlocksFromSymbolsFactory factory, String[] data) {
        //set every rectangle value to the one set in the default
        Background fill = defaultFill;
        int height = defaultHeight;
        int width = defaultWidth;
        Color stroke = defaultStroke;
        String symbol = "";
        //check every key and set it's value.
        for (int i = 1; i < data.length; i++) {
            String key = data[i].split(":")[0];
            String value = data[i].split(":")[1];
            switch (key) {
                case "symbol":
                    symbol = value;
                    break;
                case "fill":
                    fill = BackgroundFactory.createBackgroundForBlock(new Point(0, 0), width, height, value);
                    break;
                case "height":
                    height = Integer.parseInt(value);
                    break;
                case "width":
                    width = Integer.parseInt(value);
                    break;
                case "stroke":
                    stroke = BackgroundFactory.colorFromString(value.split("[()]"));
                    break;
                default:
            }
        }
        //set the new rectangle by the updated values (if there are)
        fill.getRectangle().setHeight(height);
        fill.getRectangle().setWidth(width);
        fill.getRectangle().setStroke(stroke);
        //add the block type to the factory
        factory.addBlockCreator(symbol, new BlockFactory(fill));
    }

    /**
     * The function parses the "sdef" lines, and adds a new spacer to the factory.
     *
     * @param factory The factory to add the spacer
     * @param data    The data of the row
     */
    private void parseSDEF(BlocksFromSymbolsFactory factory, String[] data) {
        String symbol = "";
        int width = 0;
        //check every key and set it's value
        for (int i = 1; i < data.length; i++) {
            String key = data[i].split(":")[0];
            String value = data[i].split(":")[1];
            if (key.equals("symbol")) {
                symbol = value;
            }
            if (key.equals("width")) {
                width = Integer.parseInt(value);
            }
        }
        //add the spacer to the factory
        factory.addSpacer(symbol, width);
    }
}
