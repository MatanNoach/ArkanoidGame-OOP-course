//ID:316441534
import java.util.List;
import java.util.Map;

/**
 * The class represents a num in a mathematical expression.
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
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
