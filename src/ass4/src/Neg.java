//ID:316441534

/**
 * represents a Negate function in a mathematical expression.
 */
public class Neg extends UnaryExpression {
    static final String NEG_SIGN = "-";

    /**
     * Constructor.
     *
     * @param e The expression
     */
    public Neg(Expression e) {
        super(e, NEG_SIGN);
    }

    @Override
    public double evaluate() throws Exception {
        return getE().evaluate() * -1;
    }

    @Override
    public String toString() {
        return "(" + NEG_SIGN + getE().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(getE().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(getE().differentiate(var));
    }

    @Override
    public Expression simplify() {
        Expression e = getE().simplify();
        double result;
        //check if the expression is number
        try {
            result = e.evaluate();
            //if the number is negative, return its evaluated result
            if (result < 0) {
                return new Num(this.evaluate());
            }
        } catch (Exception ignored) {

        }
        //return a new neg of the simplified expression
        return new Neg(e);
    }
}
