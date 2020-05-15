//ID:316441534

import java.util.Map;

/**
 * The class represents a log function in an expression.
 */
public class Log extends BinaryExpression {
    static final String LOG_SIGN = "Log";

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
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double e1Value = getE1().evaluate(assignment);
        double e2Value = getE2().evaluate(assignment);
        try {
            if (e1Value == 0) {
                throw new Exception("Invalid Log exponent");
            }
            if (e2Value == 0 || e2Value == 1) {
                throw new Exception("Invalid Log base");
            }
        } catch (Exception e) {
            System.out.println(e);
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
            //if the first expression is 1, return 0
            if (r1 == 1) {
                return new Num(0);
            }
        } catch (Exception e) {
            isNum1 = false;
        }
        try {
            r2 = e2.evaluate();
            //if there are both equal numbers, return 1
            if (isNum1 && r1 == r2) {
                return new Num(1);
            }
        } catch (Exception e) {
            //if they are both equal and not numbers, return 1
            if (e1.equals(e2)) {
                return new Num(1);
            }
        }
        return new Log(e1, e2);
    }
}
