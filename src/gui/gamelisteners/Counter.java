//ID:316441534
package gui.gamelisteners;

/**
 * A counter to a general object.
 * variables:
 * num - the number of the current object
 */
public class Counter {
    private int num;

    /**
     * A default constructor that set the number to 0.
     */
    public Counter() {
        this.num = 0;
    }

    /**
     * Adds a number to the current count.
     * @param number The amount to add
     */
    public void increase(int number) {
        this.num += number;
    }

    /**
     * Subtract a number from the current count.
     * @param number The number to subtract
     */
    public void decrease(int number) {
        this.num -= number;
    }

    /**
     * @return The current count
     */
    public int getValue() {
        return this.num;
    }
}
