//ID:316441534

import java.util.Map;

/**
 * represents a Negate function in a mathematical expression.
 */
public class Neg extends UnaryExpression {
    static final String NEG_SIGN = "Neg";

    /**
     * Constructor.
     *
     * @param e The expression
     */
    public Neg(Expression e) {
        super(e, NEG_SIGN);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getE().evaluate(assignment) * -1;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(getE().assign(var, expression));
    }
}
