//ID:316441534

import java.util.Map;

/**
 * The class represents the multiplication function in an expression.
 */
public class Mult extends BinaryExpression {
    static final String MULT_SIGN = "*";

    /**
     * Constructor.
     *
     * @param e1 The first expression
     * @param e2 The second expression
     */
    public Mult(Expression e1, Expression e2) {
        super(e1, e2, MULT_SIGN);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getE1().evaluate(assignment) * getE2().evaluate(assignment);
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Mult(getE1().assign(var, expression), getE2().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(new Mult(getE1().differentiate(var), getE2()), new Mult(getE2().differentiate(var), getE1()));
    }

    @Override
    public Expression simplify() {
        Expression e1 = getE1().simplify();
        Expression e2 = getE2().simplify();
        double r1 = 0, r2;
        boolean isNum1 = true;
        //Checks if e1 is 0 or 1
        try {
            r1 = e1.evaluate();
            //if one of the expressions is zero, return zero
            if (r1 == 0) {
                return new Num(0);
            }
            //if one of the expression is one, return the other
            if (r1 == 1) {
                return e2;
            }
        } catch (Exception e) {
            isNum1 = false;
        }
        //Checks if e2 is 0 or 1
        try {
            r2 = e2.evaluate();
            if (r2 == 0) {
                return new Num(0);
            }
            if (r2 == 1) {
                return e1;
            }
            //if both are numbers, return their mult result.
            if (isNum1) {
                return new Num(r1 * r2);
            }
        } catch (Exception e) {
        }
        return new Mult(e1, e2);
    }
}
