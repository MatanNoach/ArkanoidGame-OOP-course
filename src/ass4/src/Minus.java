//ID:316441534

/**
 * The class represents the minus function in an expression.
 */
public class Minus extends BinaryExpression {
    static final String MINUS_SIGN = "-";

    /**
     * Constructor.
     *
     * @param e1 The first expression
     * @param e2 The second expression
     */
    public Minus(Expression e1, Expression e2) {
        super(e1, e2, MINUS_SIGN);
    }

    @Override
    public double evaluate() throws Exception {
        return getE1().evaluate() - getE2().evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(getE1().assign(var, expression), getE2().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Minus(getE1().differentiate(var), getE2().differentiate(var));
    }

    @Override
    public Expression simplify() {
        Expression e1 = getE1().simplify();
        Expression e2 = getE2().simplify();
        double r1 = 0, r2;
        boolean isNum1 = true;
        //check if the first expression is a number
        try {
            r1 = e1.evaluate();
            //if its zero, return the neg of the second expression
            if (r1 == 0) {
                return new Neg(e2);
            }
        } catch (Exception e) {
            isNum1 = false;
        }
        //check if the second expression is number
        try {
            r2 = e2.evaluate();
            //if its zero, return the first expression
            if (r2 == 0) {
                return e1;
            }
            //if both are numbers and equal return their minus result
            if (isNum1) {
                return new Num(r1 - r2);
            }
        } catch (Exception e) {
            //if they are both equal, return 0
            if (e1.toString().equals(e2.toString())) {
                return new Num(0);
            }
        }
        //return a new minus of the simplified expressions
        return new Minus(e1, e2);
    }
}
