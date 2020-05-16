//ID:316441534

import java.util.Map;
import java.util.TreeMap;

/**
 * The class run tests on Expressions.
 */
public class ExpressionTest {
    /**
     * This is function runs 5 tests on Expressions.
     *
     * @param args User arguments.
     * @throws Exception thrown if one of the function throws an exception
     */
    public static void main(String[] args) throws Exception {
        Var x = new Var("x");
        Var y = new Var("y");
        Var e = new Var("e");
        Expression exp = new Plus(new Mult(new Num(2), x), new Plus(new Sin(new Mult(new Num(4), y)), new Pow(e, x)));
        Map<String, Double> map = new TreeMap<>();
        //Task 1: print the expression
        System.out.println(exp);
        //Task 2: print the expression value
        map.put("x", 2d);
        map.put("y", 0.25);
        map.put("e", 2.71);
        System.out.println(exp.evaluate(map));
        //Task 3: print the differentiated expression
        System.out.println(exp.differentiate("x"));
        //Task 4: print the differentiated expression's value
        System.out.println(exp.differentiate("x").evaluate(map));
        //Task 5: print the simplified differentiated version
        System.out.println(exp.differentiate("x").simplify());
    }
}
