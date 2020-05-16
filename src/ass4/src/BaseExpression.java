//ID:316441534

import java.util.Map;

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

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        Expression eAssigned = this;
        for (Map.Entry<String, Double> entry : assignment.entrySet()) {
            eAssigned = eAssigned.assign(entry.getKey(), new Num(entry.getValue()));
        }
        return eAssigned.evaluate();
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