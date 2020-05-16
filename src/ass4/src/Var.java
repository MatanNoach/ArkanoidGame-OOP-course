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
        double value;
        //if there is a variable in the expression, return it
        try {
            value = assignment.get(var);
            return value;
        } catch (Exception e) { // else, throw an exception
            throw new Exception("The variable does not exists in the map!");
        }
    }

    @Override
    public double evaluate() throws Exception {
        throw new Exception("Can't evaluate a variable");
    }

    @Override
    public List<String> getVariables() {
        List<String> l = new LinkedList<>();
        l.add(var);
        return l;
    }

    @Override
    public Expression assign(String variable, Expression expression) {
        if (variable.equals(this.var)) {
            return expression;
        }
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        Var v = (Var) obj;
        return this.var.equals(v.getVar());
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public Expression differentiate(String variable) {
        if (variable.equals(var)) {
            return new Num(1);
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
