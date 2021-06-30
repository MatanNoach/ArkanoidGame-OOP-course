# Arkanoid Game
## Overview
This is an Arkanoid game written in Java programming language. The game was a part of OOP Course in Bar-Ilan University.

## The Game
### Goal
The goal is to destroy all the blocks on the screen using the balls, without letting the balls hit the ground.
The user's purpose is to move the paddle using the keypad to prevent the ball from hitting the ground or to manipulate the balls movement to hit more blocks.

Example:
![image](https://user-images.githubusercontent.com/61862949/123745175-8d3b7c00-d8b8-11eb-93e5-d0d31c8fa24a.png)

Red - The paddle of which the player controls

Blue - The balls the player is using to destroy the blocks

Green - The blocks needed to be destroy

Once a ball hits the ground, it will dissappear. If all the balls dissappears, the player will lose the game.

### How To Play
Using <- and -> arrow, the player can move the paddle left and right. 

The ball's direction is changing by where the ball hits the paddle. For example, if the ball hits the center of the paddle, the ball will move in 90 degrees angle to the top

The player can't control the paddle's speed and the ball's speed. Each level has a different values for the ball's and paddle's speed.

### Scores
Each block hit will grant the player 5 points, and by completing a full level, the player will gain an additional 100 points.

When the player losses or finishes the game, his current score is being matched with the highest score saved so far on the computer. If it is higher, the currrent score will be the new high score.

### Game Flow
![image](https://user-images.githubusercontent.com/61862949/123746610-8ada2180-d8ba-11eb-8fbb-d1a3baa6e7e2.png)


## Technical Requirements
Java Virtual Machine (JVM) installed on the computer. The latest version can be found on: https://java.com/en/download/

Recommended: Apache ant installed on the computer. The latest version can be found on: https://ant.apache.org/bindownload.cgi

## Installation And Running
1) Download the git repository or clone it using "git clone" command
2) Open any terminal on the folder created
3) In order to run the game, type the following commands:
  ```
    javac -d bin -cp biuoop-1.4.jar;src/gui;src src/ass7/src/Ass7Game.java
    java -cp biuoop-1.4.jar;resources;bin Ass7Game
   ```
   * The first command is only necessary on the initial running. The latter is necessary to start the game and needs to be written every time
   * In order to change the level, just type the level name + .txt in the end of command b.
   * Example: java -cp biuoop-1.4.jar;resources;bin Ass7Game **levels1.txt**
    
**NOTE:** If you have Apache Ant installed on your PC, you can skip stage 3, by running the following commands:
  ```
    ant compile
    ant -Dargs"levelName.txt" run
   ```
## Additional Features
### Adding Levels
The game has a feature of creating customize set of levels. Each level should be in a specific format with all values filled.
You can specify you level's name, background blocks style and location and more.
In order to create a complete level, you need to write 2 .txt files: levels file and blocks file

The levels files should be places in: resources/levels

The blocks files should be places in: resources/blocks

The images should be places in: resources/images
### Levels File
The levels file specify properties for a set of levels. Each file contains multiple level seperated by START_LEVEL and END_LEVEL rows,
and inside these rows, there are the properties and values for the level
The properties:
* each property name is followed by : and the value 
1) START_LEVEL - a line that marks the start of level properties. no value should come after
2) level_name: the level name
3) ball_velocities: angle,speed. for multiple balls, add space and another angle,speed
4) background: color or image(imagename.jpg/png)
5) paddle_speed: a number
6) paddle_width: a number
7) block_definitions: the blocks file (Explanation below)
8) blocks_start_x: the start x value of the first block
9) blocks_start_y: the start y value of the first block
10) row_height: a row height
11) num_blocks: the total number of blocks in the level
12) START_BLOCKS: the start of the block definition
13) The blocks order by blocks and spaces specified in the blocks file
14) END_BLOCKS: the end of the blocks definition
15) END_LEVEL: the end of the level definition
* Example for the default level:
```
START_LEVEL
level_name:The rainbow
ball_velocities:0,5
background:image(clouds.png)
paddle_speed:5
paddle_width:100
block_definitions:defaultBlocks.txt
blocks_start_x:10
blocks_start_y:150
row_height:20
num_blocks:78
START_BLOCKS
rrrrrrrrrrrrr
ooooooooooooo
yyyyyyyyyyyyy
ggggggggggggg
bbbbbbbbbbbbb
mmmmmmmmmmmmm
END_BLOCKS
END_LEVEL
```
### Blocks File
The blocks file is a resuable file which specifies the properties for mutliple blocks to be used in a specific levels.
In each file there are 3 sections:
1) Default Values - default values for the block
2) Blocks Defnition - specify different type of blocks
3) Spacers Defnition - specify different type of spaces

**Default Values**

one line that specifies the different values for the blocks
1) default height: height_in_pixels
2) default width: width_in_pixels
3) stroke: color(color_name)

**Blocks Defnition**

Each line specifies different type of blocks
1) bdef - a symbol represents the start of another block definition 
2) symbol: a string that represents it in levels file
3) fill: color(color_name) or image(imagename.jpg/png)

**Spacers Defnition**

Each line sepcifies differnt type of spacing that can be between each block:
1) bdef - a symbol represents the start of another spacers definition 
2) symbol: a string that represents it in levels file
3) width: width_in_pixels

* Example for blocks filef for the default level:
```
# default values for blocks
default height:20 width:60 stroke:color(black)

# block definitions
bdef symbol:r fill:color(red)
bdef symbol:b fill:color(blue)
bdef symbol:y fill:color(yellow)
bdef symbol:g fill:color(green)
bdef symbol:o fill:color(orange)
bdef symbol:m fill:color(magenta)

# spacers definitions
sdef symbol:* width:50
```



