//ID:316441534
package gui.backgrounds;

import gui.gamedata.GameSettings;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import gui.shapes.Rectangle;
import gui.shapes.Point;

/**
 * The class creates background objects and parses colors and images from string to objects.
 */
public class BackgroundFactory {
    /**
     * Gets a color from string.
     *
     * @param s The string to parse form
     * @return The Color
     */
    public static Color colorFromString(String[] s) {
        //if the color is RGB, create and return a new color by it's values
        if (s[1].equals("RGB")) {
            String[] values = s[2].split(",");
            return new Color(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
        } else {
            Color color;
            try {
                //if the color is by an object name, try to parse it. if it exists, return it, else return null.
                Field field = Class.forName("java.awt.Color").getField(s[1]);
                color = (Color) field.get(null);
                return color;
            } catch (Exception e) {
                return null;
            }
        }
    }

    /**
     * Parsing an image from string.
     *
     * @param s The string to parse from
     * @return The image
     */
    public static Image imageFromString(String[] s) {
        Image img;
        try {
            //try to get the image by its name from the resource path. if it exist, return it, else, return null.
            InputStream is = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(GameSettings.IMAGES_PATH + s[1]);
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("(BackgroundFactory.java) Something went wrong while reading the file " + s[1]);
            return null;
        }
        return img;
    }

    /**
     * The function creates a level background.
     *
     * @param s The string contains the background information for the level
     * @return The new background
     */
    public static Background createBackgroundForLevel(String s) {
        //set the background to be the whole screen
        Rectangle rectangle = null;
        Point upperLeft = new Point(0, 0);
        int width = GameSettings.WINDOW_WIDTH;
        int height = GameSettings.WINDOW_HEIGHT;
        String[] values = s.split("[()]");
        //if the background is a color, parse and return it
        if (values[0].equals("color")) {
            Color c = colorFromString(values);
            rectangle = new Rectangle(upperLeft, width, height, c);
        } else if (values[0].equals("image")) { //if it's an image, parse and return it
            Image img = imageFromString(values);
            rectangle = new Rectangle(upperLeft, width, height, null);
            rectangle.setImage(img);
        }
        //return a new background.
        return new Background(rectangle);
    }

    /**
     * The function creates a background for a block
     *
     * @param upperLeft The block's upperleft point
     * @param width     The block's width
     * @param height    The block's height
     * @param s         The string to get the data from
     * @return The background
     */
    public static Background createBackgroundForBlock(Point upperLeft, int width, int height, String s) {
        //creates a level background, and change it's values so it will be as the block's size.
        Background b = createBackgroundForLevel(s);
        b.getRectangle().setUpperLeft(upperLeft.getX(), upperLeft.getY());
        b.getRectangle().setWidth(width);
        b.getRectangle().setHeight(height);
        //return the background
        return b;
    }
}
