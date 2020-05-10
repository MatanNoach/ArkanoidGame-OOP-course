//ID:316441534

import java.util.List;

/**
 * The class represents a mathematical expression with one variable.
 * Variables:
 * e - The expression
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression e;

    /**
     * Constructor.
     *
     * @param e    The expression
     * @param sign The expression's sign
     */
    public UnaryExpression(Expression e, String sign) {
        super(sign);
        this.e = e;
    }

    @Override
    public String toString() {
        return  getSign() + "(" + getE().toString() + ")";
    }

    /**
     * @return The expression
     */
    public Expression getE() {
        return e;
    }

    @Override
    public List<String> getVariables() {
        return e.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return getE().assign(var, expression);
    }
}
