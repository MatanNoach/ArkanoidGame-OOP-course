//ID:316441534

import java.util.Map;

/**
 * The class represents a sinus function in an expression.
 */
public class Sin extends UnaryExpression {
    static final String SIN_SIGN = "sin";

    /**
     * Constructor.
     *
     * @param e The expression
     */
    public Sin(Expression e) {
        super(e, SIN_SIGN);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin(Math.toRadians(getE().evaluate(assignment)));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(getE().assign(var, expression));
    }
}
