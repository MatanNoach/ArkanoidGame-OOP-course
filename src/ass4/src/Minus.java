//ID:316441534

import java.util.Map;

/**
 * The class represents the minus function in an expression.
 */
public class Minus extends BinaryExpression {
    static final String MINUS_SIGN = "-";

    /**
     * Constructor.
     *
     * @param e1 The first expression
     * @param e2 The second expression
     */
    public Minus(Expression e1, Expression e2) {
        super(e1, e2, MINUS_SIGN);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getE1().evaluate(assignment) - getE2().evaluate(assignment);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(getE1().assign(var, expression), getE2().assign(var, expression));
    }
}
