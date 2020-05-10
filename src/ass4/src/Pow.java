//ID:316441534

import java.util.Map;

/**
 * The class represents a power function in a mathematical expression.
 */
public class Pow extends BinaryExpression {
    static final String POW_SIGN = "^";

    /**
     * Constructor.
     *
     * @param e1 The first expression
     * @param e2 The second expression
     */
    public Pow(Expression e1, Expression e2) {
        super(e1, e2, POW_SIGN);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.pow(getE1().evaluate(assignment), getE2().evaluate(assignment));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(getE1().assign(var, expression), getE2().assign(var, expression));
    }
}
