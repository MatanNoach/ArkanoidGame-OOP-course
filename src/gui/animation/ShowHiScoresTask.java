//ID:316441534
package gui.animation;


import biuoop.KeyboardSensor;

/**
 * The class is a menu task, that shows the high scores.
 * variables:
 * runner - The animation runner who runs the task
 * kpsa - The keyPressedStoppableAnimation
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private KeyPressStoppableAnimation kpsa;

    /**
     * Constructor.
     *
     * @param runner              The animation runner to run from
     * @param highScoresAnimation The animation to run
     * @param ks                  The keyboardSensor
     */
    public ShowHiScoresTask(AnimationRunner runner, KeyboardSensor ks, Animation highScoresAnimation) {
        this.runner = runner;
        this.kpsa = new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, highScoresAnimation);
    }

    @Override
    public Void run() {
        this.runner.run(this.kpsa);
        this.kpsa.resetStop();
        return null;
    }
}
