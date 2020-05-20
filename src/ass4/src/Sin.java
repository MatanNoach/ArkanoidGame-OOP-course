//ID:316441534

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
    public double evaluate() throws Exception {
        double result = getE().evaluate();
        //if the number is divided by 180, return 0
        if (result % 180 == 0) {
            return 0;
        }
        //if the number is divided by 270, return -1
        if (result % 270 == 0) {
            return -1;
        }
        //if the number us divided by 90, return 1
        if (result % 90 == 0) {
            if (result > 0) {
                return 1;
            } else {
                return -1;
            }
        }
        return Math.sin(Math.toRadians(result));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(getE().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(getE().differentiate(var), new Cos(getE()));
    }

    @Override
    public Expression simplify() {
        Expression e = getE().simplify();
        double result;
        //check if the expression is a num
        try {
            result = this.evaluate();
            return new Num(result);
        } catch (Exception ignored) {

        }
        //return a new sin with the simplified expressions
        return new Sin(e);
    }
}
