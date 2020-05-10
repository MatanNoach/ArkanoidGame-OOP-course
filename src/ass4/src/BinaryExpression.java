//ID:316441534

import java.util.List;

/**
 * The class represents a mathematical expression with 2 variables.
 * Variables:
 * e1 - The first expression
 * e2 - The second expression
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression e1;
    private Expression e2;

    /**
     * Constructor.
     *
     * @param e1   The first expression
     * @param e2   The second expression
     * @param sign The expression's sign
     */
    public BinaryExpression(Expression e1, Expression e2, String sign) {
        super(sign);
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public List<String> getVariables() {
        List<String> l1 = e1.getVariables();
        List<String> l2 = e2.getVariables();
        //if both of them are not null, then combine them.
        if (l1 != null && l2 != null) {
            l1.addAll(l2);
            return l1;
        } else if (l1 != null) { //if not, then return null, or the one which in not null.
            return l1;
        }
        return l2;
    }

    @Override
    public String toString() {
        return  "(" + getE1().toString() + " " + getSign() + " " + getE2().toString() + ")";
    }

    /**
     * @return The first expression
     */
    public Expression getE1() {
        return e1;
    }

    /**
     * @return The first expression
     */
    public Expression getE2() {
        return e2;
    }
}
