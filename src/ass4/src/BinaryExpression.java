import java.util.List;

//ID:316441534
public abstract class BinaryExpression extends BaseExpression {
    private Expression e1;
    private Expression e2;

    public BinaryExpression(Expression e1, Expression e2) {
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
