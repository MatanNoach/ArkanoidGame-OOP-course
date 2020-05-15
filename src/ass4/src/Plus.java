//ID:316441534

import java.util.Map;

/**
 * The class represents the plus function in an expression.
 */
public class Plus extends BinaryExpression {
    static final String PLUS_SIGN = "+";

    /**
     * Constructor.
     *
     * @param e1 The first expression
     * @param e2 The second expression
     */
    public Plus(Expression e1, Expression e2) {
        super(e1, e2, PLUS_SIGN);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getE1().evaluate(assignment) + getE2().evaluate(assignment);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(getE1().assign(var, expression), getE2().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(getE1().differentiate(var), getE2().differentiate(var));
    }

    @Override
    public Expression simplify() {
        Expression e1 = getE1().simplify();
        Expression e2 = getE2().simplify();
        boolean isNum1 = true;
        double r1 = 0, r2;
        //Check if the first expression is Num
        try {
            r1 = e1.evaluate();
            //if one of the numbers is zero, return the other one
            if (r1 == 0) {
                return e2;
            }
        } catch (Exception e) { //mark it as not a number
            isNum1 = false;
        }
        //Check if the second expression is Num
        try {
            r2 = e2.evaluate();
            if (r2 == 0) {
                return e1;
            }
            //if both expression are numbers, return their plus result
            if (isNum1) {
                return new Num(r1 + r2);
            }
        } catch (Exception e) {
        }
        return new Plus(e1, e2);
    }
}
