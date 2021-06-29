//ID:316441534
package gui.factories;

import gui.backgrounds.BackgroundFactory;
import gui.gamedata.GameSettings;
import gui.gameobjects.Block;
import gui.levels.BasicLevel;
import gui.levels.LevelInformation;
import gui.shapes.Velocity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is responsible for reading a levelDefinition file.
 */
public class LevelSpecificationReader {
    /**
     * The class reads a levelDefinition file and returns a list of Level information by it.
     *
     * @param reader The object that reads the file.
     * @return A list of level information.
     * @throws Exception Thrown when there is a problem while reading the file
     */
    public List<LevelInformation> fromReader(BufferedReader reader) throws Exception {
        List<LevelInformation> levels = new ArrayList<>();
        //Split the file by levels
        List<String> levelsData = splitToLevels(reader);
        for (String levelData : levelsData) {
            //split the levelData the level to lines
            String[] lines = levelData.split("\n");
            //parse the level and add it to the level's list
            LevelInformation l = parseFile(lines);
            levels.add(l);
        }
        return levels;
    }

    /**
     * Split the level specs file to a list of files.
     *
     * @param reader The reader of the file
     * @return The list of levels
     * @throws Exception thrown when there is a reading problem
     */
    private List<String> splitToLevels(BufferedReader reader) throws Exception {
        List<String> files = new ArrayList<>();
        String readLine = "";
        String fileData = "";
        readLine = reader.readLine();
        //while there are lines in the file
        while (readLine != null) {
            //if the line equals to START_LEVEL, start collecting them until there is an END_LEVEL line
            if (readLine.equals("START_LEVEL")) {
                while (!readLine.equals("END_LEVEL")) {
                    readLine = reader.readLine();
                    fileData = fileData.concat(readLine + '\n');
                }
                //after collecting the lines, add the files to the String list.
                files.add(fileData);
            }
            //Start a new level reading
            fileData = "";
            readLine = reader.readLine();
        }
        return files;
    }

    /**
     * The function parses a level in the levels1.txt file.
     *
     * @param lines The level's lines.
     * @return A new level information
     * @throws Exception Thrown if there are missing values in the file.
     */
    private LevelInformation parseFile(String[] lines) throws Exception {
        //create a basic level
        BasicLevel level = new BasicLevel();
        String blocksData = "";
        String blocksDefinitionFile = "";
        boolean startCollecting = false;
        int blocksStartX = 0, blocksStartY = 0, rowHeight = 0;
        for (String line : lines) {
            //if the reader didn't started to collect blocks order data, parse the lines
            if (!startCollecting) {
                String[] data = line.split(":");
                //for evey line, check which key it is, and set the it's value to the proper variable.
                switch (data[0]) {
                    case "level_name":
                        level.setLevelName(data[1]);
                        break;
                    case "ball_velocities":
                        level.setVelocities(parseVelocities(data[1]));
                        break;
                    case "paddle_speed":
                        level.setPaddleSpeed(Integer.parseInt(data[1]));
                        break;
                    case "paddle_width":
                        level.setPaddleWidth(Integer.parseInt(data[1]));
                        break;
                    case "background":
                        level.setBackground(BackgroundFactory.createBackgroundForLevel(data[1]));
                        break;
                    case "num_blocks":
                        level.setBlocksNum(Integer.parseInt(data[1]));
                        break;
                    case "block_definitions":
                        blocksDefinitionFile = data[1];
                        break;
                    case "blocks_start_x":
                        blocksStartX = Integer.parseInt(data[1]);
                        break;
                    case "blocks_start_y":
                        blocksStartY = Integer.parseInt(data[1]);
                        break;
                    case "row_height":
                        rowHeight = Integer.parseInt(data[1]);
                        break;
                    default:
                }
            } else { //add the line to the blocksData
                blocksData = blocksData.concat(line + "\n");
            }
            //if the line is "START_BLOCKS", start collecting the blocks order information
            if (line.equals("START_BLOCKS")) {
                startCollecting = true;
            }
        }
        //if there are missing values in the file, throw an exception
        if (level.isMissingValue()) {
            throw new Exception("There are missing values in the file!");
        }
        //build the blocks and set them to the levels.
        level.setBlocks(buildBlocks(blocksDefinitionFile, blocksStartX, blocksStartY, rowHeight, blocksData));
        return level;
    }

    /**
     * Parsing a velocities String and returns a list of velocities.
     *
     * @param info The string that contains the data
     * @return List of velocities
     */
    private List<Velocity> parseVelocities(String info) {
        List<Velocity> velocities = new ArrayList<>();
        String[] velocitiesData = info.split("[^0-9-]");
        //get the angle and speed for every velocity, create a new velocity object, and add it to the list.
        for (int i = 0; i < velocitiesData.length; i += 2) {
            int angle = Integer.parseInt(velocitiesData[i]);
            int speed = Integer.parseInt(velocitiesData[i + 1]);
            velocities.add(new Velocity(0, 0).fromAngleAndSpeed(angle, speed));
        }
        //return the list
        return velocities;
    }

    /**
     * The function creates a list of blocks by a blockDefinition file and levelDefinition file.
     *
     * @param fileName   The blockDefinition file name.
     * @param startX     The start x to create the blocks from
     * @param startY     The start y to create the blocks from
     * @param rowHeight  The height between each blocks row
     * @param blocksData A string representing the order of the blocks
     * @return A blocks list by the order
     */
    private List<Block> buildBlocks(String fileName, int startX, int startY, int rowHeight, String blocksData) {
        //split the data by rows
        String[] lines = blocksData.split("\n");
        List<Block> blocks = new ArrayList<>();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(GameSettings.BLOCKS_PATH + fileName);
        BufferedReader reader = null;
        try {
            //read the blockDefinition file
            reader = new BufferedReader(new InputStreamReader(is));
            BlockDefinitionReader definition = new BlockDefinitionReader();
            BlocksFromSymbolsFactory factory = definition.fromReader(reader, fileName);
            for (String line : lines) {
                //split the line to chars
                String[] chars = line.split("(?!^)");
                //the starting x of the block
                int x = startX;
                for (String s : chars) {
                    //if the char is a space symbol, increase the x distance
                    if (factory.isSpaceSymbol(s)) {
                        x += factory.getSpaceWidth(s);
                    } else if (factory.isBlockSymbol(s)) { //if the char is a block symbol, create it
                        Block b = new Block(factory.getBlock(s, x, startY));
                        x += b.getCollisionRectangle().getWidth();
                        blocks.add(b);
                    }
                }
                //move to the next row
                startY += rowHeight;
            }

        } catch (Exception e) {
            System.out.println("(LevelSpecificationReader) Something went wrong while reading the file " + fileName);
        }
        //return the blocks
        return blocks;
    }
}
