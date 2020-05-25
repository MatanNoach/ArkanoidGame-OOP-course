//ID:316441534

import gui.gamedata.Game;

/**
 * The class runs the game.
 */
public class Ass5Game {
    /**
     * The main function of the class.
     *
     * @param args user arguments. Does not need any in the function
     */
    public static void main(String[] args) {
        Game g = new Game();
        g.initialize();
        g.run();
    }
}
