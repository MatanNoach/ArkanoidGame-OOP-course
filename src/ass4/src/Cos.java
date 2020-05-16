//ID:316441534

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
    public double evaluate() throws Exception {
        return Math.cos(Math.toRadians(getE().evaluate()));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(getE().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(new Mult(getE().differentiate(var), new Sin(getE())));
    }

    @Override
    public Expression simplify() {
        Expression e = getE().simplify();
        double result;
        //checks if the expression is a number
        try {
            result = e.evaluate();
            //if the number is divided by 360, return 1
            if (result % 360 == 0) {
                return new Num(1);
            }
            //if the number is divided by 180, return -1
            if (result % 180 == 0) {
                return new Num(-1);
            }
            //if the number is divided by 90 return 0
            if (result % 90 == 0) {
                return new Num(0);
            }
        } catch (Exception ignored) {

        }
        //if its not a number, return new Cos of the simplified expression
        return new Cos(e);
    }
}
