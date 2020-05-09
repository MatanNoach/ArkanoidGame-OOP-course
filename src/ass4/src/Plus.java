//ID:316441534

import java.util.Map;


public class Plus extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param e1 The first expression
     * @param e2 The second expression
     */
    public Plus(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getE1().evaluate(assignment) + getE2().evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        return 0;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return null;
    }
}
