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
        return  getSign() + "(" + getE1().toString() + ", " + getE2().toString() + ")";
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.log(getE2().evaluate(assignment)) / Math.log(getE1().evaluate(assignment));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(getE1().assign(var, expression), getE2().assign(var, expression));
    }
}
