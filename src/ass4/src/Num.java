//ID:316441534

import java.util.List;
import java.util.Map;

/**
 * The class represents a number in a mathematical expression.
 * Variables:
 * num - The number
 */
public class Num implements Expression {
    private double num;

    /**
     * Constructor.
     *
     * @param num The number
     */
    public Num(double num) {
        this.num = num;
    }

    /**
     * @return The number
     */
    public double getNum() {
        return num;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        return num;
    }

    @Override
    public List<String> getVariables() {
        return null;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Num(num);
    }

    /**
     * The function checks if two doubles are equal by subtracting them and comparing them to epsilon.
     * <p>
     * if the result is smaller than epsilon, the difference between both numbers
     * is so small that they are practically the same.
     * </p>
     *
     * @param number The number to compare
     * @return True if they are considered equals and false otherwise
     */
    public boolean equals(double number) {
        double epsilon = 0.0000000001;
        double result = Math.abs(number - num);
        return epsilon >= result;
    }

    @Override
    public String toString() {
        return "" + num;
    }

}
