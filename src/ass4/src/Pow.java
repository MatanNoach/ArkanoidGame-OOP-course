//ID:316441534

/**
 * The class represents a power function in a mathematical expression.
 */
public class Pow extends BinaryExpression {
    static final String POW_SIGN = "^";

    /**
     * Constructor.
     *
     * @param e1 The first expression
     * @param e2 The second expression
     */
    public Pow(Expression e1, Expression e2) {
        super(e1, e2, POW_SIGN);
    }

    @Override
    public double evaluate() throws Exception {
        return Math.pow(getE1().evaluate(), getE2().evaluate());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(getE1().assign(var, expression), getE2().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        Expression e1 = getE1();
        Expression e2 = getE2();
        Var e = new Var("e");
        Log l = new Log(e, e1);
        Mult m1 = new Mult(e2.differentiate(var), l);
        Div div = new Div(e2, e1);
        Mult m2 = new Mult(e1.differentiate(var), div);
        Plus p = new Plus(m2, m1);
        return new Mult(this, p);
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
        } catch (Exception e) {
            isNum1 = false;
        }
        //check if the second expression is a number
        try {
            r2 = e2.evaluate();
            //if the power is 0, return 1
            if (r2 == 0) {
                return new Num(1);
            }
            //if the power is 1, return he first expression
            if (r2 == 1) {
                return e1;
            }
            //if both are numbers, return their result
            if (isNum1) {
                return new Num(Math.pow(r1, r2));
            }
        } catch (Exception ignored) {

        }
        //return a new pow with the simplified expressions
        return new Pow(e1, e2);
    }
}
