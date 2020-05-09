//ID:316441534

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * The class represents a variable in a mathematical expression.
 * Variables:
 * var - The variable sign
 */
public class Var implements Expression {
    private String var;

    /**
     * Constructor.
     *
     * @param var The variable sign
     */
    public Var(String var) {
        this.var = var;
    }

    /**
     * @return the var sign
     */
    public String getVar() {
        return var;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        //if there is a variable in the expression, return it
        try {
            return assignment.get(var);
        } catch (NullPointerException e) { // else, throw an exception
            System.out.println("The variable does not exist in the expression!");
            throw e;
        }
    }

    @Override
    public double evaluate() throws Exception {
        return 0;
    }

    @Override
    public List<String> getVariables() {
        List<String> l = new LinkedList<>();
        l.add(var);
        return l;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
