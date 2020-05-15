//ID:316441534

import java.util.Map;
import java.util.List;

/**
 * The interface represents a mathematical expression.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     *
     * @param assignment A map contains the variable values.
     * @return The result of the evaluation
     * @throws Exception Thrown when the expression contains a variable which is not in the assignment.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A method that evaluates without parameters for classes that do not need them.
     *
     * @return The result of the evaluation
     * @throws Exception Thrown when the expression contains a variable which is not in the assignment.
     */
    double evaluate() throws Exception;

    /**
     * @return A list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * @return A string representation of the expression.
     */
    String toString();

    /**
     * Replace all of the var occurrences in the expression, with a different expression without modifying it.
     *
     * @param var        The var to replace
     * @param expression The expression to replace the var with
     * @return The new expression formed
     */
    Expression assign(String var, Expression expression);

    /**
     * Differentiate a mathematical expression by a certain var.
     *
     * @param var The var to differentiate by
     * @return The new equation.
     */
    Expression differentiate(String var);

    /**
     * Simplify an expression.
     * <p>
     * The function removes unnecessary parts of the expression so it could be more readable.
     * </p>
     * @return The simplified version of the expression
     */
    Expression simplify();
}
