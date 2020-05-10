//ID:316441534

import java.util.Map;

/**
 * The class represents a cosinus function in a mathematical expression.
 */
public class Cos extends UnaryExpression {
    static final String COS_SIGN = "cos";

    /**
     * Constructor.
     *
     * @param e The expression
     */
    public Cos(Expression e) {
        super(e, COS_SIGN);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(getE().evaluate(assignment)));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(getE().assign(var, expression));
    }
}
