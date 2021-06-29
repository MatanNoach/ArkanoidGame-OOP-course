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
   * Example: java -cp biuoop-1.4.jar;resources;bin Ass7Game *levels1.txt*
    
*NOTE:* If you have Apache Ant installed on your PC, you can skip stage 3, by running the following commands:
  ```
    ant compile
    ant -Dargs"levelName.txt" run
   ```
## Additional Features
### Creating Customize Levels
There is a feature of creating customize set of levels. In order to create it, you need to write a .txt file in the following format:
* each property name is followed by : and the value 
1) START_LEVEL - a line that marks the start of level properties. no value should come after
2) level_name: the level name
3) ball_velocities: angle,speed. for multiple balls, add space and another angle,speed
4) background: color or image(imagename.jpg/png)
5) paddle_speed: a number
6) paddle_width: a number
7) block_definitions: a file with the blocks definition
8) blocks_start_x: the start x value of the first block
9) blocks_start_y: the start y value of the first block
10) row_height: a row height
11) num_blocks: the total number of blocks in the level
12) START_BLOCKS: the start of the block definition
13) END_BLOCKS: the end of the blocks definition
14) END_LEVEL: the end of the level definition
