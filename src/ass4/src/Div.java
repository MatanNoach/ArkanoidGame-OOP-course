//ID:316441534

/**
 * The class represent the division function in an expression.
 */
public class Div extends BinaryExpression {
    static final String DIV_SIGN = "/";

    /**
     * Constructor.
     *
     * @param e1 The first expression
     * @param e2 The second expression
     */
    public Div(Expression e1, Expression e2) {
        super(e1, e2, DIV_SIGN);
    }

    @Override
    public double evaluate() throws Exception {
        double e1Value = getE1().evaluate();
        double e2Value = getE2().evaluate();
        if (e2Value == 0) {
            throw new Exception("Invalid denominator");
        }
        return e1Value / e2Value;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(getE1().assign(var, expression), getE2().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        Expression e1 = getE1();
        Expression e2 = getE2();
        Expression numerator = new Minus(new Mult(e1.differentiate(var), e2), new Mult(e1, e2.differentiate(var)));
        Expression denominator = new Pow(e2, new Num(2));
        return new Div(numerator, denominator);
    }

    @Override
    public Expression simplify() {
        Expression e1 = getE1().simplify();
        Expression e2 = getE2().simplify();
        double r1 = 0, r2;
        boolean isNum1 = true;
        //checks if the first expression is a number
        try {
            r1 = e1.evaluate();
            if (r1 == 0) {
                return new Num(0);
            }
        } catch (Exception e) {
            isNum1 = false;
        }
        //checks if the second expression is a number
        try {
            r2 = e2.evaluate();
            //it its value is 1, return the nominator
            if (r2 == 1) {
                return e1;
            }
            //if they are both numbers, return their evaluated value
            if (isNum1) {
                return new Num(this.evaluate());
            }
        } catch (Exception e) {
            //if both expressions are equal, return 1
            if (e1.toString().equals(e2.toString())) {
                return new Num(1);
            }
        }
        //return a new Div expression with the simplified expressions.
        return new Div(e1, e2);
    }
}
