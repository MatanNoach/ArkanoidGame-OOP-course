//ID:316441534

/**
 * The class represents a basic mathematical expression.
 */
public abstract class BaseExpression implements Expression {
    private String sign;

    /**
     * Constructor.
     *
     * @param sign The sign of the expression
     */
    public BaseExpression(String sign) {
        this.sign = sign;
    }

    /**
     * @return The sign
     */
    public String getSign() {
        return sign;
    }

    @Override
    public double evaluate() throws Exception {
        throw new Exception("This expression is not a number");
    }

}