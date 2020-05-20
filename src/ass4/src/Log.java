//ID:316441534

/**
 * The class represents a log function in an expression.
 */
public class Log extends BinaryExpression {
    static final String LOG_SIGN = "log";

    /**
     * Constructor.
     *
     * @param e1 The first expression
     * @param e2 The second expression
     */
    public Log(Expression e1, Expression e2) {
        super(e1, e2, LOG_SIGN);
    }

    @Override
    public String toString() {
        return getSign() + "(" + getE1().toString() + ", " + getE2().toString() + ")";
    }

    @Override
    public double evaluate() throws Exception {
        double e1Value = getE1().evaluate();
        double e2Value = getE2().evaluate();
        if (e1Value <= 0 || e1Value == 1) {
            throw new Exception("Invalid Log base");
        }
        if (e2Value <= 0) {
            throw new Exception("Invalid Log exponent");
        }
        return Math.log(e2Value) / Math.log(e1Value);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(getE1().assign(var, expression), getE2().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        Expression fx = getE2();
        Expression gx = getE1();
        Expression e = new Var("e");
        Expression leftNumerator = new Div(new Mult(new Log(fx, e), gx.differentiate(var)), gx);
        Expression rightNumerator = new Div(new Mult(fx.differentiate(var), new Log(gx, e)), fx);
        Expression denominator = new Pow(new Log(fx, e), new Num(2));
        return new Div(new Minus(leftNumerator, rightNumerator), denominator);
    }

    @Override
    public Expression simplify() {
        Expression e1 = getE1().simplify();
        Expression e2 = getE2().simplify();
        double r1 = 0, r2;
        boolean isNum1 = true;
        //Checks if the first expression is a number
        try {
            r1 = e1.evaluate();
            //if its 1, return 0
            if (r1 == 1) {
                return new Num(0);
            }
        } catch (Exception e) {
            isNum1 = false;
        }
        try {
            r2 = e2.evaluate();
            //if they are both numbers
            if (isNum1) {
                //if they are equal, return 1
                if (r1 == r2) {
                    return new Num(1);
                }
                //else, return their evaluated result
                return new Num(this.evaluate());
            }
        } catch (Exception e) {
            //if they are both equal, return 1
            if (e1.toString().equals(e2.toString())) {
                return new Num(1);
            }
        }
        //return a new log of the simplified expressions
        return new Log(e1, e2);
    }
}
