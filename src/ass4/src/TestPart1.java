//ID:316441534

import java.util.Map;
import java.util.TreeMap;


public class TestPart1 {
    public static void main(String[] args) throws Exception {
        Var x = new Var("x");
        Map<String, Double> map = new TreeMap<>();
        map.put("x", 5.0);
        map.put("y", 3.0);
        map.put("e", Math.E);
        Num zero = new Num(0);
        Num one = new Num(1);
        Expression testPlus = new Plus(zero, new Plus(x, new Plus(zero, x)));
        System.out.println(testPlus.simplify());
        Expression testMinus = new Minus(zero, new Minus(x, new Minus(x, zero)));
        System.out.println(testMinus.simplify());
        Expression testMult = new Mult(one, new Plus(x, new Mult(x, zero)));
        System.out.println(testMult.simplify());
        Expression testDiv = new Div(one, new Plus(x, new Div(x, x)));
        System.out.println(testDiv.simplify());
        Expression testLog = new Log(x, x);
        System.out.println(testLog.simplify());
        Expression testNumbers = new Div(new Minus(new Plus(new Mult(new Num(2), new Num(8)), new Num(6)), new Num(2)), new Num(5));
        System.out.println(testNumbers.simplify());
        Expression tesEdgeCasesLog = new Log(zero, one);
        System.out.println(tesEdgeCasesLog.evaluate(map));
        Expression testPow = new Pow(x, zero);
        System.out.println(testPow.simplify());
        Expression testNeg = new Neg(new Neg(one));
        System.out.println(testNeg.simplify());
        Expression testSin = new Sin(new Num(0));
        System.out.println(testSin.simplify());
        Expression testCos = new Cos(new Num(0));
        System.out.println(testCos.simplify());
    }
}
