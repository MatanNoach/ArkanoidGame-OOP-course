//ID:316441534
package gui.animation;


import biuoop.KeyboardSensor;

/**
 * The class is a menu task, that shows the high scores.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private KeyPressStoppableAnimation kspa;

    /**
     * Constructor.
     *
     * @param runner              The animation runner to run from
     * @param highScoresAnimation The animation to run
     * @param ks                  The keyboardSensor
     */
    public ShowHiScoresTask(AnimationRunner runner, KeyboardSensor ks, Animation highScoresAnimation) {
        this.runner = runner;
        this.kspa = new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, highScoresAnimation);
    }

    @Override
    public Void run() {
        this.runner.run(this.kspa);
        this.kspa.resetStop();
        return null;
    }
}
